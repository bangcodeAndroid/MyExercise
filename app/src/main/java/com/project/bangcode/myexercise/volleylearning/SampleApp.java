package com.project.bangcode.myexercise.volleylearning;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by bangcode on 1/6/18.
 */

public class SampleApp extends Application {

    private static SampleApp sampleApp;
    private RequestQueue volley;

    @Override
    public void onCreate() {
        super.onCreate();
        sampleApp = this;
    }

    public static SampleApp getInstance() {
        return sampleApp;
    }

    public RequestQueue getQueue() {
        if (volley == null) {
            volley = Volley.newRequestQueue(this);

        }
        return volley;
    }
}
