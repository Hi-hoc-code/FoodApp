package com.example.foodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.database.DbHelper;

public class ResetPassword extends AppCompatActivity {
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);



    }
}