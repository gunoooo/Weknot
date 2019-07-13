package com.example.weknot.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.weknot.main_page.fragment.BaseFragment;
import com.example.weknot.main_page.fragment.NewsFeedFragment;
import com.example.weknot.main_page.fragment.OpenChatFragment;
import com.example.weknot.main_page.fragment.ProfileFragment;
import com.example.weknot.main_page.fragment.SocialFragment;
import com.example.weknot.main_page.fragment.VideoCallFragment;


public class PagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 4;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {


            switch (i) {
                case 0:
                    return BaseFragment.newInstance(new NewsFeedFragment());
                case 1:
                    return BaseFragment.newInstance(new OpenChatFragment());
                case 2:
                    return BaseFragment.newInstance(new VideoCallFragment());
                case 3:
                    return BaseFragment.newInstance(new SocialFragment());
                case 4:
                    return BaseFragment.newInstance(new ProfileFragment());
                default:
                    return null;
            }

    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
