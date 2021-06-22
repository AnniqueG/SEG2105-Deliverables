package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Success page for admin
public class SuccessAdminActivity extends AppCompatActivity {

    Button manageBTN;
    Button deleteAccountBTN;
    TextView welcomeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_admin);

        //
        manageBTN = findViewById(R.id.manageCourses);
        deleteAccountBTN = findViewById(R.id.deleteaccounts);

        //
        String userName = LoginActivity.getUserName();
        String passWord = LoginActivity.getPassWord();

        welcomeTXT = findViewById(R.id.awelcomeMSG);
        welcomeTXT.setText("Welcome '" + userName + "'! You are logged in as 'Administrator'.");
    }

    /**
    * Brings you to the manage courses page when you click the 'manage courses' button
    */
    public void manageCourses(View view){
        Intent intent = new Intent(this, ManageAdminActivity.class);
        startActivity(intent);
    }

    /**
    * Brings you to the manage users page when you click the 'manage users' button
    */
    public void DeleteUserAccount(View view){
        Intent intent = new Intent(this, ManageUsersActivity.class);
        startActivity(intent);
    }
}
