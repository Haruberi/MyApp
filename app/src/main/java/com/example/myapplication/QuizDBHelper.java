package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuizContract.*;
import com.example.myapplication.TheWord.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="JapaneseQuizTable.db";
    public static final int DATABASE_VERSION=1;
    private SQLiteDatabase db;

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " +
                WordTable.TABLE_NAME + " ( " +
                WordTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WordTable.COLUMN_WORD + " TEXT, " +
                WordTable.COLUMN_SENTENCE + " TEXT, " +
                WordTable.COLUMN_SENTENCE_TRANSLATION + " TEXT, " +
                WordTable.COLUMN_OPTION1 + " TEXT, " +
                WordTable.COLUMN_OPTION2 + " TEXT, " +
                WordTable.COLUMN_OPTION3 + " TEXT, " +
                WordTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_WORDS_TABLE);
        fillWordsTable();
    }

    //Om man vill lägga till fler columner måste man meddela det här i onUpgrade samt skriva version 1 till 2 i database_version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillWordsTable(){
        TheWord tw1=new TheWord("コーヒー", "彼は喫茶店で '茶色暖かい飲み物' を飲みました ", "He drank a 'brown warm drink' at the café.", "Coffee", "Cat", "Milk", 1 );
        addWord(tw1);


        TheWord tw2=new TheWord("お水", "友達とジムに行ったときにいつも '冷たい飲み物' を持ってきました。", "When I went to the gym with my friend, I always brought a 'bottle of cold drink' with me.", "Warm tea", "Water", "Pineapple", 2 );
        addWord(tw2);

        TheWord tw3=new TheWord("ケーキ", "明日は彼氏の誕生日なので私は 'バッターから焼いてできたスイーツ' を作ります。", "Tomorrow is the birthday of my boyfriend so I am going to make 'a sweet that’s made from batter' for him.", "Flower", "Walk", "Cake", 3 );
        addWord(tw3);

        TheWord tw4=new TheWord("犬", "公園に 'ワンワンしていた動物' と一緒に散歩しました。", "I took a walk with 'an animal who said woof' in the park.", "Dog", "Elephant", "Rose", 1 );
        addWord(tw4);

        TheWord tw5=new TheWord("バナナ", "果物なら '一本黄色い果物' が好きです。", "If there is one fruit I like, it is 'one long yellow fruit'.", "Apple", "Banana", "Paper", 2 );
        addWord(tw5);

    }

    private void addWord(TheWord theWord){
        ContentValues cv=new ContentValues();
        cv.put(WordTable.COLUMN_WORD, theWord.getTheWord());
        cv.put(WordTable.COLUMN_SENTENCE,theWord.getWordSentence());
        cv.put(WordTable.COLUMN_SENTENCE_TRANSLATION,theWord.getSentenceTranslation());
        cv.put(WordTable.COLUMN_OPTION1,theWord.getOption1());
        cv.put(WordTable.COLUMN_OPTION2,theWord.getOption2());
        cv.put(WordTable.COLUMN_OPTION3,theWord.getOption3());
        cv.put(WordTable.COLUMN_ANSWER_NR,theWord.getAnswerNr());
        db.insert(WordTable.TABLE_NAME,null,cv);

    }

    public List<TheWord> getAllWords(){
        List<TheWord> theWordList =new ArrayList<>();
        db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + WordTable.TABLE_NAME,null);

        if (c.moveToFirst()){
            do {
                TheWord theWord=new TheWord();
                theWord.setTheWord(c.getString(c.getColumnIndex(WordTable.COLUMN_WORD)));
                theWord.setWordSentence(c.getString(c.getColumnIndex(WordTable.COLUMN_SENTENCE)));
                theWord.setOption1(c.getString(c.getColumnIndex(WordTable.COLUMN_OPTION1)));
                theWord.setOption2(c.getString(c.getColumnIndex(WordTable.COLUMN_OPTION2)));
                theWord.setOption3(c.getString(c.getColumnIndex(WordTable.COLUMN_OPTION3)));
                theWord.setAnswerNr(c.getInt(c.getColumnIndex(WordTable.COLUMN_ANSWER_NR)));
                theWordList.add(theWord);
            } while (c.moveToNext());
        }

        c.close();
        return theWordList;
    }
}
