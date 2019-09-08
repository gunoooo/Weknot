const express = require('express');
const router = express.Router();

const mariadb = require('mariadb');
const jwt = require('jsonwebtoken');

const dbcp = require('../models/dbcp');
const users = require('../models/users');
const authMiddle = require("../middlewares/auth");

/* GET user authentication. */
router.get('/', (req, res, next) => {
  res.send('auth main');
});


router.post('/login',(req, res, next) => {//userId,userPassword
  // const userId = req.body.userId;
  // const password = req.body.userPassword;

  const {id, password} = req.body;
  if(!id || !password){
    res.status(403).json({
      message:"invalid parameter"
    });
    return;
  }

  users.getQueryUser(id)
  .then((result) => {
    let user;
    if(result != null) user = result[0];
    if(user == undefined){
      res.status(403).json({
        message:"undefined user"
      });
      return;
    }
    if(user.password == password){
      users.loginUser(req,res,user)
      .then((token) => {
        res.json({
          data:{token,user:user},
          message: "login"
        });
      })
      
    }
    else
      res.status(403).json({
        message:"invalid password"
    });
  })
  .catch((err) => {
    console.log(err);
    //res.render('error', {error:err});
    res.status(500).json({message:err.message});
  });
});

router.post('/autoLogin', authMiddle, (req,res,next) => {
  console.log(req.decodedToken);
  const id = req.decodedToken.sub;
  console.log(id);
  users.getQueryUser(id)
  .then((result) => {
    let user;
    if(result != null) user = result[0];
    if(user == undefined){
      res.status(403).json({
        message:'undefined user'
      });
      return;
    } else {
      res.json({
        data:{
          id:user.id,
          name:user.name,
          birth:user.birth,
          gender:user.gender,
          phoneNumber:user.phoneNumber
        },
        message: "login"});
    }
  })
  .catch((err) => {
    console.log(err);
    res.status(500).json({ message:err.message});
  });
})

router.post('/register',(req, res, next) => {
  const user = {
    id: req.body.id,
    name: req.body.userName,
    password: req.body.userPassword,
    birth: req.body.userBirth,
    gender: req.body.userGender,
    phoneNumber: req.body.userPhoneNumber
  };

  console.log(user.birth);

  users.registerUser(user)
  .then((result) => {
    if(result.affectedRows === 1)
    {
      res.json({
                message:"success"});
    }
    else
    {
      res.json(
        {error:{message:"fail"}
      });
    }
  })
  .catch((err) => {
    res.json({
      error: {message:err.message}});
  });
});

// 나중에 구현
router.post('/checkUserId',(req, res, next) => {//userName,userPhoneNumber
  const userId = req.body.userId;

  console.log(userId);

  users.getQueryUser(userId)
  .then((result) => {
    let user;
    if(result != null) user = result[0];
    
    if(user == undefined){
      res.json({
                error:{ message:"존재하지 않는 아이디입니다."}
        });
    }
    else if(user.id !== undefined){
      res.json({result:"fail",
                message:"이미 존재하는 아이디입니다."});
    }
    else
      res.json({
        result:"fail",
        error:"error"});
  })
  .catch((err) => {
    res.json({
      result:"error",
      error: err.message});
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

//router.post('/addLike',(req, res, next) => {//friendId
 // const friendId = req.body.friendId;



//});

//친구 요청 하는 api friend 테이블에 state를 0으로 집어넣음.
router.post('/addFriend',authMiddle, (req, res, next) => {//userId,friendId
  const idList = [
    requester = req.decodedToken.sub,
    receiver = req.body.receiver,
    state = 0
  ];

  users.addFriend(idList)
  .then((result) => {
    if(result)
    {
      res.json({result:"success",
                message:"친구 요청 성공했습니다."});
    }
    else
    {
      res.json({result:"fail",
                message:"친구 요청 실패했습니다."});
    }
  })
  .catch((err) => {
    res.status(500).json({ message:err.message});
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
    res.json({
      result:"error",
      error:err.message});
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
    res.json({
      result:"error",
      error:err.message});
  });
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
    res.json({
      result:"error",
      error:err.message});
  });
});//friendId,friendPicture,message,date,isRead,dmId

router.get('/dm/:dmId',(req, res, next) => {
  const dmId = req.params.dmId;
});//friendId,friendPicture,message,date,dmId,files

router.get('/friends', (req,res,next) => {//friend에서 state가 2인 사람들을 불러와야함.
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

router.post('/requestFriend', (req,res,next) => {
  const userId = "wowjddl";
  const friendId = "Ryu";

  res.json({
    result: "success",
    message: "ok"
  });
  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
});

router.get('/myResponseList', (req,res,next) => {// 내가 리시버이고 state가 1인 애들을 가져옴
  const userId = "wowjddl";

  const requesters = [
    {
      friendId: "asdf1",
      friendPicture: "google1"
    },
    {
      friendId: "asdf2",
      friendPicture: "google2"
    },
    {
      friendId: "asdf3",
      friendPicture: "google3"
    },
    {
      friendId: "asdf4",
      friendPicture: "google4"
    }
  ]

  res.json({
    result: requesters,
    message: "ok"
  })

  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
})

router.post('./createChattingRoom',(req,res,next) => {
  const userId= "wowjddl1";
  const roomName="15세 남 친구 구해요";
  const roomPassword="";
  const roomType="free";

  const roomId = 2;
  /*
    addMember()
  */

  res.json({
    result: ["success",roomId],
    message: "ok"
  })

  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
});

router.put('./enterRoom', (req,res,next) => {
  const roomId = 2;
  const userId = "wowj";

  res.json({
    result: "success",
    message: "ok"
  });

  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
});

router.post('./exitChattingRoom', (req,res,next) => {// 퇴장 할 때마다 룸의 멤버 수를 체크하고 메버 수가 0명이 되었을 경우 룸 삭제해야함.
  const roomId = 2;
  const userId = "wowjd";

  res.json({
    result: "success",
    message: "ok"
  });

  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
});

router.post('./requestMatching', (req,res,next) => {// 매칭하기를 누르면 매칭 테이블에 넣고, 매칭 취소하면 빼기
  const userId = "wowjd";

  const otherUser = [
    {
      id: "fkffkffn",
      name: "fkffkffn",
      password: "fkffkffn",
      birth: 19566854,
      gender: "0",//남자
      phoneNumber: "010-9121-0769",
      picture:"dsd",
      intro: "나는 15세 남자다",
      point: 35030,
    }
  ]
  //createChattingRoom
  res.json({
    result: otherUser,
    message: "ok"
  })
  
  /*
  res.json({
    result: "fail",
    message: "error"
  })
  */
});

module.exports = router;
