package com.example.weknot.main_page.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;
import com.example.weknot.adapter.SocialRecyclerViewAdapter;
import com.example.weknot.api.FriendApi;
import com.example.weknot.data.Friend;
import com.example.weknot.databinding.SocialFragmentBinding;
import com.example.weknot.retrofit.MyRetrofit;
import com.example.weknot.viewmodel.SocialViewModel;

import java.util.List;

public class SocialFragment extends BaseFragment<SocialFragmentBinding> {

    public SocialFragment() {
        super(R.layout.social_fragment);
    }

    private List<Friend> requestList;
    private List<Friend> friendList;

    private SocialViewModel socialViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        socialViewModel = new SocialViewModel(getContext(), SocialViewModel.class);
        init();
    }

    private void init() {

        initData();

        setRecyclerView();

    }

    private void initData() {

    }

    private void setSocialList() {

        setRequestList();
        setFriendList();

    }
    private void setRequestList() {

    }
    private void setFriendList() {

    }

    private void setRecyclerView() {

        connetRecyclerView(binding.recyclerViewRequestFriend, requestList, false);
        connetRecyclerView(binding.recyclerViewFriend, friendList, true);

    }

    private void connetRecyclerView(RecyclerView recyclerView, List<Friend> list, boolean type) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        SocialRecyclerViewAdapter socialRecyclerViewAdapter = new SocialRecyclerViewAdapter(type);
        recyclerView.setAdapter(socialRecyclerViewAdapter);

    }
}
