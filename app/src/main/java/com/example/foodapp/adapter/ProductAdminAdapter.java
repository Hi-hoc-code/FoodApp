package com.example.foodapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.MyViewHolder> {
    ArrayList<Products> list;
    Context context;
    ProductDAO productDAO;

    public ProductAdminAdapter(ArrayList<Products> list, Context context) {
        this.list = list;
        this.context = context;
        this.productDAO = new ProductDAO(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products products = list.get(position);
        holder.tvNameFood.setText(products.getName());
        holder.tvPriceFood.setText(products.getPrice() + "$");
        holder.tvDescription.setText(products.getDes());
        holder.tvTypeOfFood.setText(String.valueOf(products.getTypeName()));
        String img = products.getImage();
        int resImg = context.getResources().getIdentifier(img, "drawable", context.getPackageName());
        holder.imgFood.setImageResource(resImg);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProductItem(products, position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductItem(products, position);
            }
        });
    }

    private void deleteProductItem(Products product, int position) {
        productDAO.delete(String.valueOf(product));
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
        Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
    }

    private void editProductItem(Products product, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.edit_product_dialog, null);
        builder.setView(view);

        EditText etProductName = view.findViewById(R.id.etProductName);
        EditText etProductPrice = view.findViewById(R.id.etProductPrice);
        EditText etProductDescription = view.findViewById(R.id.etProductDescription);
        EditText etProductType = view.findViewById(R.id.etProductType);
        View btnUpdateProduct = view.findViewById(R.id.btnUpdateProduct);

        etProductName.setText(product.getName());
        etProductPrice.setText(String.valueOf(product.getPrice()));
        etProductDescription.setText(product.getDes());
        etProductType.setText(product.getTypeName());

        AlertDialog dialog = builder.create();

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setName(etProductName.getText().toString());
                product.setPrice(Integer.parseInt(etProductPrice.getText().toString()));
                product.setDes(etProductDescription.getText().toString());
                product.setTypeName(etProductType.getText().toString());

                productDAO.update(product);
                list.set(position, product);
                notifyItemChanged(position);
                dialog.dismiss();
                Toast.makeText(context, "Product updated", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();

    }
    public void filterList(ArrayList<Products> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvNameFood, tvPriceFood, tvDescription, tvTypeOfFood;
        ImageView btnEdit, btnDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            imgFood = view.findViewById(R.id.imgFood);
            tvNameFood = view.findViewById(R.id.tvNameFood);
            tvPriceFood = view.findViewById(R.id.tvPriceFood);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvTypeOfFood = view.findViewById(R.id.tvTypeOfFood);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }
}
