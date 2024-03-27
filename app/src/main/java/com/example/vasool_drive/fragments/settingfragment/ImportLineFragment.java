package com.example.vasool_drive.fragments.settingfragment;

import android.content.DialogInterface;
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
import com.example.vasool_drive.adpters.SettingLineAdapter;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentImportLineBinding;
import com.example.vasool_drive.fragments.CollectionFragment;
import com.example.vasool_drive.fragments.LineDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class ImportLineFragment extends Fragment implements LineDialogFragment.OnListClickLitsener {

    private FragmentImportLineBinding binding;
    private BottomNavigationView bottomNavigationView;

    LineDialogData lineDialogData;
    String[] LineList;
    private ArrayList<LineDialogData> arrayList1;
    int selectedposition;


    public ImportLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentImportLineBinding.inflate(inflater,container,false);

        init();

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.layout6.setVisibility(View.GONE);
        binding.ivBtnDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layout6.getVisibility() == View.GONE){
                    binding.layout6.setVisibility(View.VISIBLE);
                }else {
                    binding.layout6.setVisibility(View.GONE);
                }

            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.importLineFragment,true);

            }
        });

        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Line.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition,LineList);
                lineDialogFragment.setTargetFragment(ImportLineFragment.this,5);
                lineDialogFragment.show(getFragmentManager(),"Line Dialog");

            }
        });



    }


    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, String[] lineIdlist, int position) {
        selectedposition=position;
        binding.etLine.setText(list[position]);
        binding.etLine.setEnabled(false);


    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

    private void init (){
        arrayList1 = new ArrayList<>();
        loadSettingLineData();

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


}