package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.database.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class QuenMatKhau extends AppCompatActivity {
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        //Khai báo biến và ánh xạ
        TextInputEditText edtSdtForgot = findViewById(R.id.edtSdtForgot);
        Button btnReset = findViewById(R.id.btnReset);

        db = new DbHelper(this);

        //Bắt sự kiện click btnReset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = edtSdtForgot.getText().toString();
//                boolean check = db.checkUsername(tenDangNhap);
                if (soDienThoai.equals("0123456789")){
                    Intent intent = new Intent(QuenMatKhau.this, ResetPassword.class);
                    intent.putExtra("sodienthoai",soDienThoai);
                    startActivity(intent);
                }else {
                    Toast.makeText(QuenMatKhau.this, "Người dùng không tồn tại!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}