package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.network.comm.UserComm
import com.example.weknot_android.view.navigator.ProfileNavigator
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

open class ProfileViewModel(application: Application) : BaseViewModel<Profile, ProfileNavigator>(application) {
    private val userComm = UserComm()

    var id: MutableLiveData<String> = MutableLiveData()

    var userName: MutableLiveData<String> = MutableLiveData()
    var userBirth: MutableLiveData<String> = MutableLiveData()
    var userIntro: MutableLiveData<String> = MutableLiveData()
    var userPoint: MutableLiveData<String> = MutableLiveData()
    var userPicture: MutableLiveData<String> = MutableLiveData()
    var userGender: MutableLiveData<Int> = MutableLiveData()
    var feedAdapter = FeedAdapter(application)

    fun getProfile() {
        addDisposable(userComm.getProfile(token, id.value!!), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: Profile) {
        userName.value = data.userName
        userBirth.value = data.userBirth
        userIntro.value = data.userIntro
        userPoint.value = data.userPoint.toString() + "점"
        userPicture.value = data.userPicture
        if (data.userGender == "m") {
            userGender.value = R.drawable.man_icon
        }
        else {
            userGender.value = R.drawable.woman_icon
        }
        feedAdapter.updateList(data.userFeeds)
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}