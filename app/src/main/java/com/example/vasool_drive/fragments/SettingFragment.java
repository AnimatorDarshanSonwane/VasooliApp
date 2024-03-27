package com.example.vasool_drive.fragments;

import static android.provider.Telephony.Mms.Part.TEXT;

import android.content.SharedPreferences;
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

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupFringerprintText();
        OnTextClick();

    }



    private void OnTextClick() {

        binding.layout0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.supportFragment);
            }
        });

        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.licenseFragment);
            }
        });

        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.settingLineFragment);
            }
        });


        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.importLineFragment);
            }
        });


        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.exportFragment);
            }
        });


        binding.layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.areaFragment);
            }
        });


        binding.layout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.expenseTypeFragment);
            }
        });


        binding.layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.investmentTypeFragment);
            }
        });


        binding.layout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.siteFragment);
            }
        });

        binding.layout15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.mySettingsFragment);
            }
        });


        binding.layout16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.enableFingerprintFragment);
            }
        });

        binding.layout17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.enable_SecurityAlert_Fragment);
            }
        });

        binding.layout18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.changePasswordFragment);
            }
        });

        binding.layout20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.signoutFragment);
            }
        });


    }
    public void setupFringerprintText(){

        SharedPreferences sharedPref = getActivity().getSharedPreferences("Authentication",0);
        String switchmode = sharedPref.getString(TEXT, "" );

        Log.d("EnableFingerprintFragment  :: "," " + switchmode);

        if (switchmode.equals("0")){

//            binding.Switch.setChecked(false);
            binding.tvEnableFingrePrint.setText(R.string.EnableFingreprint);

        }
        if (switchmode.equals("1")){

            binding.tvEnableFingrePrint.setText(R.string.DisableFingerprint);

        }

    }

}