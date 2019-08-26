package com.example.weknot_android.network.request;

import com.example.weknot_android.util.Utils;

import java.security.NoSuchAlgorithmException;

public class LoginRequest {

    private String id;

    private String pw;

    public LoginRequest(String id, String pw) {
        try {
            this.id = id;
            this.pw = Utils.encryptSHA512(pw);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
