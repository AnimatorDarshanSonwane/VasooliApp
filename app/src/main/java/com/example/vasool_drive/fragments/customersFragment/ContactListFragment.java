package com.example.vasool_drive.fragments.customersFragment;

import static android.content.ContentValues.TAG;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.ContactsContract;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.ContactRecyclerViewAdapter;
import com.example.vasool_drive.classes.AddCustomerData;
import com.example.vasool_drive.classes.ContactDatas;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.databinding.FragmentContactListBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;


public class ContactListFragment extends Fragment  {

    private BottomNavigationView bottomNavigationView;
    FragmentContactListBinding binding;
    ArrayList<ContactDatas>  arrayList;
    ContactRecyclerViewAdapter adapter;

    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };


    String [] permission = {"android.permission.READ_CONTACTS"};
    private static final int CONTACT_Permission_REQUEST = 1;

    SparseBooleanArray selectedRows;
    ArrayList<ContactDatas> filteredlist;


    public ContactListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactListBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        onClickEvent(view);


        binding.btnClose.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.customerFragment);

            Navigation.findNavController(v).popBackStack(R.id.contactListFragment,true);

            bottomNavigationView.setVisibility(View.VISIBLE);
        });

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });




    }

    private void init (){

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setVisibility(View.GONE);

        arrayList = new ArrayList<>();
        adapter = new ContactRecyclerViewAdapter(getContext(),arrayList);
        binding.rvRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecyclerview.setAdapter(adapter);
        binding.rvRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        checkPermission();
//        contactsList = binding.multiList;
//        // Gets a CursorAdapter
//
//        ContentResolver resolver = getActivity().getContentResolver();
//        contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String [] projection = null;
//        String selection  = null;
//        String [] selectionArgs = null;
//        String order = null;
//
//        Cursor cursor = resolver.query(contactUri, projection, selection, selectionArgs, order);
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, cursor, FROM_COLUMNS, TO_IDS);
//
//        contactsList.setAdapter(adapter);

        EditText searchEditText = binding.searchBar.findViewById(androidx.appcompat.R.id.search_src_text);

        searchEditText.setTextColor(Color.BLACK);
        searchEditText.setHintTextColor(Color.BLACK);

        ImageView searchCloseBtn = binding.searchBar.findViewById(androidx.appcompat.R.id.search_close_btn);
        ImageView searchSearchBtn = binding.searchBar.findViewById(androidx.appcompat.R.id.search_button);
        searchSearchBtn.setColorFilter(Color.BLACK);
        searchCloseBtn.setColorFilter(Color.BLUE);


    }



    private void getContactList() {
        ContentResolver cr = getActivity().getContentResolver();

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null) {
            HashSet<String> mobileNoSet = new HashSet<String>();
            try {
                final int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name, number;
                while (cursor.moveToNext()) {
                    ContactDatas datas = new ContactDatas();
                    name = cursor.getString(nameIndex);
                    number = cursor.getString(numberIndex);
                    number = number.replace(" ", "");
                    datas.setContactName(name);
                    datas.setContactNumber(number);
                    arrayList.add(datas);
                    adapter.notifyDataSetChanged();

                    if (!mobileNoSet.contains(number)) {

                        mobileNoSet.add(number);

                    }

                }
            } finally {
                cursor.close();
            }
        }
    }


    private void onClickEvent(View view) {
        binding.bottomContainer.ImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 selectedRows = adapter.getSelectedIds();
                if (selectedRows.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    ArrayList<AddCustomerData> addCustomerDataArrayList = new ArrayList<>();
                    for (int i = 0; i < selectedRows.size(); i++) {

                        if (selectedRows.valueAt(i)) {
                            ContactDatas datas = arrayList.get(selectedRows.keyAt(i));

                          // selected contact from array list
                               String selectedContactName = datas.getContactName();
                                stringBuilder.append(selectedContactName + "\n");

                                addContactsAsCustomer(i);
                        }
                    }

                    //Toast.makeText(getContext(), "Selected Rows\n" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getContext(), "Please Select Contact" , Toast.LENGTH_SHORT).show();

                }

            }
        });

        // for delete contact from list
//        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SparseBooleanArray selectedRows = adapter.getSelectedIds();
//                if (selectedRows.size() > 0) {
//                    for (int i = (selectedRows.size() - 1); i >= 0; i--) {
//                        if (selectedRows.valueAt(i)) {
//                            arrayList.remove(selectedRows.keyAt(i));
//                        }
//                    }
//                    adapter.removeSelection();
//                }
//            }
//        });

        // for select all Contact from list

        // here Select Button is useas Import Button
        binding.bottomContainer.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check the current text of Select Button
                if (binding.bottomContainer.selectButton.getText().toString().equals(getResources().getString(R.string.select_all))) {

                    //If Text is Select All then loop to all array List items and check all of them
                    for (int i = 0; i < arrayList.size(); i++)
                        adapter.checkCheckBox(i, true);

                        //After checking all items change button text
                        binding.bottomContainer.selectButton.setText(getResources().getString(R.string.deselect_all));
                    } else{
                        //If button text is Deselect All remove check from all items
                        adapter.removeSelection();

                        //After checking all items change button text
                        binding.bottomContainer.selectButton.setText(getResources().getString(R.string.select_all));
                    }
            }
        });

    }


    private void checkPermission () {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermissions(permission,CONTACT_Permission_REQUEST);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CONTACT_Permission_REQUEST) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Showing the toast message
                //Toast.makeText(getActivity(), " Permission Granted", Toast.LENGTH_SHORT).show();

                getContactList();

            }
            else {
                Toast.makeText(getActivity(), " Permission Denied", Toast.LENGTH_SHORT).show();


            }
        }

    }


    private void addContactsAsCustomer (int position) {
        // call data from category activity
        ContactDatas data;
        if (filteredlist == null){
            data = arrayList.get(selectedRows.keyAt(position));
       } else {
            data = filteredlist.get(selectedRows.keyAt(position));
        }

       if (data == null){
            // binding.swipetorefresh.setRefreshing(false);
            return;
        }
        if (isNetworkConnected()==false){
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        //binding.progressCircular.setVisibility(View.VISIBLE);
        MyApplication.firebase.collection(MyReferance.AddCustomerData).add(data).addOnSuccessListener(documentReference -> {

            //binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "New Customer Added", Toast.LENGTH_SHORT).show();
            adapter.removeSelection();
            //  Navigation.findNavController(getView()).navigate(R.id.customerFragment);
            //  Navigation.findNavController(getView()).popBackStack(R.id.contactListFragment,true);

        }).addOnFailureListener(e -> {
            // binding.progressCircular.setVisibility(View.GONE);
            Log.d(TAG, "addLineText: " + e);
            Toast.makeText(getActivity(), "Somethig Went Wrong", Toast.LENGTH_SHORT).show();


        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (ContactDatas item : arrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getContactName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
}

