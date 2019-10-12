package com.example.weknot_android.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.ProfileActivityBinding
import com.example.weknot_android.viewmodel.ProfileViewModel

class ProfileActivity : BaseActivity<ProfileActivityBinding, ProfileViewModel>() {

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
            onErrorEvent.observe(this@ProfileActivity, Observer {
                simpleToast(it.message)
            })

            onRefreshEvent.observe(this@ProfileActivity, Observer {
                setUp()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setUp()
    }

    private fun setUp() {
        viewModel.id.value = intent.getStringExtra("id")
        viewModel.setUp()
    }
}