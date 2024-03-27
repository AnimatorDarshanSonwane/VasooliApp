package com.example.vasool_drive.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentLineDialogBinding;

public class ExpenseTypeDialogFragment extends DialogFragment {

    // default position
    int selectedposition;
    String [] list;
    String [] lineId;

    // SettingLineData settingLineData = arrayList.get(selectedposition);

    public interface OnExpenseListClickLitsener {
        void  onExpensePositiveButtonClick (DialogInterface dialogInterface, String [] expenseTypeList, String[] ExpenseIdList, int position);
        void onExpenseNegativeButtonClick (DialogInterface dialogInterface, int position);
    }

    public ExpenseTypeDialogFragment.OnExpenseListClickLitsener mlitsener;

    public ExpenseTypeDialogFragment(int position,String[] ExpenseList) {
        this.selectedposition = position;
        this.list= ExpenseList;
    }
    public ExpenseTypeDialogFragment(int position){
        this.selectedposition= position;
    }

    public ExpenseTypeDialogFragment(int selectedposition, String[] ExpenseList, String[] ExpenseIdList) {
        this.selectedposition =selectedposition;
        this.list=ExpenseList;
        this.lineId = ExpenseIdList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlitsener = (ExpenseTypeDialogFragment.OnExpenseListClickLitsener) getTargetFragment();
        }catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(getActivity().toString()+ "OnListClickLitsener must implement");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        //String [] list = getResources().getStringArray(R.array.status);

        builder.setTitle("ExpenseType")
                .setSingleChoiceItems(list,selectedposition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        selectedposition=i;

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mlitsener.onExpensePositiveButtonClick(dialog,list,lineId,selectedposition);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mlitsener.onExpenseNegativeButtonClick(dialog,selectedposition);
            }
        });

        return builder.create();

    }

}
