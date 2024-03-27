package com.example.vasool_drive.fragments.settingfragment;

import static android.content.ContentValues.TAG;

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
import com.example.vasool_drive.adpters.AreaAdapter2;
import com.example.vasool_drive.classes.AddAreaData;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentAddAreaBinding;
import com.example.vasool_drive.fragments.CustomerFragment;
import com.example.vasool_drive.fragments.LineDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firestore.v1.WriteResult;

import java.util.ArrayList;
import java.util.Collections;


public class AddAreaFragment extends Fragment implements LineDialogFragment.OnListClickLitsener {


    private FragmentAddAreaBinding binding;
    private BottomNavigationView bottomNavigationView;

    // variable for line dialog
    LineDialogData lineDialogData;
    String[] LineList, LineIdList;
    private ArrayList<LineDialogData> arrayList1;
    int selectedposition;
    String LineId = "";

    public AddAreaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddAreaBinding.inflate(inflater,container,false);

        init();

        binding.btnClose.setOnClickListener(v -> {
            //Navigation.findNavController(v).navigate(R.id.areaFragment);

            Navigation.findNavController(v).popBackStack(R.id.addAreaFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);

        });

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.GONE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set Line Type btn inactive
        if (binding.etLine.isEnabled()){
            binding.etLine.setEnabled(false);
            //binding.etLine.setTextColor(R.color.default_inactive_color);
            //binding.etLine.setHintTextColor(R.color.default_inactive_color);
        }
        binding.Line.setEndIconOnClickListener(v -> {
            //Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
            LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition,LineList,LineIdList);
            lineDialogFragment.setTargetFragment(AddAreaFragment.this,7);
            lineDialogFragment.show(getFragmentManager(),"Line Dialog");
        });

        binding.btnSave.setOnClickListener(v -> {
            addArea(LineId);
        });

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
        LineIdList = new String[arrayList1.size()];
        if (arrayList1.size()>0){
            Log.d(" arraylist size "," " + arrayList1.size());

            for (int i = 0; i < arrayList1.size(); i++) {
                LineDialogData dialogData = arrayList1.get(i);
                LineList[i] = String.valueOf(dialogData.getLineName());
                LineIdList[i]= String.valueOf(dialogData.getId());
                Log.d(" arraylist "," " + LineList[i]);
            }
            Log.d(" Linelistsize "," " + LineList.length);

        }
    }


    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, String[]lineIdList, int position) {
        selectedposition=position;
        binding.etLine.setEnabled(true);
        binding.etLine.setText(list[position]);
        Log.d("Add Area Fragment  : ", " Line ID  : " + lineIdList[position]);
        LineId =lineIdList[position];
        binding.etLine.setEnabled(false);
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

    private SettingLineData getAreaData(){

        String AreaName = binding.etAreaName.getText().toString();
      //  String LineName = binding.etLine.getText().toString();

//        if (LineName.isEmpty()){
//            binding.etLine.setError("Please Select Line ");
//            return null;
//        }

        if (AreaName.isEmpty()){
            binding.etAreaName.setError("Please Enter Area ");
            return null;
        }

        SettingLineData data = new SettingLineData();
        //data.setLineName(LineName);
        data.setAreaName(Collections.singletonList(binding.etAreaName.getText().toString()));
        return data;
    }

    private void addArea(String lineId) {
        // call data from category activity
        SettingLineData data = getAreaData();
        if (data == null && lineId.isEmpty()) {
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);

        Log.d("Add Aread ", " name  " + data.getAreaName());

    // Atomically add a new region to the "regions" array field.
        MyApplication.firebase.collection(MyReferance.SettingLineData).document(lineId).update("AreaName",FieldValue.arrayUnion(binding.etAreaName.getText().toString()));
        Toast.makeText(getContext(), "Area Add", Toast.LENGTH_SHORT).show();
        binding.progressCircular.setVisibility(View.GONE);

        // Navigate
        Navigation.findNavController(getView()).popBackStack(R.id.addAreaFragment,true);
        bottomNavigationView.setVisibility(View.VISIBLE);


    // Atomically remove a region from the "regions" array field.
       // MyApplication.firebase.collection(MyReferance.SettingLineData).document(lineId).update("AreaName", FieldValue.arrayRemove(data.getAreaName()));
    }




}

