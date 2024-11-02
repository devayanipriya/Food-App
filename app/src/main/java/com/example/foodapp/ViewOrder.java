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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase sqLiteDatabase;
    PlaceOrderAdapter poAdapter;
    Database database;
    RecyclerView recyclerView;
    FloatingActionButton forward, backward;
    TextView displayOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        database = new Database(this);
        forward = findViewById(R.id.voforwarbtn);
        backward = findViewById(R.id.vobackbtn);
        recyclerView = findViewById(R.id.vOrecycleView);
        displayOrder = findViewById(R.id.displayOrder);

        displayData();

        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));


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


            for(int i=0;i>orderModels.size();i++ ){
                price=i;
            }
            displayOrder.setText("  Total price: "+price + "  ");

            orderModels.add(new OrderModel(hotelName, avatar, meal, price));

        }
        cursor.close();
        poAdapter = new PlaceOrderAdapter(getApplicationContext(), R.layout.single_data, orderModels, sqLiteDatabase);
        recyclerView.setAdapter(poAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.voforwarbtn:{
                Intent intent = new Intent(ViewOrder.this, PlaceOrder.class);
                startActivity(intent);
            }break;
            case R.id.vobackbtn:{
                Intent intent = new Intent(ViewOrder.this, SelectMeal.class);
                startActivity(intent);
            }
        }

    }
}