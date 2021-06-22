package com.example.courseselectionapp;

public class Course {

    public String name;
    public int courseCode;
    public int capacity;
    public String hours;
    public String days;
    public String description;

    public Course(){

    }

    /**
     * When admin is creating a course
     * @param name course name
     * @param courseCode code of the course
     */
    public Course(String name, int courseCode){
        this.name = name;
        this.courseCode = courseCode;
    }

    /**
     * Second constructor, I think it will be needed for deliverable 2
     * @param name
     * @param courseCode
     * @param capacity
     * @param hours
     * @param days
     * @param description
     */
    public Course(String name, int courseCode, int capacity, String hours, String days, String description ){
        this.name = name;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.hours = hours;
        this.days = days;
        this.description = description;
    }


    //Setters and getters

    public String getCourseName(){
        return this.name;
    }

    public int getCourseCode(){
        return this.courseCode;
    }

    public void setCourseName(String name){
        this.name = name;
    }

    public void setCourseCode(int code){
        this.courseCode = code;
    }

}
