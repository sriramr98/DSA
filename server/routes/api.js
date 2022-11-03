const express = require("express")
const router = express.Router()

const IndexController = require("../controllers/index.controller")
const CodeController = require('../controllers/code.controller')
const { validate } = require("../middlewares/validators/wrapper.validator")
const { indexValidator } = require("../middlewares/validators/index.validations")
const { authorizationValidator } = require("../middlewares/validators/authorize.validations")
const { authorizeUser } = require("../middlewares/auth/authorize")
const { executeCodeValidator } = require("../middlewares/validators/code.validations")

router.get("/", validate(authorizationValidator), authorizeUser, IndexController.index)
router.post("/", validate(indexValidator), IndexController.indexPost)

router.post("/execute", authorizeUser, validate(executeCodeValidator), CodeController.executeCode);

module.exports = router
