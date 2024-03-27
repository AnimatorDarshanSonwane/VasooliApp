package com.example.vasool_drive.adpters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vasool_drive.fragments.notification.NotesFragment;
import com.example.vasool_drive.fragments.notification.ReminderFragment;

public class CollectionViewPagerAdapter extends FragmentStateAdapter {


    public CollectionViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            // case 0 means home fragments
            case 0:
            default:
                return new ReminderFragment("REMINDER");
            case 1:
                return new NotesFragment();


        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
