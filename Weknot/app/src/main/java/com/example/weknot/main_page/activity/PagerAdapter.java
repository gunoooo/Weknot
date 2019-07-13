package com.example.weknot.main_page.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.weknot.main_page.fragment.NewsFeedFragment;
import com.example.weknot.main_page.fragment.OpenChatFragment;
import com.example.weknot.main_page.fragment.ProfileFragment;
import com.example.weknot.main_page.fragment.SocialFragment;
import com.example.weknot.main_page.fragment.VideoCallFragment;


public class PagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 5;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {


            switch (i) {
                case 0:
                    return NewsFeedFragment.newInstance();
                case 1:
                    return OpenChatFragment.newInstance();
                case 2:
                    return VideoCallFragment.newInstance();
                case 3:
                    return SocialFragment.newInstance();
                case 4:
                    return ProfileFragment.newInstance();
                default:
                    return null;
            }

    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
