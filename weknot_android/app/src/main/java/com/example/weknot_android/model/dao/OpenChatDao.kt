package com.example.weknot_android.model.dao

import androidx.room.Dao
import androidx.room.Query

import com.example.weknot_android.base.BaseDao
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.entity.videocall.VideoCall
import io.reactivex.Single

@Dao
interface OpenChatDao : BaseDao<OpenChatRoom> {

    @Query("SELECT * FROM openchat_table WHERE roomNumber=:roomNumber")
    fun getOpenChat(roomNumber: String): Single<OpenChatRoom>?
}
