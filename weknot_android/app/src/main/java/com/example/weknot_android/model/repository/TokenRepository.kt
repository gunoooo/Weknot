package com.example.weknot_android.model.repository

import android.content.Context
import android.util.Base64
import com.example.weknot_android.model.sharedpreference.Token
import org.json.JSONException
import org.json.JSONObject

class TokenRepository(private val context: Context) {
    private val PLAYLOAD_USER_ID = "memberId"
    val token: Token

    fun setToken(token: String) {
        Token(context).token = token
    }

    val myId: String?
        get() {
            try {
                val token = token
                if (token.token == "") {
                    return null
                }
                val payload = decodedPayloadObject(token.token)
                return payload!!.getString(PLAYLOAD_USER_ID)
            } catch (ignore: JSONException) {
                return null
            }
        }

    companion object {
        private fun decodedPayloadObject(tokenString: String): JSONObject? {
            return try {
                val split = tokenString.split("\\.").toTypedArray()
                JSONObject(String(Base64.decode(split[1], Base64.DEFAULT)))
            } catch (ignore: JSONException) {
                null
            }
        }
    }

    init {
        token = Token(context)
    }
}