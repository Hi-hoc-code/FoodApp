package com.example.foodapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodapp.fragment.ProductEditFragment;
import com.example.foodapp.fragment.TypeEditFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProductEditFragment();
            case 1:
                return new TypeEditFragment();
            default:
                return new ProductEditFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
