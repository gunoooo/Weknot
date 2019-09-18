package com.example.weknot_android.widget.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.databinding.OpenChatItemBinding
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.widget.recyclerview.holder.OpenChatViewHolder


class OpenChatAdapter(private val context: Context) : Adapter<OpenChatViewHolder>() {
    private lateinit var openChatRooms: List<OpenChatRoom>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenChatViewHolder {
        return OpenChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.open_chat_item, parent, false))
    }

    override fun onBindViewHolder(holder: OpenChatViewHolder, position: Int) {
        var openChatRoom: OpenChatRoom = openChatRooms.get(position)

        initView(holder.binding, openChatRoom)
    }

    private fun initView(binding: OpenChatItemBinding, openChatRoom: OpenChatRoom) {
        binding.roomName.text = openChatRoom.roomName
        binding.roomNumber.text = openChatRoom.roomNumber
        binding.roomMaster.text = openChatRoom.masterName
        Glide.with(context).load(initRoomTypeDrawable(openChatRoom.roomType)).into(binding.roomType)
    }

    private fun initRoomTypeDrawable(roomType: String) : Int? {
        return when (roomType) {
            "free" -> R.drawable.ic_room_type_free
            "game" -> R.drawable.ic_room_type_game
            "worry" -> R.drawable.ic_room_type_worry
            "secret" -> R.drawable.ic_room_type_secret
            else -> null
        }
    }

    fun updateList(openChatRooms: List<OpenChatRoom>) {
        this.openChatRooms = openChatRooms
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if(::openChatRooms.isInitialized) openChatRooms.size else 0
    }

}