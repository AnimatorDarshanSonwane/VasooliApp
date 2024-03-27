package com.example.vasool_drive.fragments.settingfragment;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
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
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentEditLineBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EditLineFragment extends Fragment {
    String LineType, LineName,BillAmountPerHundread, NoOfInstall, BadLoanDays, ID;

    private BottomNavigationView bottomNavigationView;
    SettingLineData data;
    private FragmentEditLineBinding binding;
    public EditLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditLineBinding.inflate(inflater,container,false);

        setUp();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.GONE);

        binding.btnClose.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(R.id.settingLineFragment);
            bottomNavigationView.setVisibility(View.VISIBLE);
        });

        binding.btnSave.setOnClickListener(v -> {

            if (!binding.LineName.getText().toString().equals(LineName)) {

                Toast.makeText(getContext(), "Some Changes " , Toast.LENGTH_SHORT).show();

                Log.d("Check New Line", " New Line " + binding.LineName.getText().toString() + " Old Name " +LineName );

                updateLineName ();

            }else if (!binding.etBillAmount.getText().toString().equals(BillAmountPerHundread)){

                updateBillAmount();

                Log.d("Check Et Bill Amount", "  " + binding.etBillAmount.getText().toString() + " Old Name " +BillAmountPerHundread );

            }
            else if (!binding.etNoOfInstall.getText().toString().equals(NoOfInstall)){

                updateNoOfInstall();

                Log.d("No of Install", "  " + binding.etNoOfInstall.getText().toString() + " Old Name " +NoOfInstall );


            }
            else if (!binding.etBadLoanDays.getText().toString().equals(BadLoanDays)){

                updateBadLoanDays();

                Log.d("Check BAD Loan Days", "  Days " + binding.etBadLoanDays.getText().toString() + " Old Name " +BadLoanDays );

            }
            else {

                Toast.makeText(getContext(), "No Changes ", Toast.LENGTH_SHORT).show();

                Log.d("Check New Line", " New Line " + binding.LineName.getText().toString() + " Old Name " +LineName );

            }

        });

    }

    @SuppressLint("ResourceAsColor")
    private void setUp (){
        ID = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getId();
        LineName  = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getLineName();
        LineType = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getLineType();
        BillAmountPerHundread = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getBillAmountPerHundread();
        NoOfInstall = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getNoOfInstall();
        BadLoanDays = EditLineFragmentArgs.fromBundle(getArguments()).getSettingLineData().getBadLoanDays();

        Log.d("EDIT  :  ", LineName);

        binding.LineName.setText(LineName);
        binding.etLineType.setText(LineType);
        binding.etBillAmount.setText(BillAmountPerHundread);
        binding.etNoOfInstall.setText(NoOfInstall);
        binding.etBadLoanDays.setText(BadLoanDays);

        //set Line Type btn inactive
        if (binding.LineType.isEnabled()){
            binding.LineType.setEnabled(false);
            binding.etLineType.setTextColor(R.color.default_inactive_color);
            binding.etLineType.setHintTextColor(R.color.default_inactive_color);
        }

        //



    }

    private void updateBadLoanDays() {
        MyApplication.firebase.collection(MyReferance.SettingLineData).document(ID).
                update("badLoanDays",binding.etBadLoanDays.getText().toString()).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Line successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    private void updateNoOfInstall() {
        MyApplication.firebase.collection(MyReferance.SettingLineData).document(ID).
                update("noOfInstall",binding.etNoOfInstall.getText().toString()).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Line successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    private void updateBillAmount() {
        MyApplication.firebase.collection(MyReferance.SettingLineData).document(ID).
                update("billAmountPerHundread",binding.etBillAmount.getText().toString()).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Line successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    private void updateLineName (){

        MyApplication.firebase.collection(MyReferance.SettingLineData).document(ID).
                update("lineName",binding.LineName.getText().toString()).
                addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Line successfully updated!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


    }



}