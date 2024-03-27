package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.SettinglinelistBinding;

import java.util.ArrayList;

public class SettingLineAdapter extends RecyclerView.Adapter<SettingLineAdapter.SettingLineViewHolder> {

    private ArrayList<SettingLineData> arrayList;
    private  OnLineClickListner  clickListner;


    public interface OnLineClickListner {
        void onLineClick (int position);
    }

    public SettingLineAdapter(ArrayList<SettingLineData> arrayList, OnLineClickListner clickListner) {
        this.arrayList = arrayList;
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public SettingLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SettingLineViewHolder(SettinglinelistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SettingLineViewHolder holder, int position) {
        SettingLineData data = arrayList.get(position);

        if (data== null){
            return;
        }

        holder.binding.etLineName.setText(data.getLineName());
        holder.binding.etLineType.setText(data.getLineType());

        holder.binding.layoutMain.setOnClickListener(v -> {
            clickListner.onLineClick(position);
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SettingLineViewHolder extends RecyclerView.ViewHolder {

        SettinglinelistBinding binding;

        public SettingLineViewHolder(@NonNull SettinglinelistBinding itemView) {
            super(itemView.getRoot());
            binding =itemView;
        }

    }
}
