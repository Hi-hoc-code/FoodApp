package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Cart;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public CartDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Thêm một mục vào giỏ hàng
    public void addToCart(Cart cart) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idUser", cart.getIdUser());
        values.put("nameFood", cart.getNameFood());
        values.put("img", cart.getImg());
        values.put("des", cart.getDes());
        values.put("price", cart.getPrice());
        db.insert("Cart", null, values);
        db.close();
    }

    // Lấy danh sách tất cả các mặt hàng trong giỏ hàng
    public List<Cart> getAllCart() {
        List<Cart> cartList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Cart";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String idUser = cursor.getString(0);
                String nameFood = cursor.getString(1);
                String img = cursor.getString(2);
                String des = cursor.getString(3);
                int price = cursor.getInt(4);
                Cart cart = new Cart(idUser, nameFood, img, des, price);
                cartList.add(cart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartList;
    }
}
