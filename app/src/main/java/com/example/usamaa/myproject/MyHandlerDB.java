package com.example.usamaa.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyHandlerDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 209;
    private static final String DATABASE_NAME = "dbgoal";
    public static final String TABLE_NAME = "ACTIVITY";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCR";

    public MyHandlerDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("+
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addGoal(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, name);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_NAME, null, values); // Inserting Row
        db.close(); // Closing database connection
    }

    public void deleteGoal(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + "=\"" + name + "\";");
    }


    public void updateGoal(String old_name, String name,  String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_TITLE + "=\"" + name + "\", " + COLUMN_DESCRIPTION + "=\"" + description + "\" WHERE " + COLUMN_TITLE + "=\"" + old_name + "\";");
    }

    public ArrayList<String> getTitles(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT TITLE FROM " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> arrayList =  new ArrayList<String>();

        if(cursor != null) {
            cursor.moveToFirst();
            while(cursor.isAfterLast() != true) {
                arrayList.add(cursor.getString(cursor.getColumnIndex("TITLE")));
                cursor.moveToNext();
            }
        }
        db.close();
        return arrayList;
    }
}
