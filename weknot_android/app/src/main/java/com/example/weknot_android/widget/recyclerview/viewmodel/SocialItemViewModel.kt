package com.example.weknot_android.widget.recyclerview.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import com.example.weknot_android.model.user.Friend
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.navigator.social.SocialItemNavigator

class SocialItemViewModel : BaseItemViewModel<Friend, SocialItemNavigator>() {
    val name = MutableLiveData<String>()
    val point = MutableLiveData<String>()
    val picture = MutableLiveData<String>()
    val acceptVisibility = MutableLiveData<Int>()

    override fun bind(data: Friend) {
        name.value = data.friendName
        point.value = data.friendPoint.toString() + "점"
        picture.value = Strings.MAIN_HOST + "/image/" + data.friendPhoto
        if (data.friendState == 0) acceptVisibility.value = View.VISIBLE else acceptVisibility.value = View.INVISIBLE
    }

    fun onClickAcceptBtn() {
        getNavigator().acceptFriend()
    }

    fun onClickRejectBtn() {
        getNavigator().rejectFriend()
    }

    fun onClickItem() {
        getNavigator().openProfile()
    }
}