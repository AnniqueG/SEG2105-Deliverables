package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button submitBTN;
    EditText userTXT;
    EditText passTXT;

    private static String name;
    private static String username;
    private static String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submitBTN = findViewById(R.id.submit);
        userTXT = findViewById(R.id.username);
        passTXT = findViewById(R.id.password);
    }

    //What happens when we click enter
    //Go to success page
    //Check is user is already in database if not add them to it

    public void clickEnter(View view){
        //Depending on which button was clicked go to different page
        //Get role, if teacher go to teacher success page, admin -> adminSuccess, and student -> success
        //For now i'll make it just go to adminSuccess page

        //Get the role
        //MODIFIED TODAY
//
        //Save Name and Username
        //"Welcome 'firstname/username'! You are logged in as 'role'."

        username = userTXT.getText().toString();
        password = passTXT.getText().toString();


        String role = SignInAsActivity.getRole();

        if(role.equals("Instructor")){
            Intent intent = new Intent(this, InstructorSuccessActivity.class);
            startActivity(intent);
        }
        else if(role.equals("Student")){
            Intent intent = new Intent(this, StudentSuccessActivity.class);
            startActivity(intent);
        }
        else if(role.equals("Administrator")){
            Intent intent = new Intent(this, SuccessAdminActivity.class);
            startActivity(intent);
        }

        //Check if user in database if not throw invalid message (BONUS)
        //Otherwise add them to database


    }

    public static String getUserName(){
        return username;
    }

    public static String getPassWord(){
        return password;
    }


}