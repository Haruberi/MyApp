package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LearnFragment extends Fragment {

    public LearnFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Navigate to JpnActivity

        //Navigate to ChiActivity

        //Navigate to KorActivity

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v =inflater.inflate(R.layout.fragment_learn,container,false);

        //Navigate to KorActivity
        Button gotoKor=(Button) v.findViewById(R.id.korBtn);
        gotoKor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent korIn=new Intent(getActivity(),KorActivity.class);
                startActivity(korIn);
            }
        });

        //Navigate to ChiActivity
        Button gotoChi =(Button) v.findViewById(R.id.chiBtn);
        gotoChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chiIn=new Intent(getActivity(),ChiActivity.class);
                startActivity(chiIn);
            }
        });

        //Navigate to JpnActivity
        Button goToJpn = (Button) v.findViewById(R.id.jpnBtn);
        goToJpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent japIn=new Intent(getActivity(),JpnActivity.class);
                startActivity(japIn);
            }
        });
        return v;
        //return inflater.inflate(R.layout.fragment_learn,
          //      container,
            //    false);






    }
}