package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.AddExpenseTypeData;
import com.example.vasool_drive.classes.AddInvestmentTypeData;
import com.example.vasool_drive.databinding.ExpensetypelistBinding;
import com.example.vasool_drive.databinding.InvestmenttypelistBinding;

import java.util.ArrayList;

public class InvestmentTypeAdapter extends RecyclerView.Adapter<InvestmentTypeAdapter.InvestmetnTypeViewHolder> {

    ArrayList <AddInvestmentTypeData> arrayList;
    private InvestmentTypeAdapter.OnButtonClickLitsener clickLitsener;

    public InvestmentTypeAdapter(ArrayList<AddInvestmentTypeData> arrayList, InvestmentTypeAdapter.OnButtonClickLitsener clickLitsener) {
        this.arrayList = arrayList;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnItemClick (int position, ArrayList<AddInvestmentTypeData> InvestmentTypeData);
        void OnDeleteClick (int position, ArrayList<AddInvestmentTypeData> InvestmentTypeData);
    }

    @NonNull
    @Override
    public InvestmetnTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvestmetnTypeViewHolder(InvestmenttypelistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmetnTypeViewHolder holder, int position) {
        AddInvestmentTypeData data = arrayList.get(position);

        if (data == null){
            return;
        }

        holder.binding.tvInvestmentTypeName.setText(data.getInvestmentTypeName());
        holder.binding.tvStaus.setText(data.getStatus());

        holder.binding.tvInvestmentTypeName.setOnClickListener(v -> {
            clickLitsener.OnItemClick(position,arrayList);
        });
        holder.binding.tvStaus.setOnClickListener(v -> {
            clickLitsener.OnItemClick(position,arrayList);
        });
        holder.binding.btnDelete.setOnClickListener(v -> {
            clickLitsener.OnDeleteClick(position,arrayList);
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class InvestmetnTypeViewHolder extends RecyclerView.ViewHolder {
        InvestmenttypelistBinding binding;
        public InvestmetnTypeViewHolder(@NonNull InvestmenttypelistBinding itemView) {

            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
