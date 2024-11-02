package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePageMenu extends AppCompatActivity implements View.OnClickListener {

    Button selectMeal, viewOrder, placeOrder, selectPaymentMethod;
    FloatingActionButton hmback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_menu);

        selectMeal = findViewById(R.id.SelectMealbtn);
        viewOrder = findViewById(R.id.ViewOrderbtn);
        placeOrder = findViewById(R.id.PlaceOrderbtn);
        selectPaymentMethod = findViewById(R.id.SelectPaymentbtn);
        hmback = findViewById(R.id.hmback);

        selectMeal.setOnClickListener(this);
        viewOrder.setOnClickListener(this);
        placeOrder.setOnClickListener(this);
        selectPaymentMethod.setOnClickListener(this);
        hmback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SelectMealbtn:{
                Intent intent = new Intent(HomePageMenu.this, SelectMeal.class);
                startActivity(intent);
            }
            break;
            case R.id.ViewOrderbtn:{
                Intent intent = new Intent(HomePageMenu.this, ViewOrder.class);
                startActivity(intent);
            }
            break;
            case R.id.PlaceOrderbtn: {
                Intent intent = new Intent(HomePageMenu.this, PlaceOrder.class);
                startActivity(intent);
            }
            break;
            case R.id.SelectPaymentbtn:{
                Intent intent = new Intent(HomePageMenu.this, SelectPaymentMethod.class);
                startActivity(intent);
            }
            break;
            case R.id.hmback:{
                Intent intent = new Intent(HomePageMenu.this, UserLoginTab.class);
                startActivity(intent);
            }
            break;
            default:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}