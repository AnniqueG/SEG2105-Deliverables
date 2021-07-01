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
    EditText searchCodeTXT;
    EditText searchNameTXT;
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
        searchCodeTXT = findViewById(R.id.editTextSearchCourseCode);
        searchNameTXT = findViewById(R.id.editTextSearchCourseName);

    }

    public void viewAll(View view){
        MyDBHandlerInstructor dbHandler = new MyDBHandlerInstructor(this);
        Cursor res = dbHandler.getAllCourses();
        if(res.getCount() == 0){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()) {
            buff.append("Course Name: " + res.getString(1) + "\n");
            buff.append("Course Code: " + res.getString(2) + "\n");
            buff.append("Has instructor: " + res.getString(3) + "\n\n");
        }
        res.close();
        showMessage("Database", buff.toString());
    }

    public void viewMy(View view){
        MyDBHandlerInstructor iidbHandler = new MyDBHandlerInstructor(this);
        Cursor res = iidbHandler.getAllCourses();
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
        res.close();
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

            Course course = lookupCourseCourseDB(courseName);
            if(course == null) {
                showMessage("Error", "Course does not exist. Admin should create it first.");
            }

            else {
                //Check if instructor already assigned
                String hInstructor = course.hasInstructor;
                boolean hasInstructor = false;
                try {
                    hasInstructor = Boolean.parseBoolean(hInstructor);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                if(hasInstructor) {
                    showMessage("Error", "Course already has an instructor.");
                }
                else {
                    Course cc = new Course(courseName, Integer.parseInt(courseCode), Integer.parseInt(capacity), hours, days, description, this.username);
                    idbHandler.addCourse(cc);
                    // set has_instructor to true
                    MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
                    cdbHandler.deleteCourse(course.name);
                    cdbHandler.addCourseInst(course);

                    //d.addCourse(cc);

                    courseNameTXT.setText("");
                    courseCodeTXT.setText("");
                    daysTXT.setText("");
                    hoursTXT.setText("");
                    descriptionTXT.setText("");
                    capacityTXT.setText("");
                }
            }
        }
    }

    /**
     * Find a specific course in database
     */
    public void lookupCourse(View view){

        MyDBHandlerInstructor idbHandler = new MyDBHandlerInstructor(this);
        Course course = null;
        Cursor res = null;

        String save = searchCodeTXT.getText().toString();
        int searchCode = Integer.parseInt(save);
        String searchName = searchNameTXT.getText().toString();

        if(save.equals("") && searchName.equals("")){
            showMessage("Error","Please input a search parameter first.");
        }
        else if(!save.equals("") && !searchName.equals("")){
            showMessage("Error", "Only one search parameter can be entered at a time");
        }
        else if(!save.equals("")){
            res = idbHandler.findCourse(searchCode);

        }
        else if(!searchName.equals("")) {
            res = idbHandler.findCourse(searchName);
        }

        if(res == null){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()){
            buff.append("Course Name: " + res.getString(1) + "\n");
            buff.append("Course Code: " + res.getString(2) + "\n");
            buff.append("Has instructor: " + res.getString(3) + "\n\n");
        }
        res.close();
        showMessage("Database", buff.toString());

    }

    public Course lookupCourseCourseDB(String name){
        MyDBHandlerCourse dbHandler = new MyDBHandlerCourse(this);
        Course course = dbHandler.findCourse(name);
        return course;

    }

    public void unassignInstructor(View view){
        MyDBHandlerInstructor dbHandler = new MyDBHandlerInstructor(this);
        boolean result = dbHandler.deleteCourse(unassignTXT.getText().toString(), username);
        if(result){
            MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
            // three lines below is to change has_instructor to false
            Course c = cdbHandler.findCourse(unassignTXT.getText().toString());
            cdbHandler.deleteCourse(c.name);
            cdbHandler.addCourse(c);
            unassignTXT.setText("Course Unassigned");

        }else{
            unassignTXT.setText("No Match Found");
        }
    }


    public static boolean isAlphaNumeric(String myString) {
        return myString.matches("[A-Za-z0-9]+");
    }
    public static boolean isNumber(String myNumber) {
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