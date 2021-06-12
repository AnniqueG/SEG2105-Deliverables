package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //
    Button loginBTN;
    Button signupBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        loginBTN = findViewById(R.id.login);
        signupBTN = findViewById(R.id.signup);
    }

    //
    public void openSignInAsActivity(View view){
        //Pass in this and the class you wish to open
        //We want to open the SignInAsActivity class
        Intent intent = new Intent(this, SignInAsActivity.class);
        startActivity(intent);
    }
}