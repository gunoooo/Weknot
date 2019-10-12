const jwt = require('jsonwebtoken')

const authMiddleware = (req, res, next) => {

  // read the token from header or url
  const token = req.headers['authorization'];// || req.query.token
  //console.log(token);
  if (!token) {
    return res.status(403).json({
      message: 'No Token'
    })
  }

  // create a promise that decodes the token
  const checkToken = new Promise(
    (resolve, reject) => {
      jwt.verify(token, req.app.get('jwt-secret'), (err, decodedToken) => {
        if (err) reject(err)
        resolve(decodedToken)
      })
    }
  )

  const checkSubject = (decodedToken) => {
    const userid = decodedToken.sub;
    const userName = decodedToken.userName;

    console.log(userid, userName);
    return decodedToken;
  }

  // if it has failed to verify, it will return an error message
  const onError = (error) => {
    res.status(403).json({
      message: error.message
    })
  }

  // process the promise
  checkToken
    .then(checkSubject)
    .then((decodedToken) => {
      req.decodedToken = decodedToken
      //console.log(req.decodedToken)
      next()
    })
    .catch(onError)
}

module.exports = authMiddleware
