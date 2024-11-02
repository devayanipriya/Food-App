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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RemoveMeal extends AppCompatActivity implements View.OnClickListener{

    Database database;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    FloatingActionButton button;
    SelectMealAdapter sAdapter;
    Button removebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_meal);

        database = new Database(this);
        recyclerView = findViewById(R.id.rmrecycleView);
        button = findViewById(R.id.rmbackbtn);
        removebtn = findViewById(R.id.rmbtn);

        removebtn.setOnClickListener(this);
        button.setOnClickListener(this);

        displayData();


        recyclerView.setLayoutManager(new LinearLayoutManager(this, recyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + MEALTABLE + "", null);
        ArrayList<MealModel> model = new ArrayList<>();
        while (cursor.moveToNext()) {
            String hotelName = cursor.getString(0);
            byte [] avatar = cursor.getBlob(1);
            String meal = cursor.getString(2);
            int price = cursor.getInt(3);
            String availability = cursor.getString(4);

            model.add(new MealModel(hotelName, avatar, meal, price, availability));
        }
        cursor.close();
        sAdapter = new SelectMealAdapter(this, R.layout.singledata, model, sqLiteDatabase);
        recyclerView.setAdapter(sAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rmbackbtn:{
                Intent intent = new Intent(RemoveMeal.this, AddMeal.class);
                startActivity(intent);
            }break;
            case R.id.rmbtn:{
                ArrayList<MealModel> model = new ArrayList<>();

                try{
                    for (MealModel models : model ) {
                        if (models.isSelect()) {
                            model.remove(models);
                            sqLiteDatabase = database.getReadableDatabase();
                            long delete = sqLiteDatabase.delete(MEALTABLE, "hotelName=?", null);
                            if (delete != -1) {
                                Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }catch (Exception e) {
                }

                //update data
                sAdapter.setModel(model);
                sAdapter.notifyDataSetChanged();
            }
            break;
            default:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}