package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InstructorSuccessActivity extends AppCompatActivity {

    TextView welcomeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_success);

        //
        String userName = LoginActivity.getUserName();
        String passWord = LoginActivity.getPassWord();

        welcomeTXT = findViewById(R.id.iwelcomeMSG);
        welcomeTXT.setText("Welcome '" + userName + "'! You are logged in as 'Instructor'.");
    }


}