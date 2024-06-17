package com.example.foodapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodapp.fragment.AllFragment;
import com.example.foodapp.fragment.Food1Fragment;
import com.example.foodapp.fragment.Food2Fragment;
import com.example.foodapp.fragment.Food3Fragment;

public class ViewPager2HomeUserAdapter extends FragmentStateAdapter {
    public ViewPager2HomeUserAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllFragment();
            case 1:
                return new Food1Fragment();
            case 2:
                return new Food2Fragment();
            case 3:
                return new Food3Fragment();

            default:
                return new AllFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
