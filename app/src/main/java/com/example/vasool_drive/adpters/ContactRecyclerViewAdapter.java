package com.example.vasool_drive.adpters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.ContactDatas;
import com.example.vasool_drive.classes.NotesData;
import com.example.vasool_drive.databinding.ContactsListItemBinding;
import com.example.vasool_drive.databinding.NotesListBinding;

import java.util.ArrayList;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter <ContactRecyclerViewAdapter.ContactViewHolder>{

    private Context context;
    private SparseBooleanArray mSelectedItemsIds;
    private ArrayList<ContactDatas> arrayList;


    public ContactRecyclerViewAdapter(Context context, ArrayList<ContactDatas> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        mSelectedItemsIds = new SparseBooleanArray();
    }
    // method for filtering our recyclerview items.
    public void filterList(ArrayList<ContactDatas> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        arrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactsListItemBinding binding = ContactsListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactDatas data = arrayList.get(position);
        if (data == null){
            return;
        }

        holder.binding.ContactsName.setText(data.getContactName());

        holder.binding.ContactsNumber.setText(data.getContactNumber());

        holder.binding.checkBox.setChecked(mSelectedItemsIds.get(position));

        holder.binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckBox(position, !mSelectedItemsIds.get(position));
            }
        });

        holder.binding.ContactsName.setOnClickListener(v -> {

            checkCheckBox(position, !mSelectedItemsIds.get(position));

        });

        holder.binding.layout1.setOnClickListener(v -> {

            checkCheckBox(position, !mSelectedItemsIds.get(position));

        });



    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        ContactsListItemBinding binding;
        public ContactViewHolder(@NonNull ContactsListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    /**
     * Remove all checkbox Selection
     **/
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    /**
     * Check the Checkbox if not checked
     **/
    public void checkCheckBox(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, true);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }
    /**
     * Uncheck checkbox if the checkbox is checked
     */



    /**
     * Return the selected Checkbox IDs
     **/
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
