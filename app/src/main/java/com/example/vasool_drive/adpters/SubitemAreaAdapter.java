package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.AreaData;
import com.example.vasool_drive.databinding.Arealist2Binding;

import java.util.ArrayList;

public class SubitemAreaAdapter extends RecyclerView.Adapter<SubitemAreaAdapter.SubitemAreaViewHolder> {

    ArrayList <AreaData> arrayList;

    public SubitemAreaAdapter(ArrayList<AreaData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SubitemAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Arealist2Binding binding = Arealist2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new SubitemAreaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubitemAreaViewHolder holder, int position) {

        AreaData data = arrayList.get(position);

        if (data ==null){
            return;
        }

        holder.binding.tvAreaName.setText(data.getAreaName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubitemAreaViewHolder extends RecyclerView.ViewHolder{

        Arealist2Binding binding;
        public SubitemAreaViewHolder(@NonNull Arealist2Binding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
