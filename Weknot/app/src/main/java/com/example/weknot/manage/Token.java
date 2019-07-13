package com.example.weknot.manage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class Token extends ContextWrapper {

    public Token(Context context) {
        super(context);
    }

    public void setToken(String token) {

        SharedPreferences sharedPreferences = getSharedPreferences("userToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);

        editor.commit();
    }

    public String getToken() {

        SharedPreferences sharedPreferences = getSharedPreferences("userToken",MODE_PRIVATE);

        String token = sharedPreferences.getString("token","");

        return token;

    }
}
