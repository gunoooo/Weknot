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


class OpenChatAdapter(private val context: Context, private val openChatRooms: List<OpenChatRoom>) : Adapter<OpenChatViewHolder>() {

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
        when (roomType) {
            "free" -> return R.drawable.ic_room_type_free
            "game" -> return R.drawable.ic_room_type_game
            "worry" -> return R.drawable.ic_room_type_worry
            "secret" -> return R.drawable.ic_room_type_secret
            else -> return null
        }
    }

    override fun getItemCount(): Int {
        return openChatRooms.size
    }

}