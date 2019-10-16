package com.example.weknot_android.widget.recyclerview.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weknot_android.BR
import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.OpenChatItemBinding
import com.example.weknot_android.model.entity.OpenChat.ChatRoom
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.viewmodel.OpenChatViewModel
import com.example.weknot_android.widget.recyclerview.navigator.openchat.OpenChatAdapterNavigator
import com.example.weknot_android.widget.recyclerview.navigator.openchat.OpenChatItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.OpenChatItemViewModel

class OpenChatViewHolder(val binding: OpenChatItemBinding) : BaseViewHolder<OpenChatAdapterNavigator>(binding.root), OpenChatItemNavigator {

    private val viewModel = OpenChatItemViewModel()

    private lateinit var key: String

    fun bind(chatRoom: ChatRoom, key: String) {
        viewModel.bind(chatRoom)
        viewModel.setNavigator(this)
        this.key = key
        binding.viewModel = viewModel
    }

    override fun openChatRoom() {
        getNavigator().openChatRoom(key)
    }
}