package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInAsActivity extends AppCompatActivity {

    Button teachBTN;
    Button studBTN;
    Button adminBTN;

    public String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as);

        teachBTN = findViewById(R.id.teacher);
        studBTN = findViewById(R.id.student);
        adminBTN = findViewById(R.id.admin);
    }

    public String getRole(){
        return this.role;
    }

    //Bring you to login page, no matter which button is clicked
    //Save which button was clicked for role variable
    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        //Save role

    }
}