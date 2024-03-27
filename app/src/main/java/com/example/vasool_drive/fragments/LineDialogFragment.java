package com.example.vasool_drive.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.SettingLineAdapter;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.FragmentLineDialogBinding;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineDialogFragment extends DialogFragment  {
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

    public OnListClickLitsener mlitsener;

    public LineDialogFragment(int position,String[] list) {
        this.selectedposition = position;
        this.list= list;
    }
    public LineDialogFragment(int position){
        this.selectedposition= position;
    }

    public LineDialogFragment(int selectedposition, String[] lineList, String[] lineIdList) {
        this.selectedposition =selectedposition;
        this.list=lineList;
        this.lineId = lineIdList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlitsener = (OnListClickLitsener) getTargetFragment();
        }catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(getActivity().toString()+ "OnListClickLitsener must implement");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLineDialogBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        //String [] list = getResources().getStringArray(R.array.line);

        builder.setTitle("Line")
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