package com.example.myapplication.Home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.BottomNavigation.FaqFragment;
import com.example.myapplication.R;
import com.example.myapplication.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    //private TextView nameText;
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

        /*nameText=(TextView)findViewById(R.id.nameUserText);
        Bundle bun=getIntent().getExtras();

        String username=bun.getString("username");
        nameText.setText(username);*/

        //botNav = findViewById(R.id.bottom_navigation);
        //botNav.setOnNavigationItemSelectedListener(navListener);

        viewPager = findViewById(R.id.viPager);
        tbLayout = findViewById(R.id.tabLayout);

        learnFrag = new LearnFragment();
        homeFrag = new HomeFragment();
        tbLayout.setupWithViewPager(viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(homeFrag, "HOME");
        viewPagerAdapter.addFragment(learnFrag, "LEARN");
        viewPager.setAdapter(viewPagerAdapter);
    }
}


