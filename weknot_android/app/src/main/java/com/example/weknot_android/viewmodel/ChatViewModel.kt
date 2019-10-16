package com.example.weknot_android.viewmodel

import android.app.Application
import android.util.Log
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.OpenChat.Chat
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.widget.SingleLiveEvent
import com.example.weknot_android.widget.recyclerview.adapter.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatViewModel(application: Application) : BaseViewModel<Any>(application) {

    var roomKey: String? = null

    val messageAdapter: MessageAdapter = MessageAdapter()

    val chat = Chat()
    private val chats: ArrayList<Chat> = ArrayList()
    private lateinit var databaseReference: DatabaseReference
    private lateinit var singleValueEventListener: ValueEventListener
    private lateinit var valueEventListener: ValueEventListener

    val sentEvent = SingleLiveEvent<Unit>()
    val receivedEvent = SingleLiveEvent<ArrayList<Chat>>()

    fun getChatting() {
        setDataBase()
        setListener()
        addListener()
    }

    private fun setDataBase() {
        databaseReference = FirebaseDatabase.getInstance().reference
                .child("groupchatrooms")
                .child(roomKey!!)
                .child("message")
    }

    private fun setListener() {
        singleValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (item in dataSnapshot.children) {
                    chats.add(item.getValue(Chat::class.java)!!)
                }

                messageAdapter.updateList(chats)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                chats.clear()

                for (item in dataSnapshot.children) {
                    chats.add(item.getValue(Chat::class.java)!!)
                }

                receivedEvent.value = chats
            }

            override fun onCancelled(databaseError: DatabaseError) { }
        }
    }

    private fun addListener() {
        databaseReference.addListenerForSingleValueEvent(singleValueEventListener)
        databaseReference.addValueEventListener(valueEventListener)
    }

    private fun setChat() {
        val simpleDateFormat = SimpleDateFormat("E hh:mm", Locale.KOREA)

        chat.uid = FirebaseAuth.getInstance().currentUser!!.uid
        chat.timeStamp = simpleDateFormat.format(Date())
        setChatWriter()
    }

    private fun setChatWriter() {
        addDisposable(repository.getUser(userId), object : DisposableSingleObserver<User>() {
            override fun onSuccess(user: User) {
                chat.writer = user.name
                sendMessage()
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }

    private fun sendMessage() {
        FirebaseDatabase.getInstance().reference
                .child("groupchatrooms")
                .child(roomKey!!)
                .child("message").push().setValue(chat)
                .addOnCompleteListener {
                    sentEvent.call()
                }
    }

    fun onClickSend() {
        setChat()
    }

    override fun onRetrieveDataSuccess(data: Any) { }

    override fun onRetrieveBaseSuccess(message: String) { }
}