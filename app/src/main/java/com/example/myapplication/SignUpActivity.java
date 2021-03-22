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

    EditText name1, email1, username1, password1;
    static LanguageDB languageDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name1 = (EditText) findViewById(R.id.signUpPageName);
        email1 = (EditText) findViewById(R.id.signUpPageEmail);
        username1 = (EditText) findViewById(R.id.signUpPageUser);
        password1 = (EditText) findViewById(R.id.signUpPagePass);

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
        String name=name1.getText().toString();
        String email=email1.getText().toString();
        String username=username1.getText().toString();
        String password=password1.getText().toString();
        boolean result=SignUpActivity.languageDB.insertData(name, email, username, password);

        if (result) {
            Toast.makeText(SignUpActivity.this,"Welcome " + username + ", you are now registered!",Toast.LENGTH_LONG).show();
            //finish
            name1.setText("");
            email1.setText("");
            username1.setText("");
            password1.setText("");
            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
        }
        else {
            AlertDialog.Builder ad=new AlertDialog.Builder(this);
            ad.setMessage("Wrong input");
            ad.show();
        }
        }
    }
