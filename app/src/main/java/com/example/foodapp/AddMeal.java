package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddMeal extends AppCompatActivity implements View.OnClickListener {
    Button addMeal, removeMeal;
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        addMeal = findViewById(R.id.AddMealbtn);
        removeMeal = findViewById(R.id.RemoveMealbtn);
        back = findViewById(R.id.mealBckbtn);

        addMeal.setOnClickListener(this);
        removeMeal.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AddMealbtn:{
                Intent intent = new Intent(AddMeal.this, AddMealHome.class);
                startActivity(intent);
            }
            break;
            case R.id.RemoveMealbtn:{
                Intent intent = new Intent(AddMeal.this, RemoveMeal.class);
                startActivity(intent);
            }
            break;
            case R.id.mealBckbtn:{
                Intent intent = new Intent(AddMeal.this, LoginTab.class);
                startActivity(intent);
            }
            break;
            default:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}