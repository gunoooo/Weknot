var express = require('express');
var router = express.Router();
const authMiddle = require("../middlewares/auth");

let channels = [];

/* GET home page. */
router.post('/', authMiddle, function(req, res, next) {
  const id = req.decodedToken.sub;

  const newChannel = () => {

    return Date.now();
  }

  if (channels.length === 0) {
    const channel = newChannel();
    res.json({
      message: "ok",
      data: {
        channel: channel
      }
    })
  }
  else {
    for (let i = 0; i < rooms.lenght; i++) {
      
    }
  }

});

module.exports = router;