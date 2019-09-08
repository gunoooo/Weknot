package com.example.weknot_android.model.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import com.example.weknot_android.model.sharedpreference.Token
import com.example.weknot_android.model.sharedpreference.UserId
import org.json.JSONException
import org.json.JSONObject

class UserIdRepository(private val context: Context) {
    val userId: UserId = UserId(context)

    fun setUserId(id: String) {
        UserId(context).id = id
    }
}