package com.example.weknot.main_page.fragment;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public class BaseFragment<TB extends ViewDataBinding, T extends Fragment> extends Fragment {

    protected TB binding;
    protected View view;

    private int layout;
    private T fragment;

    public BaseFragment(int layout, T fragment) {
        this.layout = layout;
        this.fragment = fragment;
    }

    public T newInstance() {
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
