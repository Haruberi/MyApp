package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuizContract.*;

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
    }

    //Om man vill lägga till fler columner måste man meddela det här i onUpgrade samt skriva version 1 till 2 i database_version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //droppar den existerande databasen
        db.execSQL("DROP TABLE IF EXISTS " + WordTable.TABLE_NAME);
        //skapar en ny
        onCreate(db);
    }
}
