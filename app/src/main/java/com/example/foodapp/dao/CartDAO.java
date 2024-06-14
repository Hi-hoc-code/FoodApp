package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Cart;
import com.example.foodapp.model.Member;

import java.util.ArrayList;

public class CartDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public CartDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Cart> getAll() {
        ArrayList<Cart> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM CART";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Integer id = cursor.getInt(0);
            String nameFood = cursor.getString(1);
            String img = cursor.getString(2);
            String des = cursor.getString(3);
            Integer price = cursor.getInt(4);
            Cart cart = new Cart(id, nameFood, img, des, price);
            list.add(cart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    // Thêm một mục vào giỏ hàng
    public boolean insert(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameFood", cart.getNameFood());
        values.put("img", cart.getImg());
        values.put("des", cart.getDes());
        values.put("price", cart.getPrice());
        long add = db.insert("Cart", null, values);
        db.close();
        return add != -1;
    }
    public boolean removeCart(Integer id) {
        db = dbHelper.getWritableDatabase();
        long check = db.delete("Cart", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return check > 0;
    }


}
