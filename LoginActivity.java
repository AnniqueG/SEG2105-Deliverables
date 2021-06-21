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

    /**
    * Method executes when the 'submit' button of the login page is clicked
    */
    public void clickEnter(View view) {

        username = userTXT.getText().toString();
        password = passTXT.getText().toString();


        String role = SignInAsActivity.getRole();

        //Check if username and password are valid (not just space)
        if(username.equals("") || password.equals("")){ //Or if user not in database
            showMessage("Error", "Invalid username and password, try again");
            return;
        }

        //Checks what role the user clicked on the 'sign in as' page and brings them to the 
        //Corresponding success page
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

            }
        }
        //Create an instance of the user database
        MyDBHandler dbHandler = new MyDBHandler(this);

        //Check if user already in database
        //If user already in database don't add another copy, otherwise add
        if(lookupUser(username) == false) { //Call method to check if user in database already
            //Add user to database
            User user = new User(username, password, role);
            dbHandler.addUser(user);
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

    /**
    * To dusplay the box with text
    */
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    /**
    *@return the users username
    */
    public static String getUserName(){
        return username;
    }

    /**
    *@return the users password
    */
    public static String getPassWord(){
        return password;
    }


}
