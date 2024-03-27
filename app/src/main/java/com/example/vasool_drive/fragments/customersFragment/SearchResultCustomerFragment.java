package com.example.vasool_drive.fragments.customersFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.SwipeAdapter;
import com.example.vasool_drive.adpters.TodayReminderAdapter;
import com.example.vasool_drive.classes.AddCustomerData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.TodayReminderData;
import com.example.vasool_drive.databinding.FragmentSearchResultCustomerBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class SearchResultCustomerFragment extends Fragment implements SwipeAdapter.OnLineClickListner {

    private FragmentSearchResultCustomerBinding binding;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<AddCustomerData> arrayList;
    private SwipeAdapter swipeAdapter;

    public SearchResultCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultCustomerBinding.inflate(inflater,container,false);

        init();
        setUp();

        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.customerFragment);

            Navigation.findNavController(v).popBackStack(R.id.searchResultCustomerFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);

        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp();
    }

    private void init (){

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        arrayList = new ArrayList<>();
        swipeAdapter = new SwipeAdapter(arrayList,this);
        binding.rvSwipe.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvSwipe.setAdapter(swipeAdapter);
        binding.rvSwipe.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loadCustomerData();

    }

    private void setUp(){

        binding.btnAdd.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.addCustomerFragment);

            //Navigation.findNavController(v).popBackStack(R.id.searchResultCustomerFragment,false);

        } );

    }
    public void loadCustomerData() {
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.AddCustomerData).get().addOnSuccessListener(queryDocumentSnapshots -> {
            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                AddCustomerData data = documentSnapshot.toObject(AddCustomerData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());

                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);
                swipeAdapter.notifyDataSetChanged();

            }

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onEditClick(int position) {
        Toast.makeText(getContext(), "Edit Click " , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        AddCustomerData data = arrayList.get(position);

        MyApplication.firebase.collection(MyReferance.AddCustomerData).document(data.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                arrayList.remove(position);
                swipeAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}