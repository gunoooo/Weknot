package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.network.request.SignUpRequest
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
            Toast.makeText(this,"빈 칸 없이 입력해 주세요.",Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.signUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.value!!.id.isEmpty() || viewModel.request.value!!.pw.isEmpty() ||
                viewModel.request.value!!.birth.isEmpty() || viewModel.request.value!!.name.isEmpty() ||
                viewModel.request.value!!.gender.isEmpty() || viewModel.request.value!!.phoneNumber.isEmpty()
    }
}