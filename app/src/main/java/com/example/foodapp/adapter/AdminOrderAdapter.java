package com.example.foodapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Order;

import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.OrderViewHolder> {
    private Context context;
    private List<Order> orders;
    private OnOrderStatusChangedListener listener;

    public AdminOrderAdapter(Context context, List<Order> orders, OnOrderStatusChangedListener listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public interface OnOrderStatusChangedListener {
        void onOrderStatusChanged(Order order, boolean confirmed);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderID;
        private TextView tvTotal;
        private TextView tvStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderID = itemView.findViewById(R.id.tvOrderID);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }

        public void bind(Order order) {
            tvOrderID.setText("Order ID: " + order.getOrderID());
            tvTotal.setText("Total: " + order.getTotal());
            tvStatus.setText("Status: " + order.getStatus());

            // Set color based on order status
            if (order.getStatus().equalsIgnoreCase("confirmed")) {
                itemView.setBackgroundColor(Color.GREEN);
            } else {
                itemView.setBackgroundColor(Color.RED);
            }

            // Set click listener to change order status
            itemView.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm Action");
                builder.setMessage("Do you want to change the status of this order?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean newStatus = !order.getStatus().equalsIgnoreCase("confirmed");
                        listener.onOrderStatusChanged(order, newStatus);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the operation, do nothing
                    }
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        }
    }
}

