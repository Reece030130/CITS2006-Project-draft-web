// app.js
require('dotenv').config();
const express = require('express');
const mysql   = require('mysql2/promise');
const path    = require('path');
const {body, validationResult} = require('express-validator');

const app = express();
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// create a MySQL connection pool
const pool = mysql.createPool({
  host:     process.env.DB_HOST,
  user:     process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit:    10,
  queueLimit:         0
});

// serve your booking page
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'main.html'));
});



app.post(
  '/book',
  // Validation & sanitization chain:
  [
    body('name')
      .trim()
      .isLength({ min: 2, max: 100 })
      .withMessage('Name must be between 2 and 100 characters')
      .escape(),

    body('email')
      .trim()
      .isEmail()
      .withMessage('Please provide a valid email')
      .normalizeEmail(),

    body('company')
      .optional({ checkFalsy: true })  // allow empty
      .trim()
      .isLength({ max: 200 })
      .withMessage('Company name is too long')
      .escape(),

    body('phone')
      .trim()
      .matches(/^[0-9+\-\s]{7,20}$/)
      .withMessage('Phone must be 7–20 digits, spaces, or “+”'),

    body('ServiceRepair')
      .isIn(['service','repair'])
      .withMessage('ServiceRepair must be “service” or “repair”'),

    body('message')
      .optional({ checkFalsy: true })
      .trim()
      .isLength({ max: 500 })
      .withMessage('Message can be up to 500 characters')
      .escape(),

    body('plate')
      .trim()
      .isLength({ min: 1, max: 20 })
      .withMessage('Plate is required and under 20 chars')
      .escape(),

    body('make')
      .trim()
      .isLength({ min: 1, max: 100 })
      .withMessage('Make is required and under 100 chars')
      .escape(),

    body('model')
      .trim()
      .isLength({ min: 1, max: 100 })
      .withMessage('Model is required and under 100 chars')
      .escape(),

    body('year')
      .trim()
      .isInt({ min: 1900, max: new Date().getFullYear() })
      .withMessage('Year must be a valid number'),

    body('odometer')
      .trim()
      .isInt({ min: 0, max: 1_000_000 })
      .withMessage('Odometer must be a non-negative integer'),
  ],

  //  Your handler:
  async (req, res, next) => {
    //  Check for validation errors:
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      // You can re-render your form with error messages & old inputs
      return res.status(400).render('main', {
        errors: errors.array(),
        old: req.body
      });
    }

    // Safe to destructure & insert:
    const {
      name, email, company = null, phone,
      ServiceRepair, message = null,
      plate, make, model, year, odometer
    } = req.body;

    const sql = `
      INSERT INTO bookings
        (name, email, company, phone, ServiceRepair, message, plate, make, model, year, odometer)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    `;

    try {
      await pool.execute(sql, [
        name, email, company, phone,
        ServiceRepair, message,
        plate, make, model, year, odometer
      ]);
      res.render('confirm', { ...req.body });
    } catch (err) {
      next(err);
    }
  }
);


// simple error handler
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).send('❗️ Sorry, something went wrong.');
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Listening: http://localhost:${port}`);
});
