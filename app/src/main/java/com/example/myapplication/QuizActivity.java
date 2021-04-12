package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewWord;
    private TextView textViewSentence;
    private TextView textViewTranslation;

    private TextView textViewScore;
    private TextView textViewWordCount;
    private TextView textViewTimer;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private Button buttonNext;

    private ColorStateList textColorDefaultRb;

    private List<TheWord> theWordList;
    //wordCounter - räknar hur många ord som visats
    private int wordCounter;
    //Hur många word counts det finns totalt
    private int wordCountTotal;
    private TheWord currWord;


    private int score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewWord=findViewById(R.id.wordId);
        //textViewSentence=findViewById(R.id.wordSentence);
        //textViewTranslation=findViewById(R.id.translationSentence);
        textViewScore=findViewById(R.id.scoreId);
        textViewWordCount=findViewById(R.id.wordCountId);
        textViewTimer=findViewById(R.id.timerId);
        rbGroup=findViewById(R.id.radioGroupId);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        buttonNext=findViewById(R.id.btnNext);

        textColorDefaultRb=rb1.getTextColors();

        QuizDBHelper dbHelper=new QuizDBHelper(this);
        theWordList=dbHelper.getAllWords();

        wordCountTotal = theWordList.size();
        Collections.shuffle(theWordList);

        showNextWord();
    }

    private void showNextWord(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (wordCounter < wordCountTotal){
            currWord = theWordList.get(wordCounter);

            textViewWord.setText(currWord.getTheWord());
            rb1.setText(currWord.getOption1());
            rb2.setText(currWord.getOption2());
            rb3.setText(currWord.getOption3());

            wordCounter++;
            textViewWordCount.setText("Word: " + wordCounter + "/" + wordCountTotal);
            answered=false;
            buttonNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }
    private void finishQuiz(){
        finish();
    }
}