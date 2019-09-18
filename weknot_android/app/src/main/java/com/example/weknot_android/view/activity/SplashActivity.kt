package com.example.weknot_android.view.activity

import android.os.Bundle
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.SplashActivityBinding
import com.example.weknot_android.view.navigator.SplashNavigator
import com.example.weknot_android.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>(), SplashNavigator {

    override fun getLayoutId(): Int {
        return R.layout.splash_activity
    }

    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        startActivityWithFinish(LoginActivity::class.java)
    }

    override fun openMainActivity() {
        startActivityWithFinish(MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        setUp()
    }

    private fun setUp() {
        if (viewModel.token == "") {
            startActivityWithFinish(LoginActivity::class.java)
        }
        else {
            viewModel.autoLogin()
        }
    }
}