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
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentLineMultiChoiseDialogBinding;
import com.google.common.primitives.Booleans;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineMultiChoiseDialogFragment extends DialogFragment {

    private FragmentLineMultiChoiseDialogBinding binding;
    int selectedposition;
    String [] listItems ;
    // initialise the list items for the alert dialog
    boolean[] checkedItems;
    List<String> selectedItems ;




    ArrayList<Integer> mUserItems = new ArrayList<>();

    public interface onMultiChoiseLitsener{
        void onPositiveButtonClicked (DialogInterface dialogInterface, int position, String [] list,ArrayList<String> selecteditemlist,ArrayList<Integer> mUserItems,boolean []checkedItems,List<String> selectedItems);
        void  onNegativeButtonClicked(DialogInterface dialogInterface, int position);
    }

    onMultiChoiseLitsener multiChoiseLitsener;


    public LineMultiChoiseDialogFragment() {
        // Required empty public constructor
    }

    public LineMultiChoiseDialogFragment (boolean []checkedItems){
        this.checkedItems =checkedItems;
    }

    public LineMultiChoiseDialogFragment(int selectedposition) {
        this.selectedposition = selectedposition;
    }
    public LineMultiChoiseDialogFragment(boolean []checkedItems, String[] listItems) {
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
    binding =  FragmentLineMultiChoiseDialogBinding.inflate(inflater,container,false);



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
        builder.setTitle("Line").setMultiChoiceItems(listItems,checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                checkedItems [which] = isChecked;
                String currentItem = selectedItems.get(which);

                Log.d("Dialog ","Dialog, " +currentItem);


            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                multiChoiseLitsener.onPositiveButtonClicked(dialog,which,listItems,selecteditemlist,mUserItems,checkedItems,selectedItems);
                Log.e("Checked Item List ::","List   :" + selectedItems);
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