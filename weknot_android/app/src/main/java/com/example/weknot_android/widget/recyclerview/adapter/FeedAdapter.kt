package com.example.weknot_android.widget.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.databinding.FeedItemBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.holder.FeedViewHolder
import com.example.weknot_android.widget.recyclerview.navigator.FeedItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.FeedItemViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.microedition.khronos.opengles.GL

class FeedAdapter : Adapter<FeedViewHolder>() {
    private lateinit var feeds: List<Feed>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.feed_item, parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(feeds[position])
    }

    fun updateList(feeds: List<Feed>?) {
        this.feeds = feeds!!
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return if(::feeds.isInitialized) feeds.size else 0
    }
}