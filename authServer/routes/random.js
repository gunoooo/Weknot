var express = require('express');
var router = express.Router();
const authMiddle = require("../middlewares/auth");
const userModel = require("../models/users");

let channels = [];

/* GET home page. */
router.post('/', authMiddle, (req, res, next) => {
  const id = req.decodedToken.sub;

  const newChannel = (id) => {
    const c = { channel: Date.now().toString(), users: [id], status: [1] }

    if (channels == null) channels = []
    channels.push(c)
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
        c.status.push(1);
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

router.post('/addPoint/:id', authMiddle, (req, res, next) => {
  const cid = req.params.id;
  console.log(cid);

  userModel.modifyUserPoint(cid)
    .then((result) => {
      if (result.affectedRows > 0) {
        res.json({ message: 'ok' });
      } else {
        res.status(403).json({
          message: 'id is not defiend',
        })
      }
    })
    .catch((err) => {
      res.status(403).json({
        message: err.message,
      });
    })
})

//  할것 : 채널(영통방)에서 나가기
router.post('/:channel', authMiddle, (req, res, next) => {
  const cid = req.params.channel;
  const userId = req.decodedToken.sub;
  let otherId = ''
  // channels 배열에서 channel 값 비교해서 (유저) 삭제
  for (let i = 0; i < channels.length; i++) {
    console.log(channels[i].channel);
    if (cid === channels[i].channel) {
      if(channels[i].users.length<2){
        channels[i].users=[]; 
        channels[i].status=[];    
      } else{
        if (userId == channels[i].users[0]) {
          channels[i].status[0] = 0;
          otherId = channels[i].users[1];
        }
        else {
          channels[i].status[1] = 0;
          otherId = channels[i].users[0];
        }
        if (channels[i].status.length == 2 && 
          channels[i].status[0] == 0 && 
          channels[i].status[1]==0){   
          channels[i].users = [];
          channels[i].status = [];
        }
      }
      break;
    }
  }
  res.json({
    message: "ok",
    data: otherId
  });
  console.log(channels);

  result = {
    message:"ok"
  };

  if(otherId != '') {
    result.data = otherId;
  }

  res.json(result);

  

});

module.exports = router;