package com.example.vasool_drive.fragments.settingfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.SettingLineAdapter;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentSettingLineBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class SettingLineFragment extends Fragment implements SettingLineAdapter.OnLineClickListner {

    private FragmentSettingLineBinding binding;
    private BottomNavigationView bottomNavigationView;
    private SettingLineAdapter adapter;
    private ArrayList<SettingLineData> arrayList;

    public SettingLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentSettingLineBinding.inflate(inflater,container,false);

       bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
       bottomNavigationView.setVisibility(View.VISIBLE);

       init();

       binding.btnBack.setOnClickListener(v -> {
           Navigation.findNavController(v).navigate(R.id.settingFragment);
           Navigation.findNavController(v).popBackStack(R.id.settingLineFragment,true);

       });

       binding.btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.settingAddLineFragment);
           }
       });

       loadSettingLineData();

       binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               arrayList.clear();
               adapter.notifyDataSetChanged();
               loadSettingLineData();
               binding.refresh.setRefreshing(false);
           }
       });

       return binding.getRoot();
    }

    private void init (){

    arrayList = new ArrayList<>();
    adapter = new SettingLineAdapter(arrayList,this);

    binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.rcView.setAdapter(adapter);

    }

    @Override
    public void onLineClick(int position) {
       SettingLineData settingLineData = arrayList.get(position);

       NavDirections action =
                SettingLineFragmentDirections.actionSettingLineFragmentToEditLineFragment(settingLineData);

       Navigation.findNavController(getView()).navigate(action);

       //Navigation.findNavController(getView()).navigate(R.id.editLineFragment);

    }

    private void loadSettingLineData (){

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.CollectionSettingLineData).get().addOnSuccessListener(queryDocumentSnapshots -> {
            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                SettingLineData data = documentSnapshot.toObject(SettingLineData.class);

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


}