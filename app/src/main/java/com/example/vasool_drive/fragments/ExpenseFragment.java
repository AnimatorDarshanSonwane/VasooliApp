package com.example.vasool_drive.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.MainActivity;
import com.example.vasool_drive.R;

import com.example.vasool_drive.adpters.ExpensesViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentExpenseBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ExpenseFragment extends Fragment {
    private String[] data = {"EXPENSE","INVESTMENT"};
    int TabPosition;
    TabLayout tabLayout;
    private AppBarConfiguration mAppBarConfiguration;
    private ExpensesViewPagerAdapter viewPagerAdapter;
    private FragmentExpenseBinding binding;

    public ExpenseFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpenseBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tabLayout.getSelectedTabPosition() == 0) {
                    Navigation.findNavController(v).navigate(R.id.addFragment);
                }
                else if (tabLayout.getSelectedTabPosition()==1){
                    Navigation.findNavController(v).navigate(R.id.addInvestmentFragment);
                }

            }
        });

        viewPagerAdapter = new ExpensesViewPagerAdapter(getActivity());
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