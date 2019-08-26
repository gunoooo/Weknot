package com.example.weknot_android.room.repository

import android.app.Application
import android.os.AsyncTask
import com.example.weknot_android.base.BaseDao
import com.example.weknot_android.room.dao.UserDao

import com.example.weknot_android.room.database.RoomDatabase
import com.example.weknot_android.room.entity.user.User
import io.reactivex.Single

class RoomRepository(application: Application) {

    protected var userDao: UserDao


    init {
        val database = RoomDatabase.getInstance(application)
        userDao = database!!.userDao()
    }


    private val user: Single<User>? = userDao.getUser(TokenRepository(application).myId!!)
    fun userInsert(entity: User) {
        InsertAsyncTask(userDao).execute(entity)
    }
    fun userUpdate(entity: User) {
        UpdateAsyncTask(userDao).execute(entity)
    }
    fun userDelete(entity: User) {
        DeleteAsyncTask(userDao).execute(entity)
    }
    fun userDeleteAll() {
        DeleteAllUsersAsyncTask(userDao).execute()
    }
    fun getUser(): Single<User>? {
        return user
    }


    private class InsertAsyncTask<TE, TDO : BaseDao<TE>>(private val dao: TDO) : AsyncTask<TE, Void, Void>() {
        override fun doInBackground(vararg entity: TE): Void? {
            dao.insert(entity[0])
            return null
        }
    }
    private class UpdateAsyncTask<TE, TDO : BaseDao<TE>>(private val dao: TDO) : AsyncTask<TE, Void, Void>() {
        override fun doInBackground(vararg entity: TE): Void? {
            dao.update(entity[0])
            return null
        }
    }
    private class DeleteAsyncTask<TE, TDO : BaseDao<TE>>(private val dao: TDO) : AsyncTask<TE, Void, Void>() {
        override fun doInBackground(vararg entity: TE): Void? {
            dao.delete(entity[0])
            return null
        }
    }

    private class DeleteAllUsersAsyncTask(private val userDao: UserDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            userDao.deleteAll()
            return null
        }
    }
}
