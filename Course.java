package com.example.courseselectionapp;

public class Course {

    public String name;
    public int courseCode;
    public int capacity;
    public String hours;
    public String days;
    public String description;
    public String instructor;
    public String hasInstructor;


    public Course(){

    }

    public Course(String courseName, String instructor){
        this.name = courseName;
        this.instructor = instructor;
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
     * @param instructor
     */
    public Course(String name, int courseCode, int capacity, String hours, String days, String description, String instructor ){
        this.name = name;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.hours = hours;
        this.days = days;
        this.description = description;
        this.instructor = instructor;
    }


    //Setters and getters

    public String getCourseName(){
        return this.name;
    }

    public int getCourseCode(){
        return this.courseCode;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public String getHours(){
        return this.hours;
    }
    public String getDays(){
        return this.days;
    }
    public String getDescription(){
        return this.description;
    }
    public String getInstructor(){
        return this.instructor;
    }
    public String getHasInstructor(){
        return this.hasInstructor;
    }

    public void setCourseName(String name){
        this.name = name;
    }
    public void setCourseCode(int code){
        this.courseCode = code;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setHours(String hours){
        this.hours = hours;
    }
    public void setDays(String days){
        this.days = days;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setInstructor(String instructor){
        this.instructor = instructor;
    }
    public void setHasInstructor(String instructor){
        this.hasInstructor = instructor;
    }

}