package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.databinding.ExpensetypelistBinding;

import java.util.ArrayList;

public class ExpenseTypeDataAdapter extends RecyclerView.Adapter<ExpenseTypeDataAdapter.ExpenseTypeDataViewHolder> {

    private ArrayList<AddExpenseTypeData> arrayList;

   private ExpenseTypeDataAdapter.OnButtonClickLitsener clickLitsener;

    public ExpenseTypeDataAdapter(ArrayList<AddExpenseTypeData> arrayList, OnButtonClickLitsener clickLitsener) {
        this.arrayList = arrayList;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnClick (int position, ArrayList<AddExpenseTypeData> ExpenseTypeData);
        void OnDeleteClick (int position, ArrayList<AddExpenseTypeData> ExpenseTypeData);
    }
    @NonNull
    @Override
    public ExpenseTypeDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpenseTypeDataViewHolder(ExpensetypelistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseTypeDataViewHolder holder, int position) {

        AddExpenseTypeData data = arrayList.get(position);

        if (data == null){
         return;
        }

        holder.binding.tvExpenseTypeName.setText(data.getExpenseTypeName());
        holder.binding.tvStaus.setText(data.getStatus());

        holder.binding.tvExpenseTypeName.setOnClickListener(v -> {
            clickLitsener.OnClick(position,arrayList);
        });
        holder.binding.tvStaus.setOnClickListener(v -> {
            clickLitsener.OnClick(position,arrayList);
        });
        holder.binding.btnDelete.setOnClickListener(v -> {
            clickLitsener.OnDeleteClick(position,arrayList);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ExpenseTypeDataViewHolder extends RecyclerView.ViewHolder{
        ExpensetypelistBinding binding;
        public ExpenseTypeDataViewHolder(@NonNull ExpensetypelistBinding itemView) {

            super(itemView.getRoot());
            binding = itemView;
        }

    }

}
