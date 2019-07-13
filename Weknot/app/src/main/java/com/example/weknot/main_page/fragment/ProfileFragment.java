package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.weknot.R;
import com.example.weknot.databinding.OpenChatFragmentBinding;
import com.example.weknot.databinding.ProfileFragmentBinding;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();

        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(args);
        return profileFragment;
    }

    private ProfileFragmentBinding binding;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.profile_fragment, container, false);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
