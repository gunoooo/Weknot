package com.example.weknot_android.widget.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.weknot_android.R.layout
import com.example.weknot_android.databinding.SocialItemBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.view.fragment.SocialFragment
import com.example.weknot_android.widget.recyclerview.holder.SocialViewHolder

class SocialAdapter(private val context: Context, private val friends: List<Friend>, private val friendStatus: Int) : Adapter<SocialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder(LayoutInflater.from(parent.context).inflate(layout.social_item, parent, false))
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        var friend: Friend = friends.get(position)

        initView(holder.binding, friend)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(binding: SocialItemBinding, friend: Friend) {
        binding.name.text = friend.friendName
        binding.point.text = Integer.toString(friend.friendPoint) + "점"
        if (friend.friendPicture == null) {
            // todo
        }
        else {
            Glide.with(context).load(friend.friendPicture).into(binding.profileImage)
        }
        if (friendStatus == 1) {
            binding.acceptBtn.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

}