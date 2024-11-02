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

public class SelectMeal extends AppCompatActivity implements View.OnClickListener, SelectMealAdapter.ChangeStatusListener{

    Database database;
    SQLiteDatabase sqLiteDatabase;
    ImageView imageView;
    RecyclerView recyclerView;
    FloatingActionButton back;
    Button select;
    TextView txt;
    SelectMealAdapter smadapter;
    private  ArrayList<MealModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal);

        database = new Database(this);
        recyclerView = findViewById(R.id.smrecycleView);
        back = findViewById(R.id.smbackbtn);
        select = findViewById(R.id.smbtn);
        displayData();

        back.setOnClickListener(this);
        select.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("select * from "+MEALTABLE+"", null);
        ArrayList<MealModel> mealModels = new ArrayList<>();
        while(cursor.moveToNext()){
            String hotelName = cursor.getString(0);
            byte [] avatar = cursor.getBlob(1);
            String meal = cursor.getString(2);
            int price = cursor.getInt(3);
            String available = cursor.getString(4);

            mealModels.add(new MealModel(hotelName, avatar, meal, price, available));

        }
        cursor.close();
        smadapter = new SelectMealAdapter(getApplicationContext(), R.layout.single_data, mealModels, sqLiteDatabase);
        recyclerView.setAdapter(smadapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.smbackbtn:{
                Intent intent = new Intent(SelectMeal.this, HomePageMenu.class);
                startActivity(intent);
            }
            break;
            case R.id.smbtn:{
                ArrayList<MealModel> model = new ArrayList<>();
                try{
                    for (MealModel models : model ){
                        if(models.isSelect()){
                            model.add(models);
                        }
                    }
                }catch (Exception e){

                }
                models = model;
                smadapter.setModel(models);
                smadapter.notifyDataSetChanged();

            }
        }

    }

    @Override
    public void onItemChangeListener(int position, MealModel model) {
        try {

        }catch (Exception e){

        }
    }
}