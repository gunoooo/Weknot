package com.example.weknot_android.widget.recyclerview.holder

import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.SocialItemBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.widget.recyclerview.navigator.social.SocialAdapterNavigator
import com.example.weknot_android.widget.recyclerview.navigator.social.SocialItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.SocialItemViewModel

class SocialViewHolder(val binding: SocialItemBinding) : BaseViewHolder<SocialAdapterNavigator>(binding.root), SocialItemNavigator {

    private val viewModel = SocialItemViewModel()

    private lateinit var friend: Friend

    fun bind(data: Friend) {
        friend = data
        viewModel.bind(data)
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }

    override fun acceptFriend() {
        getNavigator().checkFriend("yes", friend)
    }

    override fun rejectFriend() {
        getNavigator().checkFriend("no" , friend)
    }

    override fun openProfile() {
        getNavigator().openProfile(friend.friendId)
    }
}