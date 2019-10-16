package com.example.weknot_android.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.base.activity.BasePictureActivity
import com.example.weknot_android.databinding.SignUpActivityBinding
import com.example.weknot_android.model.entity.user.FbUser
import com.example.weknot_android.viewmodel.SignUpViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SignUpActivity : BasePictureActivity<SignUpActivityBinding, SignUpViewModel>() {

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

            fbSignUpEvent.observe(this@SignUpActivity, Observer {
                fbSignUp()
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

    private fun fbSignUp() {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword("111" + "@weknot.com", "111" + "111111")
                .addOnCompleteListener(this@SignUpActivity) { task ->
                    val uid: String = task.result!!.user!!.uid

                    val user = FbUser()
                    user.uid = FirebaseAuth.getInstance().currentUser!!.uid
                    user.name = viewModel.request.value!!.name
                    FirebaseDatabase.getInstance().reference.child("users").child(uid).setValue(user)
                }

        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword("222" + "@weknot.com", "222" + "111111")
                .addOnCompleteListener(this@SignUpActivity) { task ->
                    val uid: String = task.result!!.user!!.uid

                    val user = FbUser()
                    user.uid = FirebaseAuth.getInstance().currentUser!!.uid
                    user.name = viewModel.request.value!!.name
                    FirebaseDatabase.getInstance().reference.child("users").child(uid).setValue(user)
                }
    }
}