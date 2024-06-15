package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;
import com.example.foodapp.fragment.AdminBillFragment;
import com.example.foodapp.fragment.AdminChartFragment;
import com.example.foodapp.fragment.AdminMemberFragment;
import com.example.foodapp.fragment.AdminNoticeFragment;
import com.example.foodapp.fragment.AdminProductFragment;
//import com.example.foodapp.fragment.AdminTypeFragment;
import com.example.foodapp.log.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AdminProductFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;


                int itemId = item.getItemId();
                if (itemId == R.id.product_manager) {
                    selectedFragment = new AdminProductFragment();
                } else if (itemId == R.id.member_manager) {
                    selectedFragment = new AdminMemberFragment();
                } else if (itemId == R.id.bill) {
                    selectedFragment = new AdminBillFragment();
                }else if(itemId==R.id.chart){
                    selectedFragment= new AdminChartFragment();
                }else if(itemId ==R.id.notification){
                    selectedFragment = new AdminNoticeFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                }

                return true;
            }
        });
    }
}
