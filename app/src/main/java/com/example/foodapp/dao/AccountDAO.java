package com.example.foodapp.dao;

import android.content.Context;

import com.example.foodapp.database.DbHelper;

public class AccountDAO {
    Context context;
    DbHelper helper;

    public AccountDAO(Context context) {
        helper = new DbHelper(context);
    }


}
