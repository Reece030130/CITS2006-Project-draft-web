// app.js
require('dotenv').config();
const express = require('express');
const mysql   = require('mysql2/promise');
const path    = require('path');

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

// handle form submission
app.post('/book', async (req, res, next) => {
  try {
    const {
      name,
      email,
      company = null,
      phone,
      ServiceRepair,
      message = null,
      plate,
      make,
      model,
      year,
      odometer
    } = req.body;

    const sql = `
      INSERT INTO bookings
        (name, email, company, phone, ServiceRepair, message, plate, make, model, year, odometer)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    `;

    await pool.execute(sql, [
      name, email, company, phone,
      ServiceRepair, message,
      plate, make, model, year, odometer
    ]);

    // redirect to a thank-you page (you can create public/thank-you.html)
        // instead render a dynamic confirmation page
    res.render('confirm', {
      name, email, company, phone,
      ServiceRepair, message,
      plate, make, model, year, odometer
    });
  } catch (err) {
    next(err);
  }
});

// simple error handler
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).send('❗️ Sorry, something went wrong.');
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`🚀 Listening: http://localhost:${port}`);
});
