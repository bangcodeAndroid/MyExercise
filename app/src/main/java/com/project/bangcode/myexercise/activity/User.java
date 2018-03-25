package com.project.bangcode.myexercise.activity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bangcode on 2/21/18.
 */

public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("token")
    private String token;

    public User(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
