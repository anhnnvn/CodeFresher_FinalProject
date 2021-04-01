package com.example.myapplication3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class RoomData {

    @Expose
    @SerializedName("list")
    private List<WeatherModel> list;
    @Expose
    @SerializedName("cnt")
    private int cnt;
    @Expose
    @SerializedName("message")
    private double message;
    @Expose
    @SerializedName("cod")
    private String cod;
    @Expose
    @SerializedName("city")
    private City city;

    public List<WeatherModel> getList() {
        return list;
    }

    public void setList(List<WeatherModel> list) {
        this.list = list;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public static class WeatherModel {
        @Expose
        @SerializedName("snow")
        private double snow;
        @Expose
        @SerializedName("rain")
        private double rain;
        @Expose
        @SerializedName("pop")
        private int pop;
        @Expose
        @SerializedName("clouds")
        private int clouds;
        @Expose
        @SerializedName("deg")
        private int deg;
        @Expose
        @SerializedName("speed")
        private double speed;
        @Expose
        @SerializedName("weather")
        private java.util.List<Weather> weather;
        @Expose
        @SerializedName("humidity")
        private int humidity;
        @Expose
        @SerializedName("pressure")
        private int pressure;
        @Expose
        @SerializedName("feels_like")
        private FeelsLike feelsLike;
        @Expose
        @SerializedName("temp")
        private Temp temp;
        @Expose
        @SerializedName("sunset")
        private int sunset;
        @Expose
        @SerializedName("sunrise")
        private int sunrise;
        @Expose
        @SerializedName("dt")
        private int dt;

        public double getSnow() {
            return snow;
        }

        public void setSnow(double snow) {
            this.snow = snow;
        }

        public double getRain() {
            return rain;
        }

        public void setRain(double rain) {
            this.rain = rain;
        }

        public int getPop() {
            return pop;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }

        public int getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public FeelsLike getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(FeelsLike feelsLike) {
            this.feelsLike = feelsLike;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }
    }

    public static class Weather {
        @Expose
        @SerializedName("icon")
        private String icon;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("main")
        private String main;
        @Expose
        @SerializedName("id")
        private int id;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class FeelsLike {
        @Expose
        @SerializedName("morn")
        private double morn;
        @Expose
        @SerializedName("eve")
        private double eve;
        @Expose
        @SerializedName("night")
        private double night;
        @Expose
        @SerializedName("day")
        private double day;

        public double getMorn() {
            return morn;
        }

        public void setMorn(double morn) {
            this.morn = morn;
        }

        public double getEve() {
            return eve;
        }

        public void setEve(double eve) {
            this.eve = eve;
        }

        public double getNight() {
            return night;
        }

        public void setNight(double night) {
            this.night = night;
        }

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }
    }

    public static class Temp {
        @Expose
        @SerializedName("morn")
        private double morn;
        @Expose
        @SerializedName("eve")
        private double eve;
        @Expose
        @SerializedName("night")
        private double night;
        @Expose
        @SerializedName("max")
        private double max;
        @Expose
        @SerializedName("min")
        private double min;
        @Expose
        @SerializedName("day")
        private double day;

        public double getMorn() {
            return morn;
        }

        public void setMorn(double morn) {
            this.morn = morn;
        }

        public double getEve() {
            return eve;
        }

        public void setEve(double eve) {
            this.eve = eve;
        }

        public double getNight() {
            return night;
        }

        public void setNight(double night) {
            this.night = night;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }
    }

    public static class City {
        @Expose
        @SerializedName("timezone")
        private int timezone;
        @Expose
        @SerializedName("population")
        private int population;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("coord")
        private Coord coord;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public int getTimezone() {
            return timezone;
        }

        public void setTimezone(int timezone) {
            this.timezone = timezone;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Coord {
        @Expose
        @SerializedName("lat")
        private double lat;
        @Expose
        @SerializedName("lon")
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}
