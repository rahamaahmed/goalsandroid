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

    private static final int DATABASE_VERSION = 202;
    private static final String DATABASE_NAME = "androidgoals.db";
    public static final String TABLE_GOALS = "RAHMA";
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_FREQUENCY = "FREQU";
    public static final String COLUMN_DESCRIPTION = "DESCR";

    public MyHandlerDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_GOALS + "("+
               // +  COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

    /*public void addGoal(String name, String frequency, String description){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_GOALS + "(" + COLUMN_NAME + ")" + "VALUES " + "('" + name + "');");
        Log.d("test", "INSERT INTO " + TABLE_GOALS + "(" + COLUMN_NAME + ", " + COLUMN_FREQUENCY + ", " + COLUMN_DESCRIPTION + ")" + " VALUES " + "('" + name + "', '" + frequency + "', '" + description + "');");
        //db.execSQL("INSERT INTO " + TABLE_GOALS + " (" + TABLE_GOALS+ ", " + COLUMN_FREQUENCY + ", " + COLUMN_DESCRIPTION + ")" + " VALUES " + "('" + name + "', '" + frequency + "', '" + description + "');");
    }*/

    public void addGoal(String name, String frequency, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_FREQUENCY, frequency);
        values.put(COLUMN_DESCRIPTION, description);
        // Inserting Row
        db.insert(TABLE_GOALS, null, values);
        db.close(); // Closing database connection
    }

    public void deleteGoal(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GOALS + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    /*public void updateGoal(String old_name, String new_name, String new_frequency, String new_description){
        SQLiteDatabase db = getWritableDatabase();
        Log.d("Hello", "AAAAAAAAAAAAAAZESXDFZRXTCFYGHYJTYCRUXESDCFGVHBKJGFCXSZTEXRYCTFUVYGBHNGVCFDXSEZ^X&CRVYGBHGVC X");
        db.execSQL("UPDATE " + TABLE_GOALS + " SET " +
                COLUMN_NAME + "=\"" + new_name + "\"" +
               // COLUMN_FREQUENCY + "=\"" + new_frequency + "\"" +
               // COLUMN_DESCRIPTION + "=\"" + new_description + "\"" +
                " WHERE " + COLUMN_NAME + "=\"" + old_name + "\";");
    }*/

    public void updateGoal(String old_name, String name, String frequency, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_GOALS + " SET " + COLUMN_NAME + "=\"" + name + "\", " + COLUMN_FREQUENCY +"=\"" + frequency + "\", " + COLUMN_DESCRIPTION + "=\"" + description + "\" WHERE " + COLUMN_NAME + "=\"" + old_name + "\";");
        /*ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_FREQUENCY, frequency);
        values.put(COLUMN_DESCRIPTION, description);
        // Update Row
        db.update(TABLE_GOALS, values, "NAME="+old_name, null);
        db.close(); // Closing database connection*/
    }

    public ArrayList<String> getDataTest(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT NAME FROM " + TABLE_GOALS + ";";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> arrayList =  new ArrayList<String>();

        if(cursor != null) {
            cursor.moveToFirst();
            while(cursor.isAfterLast() != true) {
                arrayList.add(cursor.getString(cursor.getColumnIndex("NAME")));
                cursor.moveToNext();
            }
        }
        db.close();
        return arrayList;
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
}
