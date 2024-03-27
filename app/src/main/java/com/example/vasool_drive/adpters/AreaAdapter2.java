package com.example.vasool_drive.adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.classes.AreaData;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.Arealist2Binding;
import com.example.vasool_drive.databinding.ArealistBinding;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter2 extends RecyclerView.Adapter<AreaAdapter2.AreaAdapter2ViewHolder>  {

    Context context;
    ArrayList<String> AreaName;
    ArrayList<String> LineId;
    private AreaAdapter2.OnButtonClickLitsener clickLitsener;

//    public AreaAdapter2 (ArrayList<AreaData> areaDataArrayList, Context context) {
//        this.areaDataArrayList = areaDataArrayList;
//        this.context = context;
//    }
    public AreaAdapter2 (ArrayList<String> areaData, ArrayList<String> LineId, Context context,AreaAdapter2.OnButtonClickLitsener clickLitsener ) {
        this.AreaName = areaData;
        this.LineId = LineId;
        this.context = context;
        this.clickLitsener = clickLitsener;
    }

    public interface OnButtonClickLitsener{
        void OnDeleteClick (int position, ArrayList<String> AreaName, ArrayList<String> LineId);
    }

    @NonNull
    @Override
    public AreaAdapter2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Arealist2Binding binding  = Arealist2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new AreaAdapter2.AreaAdapter2ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter2ViewHolder holder, int position) {

        if(AreaName.isEmpty()){

            return;
        }

        holder.binding.tvAreaName.setVisibility(View.VISIBLE);
        holder.binding.tvAreaName.setText(AreaName.get(position));

        holder.binding.tvLineID.setVisibility(View.VISIBLE);
        holder.binding.tvLineID.setText(LineId.get(position));

        holder.binding.btnDelete.setOnClickListener(view -> {
            clickLitsener.OnDeleteClick(position,AreaName,LineId);
        });

    }

    @Override
    public int getItemCount() {
        return AreaName.size();
    }

    class AreaAdapter2ViewHolder extends RecyclerView.ViewHolder{
        Arealist2Binding binding;
        public AreaAdapter2ViewHolder(@NonNull Arealist2Binding itemView) {
            super(itemView.getRoot());
            binding= itemView;
        }
    }


}
