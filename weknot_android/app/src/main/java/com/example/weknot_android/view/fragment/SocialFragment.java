package com.example.weknot_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseFragment;
import com.example.weknot_android.databinding.SocialFragmentBinding;
import com.example.weknot_android.model.entity.user.Friend;
import com.example.weknot_android.viewmodel.SocialViewModel;
import com.example.weknot_android.widget.recyclerview.adapter.SocialAdapter;

public class SocialFragment extends BaseFragment<SocialFragmentBinding> {

    private SocialAdapter receiveAdapter;
    private SocialAdapter friendAdapter;

    private SocialViewModel socialViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        initData();

        observeSocialViewModel();
    }

    private void initViewModel() {
        socialViewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
    }

    private void initData() {
        socialViewModel.getFriends();
    }

    private void observeSocialViewModel() {
        socialViewModel.getData().observe(this, friends -> {
            for (Friend friend: friends) {
                if (friend.getFriendPoint() == 1) {
                    socialViewModel.receiveList.getValue().add(friend);
                }
                else if (friend.getFriendPoint() == 2) {
                    socialViewModel.friendList.getValue().add(friend);
                }
            }
            receiveAdapter = new SocialAdapter(getContext(), socialViewModel.receiveList.getValue());
            friendAdapter = new SocialAdapter(getContext(), socialViewModel.friendList.getValue());

            setRecyclerView();
        });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.receiveRecyclerview.setAdapter(receiveAdapter);
        binding.friendRecyclerview.setAdapter(friendAdapter);
        binding.receiveRecyclerview.setLayoutManager(linearLayoutManager);
        binding.friendRecyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int layoutId() {
        return R.layout.social_fragment;
    }
}
