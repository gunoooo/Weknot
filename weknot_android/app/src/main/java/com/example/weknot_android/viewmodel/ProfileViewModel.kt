package com.example.weknot_android.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.R
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.network.comm.FeedComm
import com.example.weknot_android.network.comm.SocialComm
import com.example.weknot_android.network.comm.UserComm
import com.example.weknot_android.network.request.FriendRequest
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.SingleLiveEvent
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
    private val socialComm = SocialComm()

    var id: MutableLiveData<String> = MutableLiveData()

    val addBtnVisibility: MutableLiveData<Int> = MutableLiveData()

    var userName: MutableLiveData<String> = MutableLiveData()
    var userBirth: MutableLiveData<String> = MutableLiveData()
    var userStatus: MutableLiveData<String> = MutableLiveData()
    var userIntro: MutableLiveData<String> = MutableLiveData()
    var userPoint: MutableLiveData<String> = MutableLiveData()
    var userPicture: MutableLiveData<String> = MutableLiveData()
    var userGender: MutableLiveData<Int> = MutableLiveData()
    var feedAdapter = FeedAdapter()

    val onRefreshEvent = SingleLiveEvent<Unit>()

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

    fun onClickAddFriend() {
        val observer = object : DisposableSingleObserver<String>() {
            override fun onSuccess(s: String) {
                onRefreshEvent.call()
                isLoading.value = false
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                isLoading.value = false
            }
        }

        when (userStatus.value) {
            "친구 요청" -> addDisposable(socialComm.addFriend(token, id.value!!), observer)
            "친구 수락" -> addDisposable(socialComm.putFriend(token, FriendRequest(id.value!!, null)), observer)
        }
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
        when (data.state) {
            0 -> userStatus.value = "친구 요청"
            10 -> userStatus.value = "친구"
            1 -> userStatus.value = "친구 수락"
            2 -> userStatus.value = "친구 요청중"
            100 -> addBtnVisibility.value = View.INVISIBLE
        }
    }
}