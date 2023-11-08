package com.vanh.timekeeping.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vanh.timekeeping.fragments.MenuHomeFragment;
import com.vanh.timekeeping.fragments.MenuSalaryFragment;
import com.vanh.timekeeping.fragments.MenuSettingsFragment;
import com.vanh.timekeeping.fragments.MenuTimeKeepingFragment;

public class MenuAdapter extends FragmentStateAdapter {

    public MenuAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MenuHomeFragment();
            case 1:
                return new MenuTimeKeepingFragment();
            case 2:
                return new MenuSalaryFragment();
            case 3:
                return new MenuSettingsFragment();
            default:
                return new MenuHomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
