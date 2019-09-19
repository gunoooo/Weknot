package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.ProfileActivityBinding
import com.example.weknot_android.view.navigator.ProfileNavigator
import com.example.weknot_android.viewmodel.ProfileViewModel

class ProfileActivity : BaseActivity<ProfileActivityBinding, ProfileViewModel>(), ProfileNavigator {

    override fun getLayoutId(): Int {
        return R.layout.profile_activity
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setUp()
    }

    private fun setUp() {
        viewModel.id.value = intent.getStringExtra("id")
        viewModel.getProfile()
    }
}