package com.example.vasool_drive.fragments.customersFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.ColorInt;
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
import com.example.vasool_drive.classes.AddCustomerData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentAddCustomerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddCustomerFragment extends Fragment {

    private FragmentAddCustomerBinding binding;
    private BottomNavigationView bottomNavigationView;
    private static final String TAG = " ADD CUSTOMER : ";

    public AddCustomerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAddCustomerBinding.inflate(inflater,container,false);

        init();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp();
    }

    private void init (){

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

    }
    private void setUp (){

        binding.btnAddArea.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.addAreaFragment);

          //  Navigation.findNavController(v).popBackStack(R.id.addCustomerFragment,false);

        });

        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.searchResultCustomerFragment);

            Navigation.findNavController(v).popBackStack(R.id.addCustomerFragment,true);

        });

        binding.btnDropAddress.setOnClickListener(v -> {
            if (binding.cardAddress.getVisibility() == View.VISIBLE){
                binding.cardAddress.setVisibility(View.GONE);
            } else {
                binding.cardAddress.setVisibility(View.VISIBLE);
            }


        } );

        binding.btnDropIDNumber.setOnClickListener(v -> {
            if (binding.card1IDNumber.getVisibility() == View.VISIBLE || binding.card2IDNumber.getVisibility() == View.VISIBLE){
                binding.card1IDNumber.setVisibility(View.GONE);
                binding.card2IDNumber.setVisibility(View.GONE);
                binding.layout15.setVisibility(View.GONE);
            } else {
                binding.card1IDNumber.setVisibility(View.VISIBLE);
                binding.card2IDNumber.setVisibility(View.VISIBLE);
                binding.layout15.setVisibility(View.VISIBLE);
            }


        } );

        binding.btnSave.setOnClickListener(v -> {

            if (isNetworkConnected()){

                addNewCustomer();



            } else {
                Toast.makeText(getActivity(), "Please Switch on Internet" , Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private AddCustomerData getCustomerData(){
        String CustomerName = binding.etCustomerName.getText().toString();
        String CustomerCode = binding.etCustomerCode.getText().toString();
        String SubCode = binding.etSubCode.getText().toString();
        String CustomerOrder = binding.etCustomerOrder.getText().toString();
        String MobileNumber = binding.etMobileNo.getText().toString();
        String AlternateMobile = binding.etAlternateMobile.getText().toString();
        String LineName = binding.etLine.getText().toString();
        String Area = binding.etArea.getText().toString();
        String Status = binding.etStatus.getText().toString();
        String Business = binding.etBusiness.getText().toString();
        String MaxLoanAmount = binding.etMaxLoanAmount.getText().toString();
        String Address = binding.etAddress.getText().toString();
        String IdNumber = binding.etIDNumber.getText().toString();

        if (CustomerName.isEmpty()){
            binding.etCustomerName.setError("Please Enter Customer Name ");
            Toast.makeText(getActivity(), "Please Enter Customer Name ", Toast.LENGTH_SHORT).show();
            return null;
        }

//        if (CustomerCode.isEmpty()){
//            binding.etCustomerCode.setError("Please Enter Customer Code ");
//            Toast.makeText(getActivity(), " Please Enter Customer Code ", Toast.LENGTH_SHORT).show();
//            return null;
//        }
//
//        if (SubCode.isEmpty()){
//            binding.etSubCode.setError("Please Enter Sub Code ");
//            Toast.makeText(getActivity(), "Please Enter Sub Code ", Toast.LENGTH_SHORT).show();
//            return null;
//        }
//
//        if (CustomerOrder.isEmpty()){
//            binding.etCustomerOrder.setError("Please Enter Customer Order ");
//            Toast.makeText(getActivity(), "Please Enter Customer Order ", Toast.LENGTH_SHORT).show();
//            return null;
//        }
        if (MobileNumber.isEmpty()){
            binding.etMobileNo.setError("Please Enter Mobile NO");
            Toast.makeText(getActivity(), "Please Enter Mobile NO", Toast.LENGTH_SHORT).show();
            return null;
        }



        if (AlternateMobile.isEmpty()){
            binding.etAlternateMobile.setError("Please Enter Alternate Mobile ");

            return null;
        }
        if (LineName.isEmpty()){
            binding.etLine.setError("Please Enter Line ");
            Toast.makeText(getActivity(), "Please Enter Line ", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (Area.isEmpty()){
            binding.etArea.setError("Please Enter Area ");
            Toast.makeText(getActivity(), "Please Enter Area ", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (Status.isEmpty()){
            binding.etStatus.setError("Please Enter Status ");
            Toast.makeText(getActivity(), "Please Enter Status ", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (Business.isEmpty()){
            binding.etBusiness.setError("Please Enter Business ");
            Toast.makeText(getActivity(), "Please Enter Business ", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (MaxLoanAmount.isEmpty()){
            binding.etMaxLoanAmount.setError("Please Enter MaxLoanAmount ");
            Toast.makeText(getActivity(), "Please Enter MaxLoanAmount ", Toast.LENGTH_SHORT).show();
            return null;
        }


        if (Address.isEmpty()){
            binding.etAddress.setError("Please Enter Address ");
            Toast.makeText(getActivity(), "Please Enter Address ", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (IdNumber.isEmpty()){
            binding.etIDNumber.setError("Please Enter ID Number ");
            Toast.makeText(getActivity(), "Please Enter ID Number ", Toast.LENGTH_SHORT).show();
            return null;
        }


        AddCustomerData data = new AddCustomerData();
        data.setLineName(LineName);
        data.setCustomerName(CustomerName);
        data.setCustomerCode(CustomerCode);
        data.setSubCode(SubCode);
        data.setCustomerOrder(CustomerOrder);
        data.setMobileNo(MobileNumber);
        data.setAlternateMobile(AlternateMobile);
        data.setArea(Area);
        data.setStatus(Status);
        data.setBusiness(Business);
        data.setMaxLoanAmount(MaxLoanAmount);
        data.setAddress(Address);
        data.setIDNumber(IdNumber);

        return data;
    }

    private void addNewCustomer () {
        // call data from category activity
        AddCustomerData data = getCustomerData();

        if(data == null){
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.AddCustomerData).add(data).addOnSuccessListener(documentReference -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "New Customer Added", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(getView()).navigate(R.id.searchResultCustomerFragment);

            Navigation.findNavController(getView()).popBackStack(R.id.addCustomerFragment,true);

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Log.d(TAG, "addLineText: " + e);
            Toast.makeText(getActivity(), "Somethig Went Wrong", Toast.LENGTH_SHORT).show();


        });
    }
}
