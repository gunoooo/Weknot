package com.example.weknot_android.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.repository.RoomRepository
import com.example.weknot_android.model.repository.TokenRepository
import com.example.weknot_android.model.sharedpreference.Token
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class BaseViewModel<DT, ET, CM> protected constructor(application: Application, protected var comm: CM) : AndroidViewModel(application!!) {
    private val disposable: CompositeDisposable
    private val tokenManager: TokenRepository
    protected val repository: RoomRepository

    protected val successMessage = MutableLiveData<String>()
    protected val errorMessage = MutableLiveData<String>()

    internal val data = MutableLiveData<DT>()
    internal val entity = MutableLiveData<ET>()

    fun getSuccessMessage(): LiveData<String> {
        return successMessage
    }
    fun getErrorMessage(): LiveData<String> {
        return errorMessage
    }
    fun getData(): LiveData<DT> {
        return data
    }
    fun getEntity(): LiveData<ET> {
        return entity
    }
    val token: Token
        get() = tokenManager.token!!

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        disposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as SingleObserver<Any>) as Disposable)
    }

    val baseObserver: DisposableSingleObserver<String>
        get() = object : DisposableSingleObserver<String>() {
            override fun onSuccess(s: String) {
                successMessage.value = s
            }

            override fun onError(e: Throwable) {
                errorMessage.value = e.message
            }
        }

    val dataObserver: DisposableSingleObserver<DT>
        get() = object : DisposableSingleObserver<DT>() {
            override fun onSuccess(t: DT) {
                data.value = t
            }

            override fun onError(e: Throwable) {
                errorMessage.value = e.message
            }
        }

    val entityObserver: DisposableSingleObserver<ET>
        get() = object : DisposableSingleObserver<ET>() {
            override fun onSuccess(t: ET) {
                entity.value = t
            }

            override fun onError(e: Throwable) {
                errorMessage.value = e.message
            }
        }

    init {
        disposable = CompositeDisposable()
        tokenManager = TokenRepository(application)
        repository = RoomRepository(application!!)
    }
}