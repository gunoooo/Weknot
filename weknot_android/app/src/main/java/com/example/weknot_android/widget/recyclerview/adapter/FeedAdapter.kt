package com.example.weknot_android.widget.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.recyclerview.holder.FeedViewHolder
import java.text.SimpleDateFormat
import java.util.*
import javax.microedition.khronos.opengles.GL

class FeedAdapter(private val context: Context) : Adapter<FeedViewHolder>() {
    private lateinit var feeds: List<Feed>

    val animLikeOnFirst : Animation = AnimationUtils.loadAnimation(context, R.anim.animation_like_on_first)
    val animLikeOnSecond : Animation = AnimationUtils.loadAnimation(context, R.anim.animation_like_on_second)
    val animLikeOffFirst : Animation = AnimationUtils.loadAnimation(context, R.anim.animation_like_off_first)
    val animLikeOffSecond : Animation = AnimationUtils.loadAnimation(context, R.anim.animation_like_off_second)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(LayoutInflater.from(parent.context).inflate(layout.feed_item, parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        initView(holder,position)
        clickEvent(holder)
    }

    private fun clickEvent(holder: FeedViewHolder) {
        likeClick(holder)
    }

    private fun likeClick(holder: FeedViewHolder) {
        holder.binding.likeOffBtn.setOnClickListener {
            holder.binding.likeOffAnimation.startAnimation(animLikeOnFirst)
            setLikeOnEvent(holder)
        }

        holder.binding.likeOnBtn.setOnClickListener {
            holder.binding.likeOnAnimation.startAnimation(animLikeOffFirst)
            setLikeOffEvent(holder)
        }
    }

    private fun setLikeOnEvent(holder: FeedViewHolder) {
        animLikeOnFirst.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                holder.binding.likeOnAnimation.startAnimation(animLikeOnSecond)
            }

            override fun onAnimationStart(p0: Animation?) {
                holder.binding.likeOffBtn.visibility = View.INVISIBLE
            }
        })
        animLikeOnSecond.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                holder.binding.likeOnBtn.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {}
        })
    }

    private fun setLikeOffEvent(holder: FeedViewHolder) {
        animLikeOffFirst.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                holder.binding.likeOffAnimation.startAnimation(animLikeOffSecond)
            }

            override fun onAnimationStart(p0: Animation?) {
                holder.binding.likeOnBtn.visibility = View.INVISIBLE
            }
        })
        animLikeOffSecond.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                holder.binding.likeOffBtn.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {}
        })
    }

    private fun initView(holder: FeedViewHolder, position: Int) {
        holder.binding.writerName.text = feeds.get(position).name
        holder.binding.content.text = feeds.get(position).comment
        holder.binding.date.text = feeds.get(position).time.split("T")[0] + " " + feeds.get(position).time.split("T")[1].split(".")[0]

        Glide.with(context).load(Strings.MAIN_HOST + "/image/" + feeds.get(position).photo).into(holder.binding.profileImage)
        Glide.with(context).load(Strings.MAIN_HOST + "/image/" + feeds.get(position).picture).into(holder.binding.image)

//        holder.binding.likeLeftCount.text = feeds.get(position).likeCount.toString()
//        holder.binding.likeRightCount.text = feeds.get(position).likeCount.toString()
//
//        if (feeds.get(position).isLiked) {
//            holder.binding.likeOffBtn.visibility = View.INVISIBLE
//            holder.binding.likeOnBtn.visibility = View.VISIBLE
//        }
//        else {
//            holder.binding.likeOffBtn.visibility = View.VISIBLE
//            holder.binding.likeOnBtn.visibility = View.INVISIBLE
//        }
    }

    fun updateList(feeds: List<Feed>?) {
        this.feeds = feeds!!
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return feeds.size
    }
}