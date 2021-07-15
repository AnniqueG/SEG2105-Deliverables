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
    EditText searchNameTXT;
    EditText searchCodeTXT;
    EditText searchDayTXT;
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
        searchCodeTXT = findViewById(R.id.editTextSearchCourseCodeStudent);
        searchNameTXT = findViewById(R.id.editTextSearchCourseNameStudent);
        searchDayTXT = findViewById(R.id.editTextSearchCourseDayStudent);
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
     * Database of all enrolled courses
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
            // filter by instructor
            if (name.equals(res.getString(8))) { //student name
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

    public Course lookupCourseCodeCourseDB(int code){
        MyDBHandlerCourse dbHandler = new MyDBHandlerCourse(this);
        Course course = dbHandler.findCourseCode(code);
        return course;
    }
    public Course lookupCourseDayCourseDB(String day){
        // instructor_db has day info
        MyDBHandlerInstructor dbHandler = new MyDBHandlerInstructor(this);
        Course course = dbHandler.findCourseDay(day);
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
//            // three lines below is to change has_instructor to false
//            Course c = cdbHandler.findCourse(unassignTXT.getText().toString());
//            cdbHandler.deleteCourse(c.name);
//            cdbHandler.addCourse(c);
            courseNameTXT.setText("Course Unassigned");
            courseCodeTXT.setText("");

        }else{
           courseNameTXT.setText("No Match Found");
        }
    }

    /**
     * Find a specific course in database
     */
    public void lookupCourse(View view){
// course code course name day of the week
        if(searchCodeTXT.getText().toString().equals("")) {
            //Searching by course code
//            String code = searchCodeTXT.getText().toString();
//            Course course = lookupCourseCourseDB(name);
//            String str = Boolean.toString(name.equals(""));
//            showMessage("Course:", name + course.getCourseCode());
        }else {
            //Search by course name
            int name = Integer.parseInt(searchCodeTXT.getText().toString());
            Course course = lookupCourseCodeCourseDB(name);
            //String str = Boolean.toString(name.equals(""));
            showMessage("Course:", course.getCourseName()+ "" + name);
        }

    }
    /**
     * Find a specific course in database
     */
    public void lookupCourse2(View view){
// course code course name day of the week
        if(searchNameTXT.getText().toString().equals("")) {
            //Searching by course code
//            String code = searchCodeTXT.getText().toString();
//            Course course = lookupCourseCourseDB(name);
//            String str = Boolean.toString(name.equals(""));
//            showMessage("Course:", name + course.getCourseCode());
        }else {
            //Search by course name
            String name = searchNameTXT.getText().toString();
            Course course = lookupCourseCourseDB(name);
            String str = Boolean.toString(name.equals(""));
            showMessage("Course:", name + "" + course.getCourseCode());
        }

    }
    /**
     * Find a specific course in database
     */
    public void lookupCourse3(View view){
// course code course name day of the week
        if(searchDayTXT.getText().toString().equals("")) {
            //Searching by course code
//            String code = searchCodeTXT.getText().toString();
//            Course course = lookupCourseCourseDB(name);
//            String str = Boolean.toString(name.equals(""));
//            showMessage("Course:", name + course.getCourseCode());
        }else {
            //Search by course name
            String name = searchDayTXT.getText().toString();
            Course course = lookupCourseDayCourseDB(name);
            String str = Boolean.toString(name.equals(""));
            showMessage("Course:", name + " " + course.getCourseName() + course.getCourseCode());
        }

    }



        }




