package com.example.weknot_android.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.MyinfoFragmentBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.viewmodel.UserViewModel
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class MyinfoFragment : BaseFragment<MyinfoFragmentBinding>() {
    private lateinit var userViewModel: UserViewModel

    private var feedAdapter: FeedAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initData()

//        var feeds: ArrayList<Feed> = ArrayList()
//        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","박건우",312321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69202764_473249329945552_962513982193664000_n.jpg?_nc_cat=110&_nc_eui2=AeH7a1b9giy40MxTdssYQk4MseQOim8RLW_fqffye6br8dUX2ZNUC6_AgbKZko07hhn1_xEMG2Uk2dFsRkcRDTKz4xCfkjuncUFRdqBlRzXsLw&_nc_oc=AQmU1Q4Y5UF1mkbl6IvjqPeoEGwM95ij3HdoGZB8yisg74T5rgJbPx8rzAPqZ3xEjo0&_nc_ht=scontent-icn1-1.xx&oh=3a7b0279cef60956ecd0a1a5b7d88f08&oe=5DCAABEA","aa",1,false))
//        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","김인직",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69604273_768139570269014_1217837345420607488_n.jpg?_nc_cat=103&_nc_eui2=AeGVEtVYLuHGo3X5T3MWiWva_hNYkI_V77pGbp2NywTrYrQh54AVRwacR7ZEjNPvS7SvM9o67N2WjdGXAVF5cQ50QIpDBJli59ZSZCF4zNGnpQ&_nc_oc=AQljah0RnMBp5MdFO_ijj-x1AbZpoiIKUvXB-5AlRyb7qGwyosyx_WX0dRjPeHs139s&_nc_ht=scontent-icn1-1.xx&oh=a9afd97099220498cc4623c836e186ff&oe=5E0C1DC1","aa",100,true))
//        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","최석준",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69280261_768139600269011_2894823346371821568_n.jpg?_nc_cat=105&_nc_eui2=AeHsLD9y-cMc-FCCCSCoQJS3xnbO3pRwQWe9H5F-cPryvJO-HEO4JUwn71DWj4kI5FvDlrK-1ILYnyTqo-aB8PmHRmLfrS54laMMMPbxBhvxog&_nc_oc=AQls240rAKJlsYHvGBTLPq6aeoEHWJnAOOdRuf773-O2-NnWQ3U9_G87b2h01QpYisc&_nc_ht=scontent-icn1-1.xx&oh=6038c08808999299603ebb4499d6dc6f&oe=5DFD86D4","aa",1321,false))
//        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","이유승",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69431091_768139633602341_1174538603288592384_n.jpg?_nc_cat=111&_nc_eui2=AeHWbz-rtzsZ-Au1aAuC-UrbSkDplHS_fIxNMkaHVBX7jcTRYaheqpS3yvIpJi2Oxrko_dux2uSYOhijaiye6USFq8ZD39tbWZquWfJxq72X2w&_nc_oc=AQmnz9pWjH6FKlyfKBs3Q42fVhChhJ8u1XlWhYe0Lpp8zXKx6qu-WZB8h39c5nHTTlg&_nc_ht=scontent-icn1-1.xx&oh=eb323d7d64da1923ad77ccc59e907b1a&oe=5E018EB1","aa",1323,false))
//
//        var profile = Profile("dd", "박건우", "2002.08.27", "", "박건우라고 합니다", "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69498469_361966534705422_8546381978646085632_n.jpg?_nc_cat=110&_nc_oc=AQmzctsOdQ8IjgMtvyiEQuobKzRcEhy17AQ3xr0L18W9OBGraiwL3rGVzgc0Of08pHQ&_nc_ht=scontent-icn1-1.xx&oh=26f5ea2e941b57f99d41ff2a9ef44e59&oe=5E130D2A",30, feeds, "남성")
//        initView(profile)
        observeUserViewModel()
    }

    private fun observeUserViewModel() {
        userViewModel.getData().observe(this, Observer { profile: Profile -> initView(profile) })
    }

    private fun initView(profile: Profile) {
        binding.intro.isSelected = true
        binding.birth.text = profile.userBirth
        binding.intro.text = profile.userIntro
        binding.name.text = profile.userName
        binding.point.text = profile.userPoint.toString()
        if (profile.userPicture == null) {
            //todo
        }
        else {
            Glide.with(this).load(profile.userPicture).into(binding.profileImage)
        }
        if (profile.userGender == "남성") {
            Glide.with(this).load(R.drawable.man_icon).into(binding.gender)
        }
        else {
            Glide.with(this).load(R.drawable.woman_icon).into(binding.gender)
        }
        feedAdapter = FeedAdapter(context!!, profile.userFeeds!!)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.feedRecyclerview.adapter = feedAdapter
        binding.feedRecyclerview.layoutManager = linearLayoutManager
    }

    private fun initData() {
        userViewModel.getProfile(userViewModel.getMyId())
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.myinfo_fragment
    }
}