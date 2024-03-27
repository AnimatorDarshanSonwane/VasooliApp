package com.example.vasool_drive.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentLineDialogBinding;
import com.example.vasool_drive.databinding.FragmentLineTypeDialogBinding;
import com.example.vasool_drive.fragments.LineDialogFragment;


public class LineTypeDialogFragment extends DialogFragment {

    // default position
    int selectedposition;

    private FragmentLineTypeDialogBinding binding;


    public interface OnListClickLitsener {
        void  onPositiveButtonClick (DialogInterface dialogInterface, String [] list, int position);
        void onNegativeButtonClick (DialogInterface dialogInterface, int position);
    }

    public LineTypeDialogFragment.OnListClickLitsener mlitsener;

    public LineTypeDialogFragment(int position) {
        this.selectedposition = position;
    }

    public LineTypeDialogFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlitsener = (LineTypeDialogFragment.OnListClickLitsener) getTargetFragment();
        }catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(getActivity().toString()+ "OnListClickLitsener must implement");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLineTypeDialogBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        String [] list = getResources().getStringArray(R.array.lineType);

        builder.setTitle("Line Type")
                .setSingleChoiceItems(list,selectedposition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        selectedposition=i;

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mlitsener.onPositiveButtonClick(dialog,list,selectedposition);

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