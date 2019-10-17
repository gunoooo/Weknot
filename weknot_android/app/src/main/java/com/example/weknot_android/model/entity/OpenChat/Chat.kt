package com.example.weknot_android.model.entity.OpenChat

import com.example.weknot_android.model.entity.user.FbUser

class Chat {
    var id: String? = null
    var writer: FbUser? = null
    var message: String? = null
    var timeStamp: String? = null
}