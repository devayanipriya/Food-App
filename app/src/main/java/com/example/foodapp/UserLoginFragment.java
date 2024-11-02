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


public class UserLoginFragment extends Fragment {

    Database database;
    SQLiteDatabase sqLiteDatabase;
    EditText uemail, upassword;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_user_login, container, false);

         database = new Database(getContext());
         uemail = (EditText) view.findViewById(R.id.ulEdEmail);
         upassword = (EditText) view.findViewById(R.id.ulEdPass);
         login = (Button) view.findViewById(R.id.ulbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = uemail.getText().toString();
                String user_password = upassword.getText().toString();

                if (uemail.getText().toString().trim().isEmpty() || upassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuseremailpassword = database.checkuseremailandpassword(uemail.getText().toString().trim(), upassword.getText().toString().trim());
                    if(checkuseremailpassword == true){
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomePageMenu.class);
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