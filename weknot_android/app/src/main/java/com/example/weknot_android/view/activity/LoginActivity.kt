package com.example.weknot_android.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.LoginActivityBinding
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.response.data.LoginData
import com.example.weknot_android.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<LoginActivityBinding>() {
    private val loginViewModel: LoginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLoginViewModel()

        clickEvent()
    }

    private fun observeLoginViewModel() {
        loginViewModel.getErrorMessage().observe(this, Observer { message: String -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show() })

        loginViewModel.getData().observe(this, Observer { loginData: LoginData ->
            loginViewModel.setToken(loginData.token)
            loginViewModel.insertUser(loginData.user)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    private fun clickEvent() {
        binding.loginBtn.setOnClickListener { v: View ->
            setRequest()
            loginViewModel.login()
        }
    }

    private fun setRequest() {
        loginViewModel.request.value = LoginRequest(binding.idText.text.toString(), binding.pwText.text.toString())
    }

    override fun layoutId(): Int {
        return layout.login_activity
    }
}