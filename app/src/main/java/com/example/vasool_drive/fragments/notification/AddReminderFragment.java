package com.example.vasool_drive.fragments.notification;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.TodayReminderData;
import com.example.vasool_drive.databinding.FragmentAddReminderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Timestamp;

import java.util.Calendar;


public class AddReminderFragment extends Fragment {

    private FragmentAddReminderBinding binding;
    private BottomNavigationView bottomNavigationView;
    private  static final String TAG = "AddReminderFRAGMENT";
    private int year;
    private int month;
    private int day;

    public AddReminderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAddReminderBinding.inflate(inflater,container,false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);

        bottomNavigationView.setVisibility(View.GONE);



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        init(calendar);

        binding.ReminderDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.valueOf(dayOfMonth) +"-"+ String.valueOf(month +1) +"-"+ String.valueOf(year);
                        binding.etReminderDate.setText(selectedDate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        binding.btnSUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminderText();
            }
        });

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).popBackStack(R.id.addReminderFragment,true);
                bottomNavigationView.setVisibility(View.VISIBLE);

            }
        });

    }
    private void init (Calendar calendar){

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month +1) +"/"+ String.valueOf(year);
                binding.etReminderDate.setText(selectedDate);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private TodayReminderData getTodayReminderData(){
        String reminderText = binding.etReminderText.getText().toString();
        String date = Timestamp.now().toDate().toString();
        if (reminderText.isEmpty()){
            binding.etReminderText.setError("Please Enter Note ");
            Toast.makeText(getActivity(), "Please Enter Note ", Toast.LENGTH_SHORT).show();
            return null;
        }

        TodayReminderData data = new TodayReminderData();
        data.setTodayReminderText(reminderText);
        data.setDate(date);

        return data;
    }
    private void addReminderText () {
        // call data from category activity
        TodayReminderData data = getTodayReminderData();

        binding.progressCircular.setVisibility(View.VISIBLE);
        MyApplication.firebase.collection(MyReferance.TODAYREMINDER).add(data).addOnSuccessListener(documentReference -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Notes Added", Toast.LENGTH_SHORT).show();


        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Log.d(TAG, "addReminderText: " + e);
            Toast.makeText(getActivity(), "Somethig Went Wrong", Toast.LENGTH_SHORT).show();


        });
    }


}