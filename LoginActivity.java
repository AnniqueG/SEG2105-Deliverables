package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button submitBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submitBTN = findViewById(R.id.submit);
    }


    //What happens when we click enter
    //Go to success page
    //Check is user is already in database if not add them to it

    public void clickEnter(View view){
        //Depending on which button was clicked go to different page
        //Get role, if teacher go to teacher success page, admin -> adminSuccess, and student -> success
        //For now i'll make it just go to adminSuccess page
        Intent intent = new Intent(this, SuccessAdminActivity.class);
        startActivity(intent);

        //Check if user in database


    }


}