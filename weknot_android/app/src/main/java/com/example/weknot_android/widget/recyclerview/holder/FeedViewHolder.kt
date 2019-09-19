package com.example.weknot_android.widget.recyclerview.holder

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseViewHolder
import com.example.weknot_android.databinding.FeedItemBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.widget.recyclerview.navigator.FeedItemNavigator
import com.example.weknot_android.widget.recyclerview.viewmodel.FeedItemViewModel

class FeedViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root), FeedItemNavigator {

    private val viewModel = FeedItemViewModel()

    override fun likeOnEvent() {
        binding.likeOnAnimation.startAnimation(animLikeOnFirst)
    }

    override fun likeOffEvent() {
        binding.likeOffAnimation.startAnimation(animLikeOnFirst)
    }

    fun bind(data: Feed) {
        viewModel.bind(data)
        viewModel.setNavigator(this)
        setUp()
        binding.viewModel = viewModel
    }

    private fun setUp() {
        setUpLikeOnListener()
        setUpLikeOffListener()
    }

    private val animLikeOnFirst : Animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.animation_like_on_first)
    private val animLikeOnSecond : Animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.animation_like_on_second)
    private val animLikeOffFirst : Animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.animation_like_off_first)
    private val animLikeOffSecond : Animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.animation_like_off_second)

    private fun setUpLikeOnListener() {
        animLikeOnFirst.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.likeOnAnimation.startAnimation(animLikeOnSecond)
            }
            override fun onAnimationStart(p0: Animation?) {
                binding.likeOffBtn.visibility = View.INVISIBLE
            }
        })
        animLikeOnSecond.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.likeOnBtn.visibility = View.VISIBLE
            }
            override fun onAnimationStart(p0: Animation?) {}
        })
    }

    private fun setUpLikeOffListener() {
        animLikeOffFirst.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.likeOffAnimation.startAnimation(animLikeOffSecond)
            }

            override fun onAnimationStart(p0: Animation?) {
                binding.likeOnBtn.visibility = View.INVISIBLE
            }
        })
        animLikeOffSecond.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.likeOffBtn.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {}
        })
    }
}