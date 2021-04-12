package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuizContract.*;
import com.example.myapplication.TheWord.*;

import androidx.annotation.Nullable;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="LanguageQuiz.db";
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
        //droppar den existerande databasen
        db.execSQL("DROP TABLE IF EXISTS " + WordTable.TABLE_NAME);
        //skapar en ny
        onCreate(db);
    }

    private void fillWordsTable(){
        TheWord tw1=new TheWord("A", "Bokstav A", "Word A", "A", "B", "C", 1 );
        addWord(tw1);


        TheWord tw2=new TheWord("B", "Bokstav B", "Word B", "A", "B", "C", 2 );
        addWord(tw2);

        TheWord tw3=new TheWord("C", "Bokstav C", "Word C", "A", "B", "C", 3 );
        addWord(tw3);

        TheWord tw4=new TheWord("D", "Bokstav D", "Word D", "D", "E", "F", 1 );
        addWord(tw4);

        TheWord tw5=new TheWord("E", "Bokstav A", "Word E", "E", "F", "G", 1 );
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
}
