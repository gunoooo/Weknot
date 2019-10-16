package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel

class ChatViewModel(application: Application) : BaseViewModel<Any>(application) {

    var roomKey: String? = null

    override fun onRetrieveDataSuccess(data: Any) { }

    override fun onRetrieveBaseSuccess(message: String) { }
}