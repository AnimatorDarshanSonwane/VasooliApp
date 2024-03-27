package com.example.vasool_drive.adpters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vasool_drive.fragments.CollectionFragment;
import com.example.vasool_drive.fragments.CustomerFragment;
import com.example.vasool_drive.fragments.ExpenseFragment;
import com.example.vasool_drive.fragments.ReportFragment;
import com.example.vasool_drive.fragments.SettingFragment;
import com.example.vasool_drive.fragments.reportfragments.Plan.DateFragment;
import com.example.vasool_drive.fragments.reportfragments.Plan.LineFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] mFragments = new Fragment[] {//Initialize fragments views
//Fragment views are initialized like any other fragment (Extending Fragment)
            new CollectionFragment(), //First fragment to be displayed within the pager tab number 1
            new ExpenseFragment(),
            new CustomerFragment(),
            new ReportFragment(),
            new SettingFragment(),
    };

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }

    @Override
    public int getItemCount() {
        return mFragments.length;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
