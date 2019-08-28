package com.example.weknot_android.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weknot_android.R.layout
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.SocialFragmentBinding
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.viewmodel.SocialViewModel
import com.example.weknot_android.widget.recyclerview.adapter.SocialAdapter

class SocialFragment : BaseFragment<SocialFragmentBinding>() {
    private val socialViewModel: SocialViewModel = ViewModelProviders.of(this).get(SocialViewModel::class.java)!!

    private var receiveAdapter: SocialAdapter? = null
    private var friendAdapter: SocialAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()

        observeSocialViewModel()
    }

    private fun initData() {
        socialViewModel.friends
    }

    private fun observeSocialViewModel() {
        socialViewModel.getData().observe(this, Observer { friends: List<Friend>? ->
            for (friend in friends!!) {
                if (friend.friendPoint == 1) {
                    socialViewModel.receiveList.value!!.add(friend)
                } else if (friend.friendPoint == 2) {
                    socialViewModel.friendList.value!!.add(friend)
                }
            }
            receiveAdapter = SocialAdapter(context!!, socialViewModel.receiveList.value)
            friendAdapter = SocialAdapter(context!!, socialViewModel.friendList.value)
            setRecyclerView()
        })
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.receiveRecyclerview.adapter = receiveAdapter
        binding.friendRecyclerview.adapter = friendAdapter
        binding.receiveRecyclerview.layoutManager = linearLayoutManager
        binding.friendRecyclerview.layoutManager = linearLayoutManager
    }

    override fun layoutId(): Int {
        return layout.social_fragment
    }
}