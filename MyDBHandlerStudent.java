package com.example.courseselectionapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database for student courses
 */
public class MyDBHandlerStudent extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    private static final String TABLE_COURSES= "courses";
    private static final String COLUMN_ID = "_id"; //Index 0
    private static final String COLUMN_NAME= "name"; //Index 1
    private static final String COLUMN_CODE = "code"; //Index 2
    private static final String COLUMN_CAPACITY = "capacity"; //Index 3
    private static final String COLUMN_HOURS = "hours"; //Index 4
    private static final String COLUMN_DAYS = "days"; //Index 5
    private static final String COLUMN_DESCRIPTION = "description"; //Index 6
    private static final String COLUMN_INSTRUCTOR_NAME = "iname"; // index 7
    private static final String COLUMN_STUDENT = "sname"; //index 8

    public MyDBHandlerStudent(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_COURSES + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_CODE + " DOUBLE,"
                + COLUMN_CAPACITY + " INTEGER," + COLUMN_HOURS + " TEXT," + COLUMN_DAYS + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," + COLUMN_INSTRUCTOR_NAME + " TEXT," + COLUMN_STUDENT + " TEXT" + ")";
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
        values.put(COLUMN_CODE, course.getCourseCode());
        values.put(COLUMN_DAYS, course.getDays());
        values.put(COLUMN_HOURS, course.getHours());
        values.put(COLUMN_DESCRIPTION, course.getDescription());
        values.put(COLUMN_CAPACITY, course.getCapacity());
        values.put(COLUMN_INSTRUCTOR_NAME, course.getInstructor());
        values.put(COLUMN_STUDENT, LoginActivity.getUserName());

        //insert into table and close
        db.insert(TABLE_COURSES, null, values);
        db.close();

    }


    public boolean deleteCourse(String name){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product then delete
        //SELECT * FROM TABLE_COURSES WHERE COLUMN_NAME = courseName
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME + " =\"" + name +  "\"";
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
     * Searches for course in database by course name. If none found, returns null.
     * @param name of course
     * @return instructor
     */
    public Cursor findCourse(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product
        //SELECT * FROM TABLE_PRODUCT WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + name + "\"";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + name + "\"", null);
        return cursor;

//        //Create an object and get the result
//        Course course = new Course();
//        if(cursor.moveToFirst()) {
//            course.setCourseName(cursor.getString(1));
//            course.setInstructor(cursor.getString(2));
//            cursor.close();
//        }else{
//            return null;
//        }
//        db.close();
//        return course;
    }

    /**
     * Searches for course in database by course code. If none found, returns null.
     * @param code of course
     * @return instructor
     */
    public Cursor findCourse(int code){
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product
        //SELECT * FROM TABLE_PRODUCT WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + code + "\"";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +
                " = \"" + code + "\"", null);

        return cursor;

//        //Create an object and get the result
//        Course course = new Course();
//        if(cursor.moveToFirst()) {
//            course.setCourseName(cursor.getString(1));
//            course.setInstructor(cursor.getString(2));
//            cursor.close();
//        }else{
//            return null;
//        }
//        db.close();
//        return course;
    }






}