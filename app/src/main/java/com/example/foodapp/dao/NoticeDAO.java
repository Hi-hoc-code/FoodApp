package com.example.foodapp.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Notice;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class NoticeDAO {
    private final DbHelper helper;

    public NoticeDAO(Context context) {
        helper = new DbHelper(context);
    }

    public ArrayList<Notice> getAll() {
        ArrayList<Notice> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT title, content FROM Notification";
            cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(0);
                    String content = cursor.getString(1);
                    Notice notice = new Notice(title, content);
                    list.add(notice);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e("NoticeDAO", "Error while trying to get notice from database", ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    // Insert
    public boolean insert(Notice notice) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", notice.getTitle());
        values.put("content", notice.getContent());
        long result = database.insert("Notification", null, values);
        database.close();
        return result != -1;
    }

    // Update
    public boolean update(Notice notice) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("notificationId", notice.getId());
        values.put("title", notice.getTitle());
        values.put("content", notice.getContent());
        int result = database.update("Notification", values, "notificationId = ?", new String[]{String.valueOf(notice.getId())});
        database.close();
        return result > 0;
    }

    // Delete
    public boolean delete(Integer id) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int result = database.delete("Notification", "notificationId = ?", new String[]{String.valueOf(id)});
        database.close();
        return result > 0;
    }
}
