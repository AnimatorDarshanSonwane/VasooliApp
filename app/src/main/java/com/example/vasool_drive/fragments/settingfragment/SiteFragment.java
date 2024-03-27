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
import com.example.vasool_drive.adpters.SiteAdapter;
import com.example.vasool_drive.classes.AddInvestmentTypeData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SiteData;
import com.example.vasool_drive.databinding.FragmentSiteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class SiteFragment extends Fragment implements SiteAdapter.OnButtonClickLitsener {

    private FragmentSiteBinding binding;
    private BottomNavigationView bottomNavigationView;

    private ArrayList<SiteData> arrayList;
    private SiteAdapter adapter;

    public SiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSiteBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);

                Navigation.findNavController(v).popBackStack(R.id.siteFragment,true);
            }
        });

        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

    }

    private void init (){

        arrayList = new ArrayList<>();
        adapter = new SiteAdapter(arrayList, this);

        binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcView.setAdapter(adapter);

        loadSiteData();
    }
    private void setUp (){



    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadSiteData (){

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.SiteData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){

                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                // category data madhe online database cha data add kela
                SiteData data = documentSnapshot.toObject(SiteData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);
                adapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }




    @Override
    public void OnEditClick(int position, ArrayList<SiteData> siteDataArrayList) {

        SiteData data = siteDataArrayList.get(position);

        Log.d("Site Name |||||| ", " " + data.getSiteName());
        NavDirections action = SiteFragmentDirections.actionSiteFragmentToEditSiteFragment(data, data.getSiteName());

        Navigation.findNavController(getView()).navigate(action);

        Log.d("SiteFragment ", " Click ");
        // Navigation.findNavController(getView()).navigate(R.id.editExpenseTypeFragment);

        //Navigation.findNavController(getView()).popBackStack(R.id.expenseTypeFragment,true);

    }
}