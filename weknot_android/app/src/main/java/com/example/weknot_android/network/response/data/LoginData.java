package com.example.weknot_android.network.response.data;

import com.example.weknot_android.room.entity.user.User;

public class LoginData {

    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
