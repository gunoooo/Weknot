package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.OpenChatFragmentBinding
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.view.navigator.OpenChatNavigator
import com.example.weknot_android.viewmodel.OpenChatViewModel
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter

class OpenChatFragment : BaseFragment<OpenChatFragmentBinding, OpenChatViewModel>(), OpenChatNavigator {

    private var isOpenWriteBtn : Boolean = true

    private lateinit var animAddShow : Animation
    private lateinit var animAddHide : Animation

    override fun getLayoutId(): Int {
        return R.layout.open_chat_fragment
    }

    override fun getViewModel(): Class<OpenChatViewModel> {
        return OpenChatViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(context,throwable.message,Toast.LENGTH_SHORT).show()
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
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)

        setScrollListener()

        viewModel.getChattingRooms()
    }

    private fun setScrollListener() {
        binding.chatRoomRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 20) {
                    if (isOpenWriteBtn) {
                        binding.createBtn.startAnimation(animAddHide)
                        binding.createBtn.visibility = View.INVISIBLE
                        isOpenWriteBtn = false
                    }
                }
                else if (dy < -20) {
                    if (!isOpenWriteBtn) {
                        binding.createBtn.startAnimation(animAddShow)
                        binding.createBtn.visibility = View.VISIBLE
                        isOpenWriteBtn = true
                    }
                }
            }
        })
    }
}