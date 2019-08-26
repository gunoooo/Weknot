package com.example.weknot_android.room.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters

import com.example.weknot_android.base.BaseDao
import com.example.weknot_android.room.dao.UserDao
import com.example.weknot_android.room.entity.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun userDao(): UserDao

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
