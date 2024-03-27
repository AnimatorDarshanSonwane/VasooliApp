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
import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.classes.AddInvestmentTypeData;
import com.example.vasool_drive.classes.ExpenseTypeDialogData;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentAddInvestmentBinding;
import com.example.vasool_drive.databinding.FragmentAddInvestmentTypeBinding;
import com.example.vasool_drive.fragments.ExpenseTypeDialogFragment;
import com.example.vasool_drive.fragments.StatusDialogFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class AddInvestmentTypeFragment extends Fragment  implements ExpenseTypeDialogFragment.OnExpenseListClickLitsener, StatusDialogFragment.OnListClickLitsener {
    private FragmentAddInvestmentTypeBinding binding;
    private BottomNavigationView bottomNavigationView;

    ExpenseTypeDialogData ExpenseTypeDialogData;
    private ArrayList<ExpenseTypeDialogData> arrayList1;
    String[] ExpenseTypeList;
    String[] ExpenseTypeIDList;
    String ExpenseTypeId = "";
    int selectedposition;
    int ExpenseTypeSelectedposition;
    public AddInvestmentTypeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddInvestmentTypeBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.GONE);

        init();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setup();

    }

    private void init (){

        arrayList1 = new ArrayList<>();
        loadExpenseTypeData();
    }

    private void setup(){

        binding.btnClose.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.investmentTypeFragment);

            Navigation.findNavController(v).popBackStack(R.id.addInvestmentTypeFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);
        });

        binding.btnSave.setOnClickListener(v -> {
            addInvestmentType();
        });

        binding.Status.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusDialogFragment StatusDialogFragment = new StatusDialogFragment(selectedposition);
                StatusDialogFragment.setTargetFragment(AddInvestmentTypeFragment.this,2);
                StatusDialogFragment.show(getFragmentManager(),"Status Dialog");

            }
        });

        binding.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.switch1.isChecked()) {

                    binding.ExpenseType.setVisibility(View.VISIBLE);

                } else {
                    binding.ExpenseType.setVisibility(View.GONE);
                }
            }
        });

        binding.ExpenseType.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExpenseTypeDialogFragment ExpenseTypeDialogFragment = new ExpenseTypeDialogFragment (ExpenseTypeSelectedposition,ExpenseTypeList,ExpenseTypeIDList);
                ExpenseTypeDialogFragment.setTargetFragment(AddInvestmentTypeFragment.this,1);
                ExpenseTypeDialogFragment.show(getFragmentManager(),"Expense Type Dialog");

            }
        });

    }

    private AddInvestmentTypeData getInvestmentTypeData(){

        String InvestmentTypeName = binding.etInvestmentTypeName.getText().toString();
        String Status = binding.etStatus.getText().toString();
        String ExpenseType = binding.etExpenseType.getText().toString();

        if (Status.isEmpty()){
            binding.etStatus.setError("Please Select Status ");
            return null;
        }

        if (InvestmentTypeName.isEmpty()){
            binding.etInvestmentTypeName.setError("Please Enter Investment Type Name ");
            return null;
        }

        AddInvestmentTypeData data = new AddInvestmentTypeData();
        //data.setLineName(LineName);
        data.setInvestmentTypeName(binding.etInvestmentTypeName.getText().toString());
        data.setStatus(Status);
        data.setExpenseType(ExpenseType);
        data.setExpenseTypePosition(ExpenseTypeSelectedposition);
        data.setId(ExpenseTypeId);
        return data;
    }

    private void addInvestmentType() {

        // call data from category activity

        AddInvestmentTypeData data = getInvestmentTypeData();
        if (data == null) {
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.InvestmentTypeData).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                binding.progressCircular.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Add Sucessfully", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).popBackStack(R.id.addInvestmentTypeFragment,true);
                bottomNavigationView.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d(" Add Investment Type Fragment ", " Expection   ::   " + e);
            }
        });

    }


    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, String[] lineIdList, int position) {

        selectedposition=position;
        binding.etStatus.setText(list[position]);
        binding.etStatus.setEnabled(false);

    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

    private void loadExpenseTypeData (){


        MyApplication.firebase.collection(MyReferance.ExpenseTypeData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela

                ExpenseTypeDialogData = documentSnapshot.toObject(ExpenseTypeDialogData.class);

                // take id from firstore category and store in category data
                ExpenseTypeDialogData.setId(documentSnapshot.getId());

                Log.d("Add InvestmentTypeFragment  :: ", documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                arrayList1.add(ExpenseTypeDialogData);

            }
            addDatatoExpenseTypeDialog();

        }).addOnFailureListener(e -> {

            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }
    private void addDatatoExpenseTypeDialog (){
        // populate line dialog list
        ExpenseTypeList = new String[arrayList1.size()];
        ExpenseTypeIDList = new String[arrayList1.size()];
        if (arrayList1.size()>0){
            Log.d(" arraylist size "," " + arrayList1.size());

            for (int i = 0; i < arrayList1.size(); i++) {
                ExpenseTypeDialogData expenseTypeDialogData = arrayList1.get(i);
                ExpenseTypeList[i] = String.valueOf(expenseTypeDialogData.getExpenseTypeName());
                ExpenseTypeIDList[i] = String.valueOf(expenseTypeDialogData.getId());

                Log.d(" arraylist "," " + ExpenseTypeList[i]);
            }
            Log.d(" ExpenseTypeListsize "," " + ExpenseTypeList.length);

        }
    }


    @Override
    public void onExpensePositiveButtonClick(DialogInterface dialogInterface, String[] expenseTypeList, String[] ExpenseIdList, int position) {

        ExpenseTypeSelectedposition = position;
        ExpenseTypeId = ExpenseIdList[position];
        binding.etExpenseType.setText(expenseTypeList[position]);
        binding.etExpenseType.setEnabled(false);


    }

    @Override
    public void onExpenseNegativeButtonClick(DialogInterface dialogInterface, int position) {

        dialogInterface.dismiss();

    }

}