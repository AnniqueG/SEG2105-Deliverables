package com.example.courseselectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id"; //Index 0 ?
    private static final String COLUMN_USERNAME = "username"; //Index 1?
    private static final String COLUMN_ROLE = "role"; //Index 2?

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_ROLE + " DOUBLE" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUserName());
        values.put(COLUMN_ROLE, user.getRole());

        //insert into table and close
        db.insert(TABLE_USERS, null, values);
        db.close();

    }


    //delete from database
    public boolean deleteUser(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product then delete
        //SELECT * FROM TABLE_PRODUCTS WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =\""
                + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USERS, null);
        return res; //Instance of cursor

    }


}
