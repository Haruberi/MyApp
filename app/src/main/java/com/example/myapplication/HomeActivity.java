package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    TextView nameText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        nameText=(TextView)findViewById(R.id.nameUserText);

        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        nameText.setText(name);

        TabLayout tabLayout=findViewById(R.id.tabLayout);
        TabItem learnTab=findViewById(R.id.learnTab);
        TabItem homeTab=findViewById(R.id.homeTab);
        TabItem faqTab=findViewById(R.id.faqTab);

        PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

    }
}
