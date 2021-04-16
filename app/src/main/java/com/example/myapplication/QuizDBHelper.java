package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.myapplication.QuizContract.*;
import com.example.myapplication.TheWord.*;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class QuizDBHelper extends SQLiteOpenHelper {

    //Ny info för existing db till denna class

    private static String DB_NAME = "LanguageAppQuiz.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase langDatabase;
    private final Context langContext;
    private boolean langNeedUpdate = false;

    public QuizDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/Databaser";
        else
            DB_PATH = "C:/Users/annah/Desktop/LIA2/" + context.getPackageName() + "/Databaser";
        this.langContext = context;
        copyDataBase();
        this.getReadableDatabase();
    }

    public void upDateDataBase() throws IOException {
        if (langNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            langNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase())
            this.getReadableDatabase();
        this.close();
        try {
            copyDBFile();
        } catch (IOException langIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    private void copyDBFile() throws IOException {
        InputStream langInput = langContext.getAssets().open(DB_NAME);
        OutputStream langOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] langBuffer = new byte[1024];
        int langLength;
        while ((langLength = langInput.read(langBuffer)) > 0)
            langOutput.write(langBuffer, 0, langLength);
        langOutput.flush();
        langOutput.close();
        langInput.close();
    }

    public boolean openDataBase() throws SQLException {
        langDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return langDatabase != null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            langNeedUpdate = true;
    }

    //for quiz
    public List<TheWord> getAllWords(){
        List<TheWord> theWordList =new ArrayList<>();
        langDatabase=getReadableDatabase();
        Cursor c=langDatabase.rawQuery("SELECT * FROM " + WordTable.TABLE_NAME,null);

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

    //slutar med ny info här

    /*private static final String DATABASE_NAME="JapaneseQuizTable.db";
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
        if (oldVersion < 2) {
            TheWord tw1 = new TheWord("コーヒー", "彼は喫茶店で '茶色暖かい飲み物' を飲みました ", "He drank a 'brown warm drink' at the café.", "A: Coffee", "B: Cat", "AC: Milk", 1);
            addWord(tw1);

            TheWord tw2 = new TheWord("お水", "友達とジムに行ったときにいつも '冷たい飲み物' を持ってきました。", "When I went to the gym with my friend, I always brought a 'bottle of cold drink' with me.", "A: Warm tea", "B: Water", "C: Pineapple", 2);
            addWord(tw2);

            TheWord tw3 = new TheWord("ケーキ", "明日は彼氏の誕生日なので私は 'バッターから焼いてできたスイーツ' を作ります。", "Tomorrow is the birthday of my boyfriend so I am going to make 'a sweet that’s made from batter' for him.", "A: Flower", "B: Walk", "C: Cake", 3);
            addWord(tw3);

            TheWord tw4 = new TheWord("犬", "公園に 'ワンワンしていた動物' と一緒に散歩しました。", "I took a walk with 'an animal who said woof' in the park.", "A: Dog", "B: Elephant", "C: Rose", 1);
            addWord(tw4);

            TheWord tw5 = new TheWord("バナナ", "果物なら '一本黄色い果物' が好きです。", "If there is one fruit I like, it is 'one long yellow fruit'.", "A: Apple", "B: Banana", "C: Paper", 2);
            addWord(tw5);
        }
        if (oldVersion<3){
            TheWord tw1 = new TheWord("コーヒー", "彼は喫茶店で '茶色暖かい飲み物' を飲みました ", "He drank a 'brown warm drink' at the café.", "A: Coffee", "B: Cat", "C: Milk", 1);
            addWord(tw1);

            TheWord tw2 = new TheWord("お水", "友達とジムに行ったときにいつも '冷たい飲み物' を持ってきました。", "When I went to the gym with my friend, I always brought a 'bottle of cold drink' with me.", "A: Warm tea", "B: Water", "C: Pineapple", 2);
            addWord(tw2);

            TheWord tw3 = new TheWord("ケーキ", "明日は彼氏の誕生日なので私は 'バッターから焼いてできたスイーツ' を作ります。", "Tomorrow is the birthday of my boyfriend so I am going to make 'a sweet that’s made from batter' for him.", "A: Flower", "B: Walk", "C: Cake", 3);
            addWord(tw3);

            TheWord tw4 = new TheWord("犬", "公園に 'ワンワンしていた動物' と一緒に散歩しました。", "I took a walk with 'an animal who said woof' in the park.", "A: Dog", "B: Elephant", "C: Rose", 1);
            addWord(tw4);

            TheWord tw5 = new TheWord("バナナ", "果物なら '一本黄色い果物' が好きです。", "If there is one fruit I like, it is 'one long yellow fruit'.", "A: Apple", "B: Banana", "C: Paper", 2);
            addWord(tw5);
        }

    }

    private void fillWordsTable(){
        TheWord tw1=new TheWord("コーヒー", "彼は喫茶店で '茶色暖かい飲み' を飲みました ", "He drank a 'brown warm drink' at the café.", "A: Coffee", "B: Cat", "AC: Milk", 1 );
        addWord(tw1);

        TheWord tw2=new TheWord("お水", "友達とジムに行ったときにいつも '冷たい飲み' を持ってきました。", "When I went to the gym with my friend, I always brought a 'bottle of cold drink' with me.", "A: Warm tea", "B: Water", "C: Pineapple", 2 );
        addWord(tw2);

        TheWord tw3=new TheWord("ケーキ", "明日は彼氏の誕生日なので私は 'バッターから焼いてできたスイー' を作ります。", "Tomorrow is the birthday of my boyfriend so I am going to make 'a sweet that’s made from batter' for him.", "A: Flower", "B: Walk", "C: Cake", 3 );
        addWord(tw3);

        TheWord tw4=new TheWord("犬", "公園に 'ワンワンしていた動' と一緒に散歩しました。", "I took a walk with 'an animal who said woof' in the park.", "A: Dog", "B: Elephant", "C: Rose", 1 );
        addWord(tw4);

        TheWord tw5=new TheWord("バナナ", "果物なら '一本黄色い果' が好きです。", "If there is one fruit I like, it is 'one long yellow fruit'.", "A: Apple", "B: Banana", "C: Paper", 2 );
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

    }*/
