package com.example.weknot_android.room.repository;

import android.content.Context;
import android.util.Base64;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weknot_android.room.sharedpreference.Token;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenRepository {

    private final String PLAYLOAD_USER_ID = "memberId";

    private Context context;
    private Token token;

    public TokenRepository(Context context) {
        this.context = context;
        token = new Token(context);
    }

    public Token getToken() {
        return token;
    }

    public void setToken(String token) {
        new Token(context).setToken(token);
    }

    private static JSONObject decodedPayloadObject(String tokenString) {
        try {
            String[] split = tokenString.split("\\.");
            return new JSONObject(new String(Base64.decode(split[1], Base64.DEFAULT)));
        } catch (JSONException ignore) {
            return null;
        }
    }

    public String getMyId() {
        try {
            Token token = getToken();
            if (token.getToken() == "") {
                System.out.println("aaaaaaaaaaaaaaaa");
                return "";
            }
            JSONObject payload = decodedPayloadObject(token.getToken());
            return payload.getString(PLAYLOAD_USER_ID);
        } catch (JSONException ignore) {
            return "";
        }
    }
}
