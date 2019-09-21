const express = require('express');
const router = express.Router();

const authMiddle = require("../middlewares/auth");
const users = require("../models/users");

//친구 요청 하는 api friend 테이블에 state를 0으로 집어넣음.
router.post('/',authMiddle, (req, res, next) => {//userId,friendId
  const idList = [
    requester = req.decodedToken.sub,
    receiver = req.body.receiver,
    state = 0
  ]; //배열로 해야 한번에 넣을 수 있음.

  users.addFriend(idList)
  .then((result) => {
    if(result)
    {
      res.json({message: 'ok', data: result});
    }
    else
    {
      res.status(500).json({
        error: {message: 'fail'}
      });
    }
  })
  .catch((err) => {
    res.status(500).json({
      error: {message:err.message}});
  });
});


//친구 조회
router.get('/', authMiddle, (req,res,next) => {//friend에서 state가 1인 사람들을 불러와야함.
  const requester = req.decodedToken.sub;

  users.getFriend(requester)
    .then((result) => {
    if(result)
    {
      res.json({message: 'ok', data: result});
    }
    else
    {
      res.status(500).json({
        error: {message: 'fail'}
      });
    }})
    .catch((err) => {
      res.status(500).json({
        error: {message:err.message}});
    });
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
});

//친구 요청 수락 또는 거절하는 api, 수락이면 friend 테이블에 1, 거절이면 딜리트
router.put('/', authMiddle, (req, res, next) => {//userId,friend,decision
  const knot = 
  {
    userId : req.decodedToken.sub,
    friend : req.body.friend,
    decision : req.body.decision
  };

  if(knot.decision === "yes")
  {
    users.acceptFriend(knot)
      .then((result) => {
        console.log(result);
      if(result.affectedRows === 1)
      {
        res.json({message: 'ok'});
      }
      else
      {
        res.status(500).json({
          error: {message: 'fail'}
        });
      }
      })
      .catch((err) => {
        res.status(500).json({
          error: {message:err.message}});
      });
  }else if(knot.decision === "no")
  {

    users.refuseFriend(knot)
    .then((result) => {
      if(result)
      {
        res.json({result:"success"});
      }
      else
      {
        res.status(500).json({
          error: {message: 'fail'}
        });
      }
      })
      .catch((err) => {
        res.status(500).json({
          error: {message:err.message}});
      });
 }
});

module.exports = router;