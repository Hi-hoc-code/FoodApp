package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.AdminOrderAdapter;
import com.example.foodapp.dao.OrderDAO;
import com.example.foodapp.model.Order;

import java.util.List;

public class AdminBillFragment extends Fragment implements AdminOrderAdapter.OnOrderStatusChangedListener {
    private RecyclerView rvOrders;
    private AdminOrderAdapter adapter;
    private OrderDAO orderDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_bill, container, false);

        rvOrders = view.findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        orderDAO = new OrderDAO(getContext());
        List<Order> orders = orderDAO.getAllOrders();

        adapter = new AdminOrderAdapter(getContext(), orders, this);
        rvOrders.setAdapter(adapter);

        return view;
    }

    @Override
    public void onOrderStatusChanged(Order order, boolean confirmed) {
        if (confirmed) {
            order.setStatus("confirmed");
        } else {
            order.setStatus("pending");
        }

        boolean result = orderDAO.update(order);
        if (result) {
            // Update successful, refresh UI if needed
            adapter.notifyDataSetChanged();
        } else {
            // Update failed, handle error
            // You can show an error message or handle it based on your application's logic
        }
    }
}
