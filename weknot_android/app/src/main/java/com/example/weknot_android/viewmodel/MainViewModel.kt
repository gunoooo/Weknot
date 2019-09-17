package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.view.navigator.MainNavigator

class MainViewModel(application: Application) : BaseViewModel<Any, MainNavigator>(application) {
    override fun onRetrieveDataSuccess(data: Any) {

    }

    override fun onRetrieveBaseSuccess(message: String) {

    }

    override fun onRetrieveError(throwable: Throwable) {

    }
}