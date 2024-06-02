package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Member;

import java.util.ArrayList;

public class MemberDAO {
    DbHelper helper;
    Context context;

    public MemberDAO(Context context) {
        helper = new DbHelper(context);
    }

    //getALL
    public ArrayList<Member> getAll() {
        ArrayList<Member> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT id,name, phone, avatar, passwordUser FROM USER";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Integer id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String avatar = cursor.getString(3);
            String passUser = cursor.getString(4);
            Member member = new Member(id, name, phone, avatar, passUser);
            list.add(member);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    //Insert
    public boolean insert(Member member) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("phone", member.getPhone());
        values.put("passUser", member.getPasswordUser());
        long add = db.insert("USER", null, values);
        db.close();
        return add != -1;
    }

    public boolean update(Member member) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("phone", member.getPhone());
        values.put("passUser", member.getPasswordUser());
        long edit = db.update("USER", values, "id = ?", new String[]{String.valueOf(member.getId())});
        return edit > 0;
    }

    //Delete
    public boolean delete(Integer id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int del = db.delete("User", "id =?", new String[]{String.valueOf(id)});
        db.close();
        return del > 0;
    }

}
