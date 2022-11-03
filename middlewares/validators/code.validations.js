const { body } = require('express-validator');


exports.executeCodeValidator = [ 
    body("language").exists().withMessage("Language required"), 
    body("code").exists().withMessage("Code required")
];