package com.example.weknot_android.model.entity.OpenChat

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "openchat_table")
data class OpenChatRoom(@field:PrimaryKey
                        var roomNumber: String,
                        var roomName: String,
                        var masterName: String,
                        var roomType: String)