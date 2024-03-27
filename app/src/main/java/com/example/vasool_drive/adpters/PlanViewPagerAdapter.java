package com.example.vasool_drive.adpters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vasool_drive.fragments.ExpensesFragment;
import com.example.vasool_drive.fragments.InvestmentFragment;
import com.example.vasool_drive.fragments.reportfragments.Plan.DateFragment;
import com.example.vasool_drive.fragments.reportfragments.Plan.LineFragment;

public class PlanViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] mFragments = new Fragment[] {//Initialize fragments views
//Fragment views are initialized like any other fragment (Extending Fragment)
            new DateFragment(),//First fragment to be displayed within the pager tab number 1
            new LineFragment(),
    };

    public PlanViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
