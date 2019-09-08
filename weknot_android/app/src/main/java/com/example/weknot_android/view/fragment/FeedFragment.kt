package com.example.weknot_android.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
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
import com.example.weknot_android.viewmodel.FeedViewModel
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    private lateinit var feedViewModel: FeedViewModel

    private var isOpenWriteBtn : Boolean = true

    private lateinit var animAddShow : Animation
    private lateinit var animAddHide : Animation
    private var feedAdapter: FeedAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initData()

        observeFeedViewModel()

        clickEvent()
        scrollEvent()
    }

    private fun observeFeedViewModel() {
        feedViewModel.getData().observe(this, Observer { feeds: List<Feed> ->
            feedAdapter = FeedAdapter(context!!, feeds)
            setRecyclerView()
        })

        feedViewModel.getErrorMessage().observe(this, Observer { message: String -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show() })
    }

    private fun initData() {
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)

        feedViewModel.getFeeds()
    }

    private fun clickEvent() {
        binding.writeBtn.setOnClickListener {
            startActivity(Intent(context, FeedWriteActivity::class.java))
            activity!!.finish()
        }
    }

    private fun scrollEvent() {
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

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.feedRecyclerview.adapter = feedAdapter
        binding.feedRecyclerview.layoutManager = linearLayoutManager
    }

    private fun initViewModel() {
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.feed_fragment
    }
}