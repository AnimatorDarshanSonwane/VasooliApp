package com.example.vasool_drive.fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.ExpensesListAdapter;
import com.example.vasool_drive.classes.ExpensesData;
import com.example.vasool_drive.databinding.FragmentExpensesBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class ExpensesFragment extends Fragment implements LineDialogFragment.OnListClickLitsener {


    private FragmentExpensesBinding binding;
    private ExpensesListAdapter adapter;
    private ArrayList<ExpensesData>arrayList;

    String FromDate;
    String ToDate;

    private int year;
    private int month;
    private int day;

    int selectedposition;
    public ExpensesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpensesBinding.inflate(inflater,container,false);

        binding.layout1.setVisibility(View.GONE);

        init();

        return binding.getRoot();
    }

    private void init (){
        arrayList = new ArrayList<>();
        adapter = new ExpensesListAdapter(arrayList);

        binding.rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcView.setAdapter(adapter);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layout1.getVisibility() == View.VISIBLE){
                    binding.layout1.setVisibility(View.GONE);
                }else{
                    binding.layout1.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.LineLy.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition);
                lineDialogFragment.setTargetFragment(ExpensesFragment.this,3);
                lineDialogFragment.show(getFragmentManager(),"Line Dialog");
            }
        });

        OpenCalender();

    }

    private void OpenCalender (){
        Calendar calendar = Calendar.getInstance();

        binding.FromDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month +1) +"/"+ String.valueOf(year);
                        binding.etFromDate.setText(selectedDate);
                        FromDate =selectedDate;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        binding.ToDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month +1) +"/"+ String.valueOf(year);
                        Log.d("TAG","selected Date   "+ selectedDate);
                        binding.etToDate.setText(selectedDate);
                        ToDate = selectedDate;

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


    }
    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list,String[] lineIdlist, int position) {
        selectedposition=position;
        binding.etLine.setText(list[position]);
        binding.etLine.setEnabled(false);

        arrayList.clear();

        ExpensesData data = new ExpensesData();
        data.setFromDate(FromDate);
        data.setToDate(ToDate);
        data.setLine(binding.etLine.getText().toString());

        arrayList.add(data);
        adapter.notifyDataSetChanged();

        Log.d("TAG","Array List" + data.getFromDate());
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

}