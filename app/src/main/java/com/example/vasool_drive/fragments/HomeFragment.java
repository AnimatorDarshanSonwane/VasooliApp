package com.example.vasool_drive.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.BottomNavViewPagerAdapter;
import com.example.vasool_drive.adpters.PlanViewPagerAdapter;
import com.example.vasool_drive.adpters.ViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentHomeBinding;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPagerAdapter viewPagerAdapter;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);

//        viewPagerAdapter = new ViewPagerAdapter(getActivity());
//        binding.viewPager.setAdapter(viewPagerAdapter);
//
//        binding.bottomNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
//            @Override
//            public void onNavigationChanged(View view, int position) {
//                binding.viewPager.setCurrentItem(position,true);
//            }
//        });
//
//        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                // yaha par jo current fragment ki position hai uski position topnvaigation par set kar di
//                binding.bottomNavigation.setCurrentActiveItem(position);
//
//            }
//        });




        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
//
////        viewPagerAdapter = new ViewPagerAdapter(getActivity());
////        binding.viewPager.setAdapter(viewPagerAdapter);
//
//        NavController navController = Navigation.findNavController(view);
//        NavigationUI.setupWithNavController(bottomNavigationView,navController);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectedFragment;
//
//                switch (item.getItemId()){
//                    case R.id.item1:
//                        selectedFragment =new HomeFragment();
//                        break;
//                    case R.id.item2:
//                        selectedFragment =new ExpenseFragment();
//                        break;
//                    case R.id.item3:
//                        selectedFragment =new CustomerFragment();
//                        break;
//                    case R.id.item4:
//                        selectedFragment =new ReportFragment();
//                        break;
//                    case R.id.settingFragment:
//                        selectedFragment =new SettingFragment();
//                        break;
//                }
//
//                return true;
//            }
//        });
//
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Fragment selectedFragment;
//                switch (item.getItemId()){
//                    case R.id.item1:
//                        selectedFragment =new HomeFragment();
//                        break;
//                    case R.id.expenseFragment:
//                        selectedFragment =new ExpenseFragment();
//                        break;
//                    case R.id.customerFragment:
//                        selectedFragment =new CustomerFragment();
//                        break;
//                    case R.id.reportFragment:
//                        selectedFragment =new ReportFragment();
//                        break;
//                    case R.id.settingFragment:
//                        selectedFragment =new SettingFragment();
//                        break;
//                }
//
//            }
//        });


    }

}