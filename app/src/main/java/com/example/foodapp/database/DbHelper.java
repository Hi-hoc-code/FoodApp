package com.example.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodapp.model.User;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "APP_FOOD";

    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_ADMIN = "Admin";
    private static final String TABLE_USER = "User";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_PRODUCT = "Product";
    private static final String TABLE_ORDERS = "Orders";
    private static final String TABLE_NOTIFICATION = "Notification";

    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_ADMIN + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "avatar TEXT," +
            "phone TEXT)";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT," +
            "phone TEXT," +
            "avatar TEXT," +
            "orderID TEXT)";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "description TEXT)";

    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "categoryId INTEGER," +
            "name TEXT," +
            "price REAL," +
            "image TEXT," +
            "description TEXT," +
            "FOREIGN KEY (categoryId) REFERENCES " + TABLE_CATEGORY + "(id))";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " (" +
            "orderID TEXT PRIMARY KEY," +
            "total REAL," +
            "userID INTEGER," +
            "FOREIGN KEY (userID) REFERENCES " + TABLE_USER + "(id))";

    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE " + TABLE_NOTIFICATION + " (" +
            "notificationId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT," +
            "content TEXT)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_NOTIFICATION);

        // Insert initial data
        db.execSQL("INSERT INTO " + TABLE_ADMIN + "(avatar, phone) VALUES('admin_avatar', '0123456789')");
        db.execSQL("INSERT INTO " + TABLE_USER + "(username, password, phone, avatar, orderID) VALUES('user1', 'password1', '0123456789', 'user_avatar', '1')");
        db.execSQL("INSERT INTO " + TABLE_USER + "(username, password, phone, avatar, orderID) VALUES('user2', 'password2', '0987654321', 'user_avatar', '2')");

        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Fruits', 'All kinds of fruits')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Vegetables', 'Various vegetables')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Meat', 'Different types of meat')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Seafood', 'Fresh seafood')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Dairy', 'Dairy products')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Beverages', 'Various drinks')");

        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Man ha noi', 5000, 'mon_an', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Dua hau khong hat', 6000, 'fb_icon', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Táo đỏ', 7000, 'gg_icon', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Thanh long ngọt', 5000, 'mon_an', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Bun dau mam tom', 6000, 'fb_icon', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(name, price, image, description, categoryId) VALUES('Táo đỏ', 7000, 'gg_icon', 'Đây là mô tả của món ăn', 2)");

        db.execSQL("INSERT INTO "+ TABLE_NOTIFICATION +"(title, content) VALUES('Giảm giá','Chương trình khuyến mãi mua 2 tặng 1')");
        db.execSQL("INSERT INTO "+ TABLE_NOTIFICATION +"(title, content) VALUES('Quà tặng cuộc sống ','Quà tặng cuộc sống được ban cho bạn')");
        db.execSQL("INSERT INTO "+ TABLE_NOTIFICATION +"(title, content) VALUES('Thông báo','Bạn đã bị thua 5 tỉ!')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        onCreate(db);
    }

    // Method to check if the user exists with given phone number and password
    public boolean checkUser(String phone, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "id" };
        String selection = "phone = ? AND password = ?";
        String[] selectionArgs = { phone, password };

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }
    public boolean checkUserExists(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "id" };
        String selection = "phone = ?";
        String[] selectionArgs = { phone };

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    // Method to insert a new user
    public boolean insertUser(String phone, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        contentValues.put("username", name);
        contentValues.put("password", password);

        long result = db.insert(TABLE_USER, null, contentValues);
        db.close();

        return result != -1; // Return true if insertion is successful
    }
    public User getCurrentUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id = 1"; // Adjust this query as per your logic

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            User user = new User();

            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("username");
            int phoneIndex = cursor.getColumnIndex("phone");

            if (idIndex != -1) {
                user.setId(cursor.getInt(idIndex));
            }
            if (nameIndex != -1) {
                user.setName(cursor.getString(nameIndex));
            }
            if (phoneIndex != -1) {
                user.setPhone(cursor.getString(phoneIndex));
            }

            cursor.close();
            db.close();
            return user;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("phone", user.getPhone());

        long result = db.update("User", values, "id=?", new String[]{String.valueOf(user.getId())});
        return result != -1;
    }

}
