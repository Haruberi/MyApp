package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Navigate to SignInActivity
        TextView alrReg = findViewById(R.id.alreadyRegistered);
        alrReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        }
        });
    }
    //Logic to call insertData of LanguageDB class
    public void signUp(View v){
        String name=signUpName.getText().toString();
        String email=signUpEmail.getText().toString();
        String username=signUpUser.getText().toString();
        String password=signUpPass.getText().toString();
        boolean result=SignUpActivity.languageDB.insertData(name, email, username, password);

        if (result) {
            Toast.makeText(SignUpActivity.this,"Welcome " + username + ", you are now registered!",Toast.LENGTH_LONG).show();
            //finish
            signUpName.setText("");
            signUpEmail.setText("");
            signUpUser.setText("");
            signUpPass.setText("");

            //Navigate to HomeActivity after user registered - *NOT HAPPENING*
            //startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
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
