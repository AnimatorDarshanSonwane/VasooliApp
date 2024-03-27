package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.ExpensesData;
import com.example.vasool_drive.databinding.ExpensesListBinding;

import java.util.ArrayList;

public class ExpensesListAdapter extends RecyclerView.Adapter<ExpensesListAdapter.ExpensesListViewHolder> {
    private ArrayList<ExpensesData> arrayList;

    public ExpensesListAdapter(ArrayList<ExpensesData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExpensesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpensesListViewHolder(ExpensesListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesListViewHolder holder, int position) {
    ExpensesData data = arrayList.get(position);
    if (data ==  null){
        return;
    }

    holder.binding.Date.setText(data.getFromDate() + " - "+ data.getToDate() );

    holder.binding.Total.setText(data.getTotal());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ExpensesListViewHolder extends RecyclerView.ViewHolder{
        ExpensesListBinding binding;
        public ExpensesListViewHolder(@NonNull ExpensesListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
