package com.project.bangcode.myexercise.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.project.bangcode.myexercise.R;
import com.project.bangcode.myexercise.volleylearning.SampleApp;
import com.project.bangcode.myexercise.volleylearning.VolleyMultiPartRequest;
import com.project.bangcode.myexercise.model.ModelData;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private static final String TAG = VolleyActivity.class.getSimpleName();
    ImageView thumb;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);


        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://174.138.20.113:3636/api/v1/test",
                        VolleyActivity.this, VolleyActivity.this) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("id", "123456789");
                        return super.getParams();
                    }
                };
                SampleApp.getInstance().getQueue().add(stringRequest);
            }
        });

        Button buttonget = (Button) findViewById(R.id.uploadButton);
        buttonget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(VolleyActivity.this);
            }
        });

        loading = new ProgressDialog(this);
        loading.setMessage("Please Wait");
        loading.setCancelable(false);

        thumb = (ImageView) findViewById(R.id.thumbnail);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = CropImage.getPickImageResultUri(this, data);
            CropImage.activity(uri)
                    .setRequestedSize(1024, 1024)
                    .setActivityTitle("Bisa Coba Crop")
                    .start(VolleyActivity.this);

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            thumb.setImageURI(result.getUri());
            thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            try{
                Bitmap uploadBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                uploadImage(uploadBitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Ora ono request", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

        Gson gson = new Gson();
        ModelData modelData = gson.fromJson(response, ModelData.class);
        Log.d(TAG, modelData.toString());
    }

    public void uploadImage(final Bitmap bitmap) {
        loading.show();
        VolleyMultiPartRequest request = new VolleyMultiPartRequest(Request.Method.POST, "http://128.199.171.175/api/pictures",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        loading.dismiss();
                        Toast.makeText(VolleyActivity.this, "Berhasil Upload Foto", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(VolleyActivity.this, "Gagal Upload Foto", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                VolleyMultiPartRequest.DataPart dataPart = new VolleyMultiPartRequest.DataPart("image.jpg",VolleyMultiPartRequest
                .getFileDataFromDrawable(VolleyActivity.this, bitmap), "image/jpeg");
                HashMap<String, VolleyMultiPartRequest.DataPart> params = new HashMap<>();
                params.put("picture",dataPart);
                return params;
            }
        };

        SampleApp.getInstance().getQueue().add(request);
    }
}
