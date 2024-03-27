package com.example.vasool_drive.fragments.settingfragment;

import static android.provider.Telephony.Mms.Part.TEXT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.vasool_drive.MainActivity;
import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.ProfileData;
import com.example.vasool_drive.classes.SiteData;
import com.example.vasool_drive.databinding.FragmentMySettingsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class MySettingsFragment extends Fragment {

    private FragmentMySettingsBinding binding;
    private BottomNavigationView bottomNavigationView;

    private ArrayList<ProfileData> arrayList;
    ProfileData data ;
    String android_id = "";
    public MySettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMySettingsBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);




        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HideAndSee();
        setup();

        android_id = String.valueOf(Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID));

        Log.d(" MY Setting Fragment"," Device Id   " + android_id);

        init();

    }

    private void init (){

        arrayList = new ArrayList<>();

        loadProfileData();

        languagebuttoncheck();
    }

    private void languagebuttoncheck() {

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String app_lang_value =sharedPref.getString("app_lang", "");

        Log.d("My Setting Fragment :: "," " +app_lang_value);

        if (app_lang_value.equals("hi")){
            binding.btnHindi.setChecked(true);
        }else if (app_lang_value.equals("en")){
            binding.btnEnglish.setChecked(true);
        }

        binding.btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.btnEnglish.isChecked()){
                    binding.btnEnglish.setChecked(true);
                    binding.btnHindi.setChecked(false);
                }else {
                    binding.btnEnglish.setChecked(false);
                }

            }
        });


        binding.btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.btnHindi.isChecked()){
                    binding.btnHindi.setChecked(true);
                    binding.btnEnglish.setChecked(false);
                }else {
                    binding.btnHindi.setChecked(false);
                }

            }
        });

    }

    private void setup(){

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.mySettingsFragment,true);

            }
        });

       binding.btnSubmit2.setOnClickListener(v -> {

           binding.progressCircular.setVisibility(View.VISIBLE);

           if (binding.btnHindi.isChecked()){

               SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();
               editor.putString("app_lang", "hi");
               editor.apply();

               setLocale("hi");

           }

           if (binding.btnEnglish.isChecked()){

               SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();
               editor.putString("app_lang", "en");
               editor.apply();

               setLocale("en");


           }

           Navigation.findNavController(v).popBackStack(R.id.mySettingsFragment,true);
           Navigation.findNavController(getView()).navigate(R.id.mySettingsFragment);
           binding.progressCircular.setVisibility(View.GONE);
       });


    }

    private void HideAndSee() {

        binding.otherSettingOptionList.setVisibility(View.VISIBLE);
        binding.profileSettingOptionList.setVisibility(View.GONE);
        binding.languageOptionList.setVisibility(View.GONE);
        binding.themeOptionList.setVisibility(View.GONE);

        binding.ivBtnDropDown1.setOnClickListener(v -> {

            if (binding.otherSettingOptionList.getVisibility() == View.VISIBLE){
                binding.otherSettingOptionList.setVisibility(View.GONE);
            }
            else {
                binding.otherSettingOptionList.setVisibility(View.VISIBLE);
                binding.profileSettingOptionList.setVisibility(View.GONE);
                binding.languageOptionList.setVisibility(View.GONE);
                binding.themeOptionList.setVisibility(View.GONE);
            }
        });

        binding.ivBtnDropDown2.setOnClickListener(v -> {
            if (binding.profileSettingOptionList.getVisibility()==View.VISIBLE){
                binding.profileSettingOptionList.setVisibility(View.GONE);
            }else {
                binding.profileSettingOptionList.setVisibility(View.VISIBLE);
                binding.otherSettingOptionList.setVisibility(View.GONE);
                binding.languageOptionList.setVisibility(View.GONE);
                binding.themeOptionList.setVisibility(View.GONE);
            }
        });

        binding.ivBtnDropDown3.setOnClickListener(v -> {
            if (binding.languageOptionList.getVisibility()==View.VISIBLE){
                binding.languageOptionList.setVisibility(View.GONE);

            }else {
                binding.languageOptionList.setVisibility(View.VISIBLE);
                binding.otherSettingOptionList.setVisibility(View.GONE);
                binding.profileSettingOptionList.setVisibility(View.GONE);
                binding.themeOptionList.setVisibility(View.GONE);
            }
        });

        binding.ivBtnDropDown4.setOnClickListener(v -> {
            if (binding.themeOptionList.getVisibility()==View.VISIBLE){
                binding.themeOptionList.setVisibility(View.GONE);

            }else {
                binding.themeOptionList.setVisibility(View.VISIBLE);
                binding.otherSettingOptionList.setVisibility(View.GONE);
                binding.profileSettingOptionList.setVisibility(View.GONE);
                binding.languageOptionList.setVisibility(View.GONE);
            }
        });

}

    private void loadProfileData (){

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.ProfileData).whereEqualTo("deviceID",android_id).get().addOnSuccessListener(queryDocumentSnapshots -> {

            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){

                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                // category data madhe online database cha data add kela
                data = documentSnapshot.toObject(ProfileData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);

            }
            setProfileData();

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }

    private void setProfileData (){

        if (data==null){

            Log.d(" MY Setting Fragment"," Null   ");

            return;
        }

        Log.d(" MY Setting Fragment"," Data  " + data.getDeviceID() );

        binding.etName.setText(data.getName());
        binding.etMobileNo.setText(data.getMobileNumber());
        binding.etEmailID.setText(data.getEmailID());
        binding.etPrimarySite.setText(data.getPrimarySite());

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




}