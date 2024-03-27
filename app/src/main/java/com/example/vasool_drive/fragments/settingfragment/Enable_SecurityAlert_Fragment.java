package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentEnableSecurityAlertBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Enable_SecurityAlert_Fragment extends Fragment {

    private FragmentEnableSecurityAlertBinding binding;
    private BottomNavigationView bottomNavigationView;

    public Enable_SecurityAlert_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEnableSecurityAlertBinding.inflate( inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.enable_SecurityAlert_Fragment,true);

            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}