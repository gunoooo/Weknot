package com.example.weknot_android.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.MyinfoFragmentBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.viewmodel.UserViewModel
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class MyinfoFragment : BaseFragment<MyinfoFragmentBinding>() {
    private lateinit var userViewModel: UserViewModel

    private var feedAdapter: FeedAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initData()

        observeUserViewModel()
    }

    private fun observeUserViewModel() {
        userViewModel.getData().observe(this, Observer { profile: Profile -> initView(profile) })
    }

    private fun initView(profile: Profile) {
        binding.intro.isSelected = true
        binding.birth.text = profile.userBirth
        binding.intro.text = profile.userIntro
        binding.name.text = profile.userName
        binding.point.text = profile.userPoint.toString()
        if (profile.userPicture == null) {
            //todo
        }
        else {
            Glide.with(this).load(profile.userPicture).into(binding.profileImage)
        }
        if (profile.userGender == "m") {
            Glide.with(this).load(R.drawable.man_icon).into(binding.gender)
        }
        else {
            Glide.with(this).load(R.drawable.woman_icon).into(binding.gender)
        }
        feedAdapter = FeedAdapter(context!!, profile.userFeeds!!)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.feedRecyclerview.adapter = feedAdapter
        binding.feedRecyclerview.layoutManager = linearLayoutManager
    }

    private fun initData() {
        userViewModel.getProfile(userViewModel.getMyId())
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.myinfo_fragment
    }
}