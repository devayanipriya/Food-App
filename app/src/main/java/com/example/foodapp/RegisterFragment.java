package com.example.foodapp;

import static android.content.ContentValues.TAG;

import static com.example.foodapp.Database.USERTABLE;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    Database database;
    SQLiteDatabase sqLiteDatabase;
    EditText email, password, confpass;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        database = new Database(getActivity());
        email = (EditText) view.findViewById(R.id.rEdName);
        password = (EditText) view.findViewById(R.id.rEdPass);
        confpass = (EditText) view.findViewById(R.id.rEdConfPass);
        submit = (Button) view.findViewById(R.id.rSubmitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty() || confpass.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }else if (!confpass.getText().toString().trim().equals(password.getText().toString().trim())){
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues cv = new ContentValues();
                    cv.put("email", email.getText().toString().trim());
                    cv.put("password", password.getText().toString().trim());

                    sqLiteDatabase = database.getWritableDatabase();
                    Long insert = sqLiteDatabase.insert(USERTABLE, null,cv);

                    if (insert != null){
                        Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                    }

                    email.setText("");
                    password.setText("");
                    confpass.setText("");
                }
            }
        });

        return view;
    }
}