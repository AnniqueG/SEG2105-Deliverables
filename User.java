package com.example.courseselectionapp;


public class User {

    private String userName;
    private String password;
    private String role;

    public User(){

    }

    public User(String userName, String password, String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }


    //Setters and getters

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }


    public String getRole(){
        return this.role;
    }

    public void setUserName(String username){
        this.userName = username;
    }
}
