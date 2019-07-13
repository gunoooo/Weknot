package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.weknot.R;
import com.example.weknot.databinding.OpenChatFragmentBinding;
import com.example.weknot.databinding.ProfileFragmentBinding;

public class ProfileFragment extends BaseFragment<ProfileFragmentBinding> {

    public ProfileFragment() {
        super(R.layout.profile_fragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
