package com.example.foodapp;

import static com.example.foodapp.Database.MEALTABLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class AddMealHome extends AppCompatActivity implements View.OnClickListener {

    Database database;
    SQLiteDatabase sqLiteDatabase;
    ImageView imageView;
    EditText hotelName, meal, price, availability;
    Button add;
    FloatingActionButton back;

    public static final int CAMERA_REQUEST = 100;
    public static final int STORAGE_REQUEST = 101;

    String [] cameraPermission;
    String [] storagePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_home);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        database = new Database(this);
        imageView = findViewById(R.id.mealImage);
        hotelName = findViewById(R.id.hotelName);
        meal = findViewById(R.id.meal);
        price = findViewById(R.id.price);
        availability = findViewById(R.id.availability);
        back = findViewById(R.id.bkbtn);
        add = findViewById(R.id.addbtn);

        imagePick();

        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bkbtn:{
                Intent intent = new Intent(AddMealHome.this, AddMeal.class);
                startActivity(intent);
            }
            break;
            case R.id.addbtn:{
                SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put("hotelName", hotelName.getText().toString().trim());
                cv.put("avatar", hotelName.getText().toString().trim());
                cv.put("meal", meal.getText().toString().trim());
                cv.put("price", price.getText().toString().trim());
                cv.put("availability", availability.getText().toString().trim());

//                sqLiteDatabase = database.getWritableDatabase();
//                Long insert = sqLiteDatabase.insert(MEALTABLE, null, cv);
                long insert = sqLiteDatabase.insertWithOnConflict(database.MEALTABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                sqLiteDatabase.close();

//                if (insert != null){
//                    Toast.makeText(this, "Meal added successfully", Toast.LENGTH_SHORT).show();
//                }

            }
        }

//        imagePick();

        hotelName.setText("");
        imageView.setImageResource(R.drawable.ic_launcher_background);
        meal.setText("");
        price.setText("");
        availability.setText("");
    }

    private void imagePick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int avatar = 0;
                if (avatar == 0){
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if (avatar == 1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {
        boolean results = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean results2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return results && results2;
    }

    private byte[] ImageViewToByte(ImageView avatar) {

        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accept = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storage_accept = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accept && storage_accept) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Enable Camera and Storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST:{
                if (grantResults.length > 0) {
                    boolean storage_accept = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storage_accept) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Enable Storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(imageView);
            }
        }
    }
}