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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentAreaMultiChoiseDialogBinding;
import com.example.vasool_drive.databinding.FragmentLineMultiChoiseDialogBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AreaMultiChoiseDialogFragment extends DialogFragment {


    private FragmentAreaMultiChoiseDialogBinding binding;
    int selectedposition;
    String [] listItems ;
    // initialise the list items for the alert dialog
    boolean[] checkedItems;
    List<String> selectedItems ;
    ArrayList<Integer> mUserItems = new ArrayList<>();


    public interface onMultiChoiseLitsener{
        void onPositiveButtonClicked (DialogInterface dialogInterface, int position, String [] list, ArrayList<String> selecteditemlist, ArrayList<Integer> mUserItems, boolean []checkedItems, List<String> selectedItems);
        void  onNegativeButtonClicked(DialogInterface dialogInterface, int position);
    }

    public onMultiChoiseLitsener multiChoiseLitsener;


    public AreaMultiChoiseDialogFragment() {
        // Required empty public constructor
    }

    public AreaMultiChoiseDialogFragment (boolean []checkedItems){
        this.checkedItems =checkedItems;
    }

    public AreaMultiChoiseDialogFragment(int selectedposition) {
        this.selectedposition = selectedposition;
    }
    public AreaMultiChoiseDialogFragment(boolean [] checkedItems, String[] listItems) {
        this.checkedItems = checkedItems;
        this.listItems= listItems;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            multiChoiseLitsener = (onMultiChoiseLitsener) getTargetFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentAreaMultiChoiseDialogBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        ArrayList<String> selecteditemlist = new ArrayList<>();

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        //listItems = getResources().getStringArray(R.array.lineType);
        if (checkedItems == null){
            checkedItems= new boolean[listItems.length];} else {
            checkedItems = checkedItems;
        }
        selectedItems = Arrays.asList(listItems);

        Log.d("Multichoise Dialog", " dialog  "+ Arrays.toString(checkedItems));
        builder.setTitle("Area").setMultiChoiceItems(listItems,checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked){
                    if (mUserItems.contains(which)){
                        mUserItems.remove(which);
                        checkedItems[which] = false;
                    }
                    else {
                        mUserItems.add(which);
                        checkedItems[which] = true;
                    }

                }
//                checkedItems [which] = isChecked;
//                String currentItem = selectedItems.get(which);
//
//                selecteditemlist.add(currentItem);
//
//                Log.d("Dialog ","Dialog, " +currentItem);


            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               multiChoiseLitsener.onPositiveButtonClicked(dialog,which,listItems,selecteditemlist,mUserItems,checkedItems,selectedItems);
                Log.e("Checked Item List :: ","List  : " + selectedItems);
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                multiChoiseLitsener.onNegativeButtonClicked(dialog, which);

            }
        });

        return builder.create();


    }

}