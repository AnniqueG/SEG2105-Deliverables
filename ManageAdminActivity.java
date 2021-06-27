package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManageAdminActivity extends AppCompatActivity {

    Button viewBTN;
    Button editBTN;
    Button deleteBTN;
    Button createBTN;
    //
    EditText newNameTXT;
    EditText newCodeTXT;
    //
    EditText bottomNameTXT;
    EditText bottomCodeTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin);

        //Initialize all buttons and text views
        viewBTN = findViewById(R.id.viewAllCourses);
        editBTN = findViewById(R.id.editCourse);
        deleteBTN = findViewById(R.id.deleteCourse);
        createBTN = findViewById(R.id.createCourse);

        //
        bottomNameTXT = findViewById(R.id.name);
        bottomCodeTXT = findViewById(R.id.code);
        newNameTXT = findViewById(R.id.nName);
        newCodeTXT = findViewById(R.id.nCode);

    }

    /**
     * Runs when the 'view all courses' button is pressed
     * @param view button clicked
     */
    public void viewAllCourses(View view){
        MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
        Cursor res = cdbHandler.getAllCourses();

        //If no courses in database
        if(res.getCount() == 0){
            showMessage("Error", "No courses made");
            return;
        }

        //If the database has courses
        StringBuffer buff = new StringBuffer();
        while(res.moveToNext()){
            buff.append("Course Name: " + res.getString(1) + "\n");
            buff.append("Course Code: " + res.getString(2) + "\n\n");
        }
        showMessage("Database", buff.toString());
    }

    /**
     * Displays the message given
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
     * Create and Add a new course to the database
     * @param view
     */
    public void newCourse (View view){

        MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);

        //need name and code
        String courseName = bottomNameTXT.getText().toString();
        if(courseName.equals("") || bottomCodeTXT.getText().toString().equals("")){
            showMessage("Error", "Fields are invalid try again.");
        }
        else {
            int courseCode = Integer.parseInt(bottomCodeTXT.getText().toString());

            //Make new course
            Course course = new Course(courseName, courseCode);
            //Set the course name and code
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);

            //If the course is already in the database (by name only) the course isn't added again
            if(lookupCourse() == null) {
                cdbHandler.addCourse(course);

                bottomNameTXT.setText("");
                bottomCodeTXT.setText("");
                return;
            }
        }
    }

    /**
     * Delete the given course when clicked
     * @param view delete button clicked
     */
    public void deleteCourse(View view){
        MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
        boolean result = cdbHandler.deleteCourse(bottomNameTXT.getText().toString()); //Call delete method

        if(result){
            bottomCodeTXT.setText("Record Deleted"); //If found it deletes the course
            bottomNameTXT.setText("");
        }else{
           bottomCodeTXT.setText("No Match Found");//If not found
        }
    }

    /**
     * Edit a given course when the button is clicked
     * @param view edit button
     */
    public void editCourse(View view){
        //Check if course is in database
        Course course = lookupCourse();
        if(course == null){
            showMessage("Error", "No course found, create the course first");
            return;
        }
        //Need to remove course from database and add new editted one
        MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
        cdbHandler.deleteCourse(course.getCourseName()); //Delete current course

        String newName = newNameTXT.getText().toString();
        int newCode = Integer.parseInt(newCodeTXT.getText().toString());


        //Create new course
        Course newCourse = new Course(newName, newCode);
        cdbHandler.addCourse(newCourse); //Add it


        newNameTXT.setText("Course Update Complete");
        newCodeTXT.setText("");
        bottomNameTXT.setText("");
        bottomCodeTXT.setText("");

    }

    /**
     * Find a specific course in database
     * @return course
     */
    public Course lookupCourse(){
        MyDBHandlerCourse cdbHandler = new MyDBHandlerCourse(this);
        Course course = cdbHandler.findCourse(bottomNameTXT.getText().toString());
        return course;

    }
}