package com.example.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "APP_FOOD";
    private static final int DATABASE_VERSION = 3; // Incremented version

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
            "status TEXT," +
            "shippingAddress TEXT," +
            "phone TEXT," +
            "name TEXT," + // Added new column
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
            "price INTEGER," +
            "quanity INTEGER)";

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
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(id, name, description) VALUES(1,'Cơm', 'Những hạt gạo và cach chế biến ngon lành')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(id, name, description) VALUES(2,'Bún', 'Bao gồm những loại bún đến từ khắp nơi')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "(id, name, description) VALUES(3,'Thức ăn nhanh', 'Nhanh chóng, an toàn và tện lợi')");

        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(1, 'Bún chả cá', 25000, 'bun', 'Bún chả cá là món ăn đặc sản miền Trung, với chả cá dai ngon hòa quyện cùng nước dùng ngọt thanh từ xương cá và rau củ', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(2, 'Bún bò', 25000, 'bun1', 'Bún bò Huế là món ăn nổi tiếng của Huế, với nước dùng đậm đà từ xương bò, kết hợp cùng thịt bò mềm, giò heo, và hương thơm từ sả, ớt.', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(3, 'Bún tái,nạm', 30000, 'bun2', 'Bún tái, nạm là món bún với nước dùng trong, thơm ngon từ xương bò, kèm theo thịt bò tái và nạm mềm, ăn kèm rau sống tươi mát', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(4, 'Bún đậu mắm tôm', 70000, 'bundau', 'Bún đậu mắm tôm là món ăn dân dã với bún tươi, đậu hũ chiên giòn, chả cốm, thịt luộc và mắm tôm đặc trưng pha chanh, đường, ớt', 1)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(6, 'Cơm chiên Dương Châu', 45000, 'comchien', 'Cơm chiên Dương Châu là món cơm chiên đặc trưng của người Hoa, với hạt cơm tơi xốp, màu sắc đẹp mắt từ trứng, đậu Hà Lan, cà rốt, xúc xích và tôm', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(7, 'Cơm tấm', 35000, 'comtam', 'Cơm tấm là món ăn đặc trưng của miền Nam Việt Nam, với cơm từ gạo tấm, sườn nướng, bì, chả trứng, ăn kèm dưa leo, cà chua, và nước mắm chua ngọt', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(8, 'Cơm niêu', 100000, 'comnieu', 'Đây là mô tả của món ăn', 2)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(9, 'Trái cây tươi', 25000, 'thucannhanh1', 'Trái cây tươi là món ăn nhẹ, bổ dưỡng với các loại trái cây tươi ngon như xoài, dứa, dưa hấu, nho, giúp cung cấp vitamin và năng lượng cho cơ thể', 3)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(10, 'Pizza', 60000, 'thucannhanh2', 'Pizza là món ăn Ý nổi tiếng, với đế bánh giòn tan, phủ phô mai, sốt cà chua và các loại nhân phong phú như thịt bò, gà, hải sản, rau củ', 3)");
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(id, name, price, image, description, categoryId) VALUES(11, 'Buger', 60000, 'mon_an', 'Burger là món ăn nhanh phổ biến, với bánh mì kẹp thịt bò nướng, phô mai, rau sống như xà lách, cà chua, dưa chuột, và sốt đặc trưng.', 3)");

        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content, notificationId) VALUES('Giảm giá', 'Chương trình khuyến mãi mua 2 tặng 1', 1)");
        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content) VALUES('Quà tặng cuộc sống', 'Quà tặng cuộc sống được ban cho bạn')");
        db.execSQL("INSERT INTO " + TABLE_NOTIFICATION + "(title, content) VALUES('Thông báo', 'Bạn đã bị thua 5 tỉ!')");

        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price, quanity) VALUES('Bún chả cá', 'bun', 'Bún chả cá là món ăn đặc sản miền Trung, với chả cá dai ngon hòa quyện cùng nước dùng ngọt thanh từ xương cá và rau củ', 25000, 2)");
        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price, quanity) VALUES('Trái cây tươi', 'thucannhanh1', 'Trái cây tươi là món ăn nhẹ, bổ dưỡng với các loại trái cây tươi ngon như xoài, dứa, dưa hấu, nho, giúp cung cấp vitamin và năng lượng cho cơ thể.', 25000, 1)");
        db.execSQL("INSERT INTO " + TABLE_CART + "(nameFood, img, des, price, quanity) VALUES('Cơm tấm', 'comtam', 'Pizza là món ăn Ý nổi tiếng, với đế bánh giòn tan, phủ phô mai, sốt cà chua và các loại nhân phong phú như thịt bò, gà, hải sản, rau củ', 60000, 3)");

        db.execSQL("INSERT INTO " + TABLE_USER + "(name, passwordUser, roleUser) VALUES('Admin', 'Admin123@', 0)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('John Doe', '123-456-7890', 'avatar1', 'order_001', 'password123', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('Jane Smith', '234-567-8901', 'avatar2', 'order_002', 'password234', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('Alice Johnson', '345-678-9012', 'avatar3', 'order_003', 'password345', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('Bob Brown', '456-789-0123', 'avatar4', 'order_004', 'password456', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('Charlie Davis', '567-890-1234', 'avatar5', 'order_005', 'password567', 1)");
        db.execSQL("INSERT INTO " + TABLE_USER + "(name, phone, avatar, orderID, passwordUser, roleUser) VALUES('HO NGOC Y', '0708332716', 'avatar5', 'order_006', 'Admin123@', 1)");
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
