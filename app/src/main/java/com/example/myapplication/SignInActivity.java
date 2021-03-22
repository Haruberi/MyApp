package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    EditText username, password;
    static LanguageDB languageDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = (EditText) findViewById(R.id.signInPageUser);
        password = (EditText) findViewById(R.id.signInPagePass);
        languageDB = new LanguageDB(this);
    }

    //logic for sign in
    public void signIn(View v){

    }
}