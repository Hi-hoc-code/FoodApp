package com.example.foodapp.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class ProductDAO {
    private final DbHelper helper;

    public ProductDAO(Context context) {
        helper = new DbHelper(context);
    }

    // GetAll
    public ArrayList<Products> getAll() {
        ArrayList<Products> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT Product.name, Product.image, Product.price, Product.description, " +
                    "Product.categoryId, Category.name as categoryName " +
                    "FROM Product " +
                    "JOIN Category ON Product.categoryId = Category.id";
            cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    String image = cursor.getString(1);
                    int price = cursor.getInt(2);
                    String description = cursor.getString(3);
                    int categoryId = cursor.getInt(4);
                    String categoryName = cursor.getString(5);
                    Products products = new Products(name, price, description, categoryId, categoryName, image);
                    list.add(products);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error while trying to get products from database", ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    // Insert
    public boolean insert(Products product) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("image", product.getImage());
        values.put("price", product.getPrice());
        values.put("description", product.getDes());
        values.put("categoryId", product.getTypeId());

        long result = database.insert("Product", null, values);
        database.close();
        return result != -1;
    }

    // Update
    public boolean update(Products product) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("image", product.getImage());
        values.put("price", product.getPrice());
        values.put("description", product.getDes());
        values.put("categoryId", product.getTypeId());

        int result = database.update("Product", values, "name = ?", new String[]{product.getName()});
        database.close();
        return result > 0;
    }

    // Delete
    public boolean delete(String name) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int result = database.delete("Product", "name = ?", new String[]{name});
        database.close();
        return result > 0;
    }
}
