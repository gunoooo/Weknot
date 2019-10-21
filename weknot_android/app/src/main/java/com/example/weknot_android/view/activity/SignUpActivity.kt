package com.example.weknot_android.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BasePictureActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.model.user.FbUser
import com.example.weknot_android.viewmodel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : BasePictureActivity<SignUpActivityBinding, SignUpViewModel>() {

    override val TAG: String
        get() = this.javaClass.name

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

            goToAlbum.observe(this@SignUpActivity, Observer {
                tedPermission()
                goToAlbum()
            })

            goToCrop.observe(this@SignUpActivity, Observer {
                goToCropPage(viewModel.tempPictureUri.value, viewModel.pictureUri.value)
            })

            backMessageToast.observe(this@SignUpActivity, Observer {
                simpleToast(R.string.exist_message)
                startActivityWithFinish(MainActivity::class.java)
            })

            onErrorEvent.observe(this@SignUpActivity, Observer {
                simpleToast(it.message)
            })
        }
    }

    override fun requestNotOkEvent() {
        viewModel.deleteFile()
    }

    override fun pickNextEvent(data: Intent) {
        viewModel.savePickData(data)
        viewModel.cropImage()
    }

    override fun cropNextEvent() { }

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