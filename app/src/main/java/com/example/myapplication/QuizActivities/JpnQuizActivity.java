package com.example.myapplication.QuizActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.QuizDatabase.QuizActivity;
import com.example.myapplication.R;

public class JpnQuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ=1;

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";
    private TextView jpnTextViewHighscore;
    private int jpnHighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpn_quiz);

        jpnTextViewHighscore=findViewById(R.id.jpntextViewHighscoreId);
        loadJpnHighscore();

        Button btnStartQuiz = findViewById(R.id.jpnBtnStart);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJpnQuiz();
            }
        });
    }

    private void startJpnQuiz(){
        Intent jpnIntent=new Intent(JpnQuizActivity.this,QuizActivity.class);
        startActivityForResult(jpnIntent,REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_QUIZ){
            if (resultCode==RESULT_OK){
                int score=data.getIntExtra(QuizActivity.SHOW_SCORE_AGAIN,0);
                if (score>jpnHighscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadJpnHighscore(){
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        jpnHighscore=prefs.getInt(KEY_HIGHSCORE,0);
        jpnTextViewHighscore.setText("Your current score: " + jpnHighscore);

    }

    private void updateHighscore(int newJpnHighscore) {
        jpnHighscore=newJpnHighscore;
        jpnTextViewHighscore.setText("Your current score: " + jpnHighscore);

        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt(KEY_HIGHSCORE, jpnHighscore);
        editor.apply();
    }
}

        //textViewHighscore=findViewById(R.id.textViewHighscore);
        //        loadHighscore();
        //
        //        Button buttonStart=findViewById(R.id.jpnBtnStart);
        //        buttonStart.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                startQuiz();
        //            }
        //        });
        //    }
        //
        //    //Navigate to QuizActivity
        //    private void startQuiz(){
        //        Intent intent=new Intent(JpnQuizActivity.this, QuizActivity.class);
        //        startActivityForResult(intent, REQUEST_CODE_QUIZ);
        //    }
        //
        //    //för att visa score
        //    @Override
        //    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        //
        //        if (requestCode==REQUEST_CODE_QUIZ){
        //            if (resultCode == RESULT_OK){
        //                int score=data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
        //                if (score>highscore){
        //                    updateHighscore(score);
        //                }
        //            }
        //        }
        //    }
        //
        //    private void loadHighscore(){
        //        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        //        highscore=prefs.getInt(KEY_HIGHSCORE,0);
        //        textViewHighscore.setText("Your highscore" + highscore);
        //    }
        //
        //    private void updateHighscore(int highscoreNew){
        //        highscore=highscoreNew;
        //        textViewHighscore.setText("Your highscore" + highscore);
        //
        //        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        //        SharedPreferences.Editor editor=prefs.edit();
        //        editor.putInt(KEY_HIGHSCORE,highscore);
        //        editor.apply();
        //    }
        //}

