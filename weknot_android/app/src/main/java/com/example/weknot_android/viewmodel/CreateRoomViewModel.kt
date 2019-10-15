package com.example.weknot_android.viewmodel

import android.app.Application
import android.view.View
import android.widget.AdapterView
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.OpenChat.ChatRoom
import com.example.weknot_android.model.entity.user.FbUser
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.widget.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.observers.DisposableSingleObserver


class CreateRoomViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val fbUser = FbUser()
    val chatRoom = ChatRoom()
    private var chatRoomUid: String? = null

    val createEvent = SingleLiveEvent<Unit>()

    fun onClickCreate() {
        createEvent.call()
    }

    fun onSelectItem(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {

    }

    fun getUser() {
        addDisposable(repository.getUser(userId), object : DisposableSingleObserver<User>() {
            override fun onSuccess(user: User) {
                onRetrieveUserSuccess(user)
            }

            override fun onError(e: Throwable) {
                onErrorEvent.call()
            }
        })
    }

    private fun setChatRoom(user: User) {
        chatRoom.masterName = user.name
        chatRoom.roomNumber = 10
    }

    private fun setFbUser(user: User) {
        fbUser.name = user.name
        fbUser.uid = FirebaseAuth.getInstance().currentUser!!.uid
    }

    private fun insertFirebase() {
        chatRoomUid = FirebaseDatabase.getInstance().reference.child("groupchatrooms").push().key!!

        FirebaseDatabase.getInstance().reference.child("groupchatrooms").child(chatRoomUid!!).setValue(chatRoom)
        FirebaseDatabase.getInstance().reference.child("groupchatrooms").child(chatRoomUid!!).child("users").push().setValue(fbUser)
    }

    private fun onRetrieveUserSuccess(user: User) {
        setChatRoom(user)
        setFbUser(user)
        insertFirebase()
    }

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}