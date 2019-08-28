package com.example.weknot_android.widget.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.weknot_android.R.layout
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.widget.recyclerview.holder.OpenChatViewHolder

class OpenChatAdapter(private val context: Context, private val openChatRooms: List<OpenChatRoom>?) : Adapter<OpenChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenChatViewHolder {
        return OpenChatViewHolder(LayoutInflater.from(parent.context).inflate(layout.open_chat_item, parent, false))
    }

    override fun onBindViewHolder(holder: OpenChatViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return openChatRooms!!.size
    }

}