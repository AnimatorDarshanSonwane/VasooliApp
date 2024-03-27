package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentEditAreaBinding;


public class EditAreaFragment extends Fragment {

    private FragmentEditAreaBinding binding;
    public EditAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditAreaBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }
}