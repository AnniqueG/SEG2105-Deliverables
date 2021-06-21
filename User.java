package com.example.courseselectionapp;

/**
* User class
*/
public class User {

    private String userName;
    private String password;
    private String role;

    public User(){

    }
    
    /**
    * Constructor 
    * User takes a username, password and role
    */
    public User(String userName, String password, String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }


    //Setters and getters

    /**
    * @return username
    */
    public String getUserName(){
        return this.userName;
    }

    /**
    *@return password
    */
    public String getPassword(){
        return this.password;
    }


    /**
    *@return role
    */
    public String getRole(){
        return this.role;
    }
}
