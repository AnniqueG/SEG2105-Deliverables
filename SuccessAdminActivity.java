package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Success page for admin
public class SuccessAdminActivity extends AppCompatActivity {

    Button manageBTN;
    Button deleteAccountBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_admin);

        //
        manageBTN = findViewById(R.id.manageaccounts);
        deleteAccountBTN = findViewById(R.id.deleteaccounts);
    }

    //When you click MANAGE COURSES BUTTON go to manage user acitivity
    public void manageCourses(View view){
        Intent intent = new Intent(this, ManageAdminActivity.class);
        startActivity(intent);
    }

    public void DeleteUserAccount(View view){
        Intent intent = new Intent(this, ManageUsersActivity.class);
        startActivity(intent);
    }
}