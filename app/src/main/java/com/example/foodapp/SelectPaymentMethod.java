package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SelectPaymentMethod extends AppCompatActivity implements View.OnClickListener, PlaceOrderAdapter.ChangeStatusListener{

    FloatingActionButton forward, backward;
    Button payButton;
    TextView cashtv, cardtv;
    private ArrayList<OrderModel> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment_method);

        forward = findViewById(R.id.spforwardbtn);
        backward = findViewById(R.id.spbackbtn);
        payButton = findViewById(R.id.btnpay);
        cashtv = findViewById(R.id.cashTv);
        cardtv = findViewById(R.id.cardTv);

        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.spforwardbtn:{
                Intent intent = new Intent(SelectPaymentMethod.this, HomePageMenu.class);
                startActivity(intent);
            }break;
            case R.id.spbackbtn:{
                Intent intent = new Intent(SelectPaymentMethod.this, PlaceOrder.class);
                startActivity(intent);
            }
            break;
            case R.id.cardTv:{
                Toast.makeText(this, "Make payment using card", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.cashTv:{
                Toast.makeText(this, "Pay using cash", Toast.LENGTH_SHORT).show();
            }
             case R.id.btnpay: {
                ArrayList<OrderModel> model = new ArrayList<>();
                try{
                    for (OrderModel models : model ){
                        if(models.isSelect()){
                            model.add(models);
                        }
                    }
                }catch (Exception e){

                }
                models = model;

                Toast.makeText(this, "Paid successfully", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onItemChangeListener(int position, OrderModel model) {

    }
}