package com.example.weknot_android.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

import com.example.weknot_android.base.BaseDao
import com.example.weknot_android.room.entity.user.User
import io.reactivex.Single

@Dao
interface UserDao : BaseDao<User> {

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getUser(id: String): Single<User>?
}
