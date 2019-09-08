var express = require('express');
var router = express.Router();
const authMiddle = require("../middlewares/auth");
const feedModel = require('../models/feeds')

// 전체 피드 조회 (내꺼 + 친구꺼)
router.get('/', authMiddle, function(req, res, next) {
  const id = req.decodedToken.sub;
  const feeds = [{
    writePicture: "Wpic3",
    writeName: "cccc",
    feedId: "windy",
    feedPicture: "Fpic3",
    feedComment: "Cry",
    likeCount: 109,
    isLiked: true
    }];

  res.json({
    data:{feeds},
    message: "ok"
  });
});

router.post('/', authMiddle,  (req, res, next) => {
  // const id = req.decodedToken.sub;
  // const feed = {
  //   writer: id,
  //   commnet: req.body.commnet
  // };
  // feedModel.addFeed(feed)
  // .then(function)
});

module.exports = router;