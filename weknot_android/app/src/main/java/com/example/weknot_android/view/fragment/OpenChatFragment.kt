package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.R
import com.example.weknot_android.base.fragment.BaseFragment
import com.example.weknot_android.base.fragment.BaseListFragment
import com.example.weknot_android.databinding.OpenChatFragmentBinding
import com.example.weknot_android.view.navigator.OpenChatNavigator
import com.example.weknot_android.viewmodel.OpenChatViewModel

class OpenChatFragment : BaseListFragment<OpenChatFragmentBinding, OpenChatViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.open_chat_fragment
    }

    override fun getViewModel(): Class<OpenChatViewModel> {
        return OpenChatViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    override fun btnShow() {
        binding.createBtn.startAnimation(animAddShow)
        binding.createBtn.visibility = View.VISIBLE
    }

    override fun btnHide() {
        binding.createBtn.startAnimation(animAddHide)
        binding.createBtn.visibility = View.INVISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding.chatRoomRecyclerview.addOnScrollListener(scrollListener)

        viewModel.getChattingRooms()
    }
}