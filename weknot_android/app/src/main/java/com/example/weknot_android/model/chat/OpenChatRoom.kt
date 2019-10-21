package com.example.weknot_android.model.chat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "openchat_table")
data class OpenChatRoom(@field:PrimaryKey
                        var roomNumber: Int,
                        var roomName: String,
                        var masterName: String,
                        var roomType: String) {

    constructor(): this(0, "", "", "")

    constructor(chatRoom: ChatRoom): this(chatRoom.roomNumber!!, chatRoom.roomName!!, chatRoom.masterName!!, chatRoom.roomType!!)
}