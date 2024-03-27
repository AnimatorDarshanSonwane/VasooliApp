package com.example.vasool_drive.fragments.settingfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.InvestmentTypeAdapter;
import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.classes.AddInvestmentTypeData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentInvestmentTypeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class InvestmentTypeFragment extends Fragment implements InvestmentTypeAdapter.OnButtonClickLitsener {

    private FragmentInvestmentTypeBinding binding;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<AddInvestmentTypeData> arrayList;
    InvestmentTypeAdapter investmentTypeAdapter;
    public InvestmentTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInvestmentTypeBinding.inflate(inflater,container,false);

        init();
        LoadInvestmentTypeData();

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.investmentTypeFragment,true);

            }
        });



        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.addInvestmentTypeFragment);
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void  init ( ){

        arrayList = new ArrayList<>();

        investmentTypeAdapter = new InvestmentTypeAdapter(arrayList,this);

        binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcView.setAdapter(investmentTypeAdapter);


    }

    @SuppressLint("NotifyDataSetChanged")
    private void LoadInvestmentTypeData () {

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.InvestmentTypeData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){

                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                // category data madhe online database cha data add kela
                AddInvestmentTypeData data = documentSnapshot.toObject(AddInvestmentTypeData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);
                investmentTypeAdapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void OnItemClick(int position, ArrayList<AddInvestmentTypeData> InvestmentTypeData) {

        AddInvestmentTypeData data = InvestmentTypeData.get(position);

        Log.d("Expense Position |||||| ", " " + data.getExpenseTypePosition());
        NavDirections action = InvestmentTypeFragmentDirections.actionInvestmentTypeFragmentToEditInvetmentTypeFragment(data, data.getId());

        Navigation.findNavController(getView()).navigate(action);

        Log.d("InvestmentTypeFragment :: ", " Click ");
        // Navigation.findNavController(getView()).navigate(R.id.editExpenseTypeFragment);

        //Navigation.findNavController(getView()).popBackStack(R.id.expenseTypeFragment,true);

    }

    @Override
    public void OnDeleteClick(int position, ArrayList<AddInvestmentTypeData> InvestmentTypeData) {

        AddInvestmentTypeData data = InvestmentTypeData.get(position);

        if (data == null|| data.getId().isEmpty()){
            Log.d(" InvestmentTypeFragment :: ", " Add Investment Type Data is Empty ");

            return;
        }

        MyApplication.firebase.collection(MyReferance.InvestmentTypeData).document(data.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                arrayList.remove(position);
                binding.rcView.removeViewAt(position);
                investmentTypeAdapter.notifyItemRemoved(position);
                investmentTypeAdapter.notifyItemRangeChanged(position, arrayList.size());

                Toast.makeText(getActivity(), "Delete Sucessfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(" InvestmentTypeFragment :: ", " Error :: " + e);

            }
        });


    }
}