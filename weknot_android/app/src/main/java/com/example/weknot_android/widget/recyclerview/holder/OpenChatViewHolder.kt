package com.example.weknot_android.widget.recyclerview.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weknot_android.BR
import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.OpenChatItemBinding
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.viewmodel.OpenChatViewModel
import com.example.weknot_android.widget.recyclerview.viewmodel.OpenChatItemViewModel

class OpenChatViewHolder(val binding: OpenChatItemBinding) : BaseViewHolder<Any>(binding.root) {

    private val viewModel = OpenChatItemViewModel()

    fun bind(data: OpenChatRoom) {
        viewModel.bind(data)
        binding.viewModel = viewModel
    }
}