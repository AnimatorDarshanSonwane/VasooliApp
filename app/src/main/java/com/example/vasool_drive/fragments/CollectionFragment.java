package com.example.vasool_drive.fragments;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentCollectionBinding;
import com.example.vasool_drive.dialog.AreaMultiChoiseDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class CollectionFragment extends Fragment  implements LineDialogFragment.OnListClickLitsener, AreaMultiChoiseDialogFragment.onMultiChoiseLitsener{


    private FragmentCollectionBinding binding;
    private BottomNavigationView bottomNavigationView;

    LineDialogData lineDialogData;


    // store Line data from firebase in arraylist 1

    private ArrayList<LineDialogData> arrayList1;

    // store area data from firebase in arraylist 2
    private ArrayList<LineDialogData> arrayList2;

    private List<String> AreaName;

    String[] LineList;
    String[] AreaList;

    private int year;
    private int month;
    private int day;

    int selectedposition;

    // initiliaze boolean array for multichoise dialog
    boolean [] CheckedItems = null;
    public CollectionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCollectionBinding.inflate(inflater,container,false);
        init();
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.VISIBLE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.notificationFragment);
            }
        });

        OpenCalender();

        binding.Line.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition,LineList);
                lineDialogFragment.setTargetFragment(CollectionFragment.this,4);
                lineDialogFragment.show(getFragmentManager(),"Line Dialog");

            }
        });

        binding.Area.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AreaList==null){
                    Toast.makeText(getContext(), "Please Select Line", Toast.LENGTH_SHORT).show();
                } else {
                    AreaMultiChoiseDialogFragment areaMultiChoiseDialogFragment = new AreaMultiChoiseDialogFragment(CheckedItems, AreaList);
                    areaMultiChoiseDialogFragment.setTargetFragment(CollectionFragment.this, 5);
                    areaMultiChoiseDialogFragment.show(getFragmentManager(), "Area");
                }
            }
        });


    }

    private void OpenCalender (){
        Calendar calendar = Calendar.getInstance();

        binding.Date.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month +1) +"/"+ String.valueOf(year);
                        binding.etDate.setText(selectedDate);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



    }

// POSITIVE BUTTON OF LINE DIALOG
    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, String[] lineIdlist, int position) {
        selectedposition=position;
        binding.etLine.setText(list[position]);
        binding.etLine.setEnabled(false);

        if (Objects.equals(binding.etLine.getText(), "")){
            return;

        }
        // load area names using selected line name
        loadAreaData(binding.etLine.getText().toString());


    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

    private void init (){
        binding.etLine.setEnabled(false);
        binding.etArea.setEnabled(false);

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

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
            Log.d(" arraylist 1 size "," " + arrayList1.size());

            for (int i = 0; i < arrayList1.size(); i++) {
                LineDialogData dialogData = arrayList1.get(i);
                LineList[i] = String.valueOf(dialogData.getLineName());
                Log.d(" arraylist 1 "," " + LineList[i]);
            }
            Log.d(" Linelistsize "," " + LineList.length);

        }
    }


    private void loadAreaData (String LineName){


        MyApplication.firebase.collection(MyReferance.CollectionSettingLineData).whereEqualTo("lineName", LineName).get().addOnSuccessListener(queryDocumentSnapshots -> {

            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela

                lineDialogData = documentSnapshot.toObject(LineDialogData.class);

                // take id from firstore category and store in category data
                lineDialogData.setId(documentSnapshot.getId());

                arrayList2.clear();
                // add data in arraylist and this array data go to category adapter class
                arrayList2.add(lineDialogData);

            }
            addDatatoAreaDialog();

        }).addOnFailureListener(e -> {

            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }

    private void addDatatoAreaDialog() {
        // populate line dialog list
        AreaName = Arrays.asList(new String[arrayList2.size()]);

        LineDialogData dialogData = arrayList2.get(0);

        // decide length of dialog list
        int AreaName = dialogData.getAreaName().size();

        // decide length of dialog list
        AreaList = new String[AreaName];

        Log.d(" Arealist size "," " + AreaName);

        if (arrayList2.size()>0){
           // Log.d(" arealist size "," " + AreaName.size());

            for (int i = 0; i < dialogData.getAreaName().size(); i++) {

                AreaList[i] = dialogData.getAreaName().get(i);
                String AreaNames = dialogData.getAreaName().get(i);

                Log.d(" arealist "," " + AreaNames);
            }

            Log.d(" arealistsize "," " + AreaList.length);

        }
    }


    // POSITIVE BUTTON OF AREA DIALOG
    @Override
    public void onPositiveButtonClicked(DialogInterface dialogInterface, int position, String[] list, ArrayList<String> selecteditemlist, ArrayList<Integer> mUserItems, boolean[] checkedItems, List<String> selectedItems) {
        binding.etArea.setEnabled(false);

        CheckedItems = checkedItems;
        String items = "";

//       for (int i = 0; i<mUserItems.size(); i++ ){
//           items = items + list [mUserItems.get(i)];
//           if (i!= mUserItems.size()-1){
//               items = items + ", ";
//
//           }
////
////           String Arealist = selectedItems.get(i);
////           Log.d("Collection Fragmemt", "onPositiveButtonClicked: " + Arealist);
//
// }

        for (int i = 0; i<checkedItems.length; i++ ){

            // check where true in boolean array and get text to set set text
            if (CheckedItems[i]){

                items = items + list [i] + ", ";

            }

        }
        binding.etArea.setText(items);
        binding.etArea.setEnabled(false);

    }

    @Override
    public void onNegativeButtonClicked(DialogInterface dialogInterface, int position) {

        dialogInterface.dismiss();

    }


}