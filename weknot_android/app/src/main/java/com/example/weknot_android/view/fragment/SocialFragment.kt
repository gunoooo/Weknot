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

    val RECEIVE = 1
    val FRIEND = 2

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initData()

        var friends: ArrayList<Friend> = ArrayList()
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 1))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 2))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 1))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",23, 2))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 2))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 1))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",23, 2))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 1))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 2))
        friends.add(Friend("rr","박건우","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/c2.0.952.952a/68531869_159856708527692_7427066346862542848_n.jpg?_nc_cat=100&_nc_oc=AQlbgpHhJ7C7-ufANnI5WsdKivngoP5zkDTVuFAMdXk_z1iT5TYNFlshVBNVNGRJ71k&_nc_ht=scontent-icn1-1.xx&oh=9b3613ce8320646f387b2a9448480163&oe=5DFA68A7",20, 2))

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

        observeSocialViewModel()
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

    private fun setRecyclerView() {
        val receiveLayoutManager: LayoutManager = GridLayoutManager(context, 1)
        val friendLayoutManager: LayoutManager = GridLayoutManager(context, 1)
        binding.receiveRecyclerview.adapter = receiveAdapter
        binding.friendRecyclerview.adapter = friendAdapter
        binding.receiveRecyclerview.layoutManager = receiveLayoutManager
        binding.friendRecyclerview.layoutManager = friendLayoutManager
    }

    private fun initData() {
        socialViewModel.friends
    }

    private fun initViewModel() {
        socialViewModel = ViewModelProviders.of(this).get(SocialViewModel::class.java)
    }

    override fun layoutId(): Int {
        return layout.social_fragment
    }
}