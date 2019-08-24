package com.example.weknot_android.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.weknot_android.room.TokenManager;
import com.example.weknot_android.room.dao.UserDao;
import com.example.weknot_android.room.database.UserDatabase;
import com.example.weknot_android.room.entity.user.User;

public class UserRepository {

    private UserDao userDao;
    private LiveData<User> user;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        user = userDao.getUser(new TokenManager(application).getMyId());
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAll() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public LiveData<User> getUser() {
        return user;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
