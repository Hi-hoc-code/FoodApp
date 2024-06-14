package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.dao.CartDAO;
import com.example.foodapp.model.Cart;

import java.util.ArrayList;

public class UserCartFragment extends Fragment implements CartAdapter.OnItemSelectedListener {
    ArrayList<Cart> listCart;
    CartAdapter cartAdapter;
    CartDAO cartDAO;
    RecyclerView rcvCart;
    Button btnCheckout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_cart, container, false);
        rcvCart = view.findViewById(R.id.rcvListCart);
        btnCheckout = view.findViewById(R.id.btnCheckout); // Ensure you have this ID in your XML

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvCart.setLayoutManager(layoutManager);

        cartDAO = new CartDAO(getContext());
        listCart = cartDAO.getAll();

        cartAdapter = new CartAdapter(getContext(), listCart, this);
        rcvCart.setAdapter(cartAdapter);

        updateCheckoutButton(0.0); // Initial total price is 0

        return view;
    }

    @Override
    public void onItemSelected(double totalPrice) {
        updateCheckoutButton(totalPrice);
    }

    private void updateCheckoutButton(double totalPrice) {
        String buttonText = "Thanh toán với: " + totalPrice + " VND";
        btnCheckout.setText(buttonText);
    }
}
