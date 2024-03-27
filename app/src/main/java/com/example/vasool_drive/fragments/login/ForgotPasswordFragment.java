package com.example.vasool_drive.fragments.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.ExpensesViewPagerAdapter;
import com.example.vasool_drive.adpters.ForgotPasswordViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentForgotPasswordBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ForgotPasswordFragment extends Fragment {
    private String[] data = {"SEND OTP","RESET PASSWORD"};
    private ForgotPasswordViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
   private FragmentForgotPasswordBinding binding;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerAdapter = new ForgotPasswordViewPagerAdapter(getActivity());
        binding.viewPager2.setAdapter(viewPagerAdapter);

        tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(data[position]);

            }
        }
        ).attach();

    }
}