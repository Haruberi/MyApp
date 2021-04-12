package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JpnLvl1Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ=1;

    //To show score - flytta detta sedan till HomeFragment, men gör först här
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";

    private TextView textViewHighscore;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpn_lvl1);

        textViewHighscore=findViewById(R.id.textViewHighscore);
        loadHighscore();

        Button buttonStart=findViewById(R.id.btnStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    //here open our second activity quizactivity
    private void startQuiz(){
        Intent intent=new Intent(JpnLvl1Activity.this,QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    //för att visa score

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score=data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if (score>highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore(){
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Your highscore" + highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore=highscoreNew;
        textViewHighscore.setText("Your highscore" + highscore);

        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }
}