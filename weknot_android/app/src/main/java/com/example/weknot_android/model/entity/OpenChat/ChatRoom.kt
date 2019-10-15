package com.example.weknot_android.model.entity.OpenChat

import com.example.weknot_android.model.entity.user.FbUser

class ChatRoom {
    var roomNumber: Int? = null
    var roomName: String? = null
    var masterName: String? = null
    var roomType: String? = null
    var users: Map<String, FbUser>? = HashMap()
    var messages: Map<String, Chat>? = HashMap()
}