package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
*Class for the managing courses page 
*/
public class ManageAdminActivity extends AppCompatActivity {

    Button viewBTN;
    Button editBTN;
    Button deleteBTN;
    Button createBTN;
    EditText currNameTXT;
    EditText currCodeTXT;
    EditText newNameTXT;
    EditText newCodeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin);

        //Set all the buttons to their ids
        viewBTN = findViewById(R.id.viewAllCourses);
        editBTN = findViewById(R.id.editCourse);
        deleteBTN = findViewById(R.id.deleteCourse);
        createBTN = findViewById(R.id.createCourse);

        String currName = currNameTXT.getText().toString();
        String currCode = currCodeTXT.getText().toString();
        String newName = newNameTXT.getText().toString();
        String newCode = newCodeTXT.getText().toString();
    }
}
