package com.example.weknot_android.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weknot_android.room.dao.UserDao;
import com.example.weknot_android.room.entity.user.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
