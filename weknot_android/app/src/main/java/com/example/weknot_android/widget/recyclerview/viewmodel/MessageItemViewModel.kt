package com.example.weknot_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import com.example.weknot_android.model.entity.OpenChat.Chat

class MessageItemViewModel : BaseItemViewModel<Chat, Any>() {

    val writer = MutableLiveData<String>()
    val message = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    override fun bind(data: Chat) {
        writer.value = data.writer
        message.value = data.message
        time.value = data.timeStamp
    }
}