package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Order;
import com.example.foodapp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insert(Order order) {
        ContentValues values = new ContentValues();
        values.put("orderID", order.getOrderID());
        values.put("total", order.getTotal());
        values.put("userID", order.getUserID());
        values.put("status", order.getStatus());
        values.put("shippingAddress", order.getShippingAddress());
        values.put("phone",order.getPhoneNumber());
        values.put("name",order.getName());
        long result = db.insert("Orders", null, values);
        return result != -1;
    }

    public boolean update(Order order) {
        ContentValues values = new ContentValues();
        values.put("orderID", order.getOrderID());
        values.put("total", order.getTotal());
        values.put("userID", order.getUserID());
        values.put("status", order.getStatus());
        values.put("shippingAddress", order.getShippingAddress());
        values.put("phone",order.getPhoneNumber());
        values.put("name",order.getName());
        int result = db.update("Orders", values, "orderID = ?", new String[]{order.getOrderID()});
        return result > 0; // Trả về true nếu cập nhật thành công, ngược lại false
    }

    public boolean delete(String orderID) {
        int result = db.delete("Orders", "orderID = ?", new String[]{orderID});
        return result > 0; // Trả về true nếu xóa thành công, ngược lại false
    }

    // Lấy danh sách tất cả các đơn hàng từ cơ sở dữ liệu
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String orderID = cursor.getString(cursor.getColumnIndexOrThrow("orderID"));
                int total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
                int userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                String shippingAddress = cursor.getString(cursor.getColumnIndexOrThrow("shippingAddress"));
                String name =cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone =cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                Order order = new Order(orderID, status, userID, total, name,phone, shippingAddress);
                // Thêm đơn hàng vào danh sách
                orders.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return orders;
    }
}
