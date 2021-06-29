package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class InstructorSuccessActivity extends AppCompatActivity {

    String username;
    TextView welcomeTXT;
    EditText courseNameTXT;
    EditText courseCodeTXT;
    EditText daysTXT;
    EditText hoursTXT;
    EditText descriptionTXT;
    EditText capacityTXT;
    EditText unassignTXT;
    //Button assignCourseButton;
    //Button unassignCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_success);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        username = LoginActivity.getUserName();
        String password = LoginActivity.getPassWord();
        final ArrayList<String> courseList = new ArrayList<>();
        setTitle("INSTRUCTOR - COURSES");
        welcomeTXT = findViewById(R.id.iwelcomeMSG);
        welcomeTXT.setText("Welcome '" + username + "'! You are logged in as 'Instructor'.");
        courseNameTXT = findViewById(R.id.editTextCourseName);
        courseCodeTXT = findViewById(R.id.editTextCourseCode);
        daysTXT = findViewById(R.id.editTextDays);
        hoursTXT = findViewById(R.id.editTextHours);
        descriptionTXT = findViewById(R.id.editTextDescription);
        capacityTXT = findViewById(R.id.editTextCapacity);
        unassignTXT = findViewById(R.id.editTextCourseNameUnassign);

    }

    public void viewAll(View view){
        MyDBHandlerCourse dbHandler = new MyDBHandlerCourse(this);
        Cursor res = dbHandler.getAllCourses();
        if(res.getCount() == 0){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()) {
            buff.append("Course Name: " + res.getString(1) + "\n");
            buff.append("Course Code: " + res.getString(2) + "\n\n");
        }
        showMessage("Database", buff.toString());
    }

    public void viewMy(View view){
        MyDBHandlerInstructor dbHandler = new MyDBHandlerInstructor(this);
        Cursor res = dbHandler.getAllCourses();
        if(res.getCount() == 0){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()){
            // filter by instructor
            if (username.equals(res.getString(7))) {
                buff.append("Course Name: " + res.getString(1) + "\n");
                buff.append("Course Code: " + res.getString(2) + "\n");
                buff.append("Course Capacity: " + res.getString(3) + "\n");
                buff.append("Course Hours: " + res.getString(4) + "\n");
                buff.append("Course Days: " + res.getString(5) + "\n");
                buff.append("Course Description: " + res.getString(6) + "\n");
                buff.append("Course Instructor: " + res.getString(7) + "\n\n");
            }
        }
        showMessage("Database", buff.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    /**
     * Create and Add a new course to the instructor db
     * @param view
     */
    public void newCourse (View view){
        // saving to instructorDB.db
        MyDBHandlerInstructor idbHandler = new MyDBHandlerInstructor(this);

        String courseName = courseNameTXT.getText().toString();
        String courseCode = courseCodeTXT.getText().toString();
        String days = daysTXT.getText().toString();
        String hours = hoursTXT.getText().toString();
        String description = descriptionTXT.getText().toString();
        String capacity = capacityTXT.getText().toString();

        //Check if instructor is already assigned

        if(courseName.equals("") || courseCode.equals("") || days.equals("") || hours.equals("")
                || description.equals("") || capacity.equals("") ){
            showMessage("Error", "At least one field is empty, try again...");
        }
        // invalid input
        else if (!isAlphaNumeric(courseName) || !isNumber(courseCode) || !isNumber(capacity)
                || !isAlphaNumeric(days) || !isAlphaNumeric(hours) || !isAlphaNumeric(description)) {

            if (!isAlphaNumeric(courseName)){
                courseNameTXT.setError("Invalid string!");
            }
            if (!isNumber(courseCode)) {
                courseCodeTXT.setError("Invalid number!");
            }
            if (!isNumber(capacity)) {
                capacityTXT.setError("Invalid number!");
            }
            if (!isAlphaNumeric(days)) {
                daysTXT.setError("Invalid string!");
            }
            if (!isAlphaNumeric(hours)) {
                hoursTXT.setError("Invalid string!");
            }
            if (!isAlphaNumeric(description)) {
                descriptionTXT.setError("Invalid string!");
            }
        }
        else {
            //Make new course
            Course course = new Course(courseName, Integer.parseInt(courseCode), Integer.parseInt(capacity), hours, days, description, username);

            if(course == null) {
                showMessage("Error", "Can't create a course");
            }
            else {

                    idbHandler.addCourse(course);

                    courseNameTXT.setText("");
                    courseCodeTXT.setText("");
                    daysTXT.setText("");
                    hoursTXT.setText("");
                    descriptionTXT.setText("");
                    capacityTXT.setText("");
                    return;
                }
            }
        }

    /**
     * Find a specific course in database
     * @return course
     */
    public Course lookupCourse(String name){
        MyDBHandlerInstructor idbHandler = new MyDBHandlerInstructor(this);
        Course course = idbHandler.findCourse(name);
        return course;

    }

    public void unassignInstructor(View view){
        MyDBHandlerInstructor dbHandler = new MyDBHandlerInstructor(this);
        boolean result = dbHandler.deleteCourse(unassignTXT.getText().toString(), username);
        if(result){
            unassignTXT.setText("Course Unassigned");

        }else{
            unassignTXT.setText("No Match Found");
        }
    }
    public boolean isAlphaNumeric(String myString) {
        return myString.matches("[A-Za-z0-9]+");
    }
    public boolean isNumber(String myNumber) {
        double n;
        try {
            n = Integer.parseInt(myNumber);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return 0 < n && n < 2147483647;
    }

}
