package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.NotesData;
import com.example.vasool_drive.databinding.NotesListBinding;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<NotesData> arrayList;
    private OnButtonClickLitsener clickLitsener;

    public NotesAdapter(ArrayList<NotesData> arrayList, OnButtonClickLitsener clickLitsener) {
        this.arrayList = arrayList;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnEditClick (int position);
        void OnDeleteClick (int position);
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotesListBinding binding = NotesListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        NotesData data = arrayList.get(position);
        if (data == null){
            return;
        }

        holder.binding.tvDate.setText(data.getDate());

        holder.binding.tvNoteText.setText(data.getNote());


        holder.binding.ivEdit.setOnClickListener(view -> {
            clickLitsener.OnEditClick(position);
        });

        holder.binding.ivDelete.setOnClickListener(view -> {
            clickLitsener.OnDeleteClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        NotesListBinding binding;
        public NotesViewHolder(@NonNull NotesListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
