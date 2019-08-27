const express = require('express');
const router = express.Router();
const mariadb = require('mariadb');
const dbcp = require('../models/dbcp');
const users = require('../models/users');


/* GET users listing. */
router.get('/', function(req, res, next) {
  const pool = mariadb.createPool(dbcp);
  users.allUsers(pool)
    .then(function(result){
      console.log(result);
      res.send(JSON.stringify(result));
    })
    .catch(function(err){
      res.render('error');
    });
});

router.get('/login',function(req,res,next){
  res.render('loginForm');
});

router.post('/login',function(req,res,next){
  let userId = req.body.id;
  let password = req.body.password;

  const pool = mariadb.createPool(dbcp);

  users.queryUser(pool, userId)
  .then(function(result){
    if(result.password === password){
      res.json({result:"success"});
    }
    else
      res.json({result:"fail"});
  })
  .catch(function(err){
    res.render('error', {error:err});
  });
});


router.post('/register',function(req,res,next) {
  let user = {
    id : req.body.id,
    name : req.body.name,
    password : req.body.password,
    birth : req.body.birth,
    gender : req.body.gender,
    phoneNumber : req.body.phoneNumber
  };
  
  const pool = mariadb.createPool(dbcp);

  users.registerUser(pool, user)
  .then(function(result){
    res.send('{"result":"sucess"}')
  })
  .catch(function(err){
    res.render('error');
  })
});

router.post('/changePassword', (req,res,next) => {
  const userId = "wowjddl";
  const newPassword = "abcd";

  res.json({
    result:"success",
    message: "ok"
  });
});

router.get('/feeds', (req,res,next) => {
  const feeds = [
    {
      writePicture: "Wpic1",
      writeName: "aaaa",
      date: "2002-12-12",
      feedId: "sunny",
      feedPicture: "Fpic1",
      feedComment: "Apple",
      likeCount: 3,
      isLiked: true
    },
    {
      writePicture: "Wpic2",
      writeName: "bbbb",
      date: "2000-01-05",
      feedId: "cloudy",
      feedPicture: "Fpic2",
      feedComment: "Bench",
      likeCount: 10,
      isLiked: false
    },
    {
      writePicture: "Wpic3",
      writeName: "cccc",
      feedId: "windy",
      feedPicture: "Fpic3",
      feedComment: "Cry",
      likeCount: 109,
      isLiked: true
    },
    {
      writePicture: "Wpic4",
      writeName: "dddd",
      feedId: "snow",
      feedPicture: "Fpic4",
      feedComment: "Im lonely",
      likeCount: 42151,
      isLiked: true
    },
  ];

  res.json({
    result: feeds,
    message: "ok"
  });
});

router.post('/like', (req, res, next) => {
  const userId = "tjrwns";
  const feedId = "sunday";

  res.json({
    result: "success",
    message: "ok"
  });
});

router.post('/feeds', (req, res, next) => {
  const feedPicture = "Fpic1";
  const feedComment = "Hello";

  res.json({
    result: "success",
    message: "ok"
  });
});

router.put('/feeds/:feedId', (req, res, next)=>{
  const feedId = req.params.feedId;
  res.json({
    result: "success",
    message: "ok"
  });
});

router.delete('/feeds/:feedId', (req, res, next)=>{
  const feedId = req.params.feedId;
  res.json({
    result: "success",
    message: "ok"
  });
});


router.



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
