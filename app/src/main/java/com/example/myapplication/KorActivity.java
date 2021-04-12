package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class KorActivity extends AppCompatActivity {

    private ViewPager korView;
    private TabLayout korLayout;

    private LearnFragment korLearn;
    //Level
    private HomeFragment korHome;

    private ViewPagerAdapter korVPAdapter;
    private BottomNavigationView korBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kor);

        korBottom=findViewById(R.id.korBottom_nav);

        korView=findViewById(R.id.korViewPager);
        korLayout=findViewById(R.id.korTabLayout);

        korLearn=new LearnFragment();
        //Level
        korHome=new HomeFragment();
        korLayout.setupWithViewPager(korView);

        korVPAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        //level
        korVPAdapter.addFragment(korHome,"HOME");
        korView.setAdapter(korVPAdapter);

    }
}