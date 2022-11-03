const { body, header } = require('express-validator')


exports.authorizationValidator = [
    header("Authorization").exists().withMessage("Authorization token required"),
    header("Authorization").custom(value => value.startsWith("Bearer")).withMessage("Invalid token format, Bearer not found")
]
