package com.example.weknot_android.room;

import android.content.Context;
import android.util.Base64;

import com.example.weknot_android.room.sharedpreference.Token;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenManager {

    private Context context;

    public TokenManager(Context context) {
        this.context = context;
    }

    private Token getToken() {
        return new Token(context);
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
            JSONObject payload = decodedPayloadObject(token.getToken());
            return payload.getString("userId");
        } catch (JSONException ignore) {
            return null;
        }
    }
}
