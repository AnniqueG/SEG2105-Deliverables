package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
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

    public void clickEnter(View view) {
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

        if(username.equals("") || password.equals("")){ //Or if user not in database
            showMessage("Error", "Invalid username and password, try again");
            return;
        }

        if (role.equals("Instructor")) {
            Intent intent = new Intent(this, InstructorSuccessActivity.class);
            startActivity(intent);
        } else if (role.equals("Student")) {
            Intent intent = new Intent(this, StudentSuccessActivity.class);
            startActivity(intent);
        } else if (role.equals("Administrator")) {
            //Check if correct user and pass for admin
            //user: admin pass: admin123
            if (!username.equals("admin") || !password.equals("admin123")) {
                //Display error message

                showMessage("Error", "Admin login invalid");

            } else {
                Intent intent = new Intent(this, SuccessAdminActivity.class);
                startActivity(intent);

                //Add user here so it doesn't add invalid login attempts
                MyDBHandler dbHandler = new MyDBHandler(this);

                //Check if user already in database
                if(lookupUser(username) == false) {
                    //Add user to database
                    User user = new User(username, password, role);
                    dbHandler.addUser(user);
                }
                //If user already in database don't add another copy
            }


        }

    }
    
     /**
     * Check if user already in database
     * @param username
     * @return true if the user is already in database, otherwise false
     */
    public boolean lookupUser(String username){
        MyDBHandler dbHandler = new MyDBHandler(this);
        User user = dbHandler.findUser(userTXT.getText().toString());

        if(user != null){
            return true;
        }else{
           return false;
        }
    }

    
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static String getUserName(){
        return username;
    }

    public static String getPassWord(){
        return password;
    }


}
