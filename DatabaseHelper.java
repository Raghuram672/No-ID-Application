package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // constructor used to create database
    public DatabaseHelper(Context context,String dbname,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating table
        db.execSQL("create table users(name varchar(50),usn varchar(50),time varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //drop the table when version changes
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    //to save the data
    public long saveNewUserData(String name,String usn,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("usn",usn);
        cv.put("time",time);
        long recordid=db.insert("users",null,cv);
        return recordid;
    }
    //to view the data
    public String getAllRecords(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users",null);
        String output="";
        while(cursor.moveToNext()){
            String name=cursor.getString(0);
            String usn=cursor.getString(1);
            String time=cursor.getString(2);
            output=output+name+"-"+usn+"-"+time+"\n";

        }
        return output;
    }
}
