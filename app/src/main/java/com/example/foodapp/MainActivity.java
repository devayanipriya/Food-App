package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button  vendor, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vendor = findViewById(R.id.vendorbtn);
        user = findViewById(R.id.userbtn);


        vendor.setOnClickListener(this);
        user.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.vendorbtn:{
                Intent intent = new Intent(MainActivity.this, LoginTab.class);
                startActivity(intent);
            }
            break;
            case R.id.userbtn:{
                Intent intent = new Intent(MainActivity.this, UserLoginTab.class);
                startActivity(intent);
            }
            break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}