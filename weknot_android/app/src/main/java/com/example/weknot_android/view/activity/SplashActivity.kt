package com.example.weknot_android.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.SplashActivityBinding
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<SplashActivityBinding>() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeSplashViewModel()
    }

    private fun init() {
        initViewModel()
        initData()
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    private fun initData() {
        if (splashViewModel.token == "") {
            startActivityWithFinish(LoginActivity::class.java)
        }
        else {
            splashViewModel.autoLogin()
        }
    }

    private fun observeSplashViewModel() {
        splashViewModel.getData().observe(this, Observer { user: User ->
            splashViewModel.insertUserId(user.id)
            startActivityWithFinish(MainActivity::class.java)
        })

        splashViewModel.getErrorMessage().observe(this, Observer { startActivityWithFinish(LoginActivity::class.java) })
    }

    override fun layoutId(): Int {
        return layout.splash_activity
    }
}