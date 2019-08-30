package com.example.weknot_android.model.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import com.example.weknot_android.model.dao.OpenChatDao

import com.example.weknot_android.model.dao.UserDao
import com.example.weknot_android.model.dao.VideoCallDao
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.entity.videocall.VideoCall

@Database(entities = [User::class, OpenChatRoom::class, VideoCall::class], version = 1, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun videoCallDao(): VideoCallDao
    abstract fun openChatDao(): OpenChatDao

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "weknot_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}
