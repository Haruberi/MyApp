package com.example.myapplication.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.QuizActivities.ChiQuizActivity;
import com.example.myapplication.QuizActivities.JpnQuizActivity;
import com.example.myapplication.QuizActivities.KorQuizActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private TextView nameText;

    public HomeFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //return inflater.inflate(R.layout.fragment_home,
          //      container,
            //    false);
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        //add text to get name
        /*nameText=(TextView) view.findViewById(R.id.nameUserText);
        Bundle bun = getActivity().getIntent().getExtras();
        String username=bun.getString("username");
        nameText.setText(username);*/

        /*nameText=(TextView)findViewById(R.id.nameUserText);
        Bundle bun=getIntent().getExtras();

        String username=bun.getString("username");
        nameText.setText(username);*/

        //Navigate to KorQuizActivity via Button
        Button goToKorQuizAgain=(Button) view.findViewById(R.id.startKorQuizAgain);
        goToKorQuizAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent korIn=new Intent(getActivity(), KorQuizActivity.class);
                startActivity(korIn);
            }
        });
        //Navigate to ChiQuizActivity via Button
        Button goToChiQuizAgain=(Button) view.findViewById(R.id.startChiQuizAgain);
        goToChiQuizAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chiIn=new Intent(getActivity(), ChiQuizActivity.class);
                startActivity(chiIn);
            }
        });

        //Navigate to JpnQuizActivity via Button
        Button goToJpnQuizAgain = (Button)  view.findViewById(R.id.startJpnQuizAgain);
        goToJpnQuizAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jpnIn=new Intent(getActivity(), JpnQuizActivity.class);
                startActivity(jpnIn);
            }
        });
        return view;



    }
}

