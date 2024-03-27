package com.example.vasool_drive.fragments.settingfragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.AreaAdapter;
import com.example.vasool_drive.adpters.AreaAdapter2;
import com.example.vasool_drive.classes.AddAreaData;
import com.example.vasool_drive.classes.AreaData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentAreaBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AreaFragment extends Fragment {


    ArrayList<AreaData> ArrayList;
    ArrayList<SettingLineData> settingLineDataArrayList;

    AreaAdapter adapter;

    private FragmentAreaBinding binding;
    private BottomNavigationView bottomNavigationView;

    public AreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAreaBinding.inflate( inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        init();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.addAreaFragment);
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.areaFragment,true);

            }
        });



    }

    private void init (){

        settingLineDataArrayList = new ArrayList<>();
        ArrayList = new ArrayList<>();


        adapter = new AreaAdapter(settingLineDataArrayList,getContext());


        binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcView.setAdapter(adapter);

        loadSettingLineData();

    }



    private void loadSettingLineData () {

        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.SettingLineData).get().addOnSuccessListener(queryDocumentSnapshots -> {
            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){

                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                SettingLineData data = documentSnapshot.toObject(SettingLineData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                // add data in arraylist and this array data go to category adapter class
                settingLineDataArrayList.add(data);
                adapter.notifyDataSetChanged();
            }

           howManyLine();

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });



    }
    private void howManyLine(){


        for (int i =0; i< settingLineDataArrayList.size();i++) {

            SettingLineData data = settingLineDataArrayList.get(i);

            if (data==null){
                return;
            }

            Log.d("Area  Fragment ", " document Id : " + data.getId());
            Log.d("Area  Names ", " Name : " + data.getAreaName());
            //getAreaData(data.getId());

        }



    }

    private void getAreaData (String documentid){

     MyApplication.firebase.collection(MyReferance.SettingLineData).document(documentid).collection(MyReferance.AreaData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                AreaData data = documentSnapshot.toObject(AreaData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());
                data.setAreaName(documentSnapshot.getString("AreaName"));

                // add data in arraylist and this array data go to category adapter class
                ArrayList.add(data);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Arrray list: "+ ArrayList.size());

            }


        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });


    }



}


