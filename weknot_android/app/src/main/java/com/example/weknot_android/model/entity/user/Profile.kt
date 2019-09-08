package com.example.weknot_android.model.entity.user

import com.example.weknot_android.model.entity.feed.Feed

class Profile(var userId: String,
              var userName: String,
              var userBirth: String,
              var userScope: String,
              var userIntro: String,
              var userPicture: String?,
              var userPoint: Int,
              var userFeeds: List<Feed>?,
              var userGender: String)