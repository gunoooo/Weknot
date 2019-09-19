package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.weknot_android.R
import com.example.weknot_android.base.fragment.BaseFragment
import com.example.weknot_android.base.fragment.BaseListFragment
import com.example.weknot_android.databinding.FeedFragmentBinding
import com.example.weknot_android.view.activity.FeedWriteActivity
import com.example.weknot_android.view.navigator.FeedNavigator
import com.example.weknot_android.viewmodel.FeedViewModel

class FeedFragment : BaseListFragment<FeedFragmentBinding, FeedViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.feed_fragment
    }

    override fun getViewModel(): Class<FeedViewModel> {
        return FeedViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {
            openFeedWrite.observe(this@FeedFragment, Observer {
                startActivityWithFinish(FeedWriteActivity::class.java)
            })
        }
    }

    override fun btnShow() {
        binding.writeBtn.startAnimation(animAddShow)
        binding.writeBtn.visibility = View.VISIBLE
    }

    override fun btnHide() {
        binding.writeBtn.startAnimation(animAddHide)
        binding.writeBtn.visibility = View.INVISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding.feedRecyclerview.addOnScrollListener(scrollListener)

        viewModel.getFeeds()
    }
}