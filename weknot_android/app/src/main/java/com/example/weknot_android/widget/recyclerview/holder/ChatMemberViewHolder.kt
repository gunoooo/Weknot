package com.example.weknot_android.widget.recyclerview.holder

import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.ChatMemberItemBinding
import com.example.weknot_android.model.user.FbUser
import com.example.weknot_android.widget.recyclerview.navigator.chatmember.ChatMemberAdapterNavigator
import com.example.weknot_android.widget.recyclerview.navigator.chatmember.ChatMemberItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.ChatMemberItemViewModel

class ChatMemberViewHolder(val binding: ChatMemberItemBinding) : BaseViewHolder<ChatMemberAdapterNavigator>(binding.root), ChatMemberItemNavigator {

    private val viewModel = ChatMemberItemViewModel()

    private lateinit var id: String

    fun bind(fbUser: FbUser) {
        viewModel.bind(fbUser)
        viewModel.setNavigator(this)
        id = fbUser.id!!
        binding.viewModel = viewModel
    }

    override fun openProfile() {
        getNavigator().openProfile(id)
    }
}