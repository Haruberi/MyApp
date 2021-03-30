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
    TextView nameText;
    TabLayout tbLayout;
    TabItem tabLearn;
    TabItem tabHome;
    TabItem tabFaq;
    ViewPager viPager2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        tbLayout = findViewById(R.id.tabLayout);
        tabLearn= findViewById(R.id.learnTab);
        tabHome = findViewById(R.id.homeTab);
        tabFaq = findViewById(R.id.faqTab);
        viPager2 = findViewById(R.id.viPager);

        //getCount of tbLayout == tabLayout from ViewPagerAdapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tbLayout.getTabCount());
        viPager2.setAdapter(viewPagerAdapter);

        viPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbLayout));
        //add
        tbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //end

        //for getting Name of user
        nameText = findViewById(R.id.nameUserText);

        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        nameText.setText(name);
    }
}


