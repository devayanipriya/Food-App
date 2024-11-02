package com.example.foodapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DBNAME = "JustOrderEats.db";
    public static final String USERTABLE = "user";
    public static final String MEALTABLE = "meal";
    public static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+USERTABLE+"(email text primary key, password text)");
        db.execSQL("create table "+MEALTABLE+"(hotelName text primary key, avatar blob, meal text, price text, availability text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+USERTABLE+"");
        db.execSQL("drop table if exists "+MEALTABLE+"");
        if(newVersion>oldVersion){
            onCreate(db);
        }
    }

    public Boolean checkuseremail(String useremail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+USERTABLE+" where email=? ", new String[] {useremail});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkuseremailandpassword(String useremail, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+USERTABLE+" where email=? and password=? ", new String[] {useremail,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
