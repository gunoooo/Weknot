package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<SignUpActivityBinding, SignUpViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.sign_up_activity
    }

    override fun getViewModel(): Class<SignUpViewModel> {
        return SignUpViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {
            onSuccessEvent.observe(this@SignUpActivity, Observer {
                simpleToast(it)
            })

            signUpEvent.observe(this@SignUpActivity, Observer {
                if (isEmpty()) {
                    simpleToast(R.string.empty_message)
                    return@Observer
                }
                viewModel.signUp()
            })

            openLogin.observe(this@SignUpActivity, Observer {
                startActivityWithFinish(LoginActivity::class.java)
            })

            onErrorEvent.observe(this@SignUpActivity, Observer {
                simpleToast(it.message)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.value!!.id.isEmpty() || viewModel.request.value!!.pw.isEmpty() ||
                viewModel.request.value!!.birth.isEmpty() || viewModel.request.value!!.name.isEmpty() ||
                viewModel.request.value!!.gender.isEmpty() || viewModel.request.value!!.phoneNumber.isEmpty()
    }
}