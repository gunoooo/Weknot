const express = require('express');
const router = express.Router();
const mariadb = require('mariadb');
const dbcp = require('../models/dbcp');
const users = require('../models/users');
const authMiddle = require("../middlewares/auth");


/* GET users listing. */
router.get('/', (req, res, next) => {
  const pool = mariadb.createPool(dbcp);
  users.allUsers(pool)
    .then((result) => {
      console.log(result);
      res.send(JSON.stringify(result));
    })
    .catch((err) => {
      res.render('error');
    });
});

router.post('/login', (req,res,next) => {
  let userId = req.body.id;
  let password = req.body.password;

  users.queryUser(userId)
  .then((result) => {
    if(result.password === password){
      res.json({result:"success"});
    }
    else
      res.json({result:"fail"});
  })
  .catch((err) => {
    res.render('error', {error:err});
  });
});


router.post('/register',(req,res,next) => {
  let user = {
    id : req.body.id,
    name : req.body.name,
    password : req.body.password,
    birth : req.body.birth,
    gender : req.body.gender,
    phoneNumber : req.body.phoneNumber
  };

  users.registerUser(user)
  .then((result)=>{
    if(result.affectedRows === 1){
      res.json({result:"success"});
    }else{
      res.status(403).json({
        message:'omg'
      })
    }
    
  })
  .catch((err) => {
    res.status(403).json({
      message:err.message,
    });
  })
});

router.post('/changePassword',authMiddle, (req,res,next) => {
  const userId = req.decodedToken.sub;
  const newPassword = req.body.newPassword;

  users.changePassword(userId, newPassword)
  .then((result) => {
    res.json({
      result:"success",
      message: "ok"
    });
  })
  
});

router.post('/like', (req, res, next) => {
  const userId = "tjrwns";
  const feedId = "sunday";

  res.json({
    result: "success",
    message: "ok"
  });
});

// state:0 아무 관계 아님
// state:1 내가 받은 요청
// state:2 내가 보낸 상태
// state:10 친구
// state: 100 자기자신
router.get('/profile/:userId',authMiddle,(req, res, next) => {
  const userId = req.params.userId;
  const myId = req.decodedToken.sub;

  users.showUserProfile(userId)
  .then((result) =>{
    if (result){
      if (userId === myId) {
        result[0].state = 100;
        res.json({message: 'ok', data: result[0]});
      } else {
        users.getFriendState(userId, myId)
        .then((state)=>{
          result[0].state = state;
          res.json({message: 'ok', data: result[0]});
        })
        .catch((err)=>{
          res.json({
            result:"error",
            error:err.message});
        })
      }
    }
    else{
      res.json({result:"fail"})
    }
  })
  .catch((err) => {
    res.json({
      result:"error",
      error:err.message});
  });
});//userId,userName,userBirth,userScope,userIntro,userPicture,userPoint,userGender
//userFeeds:feddId,feddPicture.

/*router.get('/:id/log/:category', function(req, res, next) {
  const pool = mariadb.createPool(dbcp);
  users.queryUser(pool, userId)
    .then(function(result){
      var dbpassword = result.password;
      if(dbpassword === password)
        res.send('{"result":"OK"}');
      else
        res.send('{"result":"NG"}');
    })
    .catch(function(err){
      res.render('error');
    })
})*/



module.exports = router;
