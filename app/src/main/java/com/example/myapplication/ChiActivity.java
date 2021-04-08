package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ChiActivity extends AppCompatActivity {

    private ViewPager chiView;
    private TabLayout chiLayout;

    private LearnFragment chiLearn;
    //Level
    private FaqFragment chiFaq;

    private ViewPagerAdapter chiVPAdapter;
    private BottomNavigationView chiBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi);

        chiBottom=findViewById(R.id.chiBottom_nav);

        chiView=findViewById(R.id.chiViewPager);
        chiLayout=findViewById(R.id.chiTabLayout);

        chiLearn=new LearnFragment();
        //Level
        chiFaq=new FaqFragment();
        chiLayout.setupWithViewPager(chiView);

        chiVPAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        //level
        chiVPAdapter.addFragment(chiFaq,"FAQ");
        chiView.setAdapter(chiVPAdapter);

    }
}