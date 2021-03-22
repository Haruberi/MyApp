package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText username1, password1;
    static LanguageDB languageDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username1 = (EditText) findViewById(R.id.signInPageUser);
        password1 = (EditText) findViewById(R.id.signInPagePass);
        languageDB = new LanguageDB(this);
    }

    //logic for sign in
    public void signIn(View v) {
        String username = username1.getText().toString();
        String password = password1.getText().toString();
        Cursor c = SignInActivity.languageDB.userLoginCheck(username);
        c.moveToFirst();

        if (c == null) {
            Toast.makeText(SignInActivity.this, "Invalid credentials for " + username, Toast.LENGTH_LONG).show();
            username1.setText("");
            password1.setText("");
        } else {
            String username2 = c.getString(0);
            String password2 = c.getString(1);
            if (password1.equals(password2)) {
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                i.putExtra("username", username2);
                startActivity(i);
            } else{
                Toast.makeText(SignInActivity.this,"Invalid credentials for user "+username,Toast.LENGTH_LONG).show();
            }
            username1.setText("");
            password1.setText("");
        }
    }
}