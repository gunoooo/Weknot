package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.UserComm
import io.reactivex.observers.DisposableSingleObserver

class UserViewModel(application: Application) : BaseViewModel<Profile, User, UserComm>(application, UserComm()) {

    internal val userData = MutableLiveData<User>()

    fun getUserData(): LiveData<User> {
        return userData
    }

    fun getProfile(userId: String) {
        addDisposable(comm.getProfile(token, userId), dataObserver)
    }

    fun getUser() {
        val userDataObserver: DisposableSingleObserver<User> =
            object : DisposableSingleObserver<User>() {
                override fun onSuccess(t: User) {
                    userData.value = t
                }

                override fun onError(e: Throwable) {
                    errorMessage.value = e.message
                }
        }

        addDisposable(comm.getUser(token), userDataObserver)
    }

    fun getMyId(id: String) {
        addDisposable(repository.getUser(id), entityObserver)
    }
}