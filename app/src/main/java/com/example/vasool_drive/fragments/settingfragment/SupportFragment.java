package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.ExpensesViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentSupportBinding;

public class SupportFragment extends Fragment {
    private String[] data = {"TAMIL","ENGLISH"};
    private FragmentSupportBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    public SupportFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupportBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main);
                mAppBarConfiguration =
                        new AppBarConfiguration.Builder(R.id.nav_host_fragment_content_main,R.id.homeFragment).build();

                NavigationUI.navigateUp(navController,mAppBarConfiguration);
            }
        });
    }

}