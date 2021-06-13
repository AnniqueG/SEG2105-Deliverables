package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StudentSuccessActivity extends AppCompatActivity {

    TextView welcomeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_success);

        String userName = LoginActivity.getUserName();
        String passWord = LoginActivity.getPassWord();

        welcomeTXT = findViewById(R.id.swelcomeMSG);
        welcomeTXT.setText("Welcome '" + userName + "'! You are logged in as 'Student'.");
    }

}