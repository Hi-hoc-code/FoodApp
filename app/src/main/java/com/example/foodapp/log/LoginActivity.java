package com.example.foodapp.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.activity.AdminActivity;
import com.example.foodapp.activity.DetailFoodActivity;
import com.example.foodapp.activity.UserActivity;
import com.example.foodapp.dao.MemberDAO;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout btnToRegister;
    private Button btnLogin;
    private EditText edtName, edtPassword;
    private MemberDAO memberDAO;
    private TextView tv_user_account, tv_admin_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        tv_user_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("HO NGOC Y");
                edtPassword.setText("Admin123@");
            }
        });
        tv_admin_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("Admin");
                edtPassword.setText("Admin123@");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Kiểm tra tên đăng nhập và mật khẩu
                if (username.isEmpty()) {
                    edtName.setError("Tên đăng nhập không được để trống");
                    edtName.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    edtPassword.setError("Mật khẩu không được để trống");
                    edtPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    edtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
                    edtPassword.requestFocus();
                    return;
                }

                // Gọi phương thức checkLogin từ MemberDAO
                String result = memberDAO.checkLogin(username, password);
                // Phân tích kết quả
                String[] parts = result.split("\\|");
                String role = parts[0];
                String userId = parts[1];

                // Xử lý kết quả
                if (role.equals("admin")) {
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                } else if (role.equals("user")) {
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void addControls() {
        btnToRegister = findViewById(R.id.btnToRegister);
        btnLogin = findViewById(R.id.btnLogin);
        edtName= findViewById(R.id.edtName);
        tv_user_account = findViewById(R.id.id_user_account);
        tv_admin_account = findViewById(R.id.id_admin_account);
        edtPassword = findViewById(R.id.edtPassword);
        memberDAO = new MemberDAO(LoginActivity.this);

    }
}
