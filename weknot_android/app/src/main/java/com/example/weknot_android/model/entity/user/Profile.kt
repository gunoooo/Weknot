package com.example.weknot_android.model.entity.user

import com.example.weknot_android.model.entity.feed.Feed

class Profile(var id: String,
              var name: String,
              var birth: String,
              var scope: String,
              var intro: String,
              var photo: String?,
              var point: Int,
              var feeds: List<Feed>?,
              var gender: String,
              var state: Int)