package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.TodayReminderData;
import com.example.vasool_drive.databinding.TodayListBinding;

import java.util.ArrayList;

public class TodayReminderAdapter extends RecyclerView.Adapter<TodayReminderAdapter.TodayReminderViewHolder> {
    private ArrayList<TodayReminderData> arrayList;
    private OnButtonClickLitsener clickLitsener;

    public TodayReminderAdapter(ArrayList<TodayReminderData> arrayList, OnButtonClickLitsener clickLitsener) {
        this.arrayList = arrayList;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnDeleteClick (int position);
    }

    @NonNull
    @Override
    public TodayReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodayListBinding binding = TodayListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TodayReminderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayReminderViewHolder holder, int position) {
        TodayReminderData data = arrayList.get(position);
        if (data == null){
            return;
        }

        holder.binding.tvDate.setText(data.getDate());

        holder.binding.tvReminderText.setText(data.getTodayReminderText());


        holder.binding.ivDelete.setOnClickListener(view -> {
            clickLitsener.OnDeleteClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class TodayReminderViewHolder extends RecyclerView.ViewHolder {

        TodayListBinding binding;
        public TodayReminderViewHolder(@NonNull TodayListBinding itemView) {
            super(itemView.getRoot());
           binding =itemView;
        }
    }
}
