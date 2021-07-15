package com.example.courseselectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database for courses
 */
public class Database extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "instructorDB.db";
    private static final String TABLE_COURSES= "courses";
    private static final String COLUMN_ID = "_id"; //Index 0
    private static final String COLUMN_NAME= "name"; //Index 1
    private static final String COLUMN_INSTRUCTOR_NAME = "iname"; // index 7

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_COURSES + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_NAME +  " TEXT," + COLUMN_INSTRUCTOR_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    public void addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, course.getCourseName());
        values.put(COLUMN_INSTRUCTOR_NAME, course.getInstructor());

        //insert into table and close
        db.insert(TABLE_COURSES, null, values);
        db.close();

    }


    public boolean deleteCourse(String name, String instructor){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product then delete
        //SELECT * FROM TABLE_COURSES WHERE COLUMN_NAME = courseName
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME + " =\"" + name + "\"" + " AND " + COLUMN_INSTRUCTOR_NAME + " =\"" + instructor + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    /**
     * Get all courses from database
     * @return database
     */
    public Cursor getAllCourses(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_COURSES, null);
        return res; //Instance of cursor

    }


    /**
     * If the course is in the database it is return, otherwise null is returned
     * @param name of course
     * @return instructor
     */
    public Course findCourse(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product
        //SELECT * FROM TABLE_PRODUCT WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + name + "\"";
        Cursor cursor = db.rawQuery(query, null);

        //Create an object and get the result
        Course course = new Course(name, "");
        if(cursor.moveToFirst()) {
            course.setCourseName(cursor.getString(1));
            course.setInstructor(cursor.getString(2));
            cursor.close();
        }else{
            return null;
        }
        db.close();
        return course;
    }

    public String findInstructor(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product
        //SELECT * FROM TABLE_PRODUCT WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        //Create an object and get the result
        Course course = new Course();
        String instructor;
        if(cursor.moveToFirst()) {
            course.setCourseName(cursor.getString(1));
            course.setInstructor(cursor.getString(2));
            instructor = course.getInstructor();
            cursor.close();
        }else{
            return "";
        }
        db.close();
        return instructor;
    }






}