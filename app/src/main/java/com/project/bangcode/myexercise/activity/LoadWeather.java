package com.project.bangcode.myexercise.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bangcode on 2/21/18.
 */

public class LoadWeather extends AsyncTask<String,Integer,String> {
    private final String TAG = getClass().getSimpleName();

    private OnResultCallback callback;
    private Context context;
    ProgressDialog dialog;

    public LoadWeather(Context context) {
        this.context = context;
    }

    public void setCallback(OnResultCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setIndeterminate(false);
        dialog.setMessage("Please wait ...");
        dialog.setCancelable(false);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (dialog!=null){
            dialog.setProgress(values[0]);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder current =new StringBuilder();
        HttpURLConnection httpURLConnection =null;
        int p =0;

        try {
            URL url= new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int data = isr.read();
            while (data!= -1){
                current.append((char) data);
                data = isr.read();
                publishProgress(p++);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }

        return current.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog!=null){
            dialog.dismiss();
        }
        if (callback != null && s!=null){
             callback.onResult(s);
         }


    }

    public interface OnResultCallback{
        void onResult(String s);
    }
}
