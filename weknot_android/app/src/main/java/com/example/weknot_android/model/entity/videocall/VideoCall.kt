package com.example.weknot_android.model.entity.videocall

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weknot_android.model.entity.user.User

@Entity(tableName = "videocall_table")
class VideoCall(@field:PrimaryKey
                var idx: Int,
                var user: User,
                var roomIdx: Int)