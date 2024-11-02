package com.example.foodapp;

import static com.example.foodapp.Database.USERTABLE;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class LoginFragment extends Fragment {

    Database database;
    SQLiteDatabase sqLiteDatabase;
    CustomModel customModel;
    EditText uemail, upassword;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        database = new Database(getActivity());
        uemail = (EditText) view.findViewById(R.id.vlEdEmail);
        upassword = (EditText) view.findViewById(R.id.vlEdPass);
        login = (Button) view.findViewById(R.id.vlLoginBtn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uemail.getText().toString().trim().isEmpty() || upassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuseremailpassword = database.checkuseremailandpassword(uemail.getText().toString().trim(), upassword.getText().toString().trim());
                    if(checkuseremailpassword == true){
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), AddMeal.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                    }
                }
                uemail.setText("");
                upassword.setText("");
            }
        });

        return view;
    }
}