package com.example.myapplication.Start;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LanguageDB;
import com.example.myapplication.R;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpName, signUpEmail, signUpUser, signUpPass;
    static LanguageDB languageDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpName = (EditText) findViewById(R.id.signUpPageName);
        signUpEmail = (EditText) findViewById(R.id.signUpPageEmail);
        signUpUser = (EditText) findViewById(R.id.signUpPageUser);
        signUpPass = (EditText) findViewById(R.id.signUpPagePass);

        languageDB = new LanguageDB(this);

        TextView alrReg = findViewById(R.id.alreadyRegistered);
        alrReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        }
        });
    }
    public void signUpPageButton(View v){
        String name=signUpName.getText().toString();
        String email=signUpEmail.getText().toString();
        String username=signUpUser.getText().toString();
        String password=signUpPass.getText().toString();
        boolean result=SignUpActivity.languageDB.insertData(name, email, username, password);

        if (result) {
            Toast.makeText(SignUpActivity.this,"Sign in to continue!",Toast.LENGTH_LONG).show();
            signUpName.setText("");
            signUpEmail.setText("");
            signUpUser.setText("");
            signUpPass.setText("");

            Intent i=new Intent(SignUpActivity.this,SignInActivity.class);
            startActivity(i);
        }
        else {
            AlertDialog.Builder ad=new AlertDialog.Builder(this);
            ad.setMessage("Wrong input");
            ad.show();
        }
        }
    }
