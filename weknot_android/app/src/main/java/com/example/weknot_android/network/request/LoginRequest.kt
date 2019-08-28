package com.example.weknot_android.network.request

import com.example.weknot_android.util.Utils
import java.security.NoSuchAlgorithmException

class LoginRequest(id: String, pw: String) {
    lateinit var id: String
    lateinit var pw: String

    init {
        try {
            this.id = id
            this.pw = Utils.encryptSHA512(pw)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}