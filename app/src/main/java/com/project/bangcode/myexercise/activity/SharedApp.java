package com.project.bangcode.myexercise.activity;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by bangcode on 2/21/18.
 */

public class SharedApp extends Application {

    private final String TAG = getClass().getSimpleName();
    private static SharedApp app;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        app= this;
        preferences = getSharedPreferences("tes",MODE_PRIVATE);

    /*save*/
        SharedPreferences.Editor e = preferences.edit();
        e.putString("token","ini token");
        e.apply();

    /*load*/
        String res = preferences.getString("token", "Tidak ada data");
        Log.d(TAG, "onCreate: "+res);


    }

    public static SharedApp getInstance(){
        return app;
    }

    public void saveUser(User user){
        SharedPreferences.Editor e = preferences.edit();
        Gson gson = new Gson();

        /*save*/
        e.putString("user",gson.toJson(user));
        e.apply();

    }

    public User loadUser(){
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString("user",null),User.class);
    }

    public boolean isLoggedIn(){
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString("user", null),User.class) != null;
    }


}
