package com.example.weknot_android.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<SignUpActivityBinding>() {
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        observeSignUpViewModel()

        clickEvent()
    }

    private fun initViewModel() {
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    private fun observeSignUpViewModel() {
        signUpViewModel!!.getErrorMessage().observe(this, Observer { message: String -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show() })
        signUpViewModel!!.getSuccessMessage().observe(this, Observer { message: String -> Toast.makeText(this, message, Toast.LENGTH_LONG).show() })
    }

    private fun clickEvent() {
        binding!!.signUpButton.setOnClickListener { v: View ->
            setRequest()
            signUpViewModel!!.signUp()
        }
    }

    private fun setRequest() {
        signUpViewModel!!.request.value = SignUpRequest(binding!!.idText.text.toString(), binding!!.pwText.text.toString(),
                binding!!.nameText.text.toString(), binding!!.birthText.text.toString(), binding!!.genderText.text.toString(),
                binding!!.phoneNumber.text.toString())
    }

    override fun layoutId(): Int {
        return layout.sign_up_activity
    }
}