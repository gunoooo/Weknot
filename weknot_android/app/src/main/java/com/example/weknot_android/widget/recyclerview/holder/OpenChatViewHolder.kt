package com.example.weknot_android.widget.recyclerview.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weknot_android.databinding.OpenChatItemBinding

class OpenChatViewHolder(itemView: View) : ViewHolder(itemView) {
    val binding: OpenChatItemBinding = DataBindingUtil.bind(itemView)!!
}