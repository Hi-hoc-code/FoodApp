package com.example.foodapp.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.QuenMatKhau;
import com.example.foodapp.R;
import com.example.foodapp.activity.ManageActivity;
import com.example.foodapp.database.DbHelper;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout btnToRegister;
    private Button btnLogin;
    private TextView btnForgot;
    private EditText edtPhone, edtPassword;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DbHelper(this);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập số điện thoại và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkUser(phone, password)) {
                        // If the phone number and password are correct
                        startActivity(new Intent(LoginActivity.this, ManageActivity.class));
                    } else {
                        // If the phone number and password are incorrect
                        Toast.makeText(LoginActivity.this, "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, QuenMatKhau.class));
            }
        });
    }

    private void addControls() {
        btnToRegister = findViewById(R.id.btnToRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgot = findViewById(R.id.btnForgot);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
    }
}
