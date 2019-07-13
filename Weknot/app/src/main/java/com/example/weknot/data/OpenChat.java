package com.example.weknot.data;

import com.google.gson.annotations.SerializedName;

public class OpenChat {

    @SerializedName("id")
    private String roomId;

    @SerializedName("masterName")
    private String masterName;

    @SerializedName("roomName")
    private String roomName;

    @SerializedName("roomPassword") // null 이 올 수도 있음.
    private String roomPassword;

    @SerializedName("roomType")
    private String roomType;

    public String getRoomId() {

        return roomId;
    }

    public void setRoomId(String roomId) {

        this.roomId = roomId;
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
