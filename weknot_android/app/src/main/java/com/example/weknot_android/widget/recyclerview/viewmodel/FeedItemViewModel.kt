package com.example.weknot_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.navigator.FeedItemNavigator

class FeedItemViewModel : BaseItemViewModel<Feed, FeedItemNavigator>() {
    val name = MutableLiveData<String>()
    val photo = MutableLiveData<String>()
    val picture = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val comment = MutableLiveData<String>()

    override fun bind(data: Feed) {
        name.value = data.name
        photo.value = Strings.MAIN_HOST + "/image/" + data.photo
        picture.value = Strings.MAIN_HOST + "/image/" +data.picture
        time.value = data.time.split("T")[0] + " " + data.time.split("T")[1].split(".")[0]
        comment.value = data.comment
    }

    fun onClickLikeOn() {
        getNavigator().likeOnEvent()
    }

    fun onClickLikeOff() {
        getNavigator().likeOffEvent()
    }
}