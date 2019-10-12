var express = require('express');
var router = express.Router();
const authMiddle = require("../middlewares/auth");

let channels = [];

/* GET home page. */
router.post('/', authMiddle, (req, res, next) => {
  const id = req.decodedToken.sub;

  const newChannel = (id) => {
    const c={channel:Date.now().toString(), users:[id], status:[1]}
    
    if(channels==null) channels=[]
    channels.push(c)
    console.log(channels)
    return res.json({
      message: "ok",
      data: {
        channel: c.channel,
        users: c.users
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
            channel: c.channel,
            users: c.users
          }
        });
      }
    }
    newChannel(id);
  }
});

//  할것 : 채널(영통방)에서 나가기
router.post('/:channel', authMiddle, (req, res, next)=>{
  const cid = req.params.channel;
  const userId = req.decodedToken.sub;
  let otherId=''
  // channels 배열에서 channel 값 비교해서 (유저) 삭제
  for (let i = 0; i < channels.length; i++) {
    console.log(channels[i].channel);
    if(cid === channels[i].channel) {
      if(userId == channels[i].users[0]){
        channels[i].status[0] = 0;
        otherId = channels[i].users[1];
      }
      else{
        channels[i].status[1] = 0;
        otherId = channels[i].users[0];
      }
      if(channels[i].status[0] == 0 && channels[i].status[1]==0)
        channels[i].users = [];
      break;
    }
  }
  res.json({
    message:"ok",
    data: otherId
  });
  console.log(channels);

});

module.exports = router;