package com.example.vasool_drive.fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentAddBinding;

import java.util.Calendar;


public class AddExpenseFragment extends Fragment implements LineDialogFragment.OnListClickLitsener {

    private FragmentAddBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    private int year;
    private int month;
    private int day;

    int selectedposition;
    public AddExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false);
        binding.etLine.setEnabled(false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.LineLy.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineDialogFragment lineDialogFragment = new LineDialogFragment(selectedposition);
                lineDialogFragment.setTargetFragment(AddExpenseFragment.this,1);
                lineDialogFragment.show(getFragmentManager(),"Line Dialog");
            }
        });
//       binding.btnDropdown.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//
//
//           }
//       });

       binding.btnClose.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main);
               mAppBarConfiguration =
                       new AppBarConfiguration.Builder(R.id.nav_host_fragment_content_main,R.id.homeFragment).build();

               NavigationUI.navigateUp(navController,mAppBarConfiguration);
           }
       });

        OpenCalender();

    }

    private void OpenCalender (){
        Calendar calendar = Calendar.getInstance();

        binding.Date.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month +1) +"/"+ String.valueOf(year);
                        binding.etExpenseDate.setText(selectedDate);
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

    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {
        dialogInterface.dismiss();
    }

}