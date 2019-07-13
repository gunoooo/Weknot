package com.example.weknot.data;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

    private String result;

    private String token;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
