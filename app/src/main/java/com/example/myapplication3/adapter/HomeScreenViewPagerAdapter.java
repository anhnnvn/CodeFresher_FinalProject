package com.example.myapplication3.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication3.homescreen.FavoriteFragment;
import com.example.myapplication3.homescreen.HomeFragment;
import com.example.myapplication3.homescreen.ProfileFragment;
import com.example.myapplication3.homescreen.SearchFragment;

public class HomeScreenViewPagerAdapter extends FragmentStatePagerAdapter {




    public HomeScreenViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new ProfileFragment();

        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

}
