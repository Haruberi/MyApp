package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class JpnActivity extends AppCompatActivity {

    private ViewPager jpnView;
    private TabLayout jpnLayout;

    private LearnFragment jpnLearn;
    //LevelFragment
    private FaqFragment jpnFaq;

    private ViewPagerAdapter jpnVPAdapter;
    private BottomNavigationView jpnBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpn);

        jpnBottom =findViewById(R.id.jpnBottom_nav);

        jpnView=findViewById(R.id.jpnViewPager);
        jpnLayout=findViewById(R.id.jpnTabLayout);

        jpnLearn=new LearnFragment();
        //LevelFragment
        jpnFaq=new FaqFragment();
        jpnLayout.setupWithViewPager(jpnView);

        jpnVPAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        jpnVPAdapter.addFragment(jpnLearn,"LEARN");
        //Level
        jpnVPAdapter.addFragment(jpnFaq,"FAQ");
        jpnView.setAdapter(jpnVPAdapter);
    }
}