package com.example.myapplication.QuizDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.QuizDatabase.QuizContract.*;
import com.example.myapplication.TheWord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyLanguageQuiz.db";
    private static final int DB_VERSION = 1; //2

    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Create the database

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " +
                JpnWordTable.TABLE_NAME + " ( " +
                JpnWordTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JpnWordTable.COLUMN_WORD + " TEXT, " +
                JpnWordTable.COLUMN_SENTENCE + " TEXT, " +
                JpnWordTable.COLUMN_SENTENCE_TRANSLATION + " TEXT, " +
                JpnWordTable.COLUMN_OPTION_1 + " TEXT, " +
                JpnWordTable.COLUMN_OPTION_2 + " TEXT, " +
                JpnWordTable.COLUMN_OPTION_3 + " TEXT, " +
                JpnWordTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_WORDS_TABLE);
        fillWordsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JpnWordTable.TABLE_NAME);
        // db
        onCreate(db);
    }
//Jpn
    private void fillWordsTable() {
        TheWord w1 = new TheWord("コーヒー", "喫茶店で '' 茶色暖かい飲み物 '' を頼みました。", "At the cafe, I ordered a '' warm brown drink '' ", "A: Coffee", "B: Dog", "C: Water", 1);
        addWord(w1);

        TheWord w2 = new TheWord("彼氏", "今日 '' 愛している男 '' とデートしに行きました。", "Today he/she went on a date with '' the male lover '' of their life.", "A: Girlfriend", "B: Boyfriend", "C: Elephant", 2);
        addWord(w2);

        TheWord w3 = new TheWord("喫茶店", " '' コーヒーやケーキなどの食い物を頼むことができるビル '' でコーヒーを頼みました。", " '' At the building where you can order food such as coffee or cake '' , I ordered a coffee.", "A: Coffee", "B: Cafe", "C: Library", 2);
        addWord(w3);

        TheWord w4 = new TheWord("同級生", " '' 同じクラスの友達 '' と図書館に勉強しに行きました。", "Together with '' a friend from class '' we went to study at the library.", "A: Coworker", "B: Classmate", "C: Older sister", 2);
        addWord(w4);

        TheWord w5 = new TheWord("図書館", "同級生と '' 色々な本が集まってる場所 '' に勉強しに行きました。", "Together with my classmate we went to study at the '' a place where many different books are gathered ''", "A: Library", "B: Cafe", "C: Yesterday", 1);
        addWord(w5);

        TheWord w6 = new TheWord("猫", "このペットの鳴き声はニャーニャー。", "The voice of this pet goes Meow", "A: Bird", "B: Dog", "C: Cat", 3);
        addWord(w6);

        TheWord w7 = new TheWord("犬", "彼は公園に '' ワンワンって動物 '' と散歩しました。", "He took a walk with '' an animal who says woof '' at the park.", "A: Dog", "B: Flower", "C: Cake", 1);
        addWord(w7);

        TheWord w8 = new TheWord("公園", " 彼は'' 花や草が咲いていて楽しめる場所 '' に犬と散歩しました。", "He took a walk with his dog at the '' the place where flowers and grass are blooming and I can enjoy myself ''.", "A: Library", "B: Park", "C: Pineapple", 2);
        addWord(w8);

        TheWord w9 = new TheWord("ローストビーフ", "一昨日 '' オーブンで蒸し焼きにした肉 '' を焼いて食べました。", "The day before yesterday I made and ate some '' meat that is braised in the oven ''. ", "A: Cake", "B: Roast beef", "C: Water", 2);
        addWord(w9);

        TheWord w10 = new TheWord("お母さん", "両親の一人の女方", "One of the female version of my parents.", "A: Mother", "B: Grandmaster", "C: Dog", 1);
        addWord(w10);
    }

    private void addWord(TheWord theWord) {
        ContentValues cv = new ContentValues();
        cv.put(JpnWordTable.COLUMN_WORD, theWord.getTheWord());
        cv.put(JpnWordTable.COLUMN_SENTENCE, theWord.getWordSentence());
        cv.put(JpnWordTable.COLUMN_SENTENCE, theWord.getSentenceTranslation());
        cv.put(JpnWordTable.COLUMN_OPTION_1, theWord.getOption1());
        cv.put(JpnWordTable.COLUMN_OPTION_2, theWord.getOption2());
        cv.put(JpnWordTable.COLUMN_OPTION_3, theWord.getOption3());
        cv.put(JpnWordTable.COLUMN_ANSWER_NR, theWord.getAnswerNr());
        db.insert(JpnWordTable.TABLE_NAME, null, cv);

    }

    public List<TheWord> getAllWords() {
        List<TheWord> theWordList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + JpnWordTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                TheWord theWord = new TheWord();
                theWord.setTheWord(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_WORD)));
                theWord.setWordSentence(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_SENTENCE)));
                theWord.setSentenceTranslation(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_SENTENCE_TRANSLATION)));
                theWord.setOption1(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION_1)));
                theWord.setOption2(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION_2)));
                theWord.setOption3(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION_3)));
                theWord.setAnswerNr(c.getInt(c.getColumnIndex(JpnWordTable.COLUMN_ANSWER_NR)));
                theWordList.add(theWord);
            } while (c.moveToNext());
        }
        c.close();
        return theWordList;
    }
}
