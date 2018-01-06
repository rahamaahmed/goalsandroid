package com.example.usamaa.myproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyHandlerDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "goals_datatestone.db";
    public static final String TABLE_GOALS = "goals_tabletestone";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FREQUENCY = "frequency";
    public static final String COLUMN_DESCRIPTION = "description";

    public MyHandlerDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_GOALS + "("
                +  COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FREQUENCY + "TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + "TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        onCreate(db);
    }

    public void addGoal(String name, String frequency, String description){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_GOALS + "(" + COLUMN_NAMEcgnbfvdkbdkdlulhvhcjhghrhclhdign + ")" + "VALUES " + "('" + name + "');");
        Log.d("Hello", "AAAAAAAAAAAAAAZESXDFZRXTCFYGHYJTYCRUXESDCFGVHBKJGFCXSZTEXRYCTFUVYGBHNGVCFDXSEZ^X&CRVYGBHGVC X");
        Log.d("test", "INSERT INTO " + TABLE_GOALS + "(" + COLUMN_NAME + ", " + COLUMN_FREQUENCY + ", " + COLUMN_DESCRIPTION + ")" + " VALUES " + "('" + name + "', '" + frequency + "', '" + description + "');");
        //db.execSQL("INSERT INTO " + TABLE_GOALS + " (" + TABLE_GOALS+ ", " + COLUMN_FREQUENCY + ", " + COLUMN_DESCRIPTION + ")" + " VALUES " + "('" + name + "', '" + frequency + "', '" + description + "');");
    }

    public void deleteGoal(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GOALS + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    public void updateGoal(String old_name, String new_name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_GOALS + " SET " + COLUMN_NAME + "=\"" + new_name + "\" WHERE "
                + COLUMN_NAME + "=\"" + old_name + "\";");
    }

    public ArrayList<String> getData() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT frequency FROM " + TABLE_GOALS + ";";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> arrayList =  new ArrayList<String>();

        if(cursor != null) {
            cursor.moveToFirst();
            while(cursor.isAfterLast() != true) {
                arrayList.add(cursor.getString(cursor.getColumnIndex("frequency")));
                cursor.moveToNext();
            }
        }
        db.close();
        return arrayList;
    }
}
