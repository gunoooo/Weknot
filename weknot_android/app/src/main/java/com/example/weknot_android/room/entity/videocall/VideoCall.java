package com.example.weknot_android.room.entity.videocall;

import com.example.weknot_android.room.entity.user.User;

public class VideoCall {

    private User user;

    private Integer roomIdx;

    public VideoCall(User user, Integer roomIdx) {
        this.user = user;
        this.roomIdx = roomIdx;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRoomIdx() {
        return roomIdx;
    }

    public void setRoomIdx(Integer roomIdx) {
        this.roomIdx = roomIdx;
    }
}
