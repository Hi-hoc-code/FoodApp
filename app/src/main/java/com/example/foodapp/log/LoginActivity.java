package com.example.foodapp.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.QuenMatKhau;
import com.example.foodapp.R;
import com.example.foodapp.activity.ManageActivity;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout btnToRegister;
    private Button btnLogin;
    TextView btnForgot;
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ManageActivity.class));
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
    }
}