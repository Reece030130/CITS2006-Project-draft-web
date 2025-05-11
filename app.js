// app.js
require('dotenv').config();
const express   = require('express');
const helmet    = require('helmet');
const session   = require('express-session');
const morgan    = require('morgan');
const { createLogger, transports, format } = require('winston');
const rateLimit = require('express-rate-limit');
const slowDown  = require('express-slow-down');
const axios     = require('axios');
const path      = require('path');
const fs        = require('fs');
const https     = require('https');
const mysql     = require('mysql2/promise');
const { body, validationResult } = require('express-validator');


const app = express();


const logger = createLogger({
  level: 'info',
  format: format.combine(
    format.timestamp(),
    format.json()
  ),
  transports: [ new transports.Console() ]
});
app.use(morgan('combined', { stream: { write: s => logger.info(s.trim()) } }));

// ─── BASIC FIREWALL MIDDLEWARE ──────────────────────────────────────────
// Reads environment variables in .env, e.g.
//   WHITELIST_IPS=127.0.0.1,::1
//   BLACKLIST_IPS=203.0.113.5,198.51.100.0/24
const { whitelist, blacklist } = (() => {
  const wl = process.env.WHITELIST_IPS
    ? process.env.WHITELIST_IPS.split(',').map(s => s.trim())
    : [];
  const bl = process.env.BLACKLIST_IPS
    ? process.env.BLACKLIST_IPS.split(',').map(s => s.trim())
    : [];
  return { whitelist: wl, blacklist: bl };
})();

// Optional: if you need CIDR matching, install ip-range-check:
//    npm install ip-range-check
let ipRangeCheck;
try {
  ipRangeCheck = require('ip-range-check');
} catch (_) { /* no CIDR support */ }

app.use((req, res, next) => {
  const clientIP = req.ip;

  // 1) BLOCKLIST takes priority
  if (blacklist.length) {
    const isBlocked = blacklist.some(block => {
      if (ipRangeCheck) {
        return ipRangeCheck(clientIP, block);
      }
      return block === clientIP;
    });
    if (isBlocked) {
      return res.status(403).send('Forbidden: Your IP is blocked.');
    }
  }

  // 2) If a WHITELIST is defined, only allow those
  if (whitelist.length) {
    const isAllowed = whitelist.some(allow => {
      if (ipRangeCheck) {
        return ipRangeCheck(clientIP, allow);
      }
      return allow === clientIP;
    });
    if (!isAllowed) {
      return res.status(403).send('Forbidden: Your IP is not on the whitelist.');
    }
  }

  // Otherwise, pass through
  next();
});
// sets HSTS, X-Frame-Options, X-Content-Type-Options, Referrer-Policy, etc.
// Hide X-Powered-By
app.use(helmet.hidePoweredBy());

// Prevent clickjacking
app.use(helmet.frameguard({ action: 'deny' }));

// Prevent MIME-type sniffing
app.use(helmet.noSniff());

// Enable HSTS (must be HTTPS-protected)
app.use(helmet.hsts({
  maxAge: 60 * 60 * 24 * 365,    // 1 year
  includeSubDomains: true,
  preload: true
}));

// Basic Referrer-Policy
app.use(helmet.referrerPolicy({ policy: 'no-referrer' }));

// Disable DNS prefetching
app.use(helmet.dnsPrefetchControl({ allow: false }));

// XSS filter (legacy IE support)
app.use(helmet.xssFilter());

// You can still add CSP later if needed:
// app.use(helmet.contentSecurityPolicy({ directives: { defaultSrc: ["'self'"], /* ... */ } }));
// trust proxy so X-Forwarded-For works
app.set('trust proxy', true);
if (process.env.NODE_ENV === 'production') {
  app.use((req, res, next) => {
    // req.secure is true when the request was via HTTPS
    // X-Forwarded-Proto header check in case you're behind certain proxies
    if (req.secure || req.headers['x-forwarded-proto'] === 'https') {
      return next();
    }
    // otherwise redirect to same URL but with https
    return res.redirect(301, `https://${req.headers.host}${req.originalUrl}`);
  });
}
app.use(session({
  name: 'sid',                                  // your cookie name
  secret: process.env.SESSION_SECRET,           // use a strong random value in .env
  resave: false,                                // don’t save unchanged sessions
  saveUninitialized: false,                     // only save sessions when set
  cookie: {
    httpOnly: true,                             // JS on the client can’t read the cookie
    secure: process.env.NODE_ENV === 'production', // only send over HTTPS in prod
    sameSite: 'lax',                            // CSRF mitigation—ok for most form posts
    maxAge: 24 * 60 * 60 * 1000                 // e.g. 1 day in milliseconds
  }
}));
// ─── ENV FLAG ─────────────────────────────────────────────────────────────
const isTestEnv = process.env.NODE_ENV === 'test';

