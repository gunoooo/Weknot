package com.example.weknot_android.view.navigator

import com.example.weknot_android.base.BaseNavigator

interface SignUpNavigator : BaseNavigator {
    fun handleSuccess(message: String)
    fun openLoginActivity()
    fun signUp()
}