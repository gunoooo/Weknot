package com.example.weknot_android.room.entity.user;

public class Friend {

    private String friendId;

    private String friendPicture;

    private Integer friendPoint;

    public Friend(String friendId, String friendPicture, Integer friendPoint) {
        this.friendId = friendId;
        this.friendPicture = friendPicture;
        this.friendPoint = friendPoint;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendPicture() {
        return friendPicture;
    }

    public void setFriendPicture(String friendPicture) {
        this.friendPicture = friendPicture;
    }

    public Integer getFriendPoint() {
        return friendPoint;
    }

    public void setFriendPoint(Integer friendPoint) {
        this.friendPoint = friendPoint;
    }
}
