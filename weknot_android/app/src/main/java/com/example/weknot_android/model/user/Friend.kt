package com.example.weknot_android.model.user

data class Friend(var friendId: String,
                  var friendName: String,
                  var friendPhoto: String?,
                  var friendPoint: Int,
                  var friendState: Int)