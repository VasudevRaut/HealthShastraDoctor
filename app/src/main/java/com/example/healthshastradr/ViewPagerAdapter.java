package com.example.healthshastradr;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Upcommings();
            case 1:
                return new Completed();
            case 2:
                return new Canceled();
            default:
                return new Upcommings();
        }
    }

    @Override
    public int getItemCount() {
        return 3; //return the count of tabs
    }
}
