package com.example.myapplication3.model;

import com.example.myapplication3.R;

import java.io.Serializable;

public class RoomInformation implements Serializable {

    private RoomType roomType;
    private RoomType address;
    private String acreage;
    private String price;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoomInformation(RoomType roomType, String acreage, String price) {
        this.roomType = roomType;
        this.address = roomType;
        this.acreage = acreage;
        this.price = price;
        this.phone = "0868967660";
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getIconResId() {
        Integer resId = 0;
        switch (Integer.parseInt(price) % 8) {
            case 0:
                resId = R.drawable.item_1;
                break;
            case 1:
                resId = R.drawable.item_2;
                break;
            case 2:
                resId = R.drawable.item_3;
                break;
            case 3:
                resId = R.drawable.item_4;
                break;
            case 4:
                resId = R.drawable.item_8;
                break;
            case 5:
                resId = R.drawable.item_7;
                break;
            case 6:
                resId = R.drawable.item_6;
                break;
            case 7:
                resId = R.drawable.item_5;
                break;
        }
        return resId;
    }

    public RoomType getAddress() {
        return address;
    }

    public void setAddress(RoomType address) {
        this.address = address;
    }

    public Integer getRoomName() {
        Integer resId = 0;
        switch (roomType) {
            case CLEAR:
                resId = R.string.CLEAR;
                break;
            case CLOUDS:
                resId = R.string.CLOUDS;
                break;
            case FOG:
                resId = R.string.FOG;
                break;
            case LIGHT_CLOUDS:
                resId = R.string.LIGHT_CLOUDS;
                break;
            case LIGHT_RAIN:
                resId = R.string.LIGHT_RAIN;
                break;
            case RAIN:
                resId = R.string.RAIN;
                break;
            case SNOW:
                resId = R.string.SNOW;
                break;
            case STORM:
                resId = R.string.STORM;
                break;
        }
        return resId;
    }

    public Integer getRoomAddress() {
        Integer resId = 0;
        switch (roomType) {
            case CLEAR:
                resId = R.string.HK;
                break;
            case CLOUDS:
                resId = R.string.HBT;
                break;
            case FOG:
                resId = R.string.HĐ;
                break;
            case LIGHT_CLOUDS:
                resId = R.string.HM;
                break;
            case LIGHT_RAIN:
                resId = R.string.CG;
                break;
            case RAIN:
                resId = R.string.BĐ;
                break;
            case SNOW:
                resId = R.string.DĐ;
                break;
            case STORM:
                resId = R.string.TX;
                break;
        }
        return resId;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RoomInformation) {
            return ((RoomInformation) o).roomType == this.roomType;
        }
        return false;
    }
}
