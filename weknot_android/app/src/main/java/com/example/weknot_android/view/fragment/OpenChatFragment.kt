package com.example.weknot_android.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.OpenChatFragmentBinding
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.viewmodel.OpenChatViewModel
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter

class OpenChatFragment : BaseFragment<OpenChatFragmentBinding>() {
    private lateinit var openChatViewModel: OpenChatViewModel

    private var openChatAdapter: OpenChatAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initData()

        observeOpenChatViewModel()
    }

    private fun observeOpenChatViewModel() {
        openChatViewModel.getData().observe(this, Observer<List<OpenChatRoom>?> { openChatRooms: List<OpenChatRoom>? ->
            openChatAdapter = OpenChatAdapter(context!!, openChatRooms)
            setRecyclerView()
        })
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.chatRoomRecyclerview.adapter = openChatAdapter
        binding.chatRoomRecyclerview.layoutManager = linearLayoutManager
    }


    private fun initData() {
        openChatViewModel.chattingRooms
    }

    private fun initViewModel() {
        openChatViewModel = ViewModelProviders.of(this).get(OpenChatViewModel::class.java)
    }

    override fun layoutId(): Int {
        return layout.open_chat_fragment
    }
}