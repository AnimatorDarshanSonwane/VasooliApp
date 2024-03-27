package com.example.vasool_drive.fragments.notification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.CollectionViewPagerAdapter;
import com.example.vasool_drive.adpters.ReminderViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentReminderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ReminderFragment extends Fragment {

   private String title;

    private ReminderViewPagerAdapter viewPagerAdapter;
    private String[] data = {"TODAY","HISTORY"};
    private FragmentReminderBinding binding;

    public ReminderFragment(String title) {
        this.title =title;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReminderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerAdapter = new ReminderViewPagerAdapter(getActivity());
        binding.viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(data[position]);

            }
        }
        ).attach();

    }
}