#!/usr/bin/env node

/**
 * manageEnv.js
 *
 * CLI to generate .env, start the server, and run tests with flags.
 */

const fs = require('fs');
const path = require('path');
const { spawn } = require('child_process');
const axios = require('axios');
const { program } = require('commander');

program
  .option('--env <type>', 'environment to use (development or test)', 'development')
  .option('--start', 'start the server')
  .option('--test', 'run validation and attack tests after starting server')
  .parse(process.argv);

const options = program.opts();

// 1) Generate .env file from templates
const envTemplates = {
  development: `
NODE_ENV=development
RECAPTCHA_SITE_KEY=6LcNTzMrAAAAAEOwwcAhGzQD7bgeHf5mA9_Vzrjj
RECAPTCHA_SECRET=6LcNTzMrAAAAAPxooL_ThmosHFIuHYDuQlxuR1Kf
DB_HOST=localhost
DB_USER=root
DB_PASS=Str0ng!Passw0rd
DB_NAME=repair_shop
DB_PORT=3306
PORT=3000
SESSION_SECRET=de6c6a3219bc197dec3e5ee07210e9eaaa05fdd6bccb44cb5d98e000d76becee
`.trim(),
  test: `
NODE_ENV=test
RECAPTCHA_SITE_KEY=6LcNTzMrAAAAAEOwwcAhGzQD7bgeHf5mA9_Vzrjj
RECAPTCHA_SECRET=6LcNTzMrAAAAAPxooL_ThmosHFIuHYDuQlxuR1Kf
DB_HOST=localhost
DB_USER=root
DB_PASS=Str0ng!Passw0rd
DB_NAME=repair_shop
DB_PORT=3306
PORT=3000
SESSION_SECRET=de6c6a3219bc197dec3e5ee07210e9eaaa05fdd6bccb44cb5d98e000d76becee
`.trim()
};

const envContent = envTemplates[options.env];
if (!envContent) {
  console.error(`Unknown environment: ${options.env}`);
  process.exit(1);
}
fs.writeFileSync(path.resolve(process.cwd(), '.env'), envContent + '\n');
console.log(`.env file created for '${options.env}' environment on port ${options.port}.`);

// 3) Utility to wait for /ping


// 4) Run a command as a promise
function runCommand(cmd, args, env = {}) {
  return new Promise((resolve, reject) => {
    const proc = spawn(cmd, args, {
      stdio: 'inherit',
      env: { ...process.env, ...env }
    });
    proc.on('exit', code => {
      if (code === 0) resolve();
      else reject(new Error(`${cmd} ${args.join(' ')} exited with code ${code}`));
    });
  });
}

(async () => {
  // 5) Start server if requested
  if (options.start) {
    console.log(`Starting server on port ${options.port}…`);
    serverProcess = spawn('node', ['app.js'], {
      stdio: 'inherit',
      env: {
        ...process.env,
        NODE_ENV: options.env,
        PORT: options.port
      }
    });
    try {
      console.log('Waiting for server to start…');
    } catch (err) {
      console.error('Server failed to start:', err.message);
      process.exit(1);
    }
  }

  // 6) Run tests if requested (against running server)
  if (options.test) {
    console.log('Running validation-multi-ip-test.js');
    try {
      await runCommand('node', ['validation-multi-ip-test.js'], { NODE_ENV: options.env });
      console.log('Running attack-simulation.js');
      await runCommand('node', ['attack-simulation.js'], { NODE_ENV: options.env });
    } catch (err) {
      console.error('❌  Test failed:', err.message);
      process.exit(1);
    } finally {
        console.log('Tests complete, shutting down server.');
    }
  }
})();
