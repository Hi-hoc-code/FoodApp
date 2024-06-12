package com.example.foodapp.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.foodapp.R;
//import com.example.foodapp.fragment.AdminTypeFragment;
import com.example.foodapp.fragment.UserCartFragment;
import com.example.foodapp.fragment.UserFoodFragment;
import com.example.foodapp.fragment.UserHomeFragment;
import com.example.foodapp.fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        String uID = getIntent().getStringExtra("idUser");
        Log.d(TAG, "ID USER: "+uID);
        Bundle bundle = new Bundle();
        bundle.putString("idUser",uID);

        // Set default fragment
        if (savedInstanceState == null) {
            UserHomeFragment homeFragment = new UserHomeFragment();
            homeFragment.setArguments(bundle); // Đặt Bundle vào fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    selectedFragment = new UserHomeFragment();
                    selectedFragment.setArguments(bundle); // Đặt Bundle vào fragment
                } else if (itemId == R.id.food) {
                    selectedFragment = new UserFoodFragment();
                } else if (itemId == R.id.cart) {
                    selectedFragment = new UserCartFragment();
                } else if (itemId == R.id.profile) {
                    selectedFragment = new UserProfileFragment();
                }

                // Đặt đối số vào fragment trước khi commit
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                }

                return true;
            }
        });
    }
}

