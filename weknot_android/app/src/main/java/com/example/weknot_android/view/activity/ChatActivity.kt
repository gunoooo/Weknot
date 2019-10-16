package com.example.weknot_android.view.activity

import android.os.Bundle
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BaseActivity
import com.example.weknot_android.databinding.ChatActivityBinding
import com.example.weknot_android.viewmodel.ChatViewModel

class ChatActivity : BaseActivity<ChatActivityBinding, ChatViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.chat_activity
    }

    override fun getViewModel(): Class<ChatViewModel> {
        return ChatViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        viewModel.roomKey = intent.getStringExtra("key")
    }

}