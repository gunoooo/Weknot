package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.fragment.BaseFragment
import com.example.weknot_android.databinding.SocialFragmentBinding
import com.example.weknot_android.view.navigator.SocialNavigator
import com.example.weknot_android.viewmodel.SocialViewModel

class SocialFragment : BaseFragment<SocialFragmentBinding, SocialViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.social_fragment
    }

    override fun getViewModel(): Class<SocialViewModel> {
        return SocialViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        viewModel.getFriends()
    }
}