package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class JpnActivity extends AppCompatActivity {

    private ViewPager jpnView;
    private TabLayout jpnLayout;

    private JpnQuizFragment jpnQuiz;
    private HomeFragment jpnHome;

    private ViewPagerAdapter jpnVPAdapter;
    private BottomNavigationView jpnBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpn);

        jpnBottom =findViewById(R.id.jpnBottom_nav);

        jpnView=findViewById(R.id.jpnViewPager);
        jpnLayout=findViewById(R.id.jpnTabLayout);

        jpnQuiz=new JpnQuizFragment();
        jpnHome=new HomeFragment();
        jpnLayout.setupWithViewPager(jpnView);

        jpnVPAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        jpnVPAdapter.addFragment(jpnQuiz,"QUIZ");
        jpnVPAdapter.addFragment(jpnHome,"HOME");
        jpnView.setAdapter(jpnVPAdapter);
    }
}