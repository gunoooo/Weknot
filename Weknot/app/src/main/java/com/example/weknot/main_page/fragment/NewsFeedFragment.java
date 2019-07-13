package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.weknot.R;
import com.example.weknot.databinding.NewsFeedFragmentBinding;

public class NewsFeedFragment extends Fragment {

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

    public static NewsFeedFragment newInstance() {
        Bundle args = new Bundle();

        NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
        newsFeedFragment.setArguments(args);
        return newsFeedFragment;
    }

    private NewsFeedFragmentBinding binding;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.news_feed_fragment, container, false);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
