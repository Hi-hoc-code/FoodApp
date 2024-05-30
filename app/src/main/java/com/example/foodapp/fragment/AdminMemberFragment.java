package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodapp.EditProfileActivity;
import com.example.foodapp.R;
import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.User;

public class AdminMemberFragment extends Fragment {
    private TextView tvUserName;
    private TextView tvUserPhone;
    private ImageView imgProfilePicture;
    private Button btnEditProfile;
    private Button btnLogOut;

    private DbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_member, container, false);

        // Initialize views
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserPhone = view.findViewById(R.id.tvUserPhone);
        imgProfilePicture = view.findViewById(R.id.imgProfilePicture);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnLogOut = view.findViewById(R.id.btnLogOut);

        // Initialize DbHelper
        dbHelper = new DbHelper(getActivity());

        // Load user profile
        loadUserProfile();

        // Set onClick listeners
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Edit Profile Activity
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout logic
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        return view;
    }

    private void loadUserProfile() {
        User user = dbHelper.getCurrentUser();
        if (user != null) {
            tvUserName.setText(user.getName());
            tvUserPhone.setText(user.getPhone());
            // Load avatar if necessary
            // imgProfilePicture.setImageResource(user.getAvatar());
        } else {
            Toast.makeText(getActivity(), "User not found", Toast.LENGTH_SHORT).show();
        }
    }
}
