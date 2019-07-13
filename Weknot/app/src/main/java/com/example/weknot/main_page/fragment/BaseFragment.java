package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    protected VB binding;
    protected View view;

    private int layout;

    public BaseFragment(int layout) {
        this.layout = layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, layout, container, false);

        view = binding.getRoot();

        return view;
    }
}
