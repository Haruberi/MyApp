package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class KorQuizFragment extends Fragment {

    public KorQuizFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v=inflater.inflate(R.layout.fragment_kor_quiz,container,false);

        //Navigate to KorQuizActivity(Quiz för koreanska)
        Button gotoKorQuiz=(Button) v.findViewById(R.id.gotoKorQuiz);
        gotoKorQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getActivity(),KorQuizActivity.class);
                startActivity(in);
            }
        });
        return v;
    }
}