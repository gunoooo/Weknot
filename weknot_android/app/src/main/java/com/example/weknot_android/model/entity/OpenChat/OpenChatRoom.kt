package com.example.weknot_android.model.entity.OpenChat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "openchat_table")
class OpenChatRoom(@field:PrimaryKey
                   var roomNumber: String,
                   var roomName: String,
                   var masterName: String,
                   var roomPassword: String?,
                   var roomType: String)