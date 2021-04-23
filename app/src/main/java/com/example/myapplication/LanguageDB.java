package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class LanguageDB extends SQLiteOpenHelper {

    Context c;
    public static String DBNAME="LanguageDB";
    public static int VERSION=1;

    public LanguageDB(Context context){
        super(context,DBNAME,null,VERSION);
        c=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String qry="create table LanguageUserTab(Name TEXT, Email TEXT, Username TEXT, Password TEXT)";
            db.execSQL(qry);
            Toast.makeText(c, "Table created successfully ", Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.e("LanguageDB","Table creation error",e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String name, String email, String username, String password) {
        try {
            String qry = "insert into LanguageUserTab values('" + name + "', '" + email + "','" + username + "', '" + password + "')";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(qry);
            Toast.makeText(c, "Glad you joined, " + name + "\n You are now an official member!", Toast.LENGTH_LONG).show();
            return true;
        } catch (Exception e) {
            Log.e("LanguageDB", "Insertion of record failed", e);
            return false;
        }
    }
//Login check
    public Cursor userLoginCheck(String username){
        try{
            String qry="Select Name, Password from LanguageUserTab where Username='"+username+"'";
            SQLiteDatabase db=getWritableDatabase();
            Cursor c=db.rawQuery(qry,null);
            return c;
        } catch(Exception e){
            Log.e("LanguageDB ", "Login error",e);
            return null;
        }
    }

}
