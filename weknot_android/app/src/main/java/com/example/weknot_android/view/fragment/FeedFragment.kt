package com.example.weknot_android.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.FeedFragmentBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.view.activity.FeedWriteActivity
import com.example.weknot_android.view.navigator.FeedNavigator
import com.example.weknot_android.viewmodel.FeedViewModel
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class FeedFragment : BaseFragment<FeedFragmentBinding, FeedViewModel>(), FeedNavigator {

    private var isOpenWriteBtn : Boolean = true

    private lateinit var animAddShow : Animation
    private lateinit var animAddHide : Animation

    override fun getLayoutId(): Int {
        return R.layout.feed_fragment
    }

    override fun getViewModel(): Class<FeedViewModel> {
        return FeedViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(context,throwable.message,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)

        viewModel.getFeeds()

        setScrollListener()
    }

    private fun setScrollListener() {
        binding.feedRecyclerview.addOnScrollListener(object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 10) {
                    if (isOpenWriteBtn) {
                        binding.writeBtn.startAnimation(animAddHide)
                        binding.writeBtn.visibility = View.INVISIBLE
                        isOpenWriteBtn = false
                    }
                }
                else if (dy < -10) {
                    if (!isOpenWriteBtn) {
                        binding.writeBtn.startAnimation(animAddShow)
                        binding.writeBtn.visibility = View.VISIBLE
                        isOpenWriteBtn = true
                    }
                }
            }
        })
    }
}