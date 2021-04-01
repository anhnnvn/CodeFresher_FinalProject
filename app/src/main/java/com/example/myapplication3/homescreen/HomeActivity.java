package com.example.myapplication3.homescreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myapplication3.adapter.HomeScreenViewPagerAdapter;
import com.example.myapplication3.R;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    public static HomeScreenViewPagerAdapter adapter;
    public ViewPager viewPager;
    private static String TAG = "TAG";
    public static final int FRAGMENT_0 = 0;
    public static final int FRAGMENT_1 = 1;
    public static final int FRAGMENT_2 = 2;
    public static final int FRAGMENT_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_home);

        initView();
        setAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {


        viewPager = findViewById(R.id.view_pager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        adapter = new HomeScreenViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.search_orange);
        tabLayout.getTabAt(2).setIcon(R.drawable.favorite_orange);
        tabLayout.getTabAt(3).setIcon(R.drawable.user_orange);


    }


    private void setAction() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}