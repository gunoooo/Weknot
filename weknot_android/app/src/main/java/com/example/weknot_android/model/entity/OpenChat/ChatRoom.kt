package com.example.weknot_android.model.entity.OpenChat

import com.example.weknot_android.model.entity.user.FbUser

class ChatRoom {
    var roomNumber: Int? = null
    var roomName: String? = null
    var masterName: String? = null

    var roomType: String? = null
        set(value) {
            field = when (value) {
                "자유방" -> "free"
                "고민 상담방" -> "worry"
                "게임방" -> "game"
                "친목방" -> "friend"
                else -> value
            }
        }

    var users: Map<String, FbUser>? = HashMap()
    var messages: Map<String, Chat>? = HashMap()
}