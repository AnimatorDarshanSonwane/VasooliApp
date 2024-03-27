package com.example.vasool_drive.fragments.login;

import static android.provider.Telephony.Mms.Part.TEXT;

import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MainActivity;
import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false);

        binding.btnLOGIN.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.loginFragment,true);
            Navigation.findNavController(getView()).navigate(R.id.collectionFragment);

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

        setUp();

    }

    private void init (){


    }

    private void setUp(){

        binding.tvSupport.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(R.id.supportFragment);

        });

        binding.tvForgotPassword.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(R.id.forgotPasswordFragment);
        });

        binding.btnOpenAnAccount.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(R.id.registerFragment);

        });


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String app_lang_value =sharedPref.getString("app_lang", "");

        Log.d("Login Screen  :: "," " +app_lang_value);

        if (app_lang_value.isEmpty()){
            setLocale("en");
        }else {
            setLocale(app_lang_value);
        }


        binding.btnFRINGERPRINT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFingerprint();
            }
        });


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Authentication", 0);
        String bio = sharedPreferences.getString(TEXT, "");

        if (bio.equals( "1")){

            binding.btnFRINGERPRINT.setVisibility(View.VISIBLE);

        }
        if (bio.equals( "0")){

            binding.btnFRINGERPRINT.setVisibility(View.GONE);

        }

        checkFingerprint();

    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        Resources res = getContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;

        res.updateConfiguration(conf, dm);

//        Intent refresh = new Intent(this, MainActivity.class);
//        startActivity(refresh);

    }

    private void checkFingerprint() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Authentication", 0);
        String bio = sharedPreferences.getString(TEXT, "");

        if (bio.equals("1")) {

            BiometricManager biometricManager = androidx.biometric.BiometricManager.from(getActivity());
            switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK | DEVICE_CREDENTIAL)) {

                // this means we can use biometric sensor
                case BiometricManager.BIOMETRIC_SUCCESS:

                    break;

                // this means that the device doesn't have fingerprint sensor
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:

                    break;

                // this means that biometric sensor is not available
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:

                    break;

                // this means that the device doesn't contain your fingerprint
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:

                    break;
                case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:
                    break;
                case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED:

                    break;
                case BiometricManager.BIOMETRIC_STATUS_UNKNOWN:

                    break;
            }
            // creating a variable for our Executor
            Executor executor = ContextCompat.getMainExecutor(getActivity());
            // this will give us result of AUTHENTICATION
            final BiometricPrompt biometricPrompt = new BiometricPrompt(LoginFragment.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                // THIS METHOD IS CALLED WHEN AUTHENTICATION IS SUCCESS
                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getContext(), "Login Success.", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(getView()).popBackStack(R.id.loginFragment,true);
                    Navigation.findNavController(getView()).navigate(R.id.collectionFragment);

                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }
            });
            // creating a variable for our promptInfo
            // BIOMETRIC DIALOG
            final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Authentication")
                    .setDescription("Use your fingerprint to login ")
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK | DEVICE_CREDENTIAL).build();

            biometricPrompt.authenticate(promptInfo);

        } else {

                Log.d("Login Fragment  ", " Fingerprint is not Active");

//            Intent intent = new Intent(StartActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
        }

    }

}