package com.example.vasool_drive.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentReportBinding;


public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReportBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnTextClick();

    }

    private void OnTextClick(){

        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.planFragment2);
            }
        });

        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.dailySummaryFragment);
            }
        });

        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.lineSummaryFragment);
            }
        });

        binding.layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Navigation.findNavController(v).navigate(R.id.expenseSummaryFragment);
            }
        });

        binding.layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.investmentSummaryFragment);
            }
        });

        binding.layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.investment_ExpenseSummaryFragment);
            }
        });

        binding.layout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.book_Excess_Loss_Summary_Fragment);
            }
        });

        binding.layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loan_SummaryFragment);
            }
        });

        binding.layout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.new_Customer_Summary_Fragment);
            }
        });


        binding.layout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bad_Loan_Summary_Fragment);
            }
        });


        binding.layout14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.new_Bad_Loan_By_Date_Fragment);
            }
        });

        binding.layout15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.missing_Customer_SummaryFragment);
            }
        });

        binding.layout16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.completed_Loan_Summary_Fragment);
            }
        });

        binding.layout17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.non_Performance_Loan_Report_Fragment);
            }
        });

        binding.layout18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loan_Analysis_Fragment);
            }
        });

        binding.layout19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.ledger_Repor_tFragment);
            }
        });
    }

}