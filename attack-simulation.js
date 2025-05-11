// attack-simulation.js
const axios = require('axios');

// point at your local server
const client = axios.create({ baseURL: 'http://localhost:3000', timeout: 5000 });

// a minimal valid payload (matches your express-validator rules)
const payload = new URLSearchParams({
  name: 'Attacker',
  email: 'spam@example.com',
  company: '',
  phone: '0412345678',
  ServiceRepair: 'service',
  message: '',
  plate: 'TEST123',
  make: 'VW',
  model: 'Golf',
  year: '2020',
  odometer: '10000',
  // test reCAPTCHA always passes with these keys
  'g-recaptcha-response': 'test'
}).toString();

(async () => {
  console.log('Starting attack simulation...');
  for (let i = 1; i <= 15; i++) {
    const start = Date.now();
    try {
      const res = await client.post('/book', payload, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      });
      console.log(`${i}: ✅ ${res.status} (${Date.now() - start}ms)`);
    } catch (err) {
      if (err.response) {
        console.log(`${i}: ❌ ${err.response.status} – ${err.response.data}`);
      } else {
        console.log(`${i}: 🚨 ${err.message}`);
      }
    }
  }
})();
