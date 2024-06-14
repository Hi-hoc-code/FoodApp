package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.CartDAO;
import com.example.foodapp.model.Cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Cart> cartList;
    private Set<Integer> selectedItems = new HashSet<>();
    private OnItemSelectedListener onItemSelectedListener;

    public CartAdapter(Context context, ArrayList<Cart> cartList, OnItemSelectedListener onItemSelectedListener) {
        this.context = context;
        this.cartList = cartList;
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Integer index = holder.getAdapterPosition();
        Cart cart = cartList.get(index);
        CartDAO cartDAO = new CartDAO(context);

        holder.tvNameFoodCart.setText(cart.getNameFood());
        holder.tvPriceFoodCart.setText(cart.getPrice() + "$");

        holder.checkboxCartItem.setOnCheckedChangeListener(null);
        holder.checkboxCartItem.setChecked(selectedItems.contains(index));

        holder.checkboxCartItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(index);
            } else {
                selectedItems.remove(index);
            }
            onItemSelectedListener.onItemSelected(getTotalPrice());
        });

        // Handle edit button click (if needed)
        holder.btnEditCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement edit functionality here
            }
        });

        // Handle remove button click (if needed)
        holder.btnRemoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartDAO.removeCart(cart.getIdCart())) {  // Use cart ID here
                    Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
                    cartList.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, cartList.size());
                    selectedItems.remove(index);
                    onItemSelectedListener.onItemSelected(getTotalPrice());
                } else {
                    Toast.makeText(context, "Failed to remove item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        for (int position : selectedItems) {
            Cart cart = cartList.get(position);
            totalPrice += cart.getPrice();
        }
        return totalPrice;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(double totalPrice);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoodCart;
        TextView tvNameFoodCart, tvPriceFoodCart;
        TextView btnEditCart, btnRemoveCart;
        CheckBox checkboxCartItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodCart = itemView.findViewById(R.id.imgFoodCart);
            tvNameFoodCart = itemView.findViewById(R.id.tvNameFoodCart);
            tvPriceFoodCart = itemView.findViewById(R.id.tvPriceFoodCart);
            btnEditCart = itemView.findViewById(R.id.btnEditCart);
            btnRemoveCart = itemView.findViewById(R.id.btnRemoveCart);
            checkboxCartItem = itemView.findViewById(R.id.checkboxCartItem);
        }
    }
}
