package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.AreaAdapter;
import com.example.vasool_drive.adpters.ExpenseTypeDataAdapter;
import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentExpenseTypeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class ExpenseTypeFragment extends Fragment implements ExpenseTypeDataAdapter.OnButtonClickLitsener {

    private FragmentExpenseTypeBinding binding;
    private BottomNavigationView bottomNavigationView;

    ArrayList<AddExpenseTypeData> arrayList;
    ExpenseTypeDataAdapter expenseTypeDataAdapter;

    public ExpenseTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentExpenseTypeBinding.inflate( inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.expenseTypeFragment,true);
            }
        });

        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.addExpenseFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        setup();

    }

    private void init (){
        arrayList = new ArrayList<>();

        expenseTypeDataAdapter= new ExpenseTypeDataAdapter(arrayList,this);

        binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcView.setAdapter(expenseTypeDataAdapter);

    }

    private void setup(){

       LoadExpenseTypeData();


    }

    private void LoadExpenseTypeData (){

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.ExpenseTypeData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){

                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                // category data madhe online database cha data add kela
                AddExpenseTypeData data = documentSnapshot.toObject(AddExpenseTypeData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);
                expenseTypeDataAdapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void OnClick(int position, ArrayList<AddExpenseTypeData> ExpenseTypeData) {

        AddExpenseTypeData data = ExpenseTypeData.get(position);

        NavDirections action = ExpenseTypeFragmentDirections.actionExpenseTypeFragmentToEditExpenseTypeFragment(data,data.getExpenseTypeName());

        Navigation.findNavController(getView()).navigate(action);

        Log.d(" ExpenseTypeFragment ::", " Click ");
       // Navigation.findNavController(getView()).navigate(R.id.editExpenseTypeFragment);

        //Navigation.findNavController(getView()).popBackStack(R.id.expenseTypeFragment,true);

    }

    @Override
    public void OnDeleteClick(int position, ArrayList<AddExpenseTypeData> ExpenseTypeData) {

        AddExpenseTypeData data = ExpenseTypeData.get(position);

        if (data == null|| data.getId().isEmpty()){
            Log.d(" ExpenseTypeFragment :: ", " Add Expense Type Data is Empty ");

            return;
        }

        MyApplication.firebase.collection(MyReferance.ExpenseTypeData).document(data.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                arrayList.remove(position);
                binding.rcView.removeViewAt(position);
                expenseTypeDataAdapter.notifyItemRemoved(position);
                expenseTypeDataAdapter.notifyItemRangeChanged(position, arrayList.size());

                Toast.makeText(getActivity(), "Delete Sucessfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(" ExpenseTypeFragment :: ", " Error :: " + e);

            }
        });

    }


}