package com.example.weknot_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseFragment;
import com.example.weknot_android.databinding.OpenChatFragmentBinding;
import com.example.weknot_android.viewmodel.OpenChatViewModel;
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter;

public class OpenChatFragment extends BaseFragment<OpenChatFragmentBinding> {

    private OpenChatAdapter openChatAdapter;

    private OpenChatViewModel openChatViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        initData();

        observeOpenChatViewModel();
    }

    private void initViewModel() {
        openChatViewModel = ViewModelProviders.of(this).get(OpenChatViewModel.class);
    }

    private void initData() {
        openChatViewModel.getChattingRooms();
    }

    private void observeOpenChatViewModel() {
        openChatViewModel.getData().observe(this, openChatRooms -> {
            openChatAdapter = new OpenChatAdapter(getContext(), openChatRooms);
            setRecyclerView();
        });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.chatRoomRecyclerview.setAdapter(openChatAdapter);
        binding.chatRoomRecyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int layoutId() {
        return R.layout.open_chat_fragment;
    }
}
