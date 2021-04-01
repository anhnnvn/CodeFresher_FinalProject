package com.example.myapplication3;

import android.util.Log;

import com.example.myapplication3.model.CategoryRoom;
import com.example.myapplication3.model.RoomInformation;
import com.example.myapplication3.model.RoomType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class NetWorkUtils {
    private static final String BASE_URL = "http://api.openweathermap.org/";

    public static ArrayList<RoomInformation> jsonToRoomList(String json) {
        ArrayList<RoomInformation> list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray roomList = jsonObject.getJSONArray("list");
            Log.e("Room List", roomList.length()+" ");
            for (int i = 0; i < roomList.length(); i++){
                JSONObject object = roomList.getJSONObject(i);
                JSONObject object1 = object.getJSONObject("temp");
                String price = getFormattedPrice(object1.getDouble("max"));
                String acreage = getFormattedAcreage(object1.getDouble("min"));

                JSONArray roomArrayList = object.getJSONArray("weather");
                JSONObject roomObject = roomArrayList.getJSONObject(0);
                RoomType roomType = RoomType.valueOf(roomObject.getString("main").toUpperCase());

                RoomInformation roomInformation = new RoomInformation(roomType, acreage, price);
                list.add(roomInformation);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static ArrayList<CategoryRoom> jsonToRoomCategory(String json) {
        ArrayList<CategoryRoom> list = new ArrayList<>();
        CategoryRoom item = new CategoryRoom("House");
        CategoryRoom item1 = new CategoryRoom("Apartment");
        CategoryRoom item2 = new CategoryRoom("Room");
        CategoryRoom item3 = new CategoryRoom("Villa");
        list.add(item);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        return list;

    }


        public static String getFormattedPrice(double temp) {
        return String.valueOf(Math.round(temp *100));
}

    public static String getFormattedAcreage(double temp) {
        return String.valueOf(Math.round(temp - 220));
    }

}
