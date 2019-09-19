package com.example.weknot_android.widget.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.databinding.SocialItemBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.view.fragment.SocialFragment
import com.example.weknot_android.widget.recyclerview.holder.SocialViewHolder

class SocialAdapter : Adapter<SocialViewHolder>() {
    private lateinit var friends: List<Friend>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.social_item, parent, false))
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    fun updateList(friends: List<Friend>) {
        this.friends = friends
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if(::friends.isInitialized) friends.size else 0
    }

}