package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.LoginActivityBinding
import com.example.weknot_android.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.login_activity
    }

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {
            openMain.observe(this@LoginActivity, Observer {
                startActivityWithFinish(LoginActivity::class.java)
            })

            openSignUp.observe(this@LoginActivity, Observer {
                startActivityWithFinish(SignUpActivity::class.java)
            })

            loginEvent.observe(this@LoginActivity, Observer {
                if (isEmpty()) {
                    Toast.makeText(this@LoginActivity, R.string.empty_message, Toast.LENGTH_SHORT).show()
                    return@Observer
                }
                viewModel.login()
            })
        }
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.id!!.isEmpty() || viewModel.request.password!!.isEmpty()
    }
}