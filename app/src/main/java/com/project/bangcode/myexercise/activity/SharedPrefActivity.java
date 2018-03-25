package com.project.bangcode.myexercise.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.bangcode.myexercise.R;

public class SharedPrefActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    Button tekan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        SharedApp.getInstance().saveUser(new User("wahyu","ini token"));
        Log.d(TAG, "onCreate: "+SharedApp.getInstance().loadUser().getName());
//        private String urlAddress = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
//        LoadWeather loadWeather = new LoadWeather(this);
//
//        loadWeather.setCallback(new LoadWeather.OnResultCallback() {
//            @Override
//            public void onResult(String s) {
//                Log.d(TAG, "onResult:"+s);
//            }
//        });
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("joss");
//        registerReceiver(receiver, filter);

        tekan = (Button) findViewById(R.id.tekan);
        tekan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.com");
                Log.d(TAG, "onClick: ditekan");
                sendBroadcast(intent);
            }
        });


//        loadWeather.execute("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");



    }

//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(ACTION_BROADCAST)){
//                Toast.makeText(context, "joss", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };

}
