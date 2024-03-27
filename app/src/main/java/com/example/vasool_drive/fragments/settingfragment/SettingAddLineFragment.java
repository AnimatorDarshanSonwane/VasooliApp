package com.example.vasool_drive.fragments.settingfragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentSettingAddLineBinding;
import com.example.vasool_drive.dialog.LineTypeDialogFragment;
import com.example.vasool_drive.fragments.CollectionFragment;
import com.example.vasool_drive.fragments.LineDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SettingAddLineFragment extends Fragment implements LineTypeDialogFragment.OnListClickLitsener{
    private static final String TAG = " SettingAddLineFragament ";
    private BottomNavigationView bottomNavigationView;
    private FragmentSettingAddLineBinding binding;

    int selectedposition;
    public SettingAddLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingAddLineBinding.inflate(inflater,container,false);

        binding.etLineType.setEnabled(false);

        binding.btnClose.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.settingLineFragment);

            Navigation.findNavController(v).popBackStack(R.id.settingAddLineFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.GONE);



        binding.LineType.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineTypeDialogFragment lineTypeDialogFragment = new LineTypeDialogFragment(selectedposition);
                lineTypeDialogFragment.setTargetFragment(SettingAddLineFragment.this,1);
                lineTypeDialogFragment.show(getFragmentManager(),"Line Type Dialog");
            }
        });

    binding.btnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           addLineData();
        }
    });
    }

    private SettingLineData getSettingLineData(){
        String LineName = binding.LineName.getText().toString();
        String LineType = binding.etLineType.getText().toString();
        String BillAmountPerHundread = binding.etBillAmount.getText().toString();
        String NoOfInstall = binding.etNoOfInstall.getText().toString();
        String BadLoanDays = binding.etBadLoanDays.getText().toString();

        if (LineName.isEmpty()){
            binding.LineName.setError("Please Enter Line Name ");
            Toast.makeText(getActivity(), "Please Enter Line Name ", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (LineType.isEmpty()){
            binding.etLineType.setError("Please Enter Line Type ");

            return null;
        }
        if (BillAmountPerHundread.isEmpty()){
            binding.etBillAmount.setError("Please Enter  Bill Amount ");
            return null;
        }
        if (NoOfInstall.isEmpty()){
            binding.etNoOfInstall.setError("Please Enter No of Install ");

            return null;
        }
        if (BadLoanDays.isEmpty()){
            binding.etBadLoanDays.setError("Please Enter Bad Loan Days ");

            return null;
        }


        SettingLineData data = new SettingLineData();
        data.setLineName(LineName);
        data.setLineType(LineType);
        data.setBillAmountPerHundread(BillAmountPerHundread);
        data.setBadLoanDays(BadLoanDays);
        data.setNoOfInstall(NoOfInstall);
        return data;
    }
    private void addLineData () {
        // call data from category activity
        SettingLineData data = getSettingLineData();
        if (data==null){
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.CollectionSettingLineData).add(data).addOnSuccessListener(documentReference -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "New Line Added", Toast.LENGTH_SHORT).show();


        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Log.d(TAG, "addLineText: " + e);
            Toast.makeText(getActivity(), "Somethig Went Wrong", Toast.LENGTH_SHORT).show();


        });
    }

    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, int position) {
        selectedposition=position;
        binding.etLineType.setText(list[position]);
        binding.etLineType.setEnabled(false);

    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
    dialogInterface.dismiss();
    }
}