const express = require('express');
const router = express.Router();

const authMiddle = require("../middlewares/auth");
const users = require("../models/auth");

//친구 요청 하는 api friend 테이블에 state를 0으로 집어넣음.
router.post('/',authMiddle, (req, res, next) => {//userId,friendId
  const idList = [
    requester = req.decodedToken.sub,
    receiver = req.body.receiver,
    state = 0
  ];

  users.addFriend(idList)
  .then((result) => {
    if(result)
    {
      res.json({message: 'ok', data: result});
    }
    else
    {
      res.status(500).json({message: 'fail'})
    }
  })
  .catch((err) => {
    res.status(500).json({ message:err.message});
  });
});


//친구 조회
router.get('/', (req,res,next) => {//friend에서 state가 1인 사람들을 불러와야함.
  const friends = [
    {
      friendId: "wowjd",
      friendPicture: "google",
      friendPoint: 3
    },
    {
      friendId: "wowjdd",
      friendPicture: "googlde",
      friendPoint: 4
    },
    {
      friendId: "wowjdz",
      friendPicture: "googlee",
      friendPoint: 5
    }
  ]
  res.json({
    result: friends,
    message: "ok"
  });
})

//친구 요청 수락 또는 거절하는 api, 수락이면 friend 테이블에 1, 거절이면 딜리트
router.put('/',(req, res, next) => {//userId,friend,decision
  const knotArr = [
    userId = req.body.userId,
    friend = req.body.friend,
    decision = req.body.decision
  ];

  if(decision == "yes")
  {

    users.acceptFriend(knotArr)
      .then((result) => {
      if(result)
      {
        res.json({message: 'ok'});
      }
      else
      {
        res.json({result:"fail"})
      }
      })
      .catch((err) => {
        res.json({
          result:"error",
          error:err.message});
      });
  }else if(decision == "no")
  {

    users.refuseFriend(knotArr)
    .then((result) => {
      if(result)
      {
        res.json({result:"success"});
      }
      else
      {
        res.json({result:"fail"})
      }
      })
      .catch((err) => {
        res.json({
          result:"error",
          error:err.message});
      });
 }
});