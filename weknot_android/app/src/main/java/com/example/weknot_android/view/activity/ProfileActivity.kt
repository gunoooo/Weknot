package com.example.weknot_android.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.ProfileActivityBinding
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.viewmodel.FeedViewModel
import com.example.weknot_android.viewmodel.UserViewModel
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class ProfileActivity : BaseActivity<ProfileActivityBinding>() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var feedViewModel: FeedViewModel

    private var feedAdapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeViewModel()
    }

    private fun init() {
        initAppbar()
        initViewModel()
        initData()
    }

    private fun observeViewModel() {
        observeUserViewModel()
        observeFeedViewModel()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    private fun initData() {
        userViewModel.id.value = intent.getStringExtra("id")
        userViewModel.getProfile()
    }

    private fun initAppbar() {
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        appBarBinding.textView.setText(R.string.feed_title)
        appBarBinding.menu.visibility = View.INVISIBLE

        appbarClickEvent()
    }

    private fun observeUserViewModel() {
        userViewModel.getData().observe(this, Observer { profile: Profile ->
            setView(profile)
        })
    }

    private fun observeFeedViewModel() {
        feedViewModel.getSuccessMessage().observe(this, Observer { message: String ->  })
    }

    private fun setView(profile: Profile) {
        dataIntoView(profile)
        dataIntoAppbar(profile.userId)
    }

    private fun dataIntoView(profile: Profile) {
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
        feedAdapter = FeedAdapter(this, profile.userFeeds!!)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.feedRecyclerview.adapter = feedAdapter
        binding.feedRecyclerview.layoutManager = linearLayoutManager
    }

    private fun dataIntoAppbar(id: String) {
        appBarBinding.textView.text = id
    }

    private fun appbarClickEvent() {
        appBarBinding.back.setOnClickListener {
            startActivityWithFinish(LoginActivity::class.java)
        }
    }

    override fun layoutId(): Int {
        return R.layout.profile_activity
    }
}