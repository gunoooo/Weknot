package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.view.navigator.SignUpNavigator
import com.example.weknot_android.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<SignUpActivityBinding, SignUpViewModel>(), SignUpNavigator {

    override fun getLayoutId(): Int {
        return R.layout.sign_up_activity
    }

    override fun getViewModel(): Class<SignUpViewModel> {
        return SignUpViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(this,throwable.message,Toast.LENGTH_SHORT).show()
    }

    override fun handleSuccess(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun openLoginActivity() {
        startActivityWithFinish(LoginActivity::class.java)
    }

    override fun signUp() {
        if (isEmpty()) {
            Toast.makeText(this,R.string.empty_message,Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.signUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.setNavigator(this)
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.value!!.id.isEmpty() || viewModel.request.value!!.pw.isEmpty() ||
                viewModel.request.value!!.birth.isEmpty() || viewModel.request.value!!.name.isEmpty() ||
                viewModel.request.value!!.gender.isEmpty() || viewModel.request.value!!.phoneNumber.isEmpty()
    }
}