package com.example.weknot_android.widget.recyclerview.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weknot_android.BR
import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.SocialItemBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.widget.recyclerview.navigator.SocialItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.SocialItemViewModel

class SocialViewHolder(val binding: SocialItemBinding) : BaseViewHolder<Friend, SocialItemViewModel>(binding.root), SocialItemNavigator {

    override fun acceptFriend() {
        // todo
    }

    override fun bind(data: Friend) {
        viewModel = SocialItemViewModel()
        viewModel.bind(data)
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }
}