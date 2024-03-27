package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.AddInvestmentTypeData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SiteData;
import com.example.vasool_drive.databinding.FragmentEditSiteBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EditSiteFragment extends Fragment {


    FragmentEditSiteBinding binding;
    String DocumentId = "";
    private BottomNavigationView bottomNavigationView;


    public EditSiteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditSiteBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.GONE);

        loadEdit();
        setup();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void  init (){


    }

    private void setup(){

        binding.btnClose.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.siteFragment);

            Navigation.findNavController(v).popBackStack(R.id.editSiteFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);
        });

        binding.btnSave.setOnClickListener(v -> {

            updateSiteData();
        });

    }
    private SiteData getSiteData () {


        String SiteName = binding.etSite.getText().toString();
        String Name = binding.etUserName.getText().toString();
        String MobileNo = binding.etMobileNo.getText().toString();
        String Address = binding.etAddress.getText().toString();
        String Agreement = binding.etAgreement.getText().toString();


        if (SiteName.isEmpty()){
            binding.etSite.setError("Please Enter SiteName ");
            return null;
        }

        if (Name.isEmpty()){
            binding.etUserName.setError("Please Enter Name ");
            return null;
        }
        if (MobileNo.isEmpty()){
            binding.etMobileNo.setError("Please Enter MobileNo ");
            return null;
        }
        if (Address.isEmpty()){
            binding.etAddress.setError("Please Enter Address ");
            return null;
        }
        if (Agreement.isEmpty()){
            binding.etAgreement.setError("Please Enter Agreement ");
            return null;
        }

        SiteData data = new SiteData();
        data.setSiteName(SiteName);
        data.setName(Name);
        data.setMobileNumber(MobileNo);
        data.setAddress(Address);
        data.setAgreement(Agreement);
        data.setId(DocumentId);
        return data;
    }

    private void updateSiteData() {
       SiteData data = getSiteData();
        if (data == null) {
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);
        MyApplication.firebase.collection(MyReferance.SiteData).document(data.getId()).update("siteName",data.getSiteName(),"name",data.getName(), "address", data.getAddress(), "mobileNumber", data.getMobileNumber(),"agreement",data.getAgreement()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                binding.progressCircular.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Update Sucessfully", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).popBackStack(R.id.editSiteFragment,true);
                bottomNavigationView.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                binding.progressCircular.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d(" Edit Site Fragment ", " Expection   ::   " + e);

            }
        });

    }

    private void loadEdit(){

        assert getArguments() != null;

        SiteData data = EditSiteFragmentArgs.fromBundle(getArguments()).getSiteData();


        if (data == null){
            Log.d("EditSiteData   :"," Data is Null ");
            return;
        }

        Log.d("EditSiteData   :"," doc id :  "+ data.getId() );


        binding.etSite.setText(data.getSiteName());
        binding.etMobileNo.setText(data.getMobileNumber());
        binding.etUserName.setText(data.getName());
        binding.etAddress.setText(data.getAddress());
        binding.etAgreement.setText(data.getAgreement());
        DocumentId = data.getId();

        Log.d("EditSiteData   :"," Site Name :  "+ data.getSiteName() );


    }


}