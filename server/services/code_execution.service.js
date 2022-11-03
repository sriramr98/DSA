const piston = require('piston-client');


const client = piston({
    host: 'https://emkc.org',
    timeout: 5000,
});


module.exports = {
    execute: async (language, code) => {
        const result = await client.execute(language, code);
        return result
    }
}