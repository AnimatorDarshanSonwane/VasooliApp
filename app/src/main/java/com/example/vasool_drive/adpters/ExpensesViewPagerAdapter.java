package com.example.vasool_drive.adpters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vasool_drive.fragments.ExpensesFragment;
import com.example.vasool_drive.fragments.InvestmentFragment;

public class ExpensesViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] mFragments = new Fragment[] {//Initialize fragments views
//Fragment views are initialized like any other fragment (Extending Fragment)
            new ExpensesFragment(),//First fragment to be displayed within the pager tab number 1
            new InvestmentFragment(),
    };
    public final String[] mFragmentNames = new String[] {//Tabs names array
            "First Tab",
            "SecondTab"
    };

    public ExpensesViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @Override
    public int getItemCount() {
        return mFragments.length;//Number of fragments displayed
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }
}
