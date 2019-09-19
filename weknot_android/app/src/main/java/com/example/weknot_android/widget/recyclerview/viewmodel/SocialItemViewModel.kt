package com.example.weknot_android.widget.recyclerview.viewmodel

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.view.navigator.SocialNavigator
import com.example.weknot_android.widget.recyclerview.navigator.SocialItemNavigator

class SocialItemViewModel : BaseItemViewModel<Friend, SocialItemNavigator>() {
    val name = MutableLiveData<String>()
    val point = MutableLiveData<String>()
    val picture = MutableLiveData<String>()
    val acceptVisibility = MutableLiveData<Int>()

    override fun bind(data: Friend) {
        name.value = data.friendName
        point.value = data.friendPoint.toString() + "점"
        picture.value = data.friendPicture
        if (data.friendStatus == 1) acceptVisibility.value = View.INVISIBLE
    }

    fun onClickAcceptBtn() {
        getNavigator().acceptFriend()
    }
}