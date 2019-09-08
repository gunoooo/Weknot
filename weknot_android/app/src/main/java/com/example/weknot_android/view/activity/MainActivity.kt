package com.example.weknot_android.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.databinding.MainActivityBinding
import com.example.weknot_android.view.fragment.*
import com.example.weknot_android.widget.viewpager.MainPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity: BaseActivity<MainActivityBinding>() {
    private lateinit var baseViewModel: BaseViewModel<*,*,*>

    private var PAGE_COUNT: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppbar(R.string.feed_title)
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager() {
        val pagerAdapter = MainPagerAdapter(supportFragmentManager)

        binding.viewPager.offscreenPageLimit = PAGE_COUNT
        binding.viewPager.adapter = pagerAdapter

        setViewPagerListener()
    }

    private fun initTabLayout() {
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablayout))

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
        })
    }

    private fun setViewPagerListener() {
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> initAppbar(R.string.feed_title)
                    1 -> initAppbar(R.string.video_call_title)
                    2 -> initAppbar(R.string.open_chat_title)
                    3 -> initAppbar(R.string.social_title)
                    4 -> initAppbar(R.string.myinfo_title)
                }
            }
        })
    }

    private fun initAppbar(title: Int) {
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        appBarBinding.textView.setText(title)

        appbarClickEvent()
    }

    private fun appbarClickEvent() {
        appBarBinding.back.setOnClickListener {
            baseViewModel.token = ""
            startActivityWithFinish(LoginActivity::class.java)
        }

        appBarBinding.menu.setOnClickListener {
            // todo
        }
    }

    override fun layoutId(): Int {
        return R.layout.main_activity
    }
}