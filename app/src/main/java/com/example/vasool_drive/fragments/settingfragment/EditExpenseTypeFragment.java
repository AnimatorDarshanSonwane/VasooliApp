package com.example.vasool_drive.fragments.settingfragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.vasool_drive.databinding.FragmentEditExpenseTypeBinding;
import com.example.vasool_drive.dialog.LineTypeDialogFragment;
import com.example.vasool_drive.fragments.StatusDialogFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EditExpenseTypeFragment extends Fragment implements StatusDialogFragment.OnListClickLitsener {
    String DocumentId = "";

    int selectedposition;

    FragmentEditExpenseTypeBinding binding;

    private BottomNavigationView bottomNavigationView;

    public EditExpenseTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditExpenseTypeBinding.inflate(inflater,container,false);

        loadEdit();
        init();
        return binding.getRoot();
    }

    private void init (){

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.GONE);

        binding.btnSave.setOnClickListener(v -> {

            UpdateExpenseTypeData();
        });

        binding.Status.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusDialogFragment StatusDialogFragment = new StatusDialogFragment(selectedposition);
                StatusDialogFragment.setTargetFragment(EditExpenseTypeFragment.this,1);
                StatusDialogFragment.show(getFragmentManager(),"Status Dialog");
            }
        });

        binding.btnClose.setOnClickListener(v -> {

            Navigation.findNavController(getView()).popBackStack(R.id.editExpenseTypeFragment,true);
            bottomNavigationView.setVisibility(View.VISIBLE);

        });

    }
    private void setup(){


    }

    private void loadEdit(){

        AddExpenseTypeData data = EditExpenseTypeFragmentArgs.fromBundle(getArguments()).getAddExpenseTypeData();

        if (data== null){
            Log.d("EditExpenseTypeData   :"," Data is Null ");
            return;
        }

        Log.d("EditExpenseTypeData   :"," doc id :  "+ data.getId() );

        DocumentId = data.getId();
        binding.etExpenseTypeName.setText(data.getExpenseTypeName());
        binding.etStatus.setText(data.getStatus());

    }


    private AddExpenseTypeData getExpenseTypeData (){


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
        data.setExpenseTypeName(ExpenseTypeName);
        data.setStatus(Status);
        data.setId(DocumentId);
        return data;
    }
    private void UpdateExpenseTypeData (){

        AddExpenseTypeData data = getExpenseTypeData();
        if (data == null||data.getId().isEmpty()) {
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);
        MyApplication.firebase.collection(MyReferance.ExpenseTypeData).document(data.getId()).update("expenseTypeName",data.getExpenseTypeName(),"status",data.getStatus()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                binding.progressCircular.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Update Sucessfully", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).popBackStack(R.id.editExpenseTypeFragment,true);
                bottomNavigationView.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d(" Edit Expense Type Fragment ", " Expection   ::   " + e);

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




}