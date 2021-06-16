package com.example.courseselectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ManageUsersActivity extends AppCompatActivity {
    
    EditText usernameTXT;
    EditText passWordTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
        //
        usernameTXT = findViewById(R.id.aUserName);
        passWordTXT = findViewById(R.id.aPassword);
    }
    
    public void viewAll(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Cursor res = dbHandler.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data in Database");
            return;
        }

        StringBuffer buff = new StringBuffer();
        //Get all Data using res object
        while(res.moveToNext()){

            buff.append("Username: " + res.getString(1) + "\n");
            buff.append("Role: " + res.getString(2) + "\n\n");
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

    public void deleteUser(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        boolean result = dbHandler.deleteUser(usernameTXT.getText().toString());

        if(result){
            usernameTXT.setText("Record Deleted");
            passWordTXT.setText("");

        }else{
            usernameTXT.setText("No Match Found");
        }
    }
}
