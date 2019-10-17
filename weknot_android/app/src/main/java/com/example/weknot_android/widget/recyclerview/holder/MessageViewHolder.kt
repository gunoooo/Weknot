package com.example.weknot_android.widget.recyclerview.holder

import android.graphics.Color
import android.view.Gravity
import android.view.View
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.MessageItemBinding
import com.example.weknot_android.model.entity.OpenChat.Chat
import com.example.weknot_android.widget.recyclerview.viewmodel.MessageItemViewModel
import com.google.firebase.auth.FirebaseAuth

class MessageViewHolder(val binding: MessageItemBinding) : BaseViewHolder<Any>(binding.root) {

    private val viewModel = MessageItemViewModel()

    fun bind(chat: Chat) {
        viewModel.bind(chat)
        binding.viewModel = viewModel

        if (chat.writer!!.uid!! == FirebaseAuth.getInstance().currentUser!!.uid) {
            binding.message.setBackgroundResource(R.drawable.background_send_message)
            binding.messageItemLinearlayoutMain.gravity = Gravity.END
            binding.writer.visibility = View.GONE
            binding.photo.visibility = View.GONE
            binding.receiveTime.visibility = View.GONE
            binding.sendTime.visibility = View.VISIBLE
        }
        else {
            binding.message.setTextColor(Color.WHITE)
            binding.message.setBackgroundResource(R.drawable.background_receive_message)
            binding.messageItemLinearlayoutMain.gravity = Gravity.START
            binding.writer.visibility = View.VISIBLE
            binding.photo.visibility = View.VISIBLE
            binding.receiveTime.visibility = View.VISIBLE
            binding.sendTime.visibility = View.GONE
        }
    }
}