# CITS2006 Project Web API

## Overview

This repository provides security enhancement modules for web applications. It is not a general web development project; instead, it focuses on improving authentication, data protection, and best practices to harden web services.

## Features

* User registration and authentication (JWT)
* CRUD operations for core resources
* Input validation and error handling
* Environment-based configuration

## Prerequisites

* **Node.js** (>= 14.x) and **npm**
* **MySQL** server (>= 5.7)

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/CITS2006-Project-draft-web.git
   cd CITS2006-Project-draft-web
   ```
2. **Install dependencies**

   ```bash
   npm install
   ```

## Configuration

1. Copy the example environment file and update with your settings:

   ```bash
   cp .env.example .env
   ```
2. Edit `.env` and set the following variables:

   ```ini
   DB_HOST=localhost
   DB_PORT=3306
   DB_USER=root
   DB_PASSWORD=Str0ng!Passw0rd   # or your own secure password
   DB_NAME=cits2006_db
   JWT_SECRET=your_jwt_secret_key
   ```

## Database Setup

1. **Start MySQL** and create the application database:

   ```sql
   CREATE DATABASE cits2006_db;
   ```
2. **Run migrations** (if using a migration tool) or import the schema:

   ```bash
   npm run migrate
   ```

## Running the Application

* **Development mode** (with auto-reload):

  ```bash
  npm run dev
  ```
* **Production mode**:

  ```bash
  npm start
  ```

The API will be available at `http://localhost:3000` by default.

## API Documentation

See [API\_DOCS.md](./API_DOCS.md) for detailed endpoint definitions, request/response samples, and error codes.

## Download Node.js

If you don’t have Node.js installed, download it from the official site:

* [https://nodejs.org/en](https://nodejs.org/en)

## Folder Structure

```
├── src/
│   ├── controllers/      # Route handlers
│   ├── models/           # Database models
│   ├── routes/           # Express routers
│   ├── middleware/       # Auth, error handling, etc.
│   └── utils/            # Helper functions
├── migrations/           # Database migration scripts
├── .env.example          # Example environment variables
├── package.json
└── README.md
```

## License

This project is licensed under the MIT License. See [LICENSE](./LICENSE) for details.
