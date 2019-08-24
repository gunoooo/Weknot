package com.example.weknot_android.view.activity;

import android.os.Bundle;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseActivity;
import com.example.weknot_android.databinding.MainActivityBinding;

public class MainActivity extends BaseActivity<MainActivityBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.main_activity;
    }
}
