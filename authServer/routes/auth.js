const express = require('express');
const router = express.Router();
const users = require('../models/users');
const authMiddle = require("../middlewares/auth");
const multer = require('multer');
const path = require('path');

/* GET user authentication. */
router.get('/', (req, res, next) => {
  res.send('auth main');
});

router.post('/login', (req, res, next) => {//userId,userPassword
  const { id, password } = req.body;
  
  if (!id || !password) {
    res.status(403).json({
      message: "invalid parameter"
    });
    return;
  }

  users.getQueryUser(id)
    .then((result) => {
      let user;
      if (result != null) user = result[0];
      if (user == undefined) {
        res.status(403).json({
          message: "undefined user"
        });
        return;
      }
      if (user.password == password) {
        users.loginUser(req, res, user)
          .then((token) => {
            res.json({
              data: { token, user: user },
              message: "login"
            });
          })

      }
      else
        res.status(403).json({
          message: "invalid password"
        });
    })
    .catch((err) => {
      res.status(500).json({ message: err.message });
    });
});

router.post('/autoLogin', authMiddle, (req, res, next) => {
  const id = req.decodedToken.sub;
  users.getQueryUser(id)
    .then((result) => {
      let user;
      if (result != null) user = result[0];
      if (user == undefined) {
        res.status(403).json({
          message: 'undefined user'
        });
        return;
      } else {
        res.json({
          data: {
            id: user.id,
            name: user.name,
            birth: user.birth,
            gender: user.gender,
            phoneNumber: user.phoneNumber
          },
          message: "login"
        });
      }
    })
    .catch((err) => {
      console.log(err);
      res.status(500).json({ message: err.message });
    });
})


let stroage = multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, 'public/image');
  },
  filename: function (req, file, callback) {
    let extension = path.extname(file.originalname);
    let basename = path.basename(file.originalname, extension);
    callback(null, basename + "-" + Date.now() + extension);
  }
});
let upload = multer({ storage: stroage }).fields([{ name: 'photo', maxCount: 1 }]);

// 회원가입 테스트 해보기
router.post('/register', upload, (req, res, next) => {
  const user = {
    id: req.body.id,
    name: req.body.name,
    password: req.body.password,
    birth: req.body.birth,
    gender: req.body.gender,
    phoneNumber: req.body.phoneNumber,
    intro: req.body.intro,
    // photo: req.files['photo'][0].filename
  };

  if (req.files != null && req.files['photo'] != null && req.files['photo'][0] != null) {
    user.photo = req.files['photo'][0].filename;
  }

  console.log(user);
  

  users.registerUser(user)
    .then((result) => {
      if (result.affectedRows === 1) {
        res.json({
          message: "success"
        });
      }
      else {
        res.status(500).json({ message: 'fail' })
      }
    })
    .catch((err) => {
      res.status(500).json({
        error: { message: err.message }
      });
    });
});

// 나중에 구현
router.post('/checkUserId', (req, res, next) => {//userName,userPhoneNumber
  const userId = req.body.userId;

  console.log(userId);

  users.getQueryUser(userId)
    .then((result) => {
      let user;
      if (result != null) user = result[0];

      if (user == undefined) {
        res.status(403).json({
          message: "존재하지 않는 아이디입니다."
        });
      }
      else if (user.id !== undefined) {
        res.status(403).json({
          message: "이미 존재하는 아이디입니다."
        });
      }
      else
        res.status(500).json({ error: err.message });
    })
    .catch((err) => {
      res.status(500).json({ error: err.message });
    });
});

module.exports = router;
