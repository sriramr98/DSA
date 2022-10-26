const express = require("express")
const router = express.Router()

const IndexController = require("../controllers/index.controller")
const { validate } = require("../middlewares/validators/wrapper.validator")
const { indexValidator } = require("../middlewares/validators/index.validations")
const { authorizationValidator } = require("../middlewares/validators/authorize.validations")
const { authorizeUser } = require("../middlewares/auth/authorize")

router.get("/", validate(authorizationValidator), authorizeUser, IndexController.index)
router.post("/", validate(indexValidator), IndexController.indexPost)

module.exports = router
