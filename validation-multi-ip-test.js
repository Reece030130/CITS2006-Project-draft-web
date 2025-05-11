// validation-multi-ip-test.js
// Usage: node validation-multi-ip-test.js

const axios = require('axios');
const fs    = require('fs');
const path  = require('path');

// Helper for URL-encoding form data
function encodeForm(data) {
  return Object.entries(data)
    .map(([k, v]) => `${encodeURIComponent(k)}=${encodeURIComponent(v)}`)
    .join('&');
}

// List of distinct IPs to rotate through
const ipList = [
  '203.0.113.1',
  '203.0.113.2',
  '203.0.113.3'
];

(async () => {
  const csvPath = path.join(__dirname, 'validation_20_tests.csv');
  const lines   = fs.readFileSync(csvPath, 'utf8').trim().split('\n');
  const headers = lines.shift().split(',');

  console.log('Running multi-IP validation tests:\n');
  for (let i = 0; i < lines.length; i++) {
    const values = lines[i].split(',');
    const payload = {};
    headers.forEach((h, idx) => { payload[h] = values[idx]; });

    const body = encodeForm(payload);
    const ip   = ipList[i % ipList.length];
    const start = Date.now();

    try {
      const res = await axios.post('http://localhost:2077/book', body, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'X-Forwarded-For': ip
        }
      });
      console.log(`Test ${i+1} [IP:${ip}] (${payload.name || '<empty>'}): ✅ ${res.status} (${Date.now()-start} ms)`);
    } catch (err) {
      const status = err.response ? err.response.status : err.code;
      console.log(`Test ${i+1} [IP:${ip}] (${payload.name || '<empty>'}): ❌ ${status} (${Date.now()-start} ms)`);
    }
  }
})();
