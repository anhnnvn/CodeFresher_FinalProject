package com.example.myapplication3.model;

import com.example.myapplication3.R;

public class CategoryRoom {
    private String name;

    public CategoryRoom(String name) {
        this.name = name;
    }

    public Integer getImage() {
        Integer resId = 0;
        switch (name) {
            case "Apartment":
                resId = R.drawable.item_2;
                break;
            case "House":
                resId = R.drawable.item_5;
                break;
            case "Room":
                resId = R.drawable.item_7;
                break;
            case "Villa":
                resId = R.drawable.item_3;
                break;
        }
        return resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
