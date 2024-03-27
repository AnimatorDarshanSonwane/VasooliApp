package com.example.vasool_drive.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentCustomerBinding;
import com.example.vasool_drive.fragments.settingfragment.ImportLineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class CustomerFragment extends Fragment implements LineDialogFragment.OnListClickLitsener  {
    // variable for getting view
    private FragmentCustomerBinding binding;
    private BottomNavigationView bottomNavigationView;

    // variable for line dialog
    LineDialogData lineDialogData;
    String[] LineList;
    private ArrayList<LineDialogData> arrayList1;
    int selectedposition;

    public CustomerFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerBinding.inflate(inflater,container,false);

        init();

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.VISIBLE);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).popBackStack(R.id.customerFragment, false);

    }


    private void init (){
        arrayList1 = new ArrayList<>();
        loadSettingLineData();

        binding.btnImportCustomer.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.contactListFragment);
           // Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).popBackStack(R.id.customerFragment, true);
        });


        binding.btnSubmit.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.searchResultCustomerFragment);
           // Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).popBackStack(R.id.customerFragment, true);

        });

        //set Line Type btn inactive
        if (binding.etLine.isEnabled()){
            binding.etLine.setEnabled(false);
            //binding.etLine.setTextColor(R.color.default_inactive_color);
            //binding.etLine.setHintTextColor(R.color.default_inactive_color);
        }
        binding.Line.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition,LineList);
                lineDialogFragment.setTargetFragment(CustomerFragment.this,6);
                lineDialogFragment.show(getFragmentManager(),"Line Dialog");
            }
        });
    }

    private void loadSettingLineData (){


        MyApplication.firebase.collection(MyReferance.CollectionSettingLineData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela

                lineDialogData = documentSnapshot.toObject(LineDialogData.class);

                // take id from firstore category and store in category data
                lineDialogData.setId(documentSnapshot.getId());

                // add data in arraylist and this array data go to category adapter class
                arrayList1.add(lineDialogData);

            }
            addDatatoLineDialog();

        }).addOnFailureListener(e -> {

            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }
    private void addDatatoLineDialog (){
        // populate line dialog list
        LineList = new String[arrayList1.size()];

        if (arrayList1.size()>0){
            Log.d(" arraylist size "," " + arrayList1.size());

            for (int i = 0; i < arrayList1.size(); i++) {
                LineDialogData dialogData = arrayList1.get(i);
                LineList[i] = String.valueOf(dialogData.getLineName());
                Log.d(" arraylist "," " + LineList[i]);
            }
            Log.d(" Linelistsize "," " + LineList.length);

        }
    }


    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list,String[] lineIdlist, int position) {
        selectedposition=position;
        binding.etLine.setEnabled(true);
        binding.etLine.setText(list[position]);
        binding.etLine.setEnabled(false);
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }


}