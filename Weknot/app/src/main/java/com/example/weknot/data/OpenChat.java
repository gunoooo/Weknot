package com.example.weknot.data;

import com.google.gson.annotations.SerializedName;

public class OpenChat {

    @SerializedName("roomNumber")
    private String roomNumber;

    @SerializedName("masterName")
    private String masterName;

    @SerializedName("roomName")
    private String roomName;

    @SerializedName("roomPassword")
    private String roomPassword;

    @SerializedName("roomType")
    private String roomType;

    public String getRoomNumber() {

        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {

        this.roomNumber = roomNumber;
    }

    public String getMasterName() {

        return masterName;
    }

    public void setMasterName(String masterName) {

        this.masterName = masterName;
    }

    public String getRoomName() {

        return roomName;
    }

    public void setRoomName(String roomName) {

        this.roomName = roomName;
    }

    public String getRoomPassword() {

        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {

        this.roomPassword = roomPassword;
    }

    public String getRoomType() {

        return roomType;
    }

    public void setRoomType(String roomType) {

        this.roomType = roomType;
    }
}
