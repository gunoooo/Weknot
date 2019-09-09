package com.example.weknot_android.model.repository

import android.app.Application
import com.example.weknot_android.model.dao.OpenChatDao
import com.example.weknot_android.model.dao.UserDao

import com.example.weknot_android.model.database.RoomDatabase
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import io.reactivex.Completable
import io.reactivex.Single

class RoomRepository(application: Application) {

    private var userDao: UserDao
    private var openChatDao: OpenChatDao

    init {
        val database = RoomDatabase.getInstance(application)!!
        userDao = database.userDao()
        openChatDao = database.openChatDao()
    }

    fun insertUser(entity: User) { Completable.fromAction { userDao.insert(entity) } }
    fun updateUser(entity: User) { Completable.fromAction { userDao.update(entity) } }
    fun deletUser(entity: User) { Completable.fromAction { userDao.delete(entity) } }
    fun getUser(id: String): Single<User> {
        return userDao.getUser(id)
    }

    fun insertOpenChat(entity: OpenChatRoom) { Completable.fromAction { openChatDao.insert(entity) }}
    fun insertOpenChat(vararg entity: OpenChatRoom) { Completable.fromAction { openChatDao.insert(entity) } }
    fun getOpenChat(roomNumber: String): Single<OpenChatRoom>? { return openChatDao.getOpenChat(roomNumber) }
}
