package com.example.weknot_android.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.LoginActivityBinding
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.response.data.LoginData
import com.example.weknot_android.viewmodel.LoginViewModel
import com.example.weknot_android.viewmodel.UserViewModel

class LoginActivity : BaseActivity<LoginActivityBinding>() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeLoginViewModel()
        clickEvent()
    }

    private fun init() {
        initViewModel()
        initView()
    }

    private fun observeLoginViewModel() {
        loginViewModel.getErrorMessage().observe(this, Observer { message: String -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show() })

        loginViewModel.getData().observe(this, Observer { loginData: LoginData ->
            loginSuccessEvent(loginData)
        })
    }

    private fun clickEvent() {
        binding.loginBtn.setOnClickListener {
            setRequest()
            loginViewModel.login()
        }

        binding.signUpButton.setOnClickListener { startActivity(SignUpActivity::class.java) }
    }


    private fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun initView() {
        binding.materialCardView.setBackgroundResource(R.drawable.background_login)
    }
    private fun loginSuccessEvent(loginData: LoginData) {
        loginViewModel.insertLoginData(loginData)
        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
        startActivityWithFinish(MainActivity::class.java)
    }

    private fun setRequest() {
        loginViewModel.request.value = LoginRequest(binding.idText.text.toString(), binding.pwText.text.toString())
    }

    override fun layoutId(): Int {
        return layout.login_activity
    }
}