package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.LoginActivityBinding
import com.example.weknot_android.view.navigator.LoginNavigator
import com.example.weknot_android.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>(), LoginNavigator {

    override fun getLayoutId(): Int {
        return R.layout.login_activity
    }

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun login() {
        if (isEmpty()) {
            Toast.makeText(this, R.string.empty_message, Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.login()
    }

    override fun openSignUpActivity() {
        startActivityWithFinish(SignUpActivity::class.java)
    }

    override fun openMainActivity() {
        startActivityWithFinish(MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.id!!.isEmpty() || viewModel.request.password!!.isEmpty()
    }
}