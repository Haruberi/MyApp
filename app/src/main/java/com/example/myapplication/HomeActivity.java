package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tbLayout;

    private LearnFragment learnFrag;
    private HomeFragment homeFrag;

    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView botNav;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        botNav=findViewById(R.id.bottom_navigation);

        viewPager = findViewById(R.id.viPager);
        tbLayout = findViewById(R.id.tabLayout);

        learnFrag = new LearnFragment();
        homeFrag = new HomeFragment();
        tbLayout.setupWithViewPager(viewPager);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(learnFrag,"LEARN");
        viewPagerAdapter.addFragment(homeFrag,"HOME");
        viewPager.setAdapter(viewPagerAdapter);
    }
}


