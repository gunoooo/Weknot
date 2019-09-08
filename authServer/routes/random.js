var express = require('express');
var router = express.Router();
const authMiddle = require("../middlewares/auth");

let channels = [];

/* GET home page. */
router.post('/', authMiddle, function(req, res, next) {
  const id = req.decodedToken.sub;

  const newChannel = (id) => {
    const c={channel:Date.now().toString(), users:[id]}
    channels.push(c)
    console.log(channels)
    return res.json({
      message: "ok",
      data: {
        channel:c.channel,
        users:c.users
      }
    });
  }

  if (channels.length === 0) {
    newChannel(id);
  }
  else {
    for (let i = 0; i < channels.length; i++) {
      let c = channels[i];
      if (c.users.length < 2) {
        c.users.push(id);
        console.log(c)
        return res.json({
          message: "ok",
          data: {
            c
          }
        });
      } 
    }
    newChannel(id);
  }
});

//  할것 : 채널(영통방)에서 나가기
router.delete('/:channel', (req, res, next)=>{
  const cid = req.param.channel;
  // channels 배열에서 channel 값 비교해서 삭제
  res.json({message:"ok"});
});

module.exports = router;