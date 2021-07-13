package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StudentSuccessActivity extends AppCompatActivity {

    TextView welcomeTXT;
    EditText courseNameTXT;
    EditText courseCodeTXT;
    private String name = LoginActivity.getUserName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_success);

        String userName = LoginActivity.getUserName();
        String passWord = LoginActivity.getPassWord();

        welcomeTXT = findViewById(R.id.swelcomeMSG);
        welcomeTXT.setText("Welcome '" + userName + "'! You are logged in as 'Student'.");
        courseNameTXT = findViewById(R.id.coursenameTXT);
        courseCodeTXT = findViewById(R.id.coursecodeTXT);
    }

    /**
     * Display database of all available courses
     * @param view
     */
    public void viewAllCourses(View view){
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

    /**
     * Database of all enrolled courses (including the course's capacity, instructor, days e.t.c.
     * @param view
     */
    public void viewMy(View view){
        MyDBHandlerStudent sDBHandler = new MyDBHandlerStudent(this);
        
        Cursor res = sDBHandler.getAllCourses();
        if(res.getCount() == 0){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()){


            if (name.equals(res.getString(8))) { //student name
                buff.append("Course Name: " + res.getString(1) + "\n");
                buff.append("Course Code: " + res.getString(2) + "\n");

               Course c = lookupCourseIntructorDB(res.getString(1));
//
                buff.append("Course Capacity: " + c.getCapacity() + "\n");
                buff.append("Course Hours: " + c.getHours() + "\n");
                buff.append("Course Days: " + c.getDays() + "\n");
                buff.append("Course Description: " + c.getDescription() + "\n");
                buff.append("Course Instructor: " + c.getInstructor()+ "\n\n");
            }
        }
        res.close();
        showMessage("Database", buff.toString());
    }

    /**
     * Find and return course in instructor database
     * To get access to all course details such as description
     * @param name
     * @return
     */
    public Course lookupCourseIntructorDB(String name){
        MyDBHandlerInstructor iDBHandler = new MyDBHandlerInstructor(this);
        Course course = iDBHandler.findCourseTwo(name);
        return course;

    }


    /**
     * Display a message
     * @param title
     * @param message
     */
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    /**
     * Find a course, used to test whether the course exists or not when unenrolling or enrolling
     * @param name
     * @return
     */
    public Course lookupCourseCourseDB(String name){
        MyDBHandlerCourse dbHandler = new MyDBHandlerCourse(this);
        Course course = dbHandler.findCourse(name);
        return course;

    }

    /**
     * Enroll in a course
     * @param view
     */
    public void newCourse (View view){
        // saving to instructorDB.db
        MyDBHandlerStudent sdbHandler = new MyDBHandlerStudent(this);

          //Check if instructor is already assigned
        String courseName = courseNameTXT.getText().toString();
            Course course = lookupCourseCourseDB(courseName);
            if(course == null) {
                showMessage("Error", "Course does not exist. Admin should create it first.");
            }

            else {
                //Check if instructor already assigned

                   //Add course to student database
                sdbHandler.addCourse(course);

                    //d.addCourse(cc);

                    courseNameTXT.setText("");
                    courseCodeTXT.setText("");

                }
            }

    /**
     * Remove a course from student's database (unenroll them)
     * @param view
     */
    public void unEnroll(View view){
        MyDBHandlerStudent dbHandler = new MyDBHandlerStudent(this);
        boolean result = dbHandler.deleteCourse(courseNameTXT.getText().toString());
        if(result){
            MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);

            courseNameTXT.setText("Course Unassigned");
            courseCodeTXT.setText("");

        }else{
           courseNameTXT.setText("No Match Found");
        }
    }
        }


