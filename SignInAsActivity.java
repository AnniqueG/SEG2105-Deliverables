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

    public static String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as);

        teachBTN = findViewById(R.id.teacher);
        studBTN = findViewById(R.id.student);
        adminBTN = findViewById(R.id.admin);
    }

    public static String getRole(){
        return role;
    }

    //Bring you to login page, no matter which button is clicked
    //Save which button was clicked for role variable
    public void login(View view){

        //MODIFIED TODAY
        //Save role
        //Go to login page
        if(view.getId() == R.id.teacher){
            role = "Instructor";
            //Go to teacher success page
            //Intent intent = new Intent(this, InstructorSuccessActivity.class);
            //startActivity(intent);

            //Set role to teacher
        }
        else if(view.getId() == R.id.student){
            role = "Student";
            //Go to student success page
//            Intent intent = new Intent(this, StudentSuccessActivity.class);
//            startActivity(intent);
            //Role is student
        }
        else if(view.getId() == R.id.admin){
            role = "Administrator";
            //Go to admin success page
//            Intent intent = new Intent(this, SuccessAdminActivity.class);
//            startActivity(intent);
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}