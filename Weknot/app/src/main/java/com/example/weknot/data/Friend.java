package com.example.weknot.data;

import com.google.gson.annotations.SerializedName;

public class Friend {

    @SerializedName("friendId")
    private String id;

    @SerializedName("friendPicture")
    private String picture;

    @SerializedName("friendPoint")
    private int point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
