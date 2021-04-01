package com.example.myapplication3.db;

import android.provider.BaseColumns;

public final class RoomContract {

    private RoomContract(){

    }

    public static class RoomEntry implements BaseColumns{
        public static final String TABLE_NAME = "room";
        public static final String ROOM_NAME = "name";
        public static final String ROOM_ADDRESS = "address";
        public static final String ROOM_PRICE = "price";
        public static final String ROOM_ACREAGE = "acreage";


    }
}
