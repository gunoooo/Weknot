package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.R
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.OpenChatFragmentBinding
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.viewmodel.OpenChatViewModel
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter

class OpenChatFragment : BaseFragment<OpenChatFragmentBinding>() {
    private lateinit var openChatViewModel: OpenChatViewModel

    private var isOpenWriteBtn : Boolean = true

    private lateinit var animAddShow : Animation
    private lateinit var animAddHide : Animation

    private var openChatAdapter: OpenChatAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observeOpenChatViewModel()
        event()
    }

    private fun init() {
        initViewModel()
        initData()
    }

    private fun observeOpenChatViewModel() {
        openChatViewModel.getData().observe(this, Observer<List<OpenChatRoom>> { openChatRooms: List<OpenChatRoom> ->
            openChatAdapter = OpenChatAdapter(context!!, openChatRooms)
            setRecyclerView()
        })
    }

    private fun event() {
        clickEvent()
        scrollEvent()
    }

    private fun initViewModel() {
        openChatViewModel = ViewModelProviders.of(this).get(OpenChatViewModel::class.java)
    }

    private fun initData() {
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)

        openChatViewModel.getChattingRooms()
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.chatRoomRecyclerview.adapter = openChatAdapter
        binding.chatRoomRecyclerview.layoutManager = linearLayoutManager
    }

    private fun clickEvent() {
        binding.createBtn.setOnClickListener {  }
    }

    private fun scrollEvent() {
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

    override fun layoutId(): Int {
        return layout.open_chat_fragment
    }
}