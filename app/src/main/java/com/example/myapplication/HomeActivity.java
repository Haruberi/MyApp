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

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    //Initialize variable
    private TextView nameText;

    private TabLayout tbLayout;

    private LearnFragment tabLearn;
    private HomeFragment tabHome;
    private FaqFragment tabFaq;

    private ViewPager viewPager;
    private ViewPagerAdapter viPaAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        tbLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viPager);

        tabLearn=new LearnFragment();
        tabHome=new HomeFragment();
        tabFaq=new FaqFragment();

        tbLayout.setupWithViewPager(viewPager);

        viPaAdapter=new ViewPagerAdapter(getSupportFragmentManager(),tbLayout.getTabCount());
        viewPager.setAdapter(viPaAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbLayout));

        tbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //for getting Name of user
        nameText = findViewById(R.id.nameUserText);
        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        nameText.setText(name);




        //getCount of tbLayout == tabLayout from ViewPagerAdapter

        //ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tbLayout.getTabCount());
        //viewPager.setAdapter(viewPagerAdapter);

    }
}


