package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class JpnLvlFragment extends Fragment {

    public JpnLvlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_jpn_lvl, container, false);

        //Navigate to JpnLvl1Activity
        Button goToJpnLvl1 = (Button) view.findViewById(R.id.btnReadMore);
        goToJpnLvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), JpnLvl1Activity.class);
                startActivity(in);
            }
        });

        //Navigate to JpnLvl2Activity
        //Navigate to JpnLvl3Activity ....
        return view;
    }
}

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_jpn_lvl, container, false);

