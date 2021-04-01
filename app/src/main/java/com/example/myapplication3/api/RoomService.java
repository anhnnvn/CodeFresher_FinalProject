package com.example.myapplication3.api;
import com.example.myapplication3.model.RoomData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoomService {
    @GET("data/{path}/forecast/daily")
    Call<RoomData> getWeatherData(
            @Path("path") String path,
            @Query("id") String id,
            @Query("appid") String token);
}
