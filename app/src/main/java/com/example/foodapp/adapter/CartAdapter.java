package com.example.foodapp.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
        holder.edtQuantity.setText(String.valueOf(cart.getQuanity()));
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

        holder.btnIncreaseQuantity.setOnClickListener(v -> {
            int quantity = cart.getQuanity();
            if (quantity < 50) {
                quantity++;
                cart.setQuanity(quantity);
                holder.edtQuantity.setText(String.valueOf(quantity));
                cartDAO.update(cart);
                notifyItemChanged(position);
                onItemSelectedListener.onItemSelected(getTotalPrice());
            }
        });

        holder.btnDecreaseQuantity.setOnClickListener(v -> {
            int quantity = cart.getQuanity();
            if (quantity > 1) {
                quantity--;
                cart.setQuanity(quantity);
                holder.edtQuantity.setText(String.valueOf(quantity));
                cartDAO.update(cart);
                notifyItemChanged(position);
                onItemSelectedListener.onItemSelected(getTotalPrice());
            }
        });

        holder.edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int quantity = Integer.parseInt(s.toString());
                    if (quantity < 1) {
                        quantity = 1;
                    } else if (quantity > 50) {
                        quantity = 50;
                    }
                    cart.setQuanity(quantity);
                    cartDAO.update(cart);
                    onItemSelectedListener.onItemSelected(getTotalPrice());
                } catch (NumberFormatException e) {
                    holder.edtQuantity.setText(String.valueOf(cart.getQuanity()));
                }
            }
        });

        holder.btnEditCart.setOnClickListener(v -> {
            // Implement edit functionality here
        });

        holder.btnRemoveCart.setOnClickListener(v -> {
            Cart cartDelete = cartList.get(holder.getAdapterPosition());
            try {
                cartDAO.removeCart(cartDelete.getIdCart());
                cartList.clear();
                cartList.addAll(cartDAO.getAll());
                notifyDataSetChanged();
                Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
                onItemSelectedListener.onItemSelected(getTotalPrice());
            } catch (Exception ex) {
                Log.d("CartAdapter", "DELETE CART: " + ex);
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
            totalPrice += cart.getPrice() * cart.getQuanity();
        }
        return totalPrice;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(double totalPrice);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoodCart;
        TextView tvNameFoodCart, tvPriceFoodCart, btnEditCart, btnRemoveCart;
        CheckBox checkboxCartItem;
        EditText edtQuantity;
        ImageButton btnIncreaseQuantity, btnDecreaseQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodCart = itemView.findViewById(R.id.imgFoodCart);
            tvNameFoodCart = itemView.findViewById(R.id.tvNameFoodCart);
            tvPriceFoodCart = itemView.findViewById(R.id.tvPriceFoodCart);
            btnEditCart = itemView.findViewById(R.id.btnEditCart);
            btnRemoveCart = itemView.findViewById(R.id.btnRemoveCart);
            checkboxCartItem = itemView.findViewById(R.id.checkboxCartItem);
            edtQuantity = itemView.findViewById(R.id.edtQuantity);
            btnIncreaseQuantity = itemView.findViewById(R.id.btnIncreaseQuantity);
            btnDecreaseQuantity = itemView.findViewById(R.id.btnDecreaseQuantity);
        }
    }
}
