package com.example.vasool_drive.adpters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.AreaData;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.SettingLineData;
import com.example.vasool_drive.databinding.ArealistBinding;
import com.example.vasool_drive.databinding.FragmentAreaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> implements AreaAdapter2.OnButtonClickLitsener {
    RecyclerView.RecycledViewPool recycledViewPool= new RecyclerView.RecycledViewPool();
    ArrayList<SettingLineData> areaDataArrayList;

    AreaAdapter2 areaAdapter2;
    Context context;

    SettingLineData data1;

    AreaViewHolder Holder;

    public AreaAdapter(ArrayList<SettingLineData> arrayList, Context context) {
        this.areaDataArrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ArealistBinding  binding  = ArealistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new AreaViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {

        Holder = holder;

        data1 = areaDataArrayList.get(position);

        if (data1 == null ){
            Log.d("data 1", " empty " );
            return;
        }


        ArrayList<String> AreaNames = new ArrayList<>();
        ArrayList<String> LineId = new ArrayList<>();
        if (data1.getAreaName()== null){
            holder.binding.layout1.setVisibility(View.GONE);
            Log.d("AreaADP ", "Empty AreaNames");
        }else {
            holder.binding.layout1.setVisibility(View.VISIBLE);
            holder.binding.tvLineName.setText(data1.getLineName());
            Log.d("LineId", "  " + data1.getId());
            Log.d("LineName", "  " + data1.getLineName());

        for (int i=0; i<data1.getAreaName().size();i++){

            Log.d("AreaADP  ", " AreaName " + data1.getAreaName().get(i) );
            AreaNames.add(data1.getAreaName().get(i));
            LineId.add(data1.getId());
        } }

            areaAdapter2 = new AreaAdapter2(AreaNames, LineId, context.getApplicationContext(),this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context.getApplicationContext());
            holder.binding.rcView.setLayoutManager(linearLayoutManager);
            holder.binding.rcView.setRecycledViewPool(recycledViewPool);
            holder.binding.rcView.setAdapter(areaAdapter2);



    }

    @Override
    public int getItemCount() {
        return areaDataArrayList.size();
    }

    @Override
    public void OnDeleteClick(int position, ArrayList<String> AreaName, ArrayList<String> LineId) {
        Log.d("Area Adp ", " Document Id  " + AreaName.get(position));
        Log.d("Area Adp ", " Document Id  " + LineId.get(position));

        // Atomically remove a region from the "regions" array field.



        MyApplication.firebase.collection(MyReferance.SettingLineData).document(LineId.get(position)).update("AreaName", FieldValue.arrayRemove(AreaName.get(position)));

        AreaName.remove(position);
        LineId.remove(position);
        Holder.binding.rcView.removeViewAt(position);
        areaAdapter2.notifyItemRemoved(position);
        areaAdapter2.notifyItemRangeChanged(position, AreaName.size());
        areaAdapter2.notifyItemRangeChanged(position, LineId.size());

        Toast.makeText(context.getApplicationContext(), "Area Deleted Sucessfully", Toast.LENGTH_SHORT).show();

    }

    class AreaViewHolder extends RecyclerView.ViewHolder {
        ArealistBinding binding;
        public AreaViewHolder(@NonNull ArealistBinding itemView) {
            super(itemView.getRoot());
            binding =itemView;

        }
    }

}
