package com.example.myapplication.QuizDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.TheWord;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

        private TextView textViewWord;

        private TextView textViewSentence;
        private TextView textViewTranslation;

        private TextView textViewScore;
        private TextView textViewWordCount;

        private RadioGroup rbGroup;
        private RadioButton rb1;
        private RadioButton rb2;
        private RadioButton rb3;
        private ColorStateList textColorDefaultForRb;
        private Button buttonNext;

        private int wordCounter;
        private int wordCountTotal;
        private TheWord currWord;
        private TheWord currSentence;
        private TheWord currTranslation;

        private int score;
        private boolean answered;
        private List<TheWord> theWordList;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //jpn quiz
        textViewWord=findViewById(R.id.wordId);
        textViewSentence=findViewById(R.id.wordSentence);
        textViewTranslation=findViewById(R.id.translationSentence);
        textViewScore=findViewById(R.id.scoreId);
        textViewWordCount=findViewById(R.id.wordCountId);
        rbGroup=findViewById(R.id.radioGroupId);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        buttonNext=findViewById(R.id.btnNext);

        textColorDefaultForRb=rb1.getTextColors();

        QuizDBHelper jpnDBHelper=new QuizDBHelper(this);
        theWordList=jpnDBHelper.getAllWords();
        wordCountTotal=theWordList.size();
        //shuffle - få ord i randow ordning
        Collections.shuffle(theWordList);

        showNextWord();
    }

    private void showNextWord(){
        rb1.setTextColor(textColorDefaultForRb);
        rb2.setTextColor(textColorDefaultForRb);
        rb3.setTextColor(textColorDefaultForRb);
        rbGroup.clearCheck();

        //if there are any words/sentence/translations left, we can show the next word
        if (wordCounter<wordCountTotal){
            currWord=theWordList.get(wordCounter);
            //currSentence=theWordList.get(wordCounter);
            //currTranslation=theWordList.get(wordCounter);

            textViewWord.setText(currWord.getTheWord());
            textViewSentence.setText(currWord.getWordSentence());
            textViewTranslation.setText(currWord.getSentenceTranslation());
            rb1.setText(currWord.getOption1());
            rb2.setText(currWord.getOption2());
            rb3.setText(currWord.getOption3());

            wordCounter++;
            textViewWordCount.setText("Word: " + wordCounter + "/" + wordCountTotal);
            answered=false;
            buttonNext.setText("Check answer");
        } else {
            finishQuiz();
        }
    }
    private void finishQuiz(){
        finish();
    }
}

    //public static final String EXTRA_SCORE ="extraScore";
    //
    //    //nya variabler
    //    private SQLiteDatabase langDb;
    //    private QuizDBHelper quizDBHelper;
    //    //slutar här
    //
    //    private TextToSpeech langTTS;
    //    private SeekBar speakSeek;
    //    private ImageButton listenBtn;
    //
    //

    //
    //    private ColorStateList textColorDefaultRb;
    //
    //    private List<TheWord> theWordList;
    //    private int wordCounter;
    //    private int wordCountTotal;
    //    private TheWord currWord;
    //
    //
    //    private int score;
    //    private boolean answered;
    //
    //    private long backPressedTime;
    //
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_quiz);
    //
    //        textViewWord=findViewById(R.id.wordId);
    //        textViewSentence=findViewById(R.id.wordSentence);
    //        textViewTranslation=findViewById(R.id.translationSentence);
    //        textViewScore=findViewById(R.id.scoreId);
    //        textViewWordCount=findViewById(R.id.wordCountId);
    //        rbGroup=findViewById(R.id.radioGroupId);
    //        rb1=findViewById(R.id.radioBtn1);
    //        rb2=findViewById(R.id.radioBtn2);
    //        rb3=findViewById(R.id.radioBtn3);
    //        buttonNext=findViewById(R.id.btnNext);
    //
    //        textColorDefaultRb=rb1.getTextColors();
    //
    //
    //        quizDBHelper = new QuizDBHelper(this);
    //        try {
    //            quizDBHelper.createDataBase();
    //            quizDBHelper.openDataBase();
    //        }
    //        catch (IOException e){
    //            e.printStackTrace();
    //        }
    //
    //        theWordList=quizDBHelper.getAllWords();
    //
    //        wordCountTotal = theWordList.size();
    //        Collections.shuffle(theWordList);
    //
    //        showNextWord();
    //        buttonNext.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                if (!answered){
    //                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
    //                        checkAnswer();
    //                    } else {
    //                        Toast.makeText(QuizActivity.this,"Please select an answer", Toast.LENGTH_SHORT).show();
    //                    }
    //                } else {
    //                    showNextWord();
    //                }
    //            }
    //
    //        });
    //    }
    //
    //    private void showNextWord(){
    //        rb1.setTextColor(textColorDefaultRb);
    //        rb2.setTextColor(textColorDefaultRb);
    //        rb3.setTextColor(textColorDefaultRb);
    //        rbGroup.clearCheck();
    //
    //        if (wordCounter < wordCountTotal){
    //            currWord = theWordList.get(wordCounter);
    //
    //            textViewWord.setText(currWord.getTheWord());
    //            textViewSentence.setText(currWord.getWordSentence());
    //            textViewTranslation.setText(currWord.getSentenceTranslation());
    //
    //            rb1.setText(currWord.getOption1());
    //            rb2.setText(currWord.getOption2());
    //            rb3.setText(currWord.getOption3());
    //
    //            wordCounter++;
    //            textViewWordCount.setText("Word: " + wordCounter + "/" + wordCountTotal);
    //            answered=false;
    //            buttonNext.setText("Confirm");
    //        } else {
    //            finishQuiz();
    //        }
    //    }
    //
    //    private void checkAnswer(){
    //        answered=true;
    //
    //        RadioButton rbSelected=findViewById(rbGroup.getCheckedRadioButtonId());
    //        int answerNr=rbGroup.indexOfChild(rbSelected) + 1;
    //
    //        if (answerNr==currWord.getAnswerNr()){
    //            score++;
    //            textViewScore.setText("Score " + score);
    //        }
    //
    //        showSolution();
    //    }
    //
    //    private void showSolution(){
    //        rb1.setTextColor(Color.BLACK);
    //        rb2.setTextColor(Color.BLACK);
    //        rb3.setTextColor(Color.BLACK);
    //
    //        switch (currWord.getAnswerNr()){
    //            case 1:
    //                rb1.setTextColor(Color.GREEN);
    //                textViewWord.setText("A is correct!");
    //                break;
    //            case 2:
    //                rb2.setTextColor(Color.GREEN);
    //                textViewWord.setText("B is correct!");
    //                break;
    //            case 3:
    //                rb3.setTextColor(Color.GREEN);
    //                textViewWord.setText("C is correct!");
    //                break;
    //        }
    //
    //        if (wordCounter < wordCountTotal){
    //            buttonNext.setText("NEXT");
    //        } else {
    //            buttonNext.setText("FINISH");
    //        }
    //
    //    }
    //    private void finishQuiz(){
    //        Intent resultIntent=new Intent();
    //        resultIntent.putExtra(EXTRA_SCORE,score);
    //        setResult(RESULT_OK, resultIntent);
    //        finish();
    //    }
    //
    //    @Override
    //    public void onBackPressed() {
    //        if (backPressedTime+2000>System.currentTimeMillis()){
    //            finishQuiz();
    //        } else {
    //            Toast.makeText(this,"Press back again to exit", Toast.LENGTH_SHORT).show();
    //        }
    //        backPressedTime=System.currentTimeMillis();
    //    }
    //}

