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

public class StatusDialogFragment extends DialogFragment {

    // default position
    int selectedposition;
    String [] list;
    String [] lineId;

    // SettingLineData settingLineData = arrayList.get(selectedposition);
    private FragmentLineDialogBinding binding;



    public interface OnListClickLitsener {
        void  onPositiveButtonClick (DialogInterface dialogInterface,String [] list,String[] lineIdList, int position);
        void onNegativeButtonClick (DialogInterface dialogInterface, int position);
    }

    public StatusDialogFragment.OnListClickLitsener mlitsener;

    public StatusDialogFragment(int position,String[] list) {
        this.selectedposition = position;
        this.list= list;
    }
    public StatusDialogFragment(int position){
        this.selectedposition= position;
    }

    public StatusDialogFragment(int selectedposition, String[] lineList, String[] lineIdList) {
        this.selectedposition =selectedposition;
        this.list=lineList;
        this.lineId = lineIdList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlitsener = (StatusDialogFragment.OnListClickLitsener) getTargetFragment();
        }catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(getActivity().toString()+ "OnListClickLitsener must implement");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        String [] list = getResources().getStringArray(R.array.status);

        builder.setTitle("Status")
                .setSingleChoiceItems(list,selectedposition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        selectedposition=i;

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mlitsener.onPositiveButtonClick(dialog,list,lineId,selectedposition);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mlitsener.onNegativeButtonClick(dialog,selectedposition);
            }
        });

        return builder.create();

    }
}
