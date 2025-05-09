// app.js
require('dotenv').config();
const express = require('express');
const path    = require('path');
const mysql   = require('mysql2/promise');
const axios   = require('axios');
const { body, validationResult } = require('express-validator');

const app = express();

// ─── MIDDLEWARE ───────────────────────────────────────────────────────────
// Parse URL-encoded bodies (form submissions)
app.use(express.urlencoded({ extended: false }));
// Serve static assets from /public
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

// ─── ROUTES ────────────────────────────────────────────────────────────────

// Health-check
app.get('/ping', (req, res) => res.send('pong'));

// Serve the booking form
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'main.html'));
});

// Handle booking submissions
app.post(
  '/book',
  // 1) Validation & sanitization chain
  [
    body('name')
      .trim().isLength({ min: 2, max: 100 })
      .withMessage('Name must be between 2 and 100 characters')
      .escape(),

    body('email')
      .trim().isEmail()
      .withMessage('Please provide a valid email')
      .normalizeEmail(),

    body('company')
      .optional({ checkFalsy: true }).trim().isLength({ max: 200 })
      .withMessage('Company name is too long')
      .escape(),

    body('phone')
      .trim().matches(/^[0-9+\-\s]{7,20}$/)
      .withMessage('Phone must be 7–20 digits, spaces, or “+”'),

    body('ServiceRepair')
      .isIn(['service','repair'])
      .withMessage('Please select Service or Repair'),

    body('message')
      .optional({ checkFalsy: true }).trim().isLength({ max: 500 })
      .withMessage('Message can be up to 500 characters')
      .escape(),

    body('plate')
      .trim().isLength({ min: 1, max: 20 })
      .withMessage('Licence plate is required and under 20 chars')
      .escape(),

    body('make')
      .trim().isLength({ min: 1, max: 100 })
      .withMessage('Make is required and under 100 chars')
      .escape(),

    body('model')
      .trim().isLength({ min: 1, max: 100 })
      .withMessage('Model is required and under 100 chars')
      .escape(),

    body('year')
      .trim().isInt({ min: 1900, max: new Date().getFullYear() })
      .withMessage(`Year must be between 1900 and ${new Date().getFullYear()}`),

    body('odometer')
      .trim().isInt({ min: 0, max: 1_000_000 })
      .withMessage('Odometer must be a non-negative integer')
  ],

  // 2) Request handler
  async (req, res, next) => {
    try {
      // ── reCAPTCHA Verification ────────────────────────────
      const token = req.body['g-recaptcha-response'];
      if (!token) {
        return res.status(400).render('main', {
          errors: [],
          old: req.body,
          captchaError: 'Please complete the CAPTCHA.'
        });
      }
      const secret = process.env.RECAPTCHA_SECRET;
      const verifyUrl = 'https://www.google.com/recaptcha/api/siteverify';
      const { data: captchaRes } = await axios.post(verifyUrl, null, {
        params: { secret, response: token, remoteip: req.ip }
      });
      if (!captchaRes.success) {
        return res.status(400).render('main', {
          errors: [],
          old: req.body,
          captchaError: 'CAPTCHA verification failed. Please try again.'
        });
      }

      // ── Validation Errors ─────────────────────────────────
      const errors = validationResult(req);
      if (!errors.isEmpty()) {
        return res.status(400).render('main', {
          errors: errors.array(),
          old: req.body
        });
      }

      // ── Destructure sanitized values ──────────────────────
      const {
        name, email, company = null, phone,
        ServiceRepair, message = null,
        plate, make, model, year, odometer
      } = req.body;

      // ── Insert into database ─────────────────────────────
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

      // ── Render confirmation page ──────────────────────────
      return res.render('confirm', {
        name,
        email,
        company,
        phone,
        ServiceRepair,
        message,
        plate,
        make,
        model,
        year,
        odometer
      });

    } catch (err) {
      next(err);
    }
  }
);

// ─── GLOBAL ERROR HANDLER ──────────────────────────────────────────────
app.use((err, req, res, next) => {
  console.error(err);
  // if you want to show a friendly error page:
  res.status(500).send('Sorry, something went wrong on our end.');
});

// ─── START SERVER ─────────────────────────────────────────────────────
const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
