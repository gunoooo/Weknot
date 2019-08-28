package com.example.weknot_android.model.dao

import androidx.room.Dao
import androidx.room.Query

import com.example.weknot_android.base.BaseDao
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.entity.videocall.VideoCall
import io.reactivex.Single

@Dao
interface VideoCallDao : BaseDao<VideoCall> {

    @Query("SELECT * FROM videocall_table WHERE idx=:idx")
    fun getVideoCall(idx: Int): Single<VideoCall>?
}
