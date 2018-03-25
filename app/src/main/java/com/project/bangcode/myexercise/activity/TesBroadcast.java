package com.project.bangcode.myexercise.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by bangcode on 2/21/18.
 */

public class TesBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "joss", Toast.LENGTH_SHORT).show();
    }
}