// ─── MIDDLEWARE ───────────────────────────────────────────────────────────
// parse form bodies
app.use(express.urlencoded({ extended: false }));
// serve static assets
app.use(express.static(path.join(__dirname, 'public')));

// ─── VIEW ENGINE ──────────────────────────────────────────────────────────
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// ─── DATABASE POOL ────────────────────────────────────────────────────────
const pool = mysql.createPool({
  host:     process.env.DB_HOST,
  user:     process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit:    10,
  queueLimit:         0
});

// ─── RATE-LIMIT + SLOW-DOWN ───────────────────────────────────────────────
const speedLimiter = slowDown({
  windowMs: 60*1000,   // 1 minute
  delayAfter: 5,       // allow 5 requests per IP
  delayMs: (used, req) => {
    const delayAfter = req.slowDown.limit;
    return (used - delayAfter) * 500;
},      // then add 500ms per request
});

const bookingLimiter = rateLimit({
  windowMs: 10*60*1000,  // 10 minutes
  max: 10,               // limit each IP to 10 requests
  message: 'Too many booking attempts from this IP, please try again later.',
  standardHeaders: true,
  legacyHeaders: false
});

// ─── ROUTES ────────────────────────────────────────────────────────────────

// health check
app.get('/ping', (req, res) => res.send('pong'));

// serve booking page
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'main.html'));
});

// handle booking submissions
app.post(
  '/book',
  speedLimiter,
  bookingLimiter,
  [
    // validation & sanitization
    body('name').trim().isLength({ min:2, max:100 }).withMessage('Name must be between 2 and 100 characters').escape(),
    body('email').trim().isEmail().withMessage('Please provide a valid email').normalizeEmail(),
    body('company').optional({ checkFalsy:true }).trim().isLength({ max:200 }).withMessage('Company too long').escape(),
    body('phone').trim().matches(/^[0-9+\-\s]{7,20}$/).withMessage('Phone must be 7–20 digits/spaces/“+”'),
    body('ServiceRepair').isIn(['service','repair']).withMessage('Please select Service or Repair'),
    body('message').optional({ checkFalsy:true }).trim().isLength({ max:500 }).withMessage('Message up to 500 chars').escape(),
    body('plate').trim().isLength({ min:1, max:20 }).withMessage('Plate required, under 20 chars').escape(),
    body('make').trim().isLength({ min:1, max:100 }).withMessage('Make required, under 100 chars').escape(),
    body('model').trim().isLength({ min:1, max:100 }).withMessage('Model required, under 100 chars').escape(),
    body('year').trim().isInt({ min:1900, max:new Date().getFullYear() }).withMessage(`Year between 1900 and ${new Date().getFullYear()}`),
    body('odometer').trim().isInt({ min:0, max:1_000_000 }).withMessage('Odometer must be 0–1,000,000')
  ],
  async (req, res, next) => {
    try {
      // reCAPTCHA (skip in test env)
      if (!isTestEnv) {
        const token = req.body['g-recaptcha-response'];
        if (!token) {
          return res.status(400).render('main', {
            errors: [], old: req.body,
            captchaError: 'Please complete the CAPTCHA.'
          });
        }
        // if token === 'test' you could skip here, but test env covers it
        const secret = process.env.RECAPTCHA_SECRET;
        const verifyUrl = 'https://www.google.com/recaptcha/api/siteverify';
        const { data: captchaRes } = await axios.post(verifyUrl, null, {
          params: { secret, response: token, remoteip: req.ip }
        });
        if (!captchaRes.success) {
          return res.status(400).render('main', {
            errors: [], old: req.body,
            captchaError: 'CAPTCHA verification failed. Please try again.'
          });
        }
      }

      // handle validation errors
      const errors = validationResult(req);
      if (!errors.isEmpty()) {
        return res.status(400).render('main', {
          errors: errors.array(), old: req.body
        });
      }

      // destructure sanitized inputs
      const {
        name, email, company = null, phone,
        ServiceRepair, message = null,
        plate, make, model, year, odometer
      } = req.body;

      // insert into DB
      const sql = `
        INSERT INTO bookings
          (name,email,company,phone,ServiceRepair,message,plate,make,model,year,odometer)
        VALUES (?,?,?,?,?,?,?,?,?,?,?)
      `;
      await pool.execute(sql, [
        name, email, company, phone,
        ServiceRepair, message,
        plate, make, model, year, odometer
      ]);

      // render confirmation
      return res.render('confirm', {
        name, email, company,
        phone, ServiceRepair, message,
        plate, make, model, year, odometer
      });
    } catch (err) {
      next(err);
    }
  }
);

// global error handler
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).send('Sorry, something went wrong on our end.');
});

// start server
const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
