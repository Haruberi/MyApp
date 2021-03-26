package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    //Initialize variable
    TextView nameText;

    private TabLayout tbLayout;
    private ViewPager vPager;
    private TabItem tabLearn, tabHome, tabFaq;
    public PagerAdapter pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        //for getting Name of user
        nameText = (TextView) findViewById(R.id.nameUserText);

        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        nameText.setText(name);

        tbLayout=(TabLayout)findViewById(R.id.tabLayout);
        tabLearn=(TabItem)findViewById(R.id.learnTab);
        tabHome=(TabItem)findViewById(R.id.homeTab);
        tabFaq=(TabItem)findViewById(R.id.faqTab);
        vPager=(ViewPager)findViewById(R.id.viPager);

        pager=new PageAdapter(getSupportFragmentManager(),tbLayout.getTabCount());
        vPager.setAdapter(pager);

        tbLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vPager.setCurrentItem(tab.getPosition());
                //0 = Learn
                if (tab.getPosition()==0){
                    pager.notifyDataSetChanged();
                //1 = Home
                } else if (tab.getPosition()==1){
                    pager.notifyDataSetChanged();
                //2 = Faq
                } else if (tab.getPosition()==2){
                    pager.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbLayout));

        }
    }

