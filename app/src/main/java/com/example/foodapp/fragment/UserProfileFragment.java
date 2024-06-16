package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.activity.ChangeInfoActivity;
import com.example.foodapp.activity.ChangePassActivity;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.dao.CartDAO;
import com.example.foodapp.log.LoginActivity;
import com.example.foodapp.model.Cart;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {
    private TextView btnLogoutUser;
    private LinearLayout btnGoToDKBM, btnGoToInFo, btnGoToPass, btnGoTOFAQ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        btnLogoutUser = view.findViewById(R.id.btnLogout);
        btnGoToDKBM = view.findViewById(R.id.btnGoToDKBM);
        btnGoToInFo = view.findViewById(R.id.btnGoToInfo);
        btnGoToPass = view.findViewById(R.id.btnGoToPassword);
        btnGoTOFAQ = view.findViewById(R.id.btnFAQ);
        btnLogoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        btnGoToDKBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Chức năng đang được phát triển", Toast.LENGTH_SHORT).show();
            }
        });
        btnGoTOFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Chức năng đang được phát triển", Toast.LENGTH_SHORT).show();
            }
        });
        btnGoToInFo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangeInfoActivity.class));
            }
        });
        btnGoToPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePassActivity.class));
            }
        });
        return view;
    }
}