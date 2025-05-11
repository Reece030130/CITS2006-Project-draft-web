// public/js/recaptcha.js

let widgetId = null;

// Called once the reCAPTCHA API has loaded
function onloadCallback() {
  if (widgetId !== null) return;
  try {
    widgetId = grecaptcha.render('recaptcha-container', {
      sitekey: '6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI', // your real key here
      callback: onCaptchaSuccess,
      'expired-callback': onCaptchaExpired
    });
    console.log('reCAPTCHA widget rendered, widgetId=', widgetId);
  } catch (err) {
    console.error('reCAPTCHA render error:', err);
  }
}

function onCaptchaSuccess(token) {
  console.log('CAPTCHA passed, token=', token);
  document.getElementById('submitBtn').disabled = false;
}

function onCaptchaExpired() {
  console.log('CAPTCHA expired');
  document.getElementById('submitBtn').disabled = true;
}

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('bookingForm');
  const submitBtn = document.getElementById('submitBtn');

  form.addEventListener('submit', (e) => {
    // 1) Disable any empty optional fields so they aren't sent
    ['company', 'message'].forEach(name => {
      const fld = form.elements[name];
      if (fld && fld.value.trim() === '') fld.disabled = true;
    });

    // 2) Final CAPTCHA guard
    if (!grecaptcha.getResponse()) {
      e.preventDefault();
      alert('Please complete the CAPTCHA before submitting.');
    }
  });
});
