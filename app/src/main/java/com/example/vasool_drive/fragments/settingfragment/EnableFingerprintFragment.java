package com.example.vasool_drive.fragments.settingfragment;

import static android.provider.Telephony.Mms.Part.TEXT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentEnableFingerprintBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EnableFingerprintFragment extends Fragment {

    private static final String SWITCH1 = "Switch";
    private FragmentEnableFingerprintBinding binding;

    private BottomNavigationView bottomNavigationView;

    public EnableFingerprintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEnableFingerprintBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.Password.setVisibility(View.GONE);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.enableFingerprintFragment,true);

            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    checkSwitchFingerprint();
    setFingerprint();
    }

    private void checkPassword (){



    }
    private void checkSwitchFingerprint (){

        SharedPreferences sharedPref = getActivity().getSharedPreferences("Authentication",0);
        String switchmode = sharedPref.getString(TEXT, "" );

        Log.d("EnableFingerprintFragment  :: "," " + switchmode);

        if (switchmode.equals("0")){
            binding.etDisableFingureprint.setText(R.string.EnableFingreprint);
            binding.Switch.setChecked(false);

        }
        if (switchmode.equals("1")){
            binding.etDisableFingureprint.setText(R.string.DisableFingerprint);
            binding.Switch.setChecked(true);

        }


    }

    private void setFingerprint(){

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.progressCircular.setVisibility(View.VISIBLE);

                if (binding.Switch.isChecked()){
                    binding.progressCircular.setVisibility(View.VISIBLE);
                    binding.Switch.setChecked(true);
//                SharedPreferences sharedPref = getActivity().getSharedPreferences("Authentication",0);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(TEXT, "1");
//                editor.apply
                    Biometric("1");
                    Toast.makeText(getContext(), "..Fringerprint Enabled", Toast.LENGTH_SHORT).show();
                    binding.progressCircular.setVisibility(View.GONE);

                    Navigation.findNavController(v).navigate(R.id.settingFragment);
                    Navigation.findNavController(v).popBackStack(R.id.enableFingerprintFragment,true);

                }else {
                    binding.progressCircular.setVisibility(View.VISIBLE);
                    binding.Switch.setChecked(false);
//                SharedPreferences sharedPref = getActivity().getSharedPreferences("Authentication",0);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(TEXT, "0");
//                editor.apply(
                    Biometric("0");

                    Toast.makeText(getContext(), "..Fringerprint Disable", Toast.LENGTH_SHORT).show();

                    binding.progressCircular.setVisibility(View.GONE);

                    Navigation.findNavController(v).navigate(R.id.settingFragment);
                    Navigation.findNavController(v).popBackStack(R.id.enableFingerprintFragment,true);

                }
            }
        });
    }

    private void Biometric(String mode){

        if (mode.isEmpty()){
            mode = "0";
        }

        androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(getActivity());

        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {

            // this means we can use biometric sensor
            case androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS:

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Authentication",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT, mode);
                editor.putBoolean(SWITCH1, binding.Switch.isChecked());
                editor.apply();

//                Intent intent = new Intent(AboutActivity.this, edit_profile.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();

                break;

            // this means that the device doesn't have fingerprint sensor
            case androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                binding.Switch.setChecked(false);
                Log.d("Enable Fingerprint Sensor","No fingerprint sensor");
                break;

            // this means that biometric sensor is not available
            case androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:

                binding.Switch.setChecked(false);
                Log.d("Enable Fingerprint Sensor","Hardware Un available");
                break;

            // this means that the device doesn't contain your fingerprint
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                binding.Switch.setChecked(false);
                Log.d("Enable Fingerprint Sensor","Biometric error none enrolled");
                break;
            case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:
                Log.d("Enable Fingerprint Sensor","Error security upadate");
                break;
            case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED:
                Log.d("Enable Fingerprint Sensor","error unsupported");
                break;
            case BiometricManager.BIOMETRIC_STATUS_UNKNOWN:
                Log.d("Enable Fingerprint Sensor","staus unkown");
                break;
        }
    }

}