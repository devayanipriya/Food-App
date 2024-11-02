package com.example.foodapp;

import static com.example.foodapp.Database.MEALTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlaceOrder extends AppCompatActivity implements View.OnClickListener, PlaceOrderAdapter.ChangeStatusListener{

    Database database;
    PlaceOrderAdapter poAdapter;
    SQLiteDatabase sqLiteDatabase;
    FloatingActionButton forward, backward;
    RecyclerView recyclerView;
    TextView displayPrice;
    Button placeOrder;
    private  ArrayList<OrderModel> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        database = new Database(this);
        forward = findViewById(R.id.poforwarbtn);
        backward = findViewById(R.id.pobackbtn);
        recyclerView = findViewById(R.id.recycleView);
        displayPrice = findViewById(R.id.displayPrice);
        placeOrder = findViewById(R.id.btnPlaceOrder);

        displayData();

        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
        placeOrder.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.poforwarbtn:{
                Intent intent = new Intent(PlaceOrder.this, SelectPaymentMethod.class);
                startActivity(intent);
            }break;
            case R.id.pobackbtn:{
                Intent intent = new Intent(PlaceOrder.this, ViewOrder.class);
                startActivity(intent);
            }break;
            case R.id.btnPlaceOrder:{

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
                poAdapter.setModel(models);
                poAdapter.notifyDataSetChanged();

                Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show();

                displayPrice.setText("");


            }break;
            default:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }

    }

    private void displayData() {
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("select * from "+MEALTABLE+"", null);
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        while(cursor.moveToNext()){
            String hotelName = cursor.getString(0);
            byte [] avatar = cursor.getBlob(1);
            String meal = cursor.getString(2);
            int price = cursor.getInt(3);

            displayPrice.setText("  Total price: "+price + "  ");
            orderModels.add(new OrderModel(hotelName, avatar, meal, price));

        }
        cursor.close();
        poAdapter = new PlaceOrderAdapter(getApplicationContext(), R.layout.single_data, orderModels, sqLiteDatabase);
        recyclerView.setAdapter(poAdapter);
    }

    @Override
    public void onItemChangeListener(int position, OrderModel model) {
        try {

        }catch (Exception e){

        }
    }
}
