package com.example.weknot_android.room.entity.OpenChat;

public class OpenChatRoom {

    private String roomNumber;

    private String roomName;

    private String masterName;

    private String roomPassword;

    private String roomType;

    public OpenChatRoom(String roomNumber, String roomName, String masterName, String roomPassword, String roomType) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.masterName = masterName;
        this.roomPassword = roomPassword;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
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
