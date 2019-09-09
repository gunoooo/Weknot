package com.example.weknot_android.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.SocialFragmentBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.viewmodel.SocialViewModel
import com.example.weknot_android.widget.recyclerview.adapter.SocialAdapter

class SocialFragment : BaseFragment<SocialFragmentBinding>() {
    private lateinit var socialViewModel: SocialViewModel

    private var receiveAdapter: SocialAdapter? = null
    private var friendAdapter: SocialAdapter? = null

    private val RECEIVE = 1
    private val FRIEND = 2

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observeSocialViewModel()
    }

    private fun init() {
        initViewModel()
        initData()
    }

    private fun observeSocialViewModel() {
        socialViewModel.getData().observe(this, Observer { friends: List<Friend> ->
            for (friend in friends) {
                if (friend.friendStatus == RECEIVE) {
                    socialViewModel.receiveList.add(friend)
                } else if (friend.friendStatus == FRIEND) {
                    socialViewModel.friendList.add(friend)
                }
            }
            receiveAdapter = SocialAdapter(context!!, socialViewModel.receiveList, RECEIVE)
            friendAdapter = SocialAdapter(context!!, socialViewModel.friendList, FRIEND)
            setRecyclerView()
        })
    }

    private fun initViewModel() {
        socialViewModel = ViewModelProviders.of(this).get(SocialViewModel::class.java)
    }

    private fun initData() {
        socialViewModel.friends
    }

    private fun setRecyclerView() {
        val receiveLayoutManager: LayoutManager = GridLayoutManager(context, 1)
        val friendLayoutManager: LayoutManager = GridLayoutManager(context, 1)
        binding.receiveRecyclerview.adapter = receiveAdapter
        binding.friendRecyclerview.adapter = friendAdapter
        binding.receiveRecyclerview.layoutManager = receiveLayoutManager
        binding.friendRecyclerview.layoutManager = friendLayoutManager
    }

    override fun layoutId(): Int {
        return layout.social_fragment
    }
}