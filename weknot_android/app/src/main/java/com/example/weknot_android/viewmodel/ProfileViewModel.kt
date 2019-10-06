package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.R
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.network.comm.FeedComm
import com.example.weknot_android.network.comm.UserComm
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

open class ProfileViewModel(application: Application) : BaseViewModel<Profile>(application) {
    private val userComm = UserComm()
    private val feedComm = FeedComm()

    var id: MutableLiveData<String> = MutableLiveData()

    var userName: MutableLiveData<String> = MutableLiveData()
    var userBirth: MutableLiveData<String> = MutableLiveData()
    var userIntro: MutableLiveData<String> = MutableLiveData()
    var userPoint: MutableLiveData<String> = MutableLiveData()
    var userPicture: MutableLiveData<String> = MutableLiveData()
    var userGender: MutableLiveData<Int> = MutableLiveData()
    var feedAdapter = FeedAdapter()

    fun setUp() {
        getProfile()
        getProfileFeeds()
    }

    private fun getProfile() {
        addDisposableLoading(userComm.getProfile(token, id.value!!), dataObserver)
    }

    private fun getProfileFeeds() {

        val observer = object : DisposableSingleObserver<List<Feed>>() {
            override fun onSuccess(t: List<Feed>) {
                feedAdapter.updateList(t)
                isLoading.value = false
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                isLoading.value = false
            }
        }

        addDisposableLoading(feedComm.getUserFeeds(token, id.value!!), observer)
    }

    override fun onRetrieveDataSuccess(data: Profile) {
        userName.value = data.name
        userBirth.value = data.birth
        userIntro.value = data.intro
        userPoint.value = data.point.toString() + "점"
        userPicture.value = Strings.MAIN_HOST + "/image/" + data.photo
        if (data.gender == "m") {
            userGender.value = R.drawable.man_icon
        }
        else {
            userGender.value = R.drawable.woman_icon
        }
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}