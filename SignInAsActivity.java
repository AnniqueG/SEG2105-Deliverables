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

    /**
    * Set role depending on which button is clicked
    * You can find which button is clicked using the 'view.getID()'
    */
    public void login(View view){

        if(view.getId() == R.id.teacher){
            role = "Instructor";
           
        }
        else if(view.getId() == R.id.student){
            role = "Student";
          
        }
        else if(view.getId() == R.id.admin){
            role = "Administrator";
            
        }

        //Go to LoginActivity page 
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
