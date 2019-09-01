package com.example.weknot_android.model.repository

import android.app.Application
import com.example.weknot_android.model.dao.OpenChatDao
import com.example.weknot_android.model.dao.UserDao
import com.example.weknot_android.model.dao.VideoCallDao

import com.example.weknot_android.model.database.RoomDatabase
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.entity.videocall.VideoCall
import io.reactivex.Completable
import io.reactivex.Single

class RoomRepository(application: Application) {

    protected var userDao: UserDao
    protected var videoCallDao: VideoCallDao
    protected var openChatDao: OpenChatDao

    init {
        val database = RoomDatabase.getInstance(application)!!
        userDao = database.userDao()
        videoCallDao = database.videoCallDao()
        openChatDao = database.openChatDao()
    }

//    private val user: Single<User>? = userDao.getUser(TokenRepository(application).myId)
    fun insertUser(entity: User) { Completable.fromAction { userDao.insert(entity) } }
    fun updateUser(entity: User) { Completable.fromAction { userDao.update(entity) } }
    fun deletUser(entity: User) { Completable.fromAction { userDao.delete(entity) } }
//    fun getUser(): Single<User>? { return user }

    fun insertVideoCall(entity: VideoCall) { Completable.fromAction { videoCallDao.insert(entity) } }
    fun insertVideoCall(vararg entity: VideoCall) { Completable.fromAction { videoCallDao.insert(entity) } }
    fun getVideoCall(idx: Int): Single<VideoCall>? { return videoCallDao.getVideoCall(idx) }

    fun insertOpenChat(entity: OpenChatRoom) { Completable.fromAction { openChatDao.insert(entity) }}
    fun insertOpenChat(vararg entity: OpenChatRoom) { Completable.fromAction { openChatDao.insert(entity) } }
    fun getOpenChat(roomNumber: String): Single<OpenChatRoom>? { return openChatDao.getOpenChat(roomNumber) }
}
