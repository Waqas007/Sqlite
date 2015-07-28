package com.example.waqas.listviewsql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Waqas on 5/27/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="mDatabase";
    //public static final String TABLE_NAME="user";
    public static final String TABLE_NAME="movies";
    public static final String KEY_MNAME="mname";
    public static final String KEY_MYEAR="myear";
    public static final String KEY_ID="id";

    public SQLiteDatabase db;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_MNAME+" TEXT, "+KEY_MYEAR+" INTEGER)";
        db.execSQL(CREATE_TABLE);

        String qury = "insert into " + TABLE_NAME + " ("+KEY_ID+"," + KEY_MNAME + "," + KEY_MYEAR + " ) "
                + " values ( '4','The Dark Knight','2008' )";
        db.execSQL(qury);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
