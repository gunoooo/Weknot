const express = require('express');
const router = express.Router();

const mariadb = require('mariadb');
const jwt = require('jsonwebtoken');

const dbcp = require('../models/dbcp');
const users = require('../models/users');

/* GET user authentication. */
router.get('/', (req, res, next) => {
  res.send('auth main');
});

router.get('/login', (req, res, next) => {
  res.render('loginForm');
});

router.post('/login',(req, res, next) => {//userId,userPassword
  const userId = req.body.id;
  const password = req.body.password;

  users.getQueryUser(userId)
  .then((result) => {
    let user;
    if(result != null) user = result[0];
    if(user == undefined){
      res.json({error: 'do not use id'})
      return;
    }
    if(user.password == password){
      res.json({result:"success"});
    }
    else
      res.json({result:"fail"});
  })
  .catch((err) => {
    console.log(err);
    res.render('error', {error:err});
  });
});

router.get('/register', (req, res, next) => {
  res.render('registerForm');
});

router.post('/register',(req, res, next) => {
  const user = [
    id = req.body.userId,
    name = req.body.userName,
    password = req.body.userPassword,
    birth = req.body.userBirth,
    gender = req.body.userGender,
    phoneNumber = req.body.userPhoneNumber
  ];

  users.registerUser(user)
  .then((result) => {
    console.log(result);
    if(result)
    {
      res.json({result:"success"});
    }
    else
    {
      res.json({result:"fail"});
    }
  })
  .catch((err) => {
    res.json({error:"error"});
  });
});

router.get('/checkUserId/:userId',(req, res, next) => {//userName,userPhoneNumber
  const userId = req.params.userId;

  users.getQueryUser(userId)
  .then((result) => {
    let user;
    if(result != null) user = result[0];
    if(user == undefined){
      res.json({result:"fail"});
    }
    else if(user.id !== undefined){
      res.json({result:"success"});
    }
    else
      res.json({error:"error"});
  })
  .catch((err) => {
    res.render('error', {error:err});
  });
});

/*router.get('/allUser',(req,res,next) => {
  users.allUsers()
  .then((result) => {
    res.json(result);
  })
  .catch((err) => {
    res.render('error',{error:err});
  })
});*/

router.post('/getMyId',(req, res, next) => {//userName,userPhoneNumber,userCertNumber
  const userName = req.body.name;
  const userPhoneNumber = req.body.phoneNumber;
  const userCertNumber = req.body.certNumber;

});//userId

router.post('/getMyPassword',(req, res, next) => {//userId,userPhoneNumber,userCertNumber
  const userName = req.body.name;
  const userPhoneNumber = req.body.phoneNumber;
  const userCertNumber = req.body.certNumber;

});//userPassword

//router.post('/addLike',(req, res, next) => {//friendId
 // const friendId = req.body.friendId;



//});

//친구 요청 하는 api friend 테이블에 state를 0으로 집어넣음.
router.post('/addFriend',(req, res, next) => {//userId,friendId
  const idList = [
    userId = req.body.userId,
    friendId = req.body.friendId,
    state = 0
  ];

  users.addFriend(idList)
  .then((result) => {
    if(result)
    {
      res.json({result:"success"});
    }
    else
    {
      res.json({result:"fail"});
    }
  })
  .catch((err) => {
    res.render('error', {error:err});
  });
});

router.get('/profile/:userId',(req, res, next) => {
  const userId = req.params.userId;

  users.showUserProfile(userId)
  .then((result) =>{
    if(result)
    {
      console.log(result);
      res.json(result);
    }
    else
    {
      res.json({result:"fail"})
    }
  })
  .catch((err) => {
    res.render('error', {error:err});
  });
});//userId,userName,userBirth,userScope,userIntro,userPicture,userPoint,userGender
//userFeeds:feddId,feddPicture

//친구 요청 수락 또는 거절하는 api, 수락이면 friend 테이블에 1, 거절이면 딜리트.
router.post('/manageKnot',(req, res, next) => {//userId,friend,decision
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
        res.json({result:"success"});
      }
      else
      {
        res.json({result:"fail"})
      }
      })
      .catch((err) => {
        res.render('error', {error:err});
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
        res.render('error', {error:err});
      });
 }
});

router.get('/chattingRooms',(req, res, next) => {//userId
  const userId = req.body.userId;

  users.getChattingRooms()
  .then((result) => {
    if(result !== undefined)
      res.json(result);
    else
      res.json({result:"fail"});
  })
  .catch((err) => {
    res.render('error',{error:err});
  })
});//roomNumber,roomName,masterName,roomPassword,roomType

router.get('/dm',(req, res, next) => {//userId
//  const userId = req.body.userId;
  const userId = "w";
  let dm;

  users.getDms(userId)
  .then((dmResult) => {
    let sender = dmResult.sender;
    users.getPicture(sender)
    .then((result) => {
      console.log(result);
      dm = Object.assign(dmResult,result);
      console.log(dm);
    })
    if(dm !== undefined)
      res.json(dm);
  })
  .catch((err) => {
    res.render('error',{error:err});
  })
});//friendId,friendPicture,message,date,isRead,dmId

router.get('/dm/:dmId',(req, res, next) => {
  const dmId = req.params.dmId;
});//friendId,friendPicture,message,date,dmId,files

module.exports = router;
