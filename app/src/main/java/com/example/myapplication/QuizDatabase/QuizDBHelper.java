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
    private static final int DB_VERSION = 1;

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
        onCreate(db);
    }

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



    //private static String DB_NAME = "LanguageAppQuiz.db";
    //    private static int DB_VERSION = 1;
    //    private static String DB_PATH = "/data/data/com.example.myapplication.QuizDatabase/databases";
    //
    //    private SQLiteDatabase langDataBase;
    //    private final Context langContext;
    //
    //    //Konstruktorn - takes and keeps a reference
    //    // of the passed context in order to access to
    //    // the application assets and resources
    //
    //    public QuizDBHelper(Context context) {
    //        super(context, DB_NAME, null, DB_VERSION);
    //        this.langContext = context;
    //        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
    //        //DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    //    }
    //    //        langContext=context;
    //    //        DB_PATH=context.getApplicationInfo().dataDir;
    //
    //
    //    //createDataBase() - Create empty database on
    //    // the system, and rewrites it with my own database.
    //    //By calling this method, an ampty database will be
    //    //created into the default system path of myapplication so
    //    // i am gonna be able to overwrite that database with my own database
    //    //
    //    //
    //    // ***** If the database does not exist, copy it from the assets ****
    //
    //    public void createDataBase() throws IOException {
    //        boolean dbExist = checkDataBase();
    //        if (dbExist) {
    //            //this.close();
    //        } else {
    //            this.getReadableDatabase();
    //            copyDataBase();
    //        }
    //    }
    //
    //    //try {
    //    //Copy the database from assets if it does not exist
    //
    //    //} catch (IOException mIOException) {
    //    //  throw new Error("ErrorCopyingDataBase");
    //    //                    throw new Error("Error copying database");
    //    //                    //System.out.println("Error copying data" + e.toString() + e.getMessage());
    //
    //
    //    //checkDataBase - Check if database file exist in databases folder
    //    //// För att undvika att man kopierar filen om och om igen varje gång jag öppnar applikationen
    //    //    // if DB exists = true, if not = false
    //
    //
    //    private boolean checkDataBase() {
    //        File dbFile = new File(DB_PATH + "/" + DB_NAME);
    //        return dbFile.exists();
    //    }
    //        /*SQLiteDatabase checkDB = null;
    //        try {
    //            String myPath = DB_PATH + "/" + DB_NAME;
    //            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    //            System.out.println("checkDB: :" + checkDB);
    //
    //        } catch (Exception e) {
    //            Log.i("SQLite Error", "database do not exist");
    //        }
    //        if (checkDB != null) {
    //            checkDB.close();
    //        }
    //
    //        return checkDB != null ? true : false;
    //    }*/
    //
    //
    //    //copyDataBase
    //    // - copy my database from my
    //    // local assets-folder to the just created empty
    //    // database in the system folder,
    //    // where it can be accessed and handled.
    //    // This is done by transfering bytestream
    //
    //    //InputStream - open my local db as the input stream
    //    //String outFileName - path to the just created ampty db
    //    //OutputStream - open the empty db as the output stream
    //    //byte[] - transfer bytes from the inputfile to the outputfile
    //
    //    //Copy the database from assets
    //
    //    private void copyDataBase() throws IOException {
    //        InputStream myInput = langContext.getAssets().open(DB_NAME);
    //        //InputStream myInput=langContext.getAssets().open("databases/" + DB_NAME);
    //        //InputStream myInput=langContext.getAssets().open(DB_NAME);
    //
    //        String outFileName = DB_PATH + "/" + DB_NAME;
    //        //OutputStream myOutput=new FileOutputStream(outFileName);
    //        OutputStream myOutput = new FileOutputStream(outFileName);
    //        byte[] buffer = new byte[1024];
    //        int myLength;
    //        while ((myLength = myInput.read(buffer)) > 0) {
    //            myOutput.write(buffer, 0, myLength);
    //        }
    //        myOutput.flush();
    //        myOutput.close();
    //        myInput.close();
    //    }
    //
    //
    //    //openDataBase - open the database
    //    //open database so we can query it
    //
    //    public void openDataBase() throws SQLException {
    //        String myPath = DB_PATH + "/" + DB_NAME;
    //        //langDataBase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    //        langDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    //    }
    //
    //    //langDataBase=SQLiteDatabase.openDatabase(DB_PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
    //    //return langDataBase!=null;
    //    //System.out.println("success");
    //
    //
    //    //close
    //    @Override
    //    public synchronized void close() {
    //        if (langDataBase != null) {
    //            langDataBase.close();
    //        }
    //        super.close();
    //    }
    //
    //    //getAllWords - Får alla ord till quiz, anropas i QuizActivity
    //    public List<TheWord> getAllWords() {
    //        List<TheWord> theWordList = new ArrayList<>();
    //        //langDataBase=getReadableDatabase();
    //
    //        String query = "SELECT * FROM " + JpnWordTable.TABLE_NAME;
    //        Cursor c = langDataBase.rawQuery(query, null);
    //
    //
    //        if (c.getCount() > 0) {
    //            if (c.moveToFirst()) {
    //                do {
    //                    TheWord theWord = new TheWord();
    //                    theWord.setTheWord(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_WORD)));
    //                    theWord.setWordSentence(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_SENTENCE)));
    //                    theWord.setSentenceTranslation(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_SENTENCE_TRANSLATION)));
    //                    theWord.setOption1(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION1)));
    //                    theWord.setOption2(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION2)));
    //                    theWord.setOption3(c.getString(c.getColumnIndex(JpnWordTable.COLUMN_OPTION3)));
    //                    theWord.setAnswerNr(c.getInt(c.getColumnIndex(JpnWordTable.COLUMN_ANSWER_NR)));
    //                    theWordList.add(theWord);
    //                } while (c.moveToNext());
    //            }
    //        }
    //
    //        //c.close();
    //        return theWordList;
    //    }
    //
    //    @Override
    //    public void onCreate(SQLiteDatabase db) {
    //
    //    }
    //
    //    @Override
    //    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //
    //    }
    //}










    //kk
    //
    //
    //private static String DB_NAME = "LanguageAppQuiz.db";
    //    private static String DB_PATH = "";
    //    private static final int DB_VERSION = 1;
    //
    //    public SQLiteDatabase langDatabase;
    //    private final Context langContext;
    //    private boolean langNeedUpdate = false;
    //
    //    public QuizDBHelper(Context context) {
    //
    //
    //
        //try {
        // InputStream inStream = context.getAssets().open("databases/LanguageAppQuiz.db");
        // } catch (IOException e) {
        //            e.printStackTrace();
        //        }


        //        super(context, DB_NAME, null, DB_VERSION);
        //        if (Build.VERSION.SDK_INT >= 17)
        //            DB_PATH = context.getApplicationInfo().dataDir + "/databases";
        //        else
        //            DB_PATH = "/app/src/main/assets/" + context.getPackageName() + "/databases";
        //        this.langContext = context;
        //        copyDataBase();
        //        this.getReadableDatabase();
        //    }
        //
        //
        //
        //    public void upDateDataBase() throws IOException {
        //        if (langNeedUpdate) {
        //            File dbFile = new File(DB_PATH + DB_NAME);
        //            if (dbFile.exists())
        //                dbFile.delete();
        //
        //            copyDataBase();
        //
        //            langNeedUpdate = false;
        //        }
        //    }
        //
        //    private boolean checkDataBase() {
        //        File dbFile = new File(DB_PATH + DB_NAME);
        //        return dbFile.exists();
        //    }
        //
        //    private void copyDataBase() {
        //        if (!checkDataBase())
        //            this.getReadableDatabase();
        //        this.close();
        //        try {
        //            copyDBFile();
        //        } catch (IOException langIOException) {
        //            throw new Error("ErrorCopyingDataBase");
        //        }
        //    }
        //
        //    private void copyDBFile() throws IOException {
        //        InputStream langInput = langContext.getAssets().open(DB_NAME);
        //        OutputStream langOutput = new FileOutputStream(DB_PATH + DB_NAME);
        //        byte[] langBuffer = new byte[1024];
        //        int langLength;
        //        while ((langLength = langInput.read(langBuffer)) > 0)
        //            langOutput.write(langBuffer, 0, langLength);
        //        langOutput.flush();
        //        langOutput.close();
        //        langInput.close();
        //    }
        //
        //    public boolean openDataBase() throws SQLException {
        //        langDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //        return langDatabase != null;
        //    }
        //
        //    @Override
        //    public synchronized void close() {
        //        if (langDatabase != null)
        //            langDatabase.close();
        //        super.close();
        //    }
        //
        //    @Override
        //    public void onCreate(SQLiteDatabase db) {
        //    }
        //
        //    @Override
        //    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //        if (newVersion > oldVersion)
        //            langNeedUpdate = true;
        //    }
        //

        //    //slutar med ny info här









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
