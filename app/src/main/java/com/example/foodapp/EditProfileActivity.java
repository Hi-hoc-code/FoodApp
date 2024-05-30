package com.example.foodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtUserPhone;
    private Button btnSaveChanges;
    private DbHelper dbHelper;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        edtUserName = findViewById(R.id.edtUserName);
        edtUserPhone = findViewById(R.id.edtUserPhone);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        // Initialize DbHelper
        dbHelper = new DbHelper(this);

        // Load current user data
        loadUserProfile();

        // Set onClick listener for save button
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });
    }

    private void loadUserProfile() {
        currentUser = dbHelper.getCurrentUser();
        if (currentUser != null) {
            edtUserName.setText(currentUser.getName());
            edtUserPhone.setText(currentUser.getPhone());
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserProfile() {
        String newUserName = edtUserName.getText().toString().trim();
        String newUserPhone = edtUserPhone.getText().toString().trim();

        if (newUserName.isEmpty() || newUserPhone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update user information
        currentUser.setName(newUserName);
        currentUser.setPhone(newUserPhone);

        boolean isUpdated = dbHelper.updateUser(currentUser);
        if (isUpdated) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }
}
