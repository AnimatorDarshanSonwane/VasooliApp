package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentLicenseBinding;


public class LicenseFragment extends Fragment {
    // getting device id
    //private String android_id = Secure.getString(getContext().getContentResolver(),
    //                                                       Secure.ANDROID_ID);


   private  FragmentLicenseBinding binding;

    public LicenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLicenseBinding.inflate(inflater,container,false);



        return binding.getRoot();

    }

}