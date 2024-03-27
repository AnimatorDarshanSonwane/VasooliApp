package com.example.vasool_drive.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.SiteData;
import com.example.vasool_drive.databinding.FragmentSiteBinding;
import com.example.vasool_drive.databinding.NotesListBinding;
import com.example.vasool_drive.databinding.SitelistBinding;

import java.util.ArrayList;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder> {

    private ArrayList<SiteData> arrayList;

    private SiteAdapter.OnButtonClickLitsener clickLitsener;

    public SiteAdapter(ArrayList<SiteData> arrayList, OnButtonClickLitsener clickLitsener) {
        this.arrayList = arrayList;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnEditClick (int position, ArrayList<SiteData> siteDataArrayList);

    }

    @NonNull
    @Override
    public SiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SitelistBinding binding = SitelistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SiteAdapter.SiteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SiteViewHolder holder, int position) {

        SiteData data = arrayList.get(position);
        if (data == null){
            return;
        }

        holder.binding.tvSiteName.setText(data.getSiteName());

        holder.binding.layout1.setOnClickListener(view -> {
            clickLitsener.OnEditClick(position, arrayList);
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SiteViewHolder extends RecyclerView.ViewHolder {

        SitelistBinding binding;
        public SiteViewHolder(@NonNull SitelistBinding itemView) {
            super(itemView.getRoot());

            binding= itemView;
        }
    }

}
