package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.fragment.BaseFragment
import com.example.weknot_android.databinding.MyinfoFragmentBinding
import com.example.weknot_android.view.navigator.ProfileNavigator
import com.example.weknot_android.viewmodel.MyinfoViewModel

class MyinfoFragment : BaseFragment<MyinfoFragmentBinding, MyinfoViewModel>(), ProfileNavigator {

    override fun getLayoutId(): Int {
        return R.layout.myinfo_fragment
    }

    override fun getViewModel(): Class<MyinfoViewModel> {
        return MyinfoViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        viewModel.id.value = viewModel.getMyId()
        viewModel.getProfile()
    }
}