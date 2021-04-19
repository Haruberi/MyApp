package com.example.myapplication.LanguageActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapplication.Home.HomeFragment;
import com.example.myapplication.R;
import com.example.myapplication.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ChiActivity extends AppCompatActivity {

    private ViewPager chiView;
    private TabLayout chiLayout;

    private ChiQuizFragment chiQuiz;
    private HomeFragment chiHome;

    private ViewPagerAdapter chiVPAdapter;
    private BottomNavigationView chiBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi);

        chiBottom=findViewById(R.id.chiBottom_nav);

        chiView=findViewById(R.id.chiViewPager);
        chiLayout=findViewById(R.id.chiTabLayout);

        chiQuiz=new ChiQuizFragment();
        chiHome=new HomeFragment();
        chiLayout.setupWithViewPager(chiView);

        chiVPAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        chiVPAdapter.addFragment(chiQuiz,"QUIZ");
        chiVPAdapter.addFragment(chiHome,"HOME");
        chiView.setAdapter(chiVPAdapter);

    }
}