package com.example.weknot_android.widget.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.R
import com.example.weknot_android.model.entity.OpenChat.Chat
import com.example.weknot_android.widget.recyclerview.holder.MessageViewHolder
import com.google.firebase.database.*

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {
    private lateinit var chats: ArrayList<Chat>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    fun updateList(chats: ArrayList<Chat>) {
        this.chats = chats
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if(::chats.isInitialized) chats.size else 0
    }
}