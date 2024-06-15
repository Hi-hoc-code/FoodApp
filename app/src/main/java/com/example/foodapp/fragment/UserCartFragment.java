package com.example.foodapp.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.dao.CartDAO;
import com.example.foodapp.dao.OrderDAO;
import com.example.foodapp.model.Cart;
import com.example.foodapp.model.Order;

import java.util.ArrayList;
import java.util.UUID;

public class UserCartFragment extends Fragment implements CartAdapter.OnItemSelectedListener {
    ArrayList<Cart> listCart;
    CartAdapter cartAdapter;
    CartDAO cartDAO;
    OrderDAO orderDAO;
    RecyclerView rcvCart;
    Button btnCheckout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_cart, container, false);
        rcvCart = view.findViewById(R.id.rcvListCart);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvCart.setLayoutManager(layoutManager);

        cartDAO = new CartDAO(getContext());
        orderDAO = new OrderDAO(getContext());
        listCart = cartDAO.getAll();

        cartAdapter = new CartAdapter(getContext(), listCart, this);
        rcvCart.setAdapter(cartAdapter);

        updateCheckoutButton(0.0); // Initial total price is 0

        btnCheckout.setOnClickListener(v -> showOrderDialog(getTotalPrice()));

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

    private double getTotalPrice() {
        double totalPrice = 0;
        for (Cart cart : listCart) {
            totalPrice += cart.getPrice() * cart.getQuanity();
        }
        return totalPrice;
    }

    private void showOrderDialog(double totalPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.order_dialog, null);
        builder.setView(dialogView);

        EditText edtShippingAddress = dialogView.findViewById(R.id.edtShippingAddress);
        Button btnConfirmOrder = dialogView.findViewById(R.id.btnConfirmOrder);

        AlertDialog dialog = builder.create();

        btnConfirmOrder.setOnClickListener(v -> {
            String shippingAddress = edtShippingAddress.getText().toString();
            if (shippingAddress.isEmpty()) {
                Toast.makeText(getContext(), "Please enter a shipping address", Toast.LENGTH_SHORT).show();
                return;
            }

            String orderID = UUID.randomUUID().toString();
            int userID = 7; // This should be the actual logged-in user's ID
            String status = "pending";
            Order order = new Order(orderID, totalPrice, userID, status, shippingAddress);

            try {
                if (orderDAO.insert(order)) {
                    Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // Clear the cart or update UI as needed
                } else {
                    Toast.makeText(getContext(), "Order placement failed!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Log.e(TAG, "Error inserting order: " + ex.getMessage());
                ex.printStackTrace();
                Toast.makeText(getContext(), "Failed to place order", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
