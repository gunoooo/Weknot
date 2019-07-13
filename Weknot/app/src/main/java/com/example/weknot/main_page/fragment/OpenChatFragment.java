package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;
import com.example.weknot.adapter.OpenChatRecyclerViewAdapter;
import com.example.weknot.adapter.SocialRecyclerViewAdapter;
import com.example.weknot.api.OpenChatApi;
import com.example.weknot.data.Friend;
import com.example.weknot.data.OpenChat;
import com.example.weknot.databinding.OpenChatFragmentBinding;
import com.example.weknot.retrofit.MyRetrofit;

import java.util.List;

public class OpenChatFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static OpenChatFragment newInstance() {
        Bundle args = new Bundle();

        OpenChatFragment openChatFragment = new OpenChatFragment();
        openChatFragment.setArguments(args);

        return openChatFragment;
    }

    private OpenChatFragmentBinding binding;

    private View view;

    private OpenChatApi openChatApi;

    private List<OpenChat> openChatList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.open_chat_fragment, container, false);

        view = binding.getRoot();

        init();

        return view;
    }

    private void init() {

        initData();

        setRecyclerView();
    }

    private void initData() {

        openChatApi = MyRetrofit.getRetrofit().create(OpenChatApi.class);
    }

    private void setRecyclerView() {

        connetRecyclerView(binding.chatRoomRecyclerView, openChatList);
    }

    private void connetRecyclerView(RecyclerView chatRoomRecyclerView, List<OpenChat> openChatList) {

        chatRoomRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        OpenChatRecyclerViewAdapter openChatRecyclerViewAdapter = new OpenChatRecyclerViewAdapter();
        chatRoomRecyclerView.setAdapter(openChatRecyclerViewAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
