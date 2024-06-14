package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.activity.DetailFoodActivity;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class GetFoodAdapter extends RecyclerView.Adapter<GetFoodAdapter.FoodViewHolder> {

    private ArrayList<Products> foodList;
    private Context context;
    private int layoutResourceId;
    private OnItemClickListener mListener;

    public GetFoodAdapter(ArrayList<Products> foodList, Context context, int layoutResourceId) {
        this.foodList = foodList;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        return new FoodViewHolder(view, layoutResourceId);
    }
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Products product = foodList.get(holder.getAdapterPosition());
        holder.bindData(product);

        View.OnClickListener detailClickListener = v -> {
            if (mListener != null) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        };

        if (layoutResourceId == R.layout.layout_item_food) {
            holder.itemDetail1.setOnClickListener(detailClickListener);
        } else if (layoutResourceId == R.layout.layout_item_food_all) {
            holder.itemDetail2.setOnClickListener(detailClickListener);
        } else {
            Toast.makeText(context, "Lỗi hiển thị ~~~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName, tvFoodPrice;
        ImageView imgFood;
        LinearLayout itemDetail2;
        CardView itemDetail1;

        public FoodViewHolder(@NonNull View itemView, int layoutResourceId) {
            super(itemView);
            // Initialize views based on the layoutResourceId
            if (layoutResourceId == R.layout.layout_item_food) {
                tvFoodName = itemView.findViewById(R.id.tvFoodNameDemo);
                tvFoodPrice = itemView.findViewById(R.id.tvFoodPriceDemo);
                imgFood = itemView.findViewById(R.id.imgFoodDemo);
                itemDetail1 = itemView.findViewById(R.id.itemDetail1);
            } else if (layoutResourceId == R.layout.layout_item_food_all) {
                tvFoodName = itemView.findViewById(R.id.tvFoodNameAll);
                tvFoodPrice = itemView.findViewById(R.id.tvFoodPriceAll);
                imgFood = itemView.findViewById(R.id.imgFoodAll);
                itemDetail2 = itemView.findViewById(R.id.itemDetail2);
            }
        }

        public void bindData(Products product) {
            tvFoodName.setText(product.getName());
            tvFoodPrice.setText(String.valueOf(product.getPrice()));
            String img = product.getImage();
            int resImg = imgFood.getContext().getResources().getIdentifier(img, "drawable", imgFood.getContext().getPackageName());
            imgFood.setImageResource(resImg);
        }
    }
}
