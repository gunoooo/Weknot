package com.example.weknot_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.R
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.navigator.FeedItemNavigator

class OpenChatItemViewModel : BaseItemViewModel<OpenChatRoom, Any>() {
    val name = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val master = MutableLiveData<String>()
    val type = MutableLiveData<Int>()

    override fun bind(data: OpenChatRoom) {
        name.value = data.roomName
        number.value = data.roomNumber
        master.value = data.masterName
        type.value = getRoomTypeDrawable(data.roomType)
    }

    private fun getRoomTypeDrawable(roomType: String) : Int? {
        return when (roomType) {
            "free" -> R.drawable.ic_room_type_free
            "game" -> R.drawable.ic_room_type_game
            "worry" -> R.drawable.ic_room_type_worry
            "secret" -> R.drawable.ic_room_type_secret
            else -> null
        }
    }
}