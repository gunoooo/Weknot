package com.example.weknot_android.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.MainActivityBinding
import com.example.weknot_android.view.fragment.*
import com.example.weknot_android.widget.viewpager.MainPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity: BaseActivity<MainActivityBinding>() {

    private var PAGE_COUNT: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initTabLayout()

    }

    private fun initViewPager() {
        val pagerAdapter = MainPagerAdapter(supportFragmentManager)

        binding.viewPager.offscreenPageLimit = PAGE_COUNT
        binding.viewPager.adapter = pagerAdapter
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

    override fun layoutId(): Int {
        return R.layout.main_activity
    }
}