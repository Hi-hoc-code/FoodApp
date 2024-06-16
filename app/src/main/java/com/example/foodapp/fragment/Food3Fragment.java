package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.adapter.Food1Adapter;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Products;
import java.util.ArrayList;

public class Food3Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Food1Adapter adapter;
    private ProductDAO productDAO;
    private ArrayList<Products> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food1, container, false);
        recyclerView = view.findViewById(R.id.rcvAllFood);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productDAO = new ProductDAO(getActivity());
        productList = productDAO.getAll();
        adapter = new Food1Adapter(productList, getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
