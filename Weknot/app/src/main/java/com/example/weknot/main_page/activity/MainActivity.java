package com.example.weknot.main_page.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.weknot.R;
import com.example.weknot.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setOffscreenPageLimit(4);
    }
}
