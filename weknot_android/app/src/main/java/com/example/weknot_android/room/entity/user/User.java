package com.example.weknot_android.room.entity.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    private String id;

    private String name;

    private String birth;

    private String gender;

    private String phoneNumber;

    private String picture;

    private String intro;

    private String scope;

    private Integer point;

    public User(String id, String name, String birth, String gender, String phoneNumber, String picture, String intro, String scope, Integer point) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.intro = intro;
        this.scope = scope;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
