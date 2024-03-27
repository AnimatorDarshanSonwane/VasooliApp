package com.example.vasool_drive.fragments.notification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.TodayReminderAdapter;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.TodayReminderData;
import com.example.vasool_drive.databinding.FragmentTodayBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class TodayFragment extends Fragment implements TodayReminderAdapter.OnButtonClickLitsener {


    private String title;
    private FragmentTodayBinding binding;
    private ArrayList<TodayReminderData> arrayList;
    private TodayReminderAdapter adapter;

    public TodayFragment(String title) {
        this.title = title;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTodayBinding.inflate(inflater,container,false);
        init();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Navigation.findNavController(v).navigate(R.id.addReminderFragment);

            }
        });


    }

    private void  init (){
        arrayList = new ArrayList<>();
        adapter = new TodayReminderAdapter(arrayList,this);
        binding.rvRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecyclerview.setAdapter(adapter);
        binding.rvRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loadTodayReminderData();
        binding.swipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                adapter.notifyDataSetChanged();
                loadTodayReminderData();
                binding.swipetorefresh.setRefreshing(false);
            }
        });
    }

    public void loadTodayReminderData() {
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.TODAYREMINDER).get().addOnSuccessListener(queryDocumentSnapshots -> {
            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                TodayReminderData data = documentSnapshot.toObject(TodayReminderData.class);

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
    public void OnDeleteClick(int position) {
        TodayReminderData data = arrayList.get(position);

        MyApplication.firebase.collection(MyReferance.TODAYREMINDER).document(data.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                arrayList.remove(position);
                adapter.notifyDataSetChanged();
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