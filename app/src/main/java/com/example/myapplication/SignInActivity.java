package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Navigate to Forgot Password page
        TextView forgotPasswordLink = findViewById(R.id.forgotPassword);
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ForgotPassActivity.class));
            }
        });
    }

    //logic for sign in
    public void signInPageButton(View v) {
        String username = signInUser.getText().toString();
        String pass = signInPass.getText().toString();
        Cursor c = SignInActivity.languageDB.userLoginCheck(username);
        c.moveToFirst();

        if (c == null) {
            Toast.makeText(SignInActivity.this, "Invalid credentials for " + username, Toast.LENGTH_LONG).show();
            signInUser.setText("");
            signInPass.setText("");
        } else {
            //name and password from languageDB
            String name = c.getString(0);
            String password = c.getString(1);

            if (pass.equals(password)) {
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                i.putExtra("name", name);
                startActivity(i);
            } else {
                Toast.makeText(SignInActivity.this, "Invalid credentials for user " + username, Toast.LENGTH_LONG).show();
            }
            signInUser.setText("");
            signInPass.setText("");
        }
    }
}
