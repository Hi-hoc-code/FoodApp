package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.foodapp.R;
import com.example.foodapp.activity.DetailFoodActivity;
import com.example.foodapp.adapter.ViewPager2HomeUserAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserHomeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        ViewPager2HomeUserAdapter adapter = new ViewPager2HomeUserAdapter(getActivity());
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("All");
                    break;
                case 1:
                    tab.setText("Fruits");
                    break;
                case 2:
                    tab.setText("Vegetables");
                    break;
                case 3:
                    tab.setText("Meat");
                    break;
                case 4:
                    tab.setText("Seafood");
                    break;
                case 5:
                    tab.setText("Dairy");
                    break;
            }
        }).attach();

        return view;
    }
}
