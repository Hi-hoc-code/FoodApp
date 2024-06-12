package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.foodapp.activity.DetailFoodActivity;
import com.example.foodapp.adapter.GetFoodAdapter;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Products;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    private RecyclerView rcvDemoFood, rcvAllFood;
    private GetFoodAdapter adapterDemo, adapterAll;
    private ArrayList<Products> foodList;
    private Handler handler;
    private int currentIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_all, container, false);
        rcvDemoFood = view.findViewById(R.id.rcvDemoFood);
        rcvAllFood = view.findViewById(R.id.rcvAllFood);

        rcvDemoFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvAllFood.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ProductDAO productDAO = new ProductDAO(getContext());
        foodList = productDAO.getAll();

        adapterDemo = new GetFoodAdapter(foodList, getContext(), R.layout.layout_item_food);
        rcvDemoFood.setAdapter(adapterDemo);

        adapterAll = new GetFoodAdapter(foodList, getContext(), R.layout.layout_item_food_all);
        rcvAllFood.setAdapter(adapterAll);

        handler = new Handler(Looper.getMainLooper());
        startAutoScroll();

        // Receive user ID from UserHomeFragment
        Bundle bundle = getArguments();
        String userId = bundle.getString("idUser");

        return view;
    }

    private void startAutoScroll() {
        handler.postDelayed(() -> {
            currentIndex++;
            if (currentIndex >= foodList.size()) {
                currentIndex = 0;
            }
            rcvDemoFood.smoothScrollToPosition(currentIndex);
            handler.postDelayed(this::startAutoScroll, 4000);
        }, 4000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
