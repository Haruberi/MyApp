package com.example.myapplication.Start;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Home.HomeActivity;
import com.example.myapplication.LanguageDB;
import com.example.myapplication.R;

import java.util.Locale;

public class SignInActivity extends AppCompatActivity {

    EditText signInUser, signInPass;
    static LanguageDB languageDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInUser = (EditText) findViewById(R.id.signInPageUser);
        signInPass = (EditText) findViewById(R.id.signInPagePass);
        languageDB = new LanguageDB(this);

        TextView forgotPasswordLink = findViewById(R.id.forgotPassword);
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ForgotPassActivity.class));
            }
        });
    }


    public void signInPageButton(View v) {
        String username = signInUser.getText().toString();
        String pass = signInPass.getText().toString();
        Cursor c = SignInActivity.languageDB.userLoginCheck(username);
        c.moveToFirst();

        if (c == null) {
            Toast.makeText(SignInActivity.this, "Invalid credentials for " + username, Toast.LENGTH_LONG).show();
            signInUser.setText("");
            signInPass.setText("");
        }
        else {
            String name = c.getString(0);
            String password = c.getString(1);

            if (pass.equals(password)) {
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                i.putExtra("name", name);
                startActivity(i);
            }
            else {
                Toast.makeText(SignInActivity.this, "Invalid credentials for user " + username, Toast.LENGTH_LONG).show();
            }
            signInUser.setText("");
            signInPass.setText("");
        }
    }
}
