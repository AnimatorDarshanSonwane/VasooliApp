package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.AddCustomerData;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.CustomerlistBinding;
import com.example.vasool_drive.databinding.SettinglinelistBinding;

import java.util.ArrayList;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder> {

    private ArrayList<AddCustomerData> arrayList;
    private SwipeAdapter.OnLineClickListner clickListner;


    public interface OnLineClickListner {
        void onEditClick (int position);
        void onDeleteClick(int position);
    }

    public SwipeAdapter(ArrayList<AddCustomerData> arrayList, SwipeAdapter.OnLineClickListner clickListner) {
        this.arrayList = arrayList;
        this.clickListner = clickListner;
    }
    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SwipeViewHolder(CustomerlistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        AddCustomerData  data= arrayList.get(position);

        if (data== null){
            return;
        }

        holder.binding.tvCustomerName.setText(data.getCustomerName());

        holder.binding.tvEdit.setOnClickListener(v -> {
            clickListner.onEditClick(position);
        });

        holder.binding.tvDelete.setOnClickListener(v -> {
            clickListner.onDeleteClick(position);
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SwipeViewHolder extends RecyclerView.ViewHolder {

        CustomerlistBinding binding;

        public SwipeViewHolder (@NonNull CustomerlistBinding itemView) {
            super(itemView.getRoot());
            binding =itemView;
        }

    }
}
