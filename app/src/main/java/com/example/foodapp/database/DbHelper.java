package com.example.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "APP_FOOD";
    private static final int DATABASE_VERSION = 5;

    private static final String TABLE_USER = "User";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_PRODUCT = "Product";
    private static final String TABLE_ORDERS = "Orders";
    private static final String TABLE_NOTIFICATION = "Notification";
    private static final String TABLE_CART = "Cart";


    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "phone TEXT," +
            "avatar TEXT," +
            "orderID TEXT," +
            "passwordUser TEXT," +
            "roleUser INTEGER)";

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
    private static final String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nameFood TEXT," +
            "img TEXT," +
            "des TEXT," +
            "price INTEGER)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
        db.execSQL(CREATE_TABLE_CART);

        // Insert initial data
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Fruits', 'All kinds of fruits')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Vegetables', 'Various vegetables')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Meat', 'Different types of meat')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Seafood', 'Fresh seafood')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Dairy', 'Dairy products')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(name, description) VALUES('Beverages', 'Various drinks')");

        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id,name, price, image, description, categoryId) VALUES(1,'Món ăn 1', 5000, 'mon_an', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id,name, price, image, description, categoryId) VALUES(2, 'Món ăn 2', 6000, 'anh3', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(3,'Món ăn 3', 7000, 'anh1', 'Đây là mô tả của món ăn', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(4, 'Món ăn 4', 5000, 'mon_an', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(5, 'Món ăn 5', 6000, 'anh2', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(6,'Món ăn 6', 7000, 'anh3', 'Đây là mô tả của món ăn', 2)");

        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content, notificationId) VALUES('Giảm giá','Chương trình khuyến mãi mua 2 tặng 1',1)");
        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content) VALUES('Quà tặng cuộc sống ','Quà tặng cuộc sống được ban cho bạn')");
        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content) VALUES('Thông báo','Bạn đã bị thua 5 tỉ!')");

        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price) VALUES('Pizza', 'anh1', 'Delicious pizza with cheese', 10)");

        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price) VALUES('Burger', 'anh2', 'Tasty burger with beef patty', 8)");

        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price) VALUES('Pasta', 'anh3', 'Italian pasta with tomato sauce', 12)");

        db.execSQL("INSERT INTO " + TABLE_USER + "(name, passwordUser, roleUser) " +
                "VALUES('Admin','Admin123@',0)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) " +
                "VALUES('John Doe', '123-456-7890', 'avatar1', 'order_001', 'password123', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser)" +
                " VALUES('Jane Smith', '234-567-8901', 'avatar2', 'order_002', 'password234', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) " +
                "VALUES('Alice Johnson', '345-678-9012', 'avatar3', 'order_003', 'password345',1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) " +
                "VALUES('Bob Brown', '456-789-0123', 'avatar4', 'order_004', 'password456', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) " +
                "VALUES('Charlie Davis', '567-890-1234', 'avatar5', 'order_005', 'password567', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) " +
                "VALUES('HO NGOC Y', '0708332716', 'avatar5', 'order_006', 'Admin123@', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }
}
