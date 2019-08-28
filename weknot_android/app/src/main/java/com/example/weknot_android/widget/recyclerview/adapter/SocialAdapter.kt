package com.example.weknot_android.widget.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.weknot_android.R.layout
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.widget.recyclerview.holder.SocialViewHolder

class SocialAdapter(private val context: Context, private val friends: List<Friend>?) : Adapter<SocialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder(LayoutInflater.from(parent.context).inflate(layout.social_item, parent, false))
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return friends!!.size
    }

}