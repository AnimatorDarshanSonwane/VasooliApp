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
import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentAddExpenseBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import java.util.Collections;


public class AddExpenseFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FragmentAddExpenseBinding binding;

    public AddExpenseFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAddExpenseBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.GONE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setup();



    }

    private void init (){


    }

    private void setup(){

        binding.btnClose.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.expenseTypeFragment);

            Navigation.findNavController(v).popBackStack(R.id.addExpenseFragment,true);



        });

        binding.btnSave.setOnClickListener(v -> {
            addExpenseType();
        });

    }

    private AddExpenseTypeData getExpenseTypeData(){

        String ExpenseTypeName = binding.etExpenseTypeName.getText().toString();
        String Status = binding.etStatus.getText().toString();

        if (Status.isEmpty()){
            binding.etStatus.setError("Please Select Status ");
            return null;
        }

        if (ExpenseTypeName.isEmpty()){
            binding.etExpenseTypeName.setError("Please Enter Area ");
            return null;
        }

        AddExpenseTypeData data = new AddExpenseTypeData();
        //data.setLineName(LineName);
        data.setExpenseTypeName(binding.etExpenseTypeName.getText().toString());
        data.setStatus(Status);
        return data;
    }

    private void addExpenseType() {

        // call data from category activity

        AddExpenseTypeData data = getExpenseTypeData();
        if (data == null) {
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.ExpenseTypeData).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                binding.progressCircular.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Add Sucessfully", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).popBackStack(R.id.addExpenseFragment,true);
                bottomNavigationView.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d(" Add Expenses Fragment ", " Expection   ::   " + e);
            }
        });

    }

}