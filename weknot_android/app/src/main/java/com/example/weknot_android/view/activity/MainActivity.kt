package com.example.weknot_android.view.activity

import android.os.Bundle
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.MainActivityBinding

class MainActivity : BaseActivity<MainActivityBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int {
        return layout.main_activity
    }
}