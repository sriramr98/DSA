const admin = require('firebase-admin')


exports.authorizeUser = async (req, res, next) => {

    const authToken = req.headers.authorization.split(' ')[1];

    try {
        const authResult = await admin.auth().verifyIdToken(authToken);
        req.app.locals.auth = authResult;
        next();
    } catch (e) {
        if (e.code === 'auth/id-token-revoked') {
            //TODO: token is revoked
        }
        //TODO: Proper error handling
        next(new Error("Unable to authorize user"));
    }

}