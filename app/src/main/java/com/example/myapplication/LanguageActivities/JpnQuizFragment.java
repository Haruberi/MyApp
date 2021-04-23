package com.example.myapplication.LanguageActivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.QuizActivities.JpnQuizActivity;
import com.example.myapplication.R;

public class JpnQuizFragment extends Fragment {

    public JpnQuizFragment() {
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
        View view = inflater.inflate(R.layout.fragment_jpn_quiz, container, false);

        //Navigate to JpnQuizActivity
        Button goToJpnQuiz = (Button) view.findViewById(R.id.gotoJpnQuiz);
        goToJpnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), JpnQuizActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}
