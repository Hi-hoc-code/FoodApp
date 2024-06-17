package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.OrderDAO;
import com.example.foodapp.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(holder.getAdapterPosition());
//        holder.tvOrderID.setText("Mã đơn hàng: "+order.getOrderID());
        holder.tvTotal.setText("Tổng tiền: "+order.getTotal() +"VND");
        holder.tvStatus.setText("Trạng thái đơn hàng: "+order.getStatus());
        holder.tvShippingAddress.setText("Địa chỉ giao hàng: "+order.getShippingAddress());
        holder.tvName.setText("Tên: "+order.getName());
        holder.tvPhone.setText("Số điện thoại: "+order.getPhoneNumber());
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder(holder);
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOrder(holder);
            }
        });
    }

    private void deleteOrder(OrderViewHolder holder) {
        OrderDAO orderDAO = new OrderDAO(context);
        Order order = orderList.get(holder.getAdapterPosition());
        if(orderDAO.delete(order.getOrderID())){
            orderList.remove(holder.getAdapterPosition());
            notifyDataSetChanged();
        }else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void editOrder(OrderViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderID, tvTotal, tvStatus, tvShippingAddress,tvName,tvPhone, tvDelete, tvEdit;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvOrderID = itemView.findViewById(R.id.tvOrderID);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvShippingAddress = itemView.findViewById(R.id.tvShippingAddress);
            tvName = itemView.findViewById(R.id.tvOrderName);
            tvPhone = itemView.findViewById(R.id.tvOrderPhone);
            tvDelete = itemView.findViewById(R.id.btnDelete);
            tvEdit=itemView.findViewById(R.id.btnEdit);
        }
    }
}
