const { execute } = require('../services/code_execution.service');

exports.executeCode = async (req, res) => {
    const { language, code } = req.body;
    const result = await execute(language, code);
    res.send(result);
}