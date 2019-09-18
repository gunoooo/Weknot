package com.example.weknot_android.viewmodel

import android.app.Application

class MyinfoViewModel(application: Application) : ProfileViewModel(application) {

    fun getMyId(): String {
        return userId
    }
}