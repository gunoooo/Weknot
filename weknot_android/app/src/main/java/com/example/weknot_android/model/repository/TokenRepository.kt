package com.example.weknot_android.model.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import com.example.weknot_android.model.sharedpreference.Token
import org.json.JSONException
import org.json.JSONObject

class TokenRepository(private val context: Context) {
    val token: Token

    fun setToken(token: String) {
        Token(context).token = token
    }

    init {
        token = Token(context)
    }
}