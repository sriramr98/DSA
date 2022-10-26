require("dotenv").config()
require("express-async-errors")
const { initializeApp, applicationDefault } = require('firebase-admin/app');

const accessLogMiddleware = require("./middlewares/logger.middleware")
const routes = require("./routes/api")

const express = require("express")
const app = express()
const cors = require("cors")

app.use(cors())

app.use(express.json())

initializeApp({
    credential: applicationDefault()
});

// Req and Res logger.
app.use(accessLogMiddleware)

app.use("/", routes)

module.exports = app
