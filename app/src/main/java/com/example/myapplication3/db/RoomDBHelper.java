package com.example.myapplication3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RoomDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "Room.db";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + RoomContract.RoomEntry.TABLE_NAME;


    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + RoomContract.RoomEntry.TABLE_NAME + " (" +
                    RoomContract.RoomEntry._ID + " INTEGER PRIMARY KEY," +
                    RoomContract.RoomEntry.ROOM_NAME + " TEXT," +
                    RoomContract.RoomEntry.ROOM_ADDRESS + " TEXT," +
                    RoomContract.RoomEntry.ROOM_PRICE + " TEXT," +
                    RoomContract.RoomEntry.ROOM_ACREAGE + " TEXT)";

    public RoomDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE );
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
