package com.example.myapplication3.manager;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.myapplication3.model.RoomInformation;
import com.example.myapplication3.model.RoomType;
import com.example.myapplication3.db.RoomContract;
import com.example.myapplication3.db.RoomDBHelper;

import static com.example.myapplication3.homescreen.HomeFragment.ROW_INDEX;

public class PreferenceManager {


    public static long saveRoomInformationToPreference(RoomInformation roomInformation, Activity activity) {
        RoomDBHelper roomDBHelper = new RoomDBHelper(activity);

        SQLiteDatabase sqLiteDatabase = roomDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(RoomContract.RoomEntry.ROOM_NAME, roomInformation.getRoomType().toString());
        values.put(RoomContract.RoomEntry.ROOM_ADDRESS, roomInformation.getRoomType().toString());
        values.put(RoomContract.RoomEntry.ROOM_PRICE, roomInformation.getPrice().toString());
        values.put(RoomContract.RoomEntry.ROOM_ACREAGE, roomInformation.getAcreage().toString());

        Log.e("Room Saved", roomInformation.getRoomType().toString());

        return sqLiteDatabase.insert(RoomContract.RoomEntry.TABLE_NAME, null, values);
    }

    public static RoomInformation getRoomInformationFromPreference(Activity activity) {
        RoomDBHelper roomDBHelper = new RoomDBHelper(activity);
        SQLiteDatabase sqLiteDatabase = roomDBHelper.getReadableDatabase();
        RoomInformation temp = null;

        String[] projection = {
                BaseColumns._ID,
                RoomContract.RoomEntry.ROOM_NAME,
                RoomContract.RoomEntry.ROOM_ADDRESS,
                RoomContract.RoomEntry.ROOM_PRICE,
                RoomContract.RoomEntry.ROOM_ACREAGE,

        };

        String selection = BaseColumns._ID + " = ?";
        long id = activity.getIntent().getLongExtra(ROW_INDEX, 1);
        String[] selectionArgs = {"" + id};

        Cursor cursor = sqLiteDatabase.query(
                RoomContract.RoomEntry.TABLE_NAME,
                projection,
                selection, selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String roomType = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_NAME));
            Log.e("Room type", roomType.toString());

            String price = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_PRICE));
            String acreage = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_ACREAGE));
            temp = new RoomInformation(
                    RoomType.valueOf(roomType),
                    acreage,
                    price
            );
        }
        cursor.close();
        return temp;

//        Log.e("TAG", cursor.toString());
    }
}
