package com.example.weknot_android.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @author 박건우
 */
public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    protected VB binding;
    protected View view;
    protected LayoutInflater inflater;
    protected ViewGroup container;

    public static<T extends Fragment> T newInstance(T fragment) {
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, layoutId(), container, false);

        this.inflater = inflater;
        this.container = container;
        view = binding.getRoot();

        return view;
    }

    @LayoutRes
    protected abstract int layoutId();
}
