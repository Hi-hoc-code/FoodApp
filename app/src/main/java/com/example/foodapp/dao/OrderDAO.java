package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Order;

import java.util.ArrayList;

public class OrderDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean insert(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("orderID", order.getOrderID());
        values.put("total", order.getTotal());
        values.put("userID", order.getUserID());
        values.put("status", order.getStatus());
        values.put("shippingAddress", order.getShippingAddress());
        long add = db.insert("Orders", null, values);
        db.close();
        return add != -1;
    }


    public ArrayList<Order> getAll() {
        ArrayList<Order> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM Orders";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String orderID = cursor.getString(0);
            double total = cursor.getDouble(1);
            int userID = cursor.getInt(2);
            String status = cursor.getString(3);
            String shippingAddress = cursor.getString(4);
            Order order = new Order(orderID, total, userID, status, shippingAddress);
            list.add(order);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean updateStatus(String orderID, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        int rows = db.update("Orders", values, "orderID = ?", new String[]{orderID});
        db.close();
        return rows > 0;
    }
}
