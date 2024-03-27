package com.example.vasool_drive.fragments.settingfragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.LineDialogData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentExportBinding;
import com.example.vasool_drive.dialog.LineMultiChoiseDialogFragment;
import com.example.vasool_drive.dialog.LineTypeDialogFragment;
import com.example.vasool_drive.fragments.LineDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.primitives.Booleans;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExportFragment extends Fragment implements LineMultiChoiseDialogFragment.onMultiChoiseLitsener,LineTypeDialogFragment.OnListClickLitsener {

    private FragmentExportBinding binding;
    int selectedpositionLineType;
    int selectedposition;
    String [] itemList;
    boolean[]checkedItems ;
    boolean []checkedItemspass;
    private BottomNavigationView bottomNavigationView;

    LineDialogData lineDialogData;
    private ArrayList<LineDialogData> arrayList1;
    String[] LineList;

    public ExportFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExportBinding.inflate( inflater,container,false);
        init();

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);


        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.settingFragment);
                Navigation.findNavController(v).popBackStack(R.id.exportFragment,true);

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.etLine.setEnabled(false);

        binding.etLineType.setEnabled(false);

        binding.LineType.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineTypeDialogFragment lineTypeDialogFragment = new LineTypeDialogFragment(selectedpositionLineType);
                lineTypeDialogFragment.setTargetFragment(ExportFragment.this,2);
                lineTypeDialogFragment.show(getFragmentManager(),"Line Type Dialog");
            }
        });



    }
    private void setup (){


    }
    private void init (){
        arrayList1 = new ArrayList<>();
        loadSettingLineData();

    }

    private void loadSettingLineData (){


        MyApplication.firebase.collection(MyReferance.CollectionSettingLineData).get().addOnSuccessListener(queryDocumentSnapshots -> {

            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela

                lineDialogData = documentSnapshot.toObject(LineDialogData.class);

                // take id from firstore category and store in category data
                lineDialogData.setId(documentSnapshot.getId());

                // add data in arraylist and this array data go to category adapter class
                arrayList1.add(lineDialogData);

            }
            addDatatoLineDialog();

        }).addOnFailureListener(e -> {

            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });

    }

    private void addDatatoLineDialog (){
        // populate line dialog list
        itemList = new String[arrayList1.size()];
        //LineList = new String[arrayList1.size()];

        if (arrayList1.size()>0){
            Log.d(" arraylist size "," " + arrayList1.size());

            for (int i = 0; i < arrayList1.size(); i++) {
                LineDialogData dialogData = arrayList1.get(i);
                itemList[i] = String.valueOf(dialogData.getLineName());
                Log.d(" arraylist "," " + itemList[i]);
            }
            Log.d(" Linelistsize "," " + itemList.length);

        }
        initLine();
    }

    private void initLine(){

        //itemList= getResources().getStringArray(R.array.lineType);
        checkedItems = new boolean[itemList.length];

//        Log.e("Export ","itemList  " + Arrays.asList(itemList));
//        Log.e("Export ","CheckedItems  " + Arrays.toString(checkedItems));

        String listString = "";
        for (String s : itemList)
        {
            listString += s + ", ";
        }

        String finalListString = listString;

        int trueitems = Booleans.countTrue(checkedItems);

        if ( trueitems == itemList.length){
            binding.checkbox1.setChecked(true);
        }else {binding.checkbox1.setChecked(false);}

        if (binding.checkbox1.isChecked()){

            for (int i = 0; i<checkedItems.length; i++){
                boolean checked = checkedItems[i];
                checkedItemspass =checkedItems;
                Arrays.fill(checkedItems,true);
                binding.etLine.setText(finalListString);

            }
        }else {
            binding.etLine.setText(" ");
            for (int i = 0; i<checkedItems.length; i++){
                boolean checked = checkedItems[i];
                Arrays.fill(checkedItems,false);
//                        checkedItems= checkedItemspass;
            }
        }

        binding.checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkbox1.isChecked()){

                    for (int i = 0; i<checkedItems.length; i++){
                        boolean checked = checkedItems[i];
                        checkedItemspass =checkedItems;
                        Arrays.fill(checkedItems,true);
                        binding.etLine.setText(finalListString);

                    }
                }else {
                    binding.etLine.setText(" ");
                    for (int i = 0; i<checkedItems.length; i++){
                        boolean checked = checkedItems[i];
                        Arrays.fill(checkedItems,false);
//                        checkedItems= checkedItemspass;
                    }
                }
            }
        });



        binding.Line.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LineMultiChoiseDialogFragment lineMultiChoiseDialogFragment = new LineMultiChoiseDialogFragment(checkedItemspass,itemList);
                lineMultiChoiseDialogFragment.setTargetFragment(ExportFragment.this,1);
                lineMultiChoiseDialogFragment.show(getFragmentManager(),"LineMultichoiseDialog");
            }
        });

    }

    // Line Dialog positive button

    @Override
    public void onPositiveButtonClicked(DialogInterface dialogInterface, int position, String[] list, ArrayList<String> selecteditemlist, ArrayList<Integer> mUserItems, boolean []checkedItems, List<String> selectedItems) {
       Log.d("Multichoise Dialog", " checked  "+ Arrays.toString(checkedItems));

        checkedItemspass = checkedItems;
        int trueitems = Booleans.countTrue(checkedItems);

        Log.e("TAG","TrueItems  " + trueitems);
        Log.e("TAG","Item List Length   " +itemList.length);

        if (trueitems==itemList.length){

            binding.checkbox1.setChecked(true);
        }else {binding.checkbox1.setChecked(false);}

        for (int i = 0; i<checkedItems.length; i++){
            boolean checked = checkedItems[i];
            if (checked) {
                //textView.setText(textView.getText() + colorsList.get(i) + "\n");
                selecteditemlist.add(selectedItems.get(i));
                Log.d("MultiDialog : ", " Selected item list:  "+selecteditemlist.toString());

                String listString = "";

                for (String s : selecteditemlist)
                {
                    listString += s + ", ";
                }

                System.out.println(listString);

                binding.etLine.setText(listString);
            }
        }


    }


    // Line  Dialog Negative button

    @Override
    public void onNegativeButtonClicked(DialogInterface dialogInterface, int position) {

    }

    // Line Type Dialog positive button
    @Override
    public void onPositiveButtonClick(DialogInterface dialogInterface, String[] list, int position) {
        selectedpositionLineType=position;
        binding.etLineType.setText(list[position]);
        binding.etLineType.setEnabled(false);

    }

    // Line Type Dialog negative button

    @Override
    public void onNegativeButtonClick(DialogInterface dialogInterface, int position) {

    }
}

