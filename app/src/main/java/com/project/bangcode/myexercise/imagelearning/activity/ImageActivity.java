package com.project.bangcode.myexercise.imagelearning.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.project.bangcode.myexercise.R;

import java.io.FileDescriptor;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();

    private static final int REQUEST_OPEN_RESULT_CODE = 0;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.image1);

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_OPEN_RESULT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OPEN_RESULT_CODE && resultCode == RESULT_OK){
            Uri uri = null;

            if (data != null){
                uri = data.getData();
                try {
                    Bitmap bitmap = getBitmapFromUri(uri);
                    imageView.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException{
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return bitmap;
    }
}
