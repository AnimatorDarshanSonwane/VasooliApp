package com.example.vasool_drive.fragments.notification;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.NotesAdapter;
import com.example.vasool_drive.classes.MyReferance;
import com.example.vasool_drive.classes.NotesData;
import com.example.vasool_drive.databinding.FragmentNotesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class NotesFragment extends Fragment implements NotesAdapter.OnButtonClickLitsener {

    private FragmentNotesBinding binding;

    private  static final String TAG = "Note FRAGMENT";
    NotesAdapter adapter;
    ArrayList<NotesData> arrayList;
    NotesData newdata;
    String NoteIdGlobal;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater,container,false);

        init();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newdata = new NotesData();
                binding.swipetorefresh.setRefreshing(true);
                String newNote = Objects.requireNonNull(binding.etNotes.getText()).toString();

                if (arrayList ==null){
                    addNotes();
                }
                if (NoteIdGlobal==null){
                    addNotes();
                } else {
                    updateNote(NoteIdGlobal,newNote);

                }

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    private void init (){
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(arrayList,this);
        binding.rvRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecyclerview.setAdapter(adapter);
        binding.rvRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loadNoteData();

        binding.swipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                adapter.notifyDataSetChanged();
                loadNoteData();
                binding.swipetorefresh.setRefreshing(false);
            }
        });
    }
    private void loadNoteData(){
        binding.progressCircular.setVisibility(View.VISIBLE);

        MyApplication.firebase.collection(MyReferance.NOTES).get().addOnSuccessListener(queryDocumentSnapshots -> {
            binding.progressCircular.setVisibility(View.GONE);
            if (queryDocumentSnapshots.isEmpty()){
                Toast.makeText(getContext(), "No Result " , Toast.LENGTH_SHORT).show();
                return;

            }
            for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                // category data madhe online database cha data add kela
                NotesData data = documentSnapshot.toObject(NotesData.class);

                // take id from firstore category and store in category data
                data.setId(documentSnapshot.getId());

                // add data in arraylist and this array data go to category adapter class
                arrayList.add(data);
                adapter.notifyDataSetChanged();

            }

        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Error is " + e.toString(), Toast.LENGTH_SHORT).show();
        });
    }
    private NotesData getNotesData(){
        String note = binding.etNotes.getText().toString();
        String date = Timestamp.now().toDate().toString();
        if (note.isEmpty()){
            binding.etNotes.setError("Please Enter Note ");
            Toast.makeText(getActivity(), "Please Enter Note ", Toast.LENGTH_SHORT).show();
            return null;
        }

        NotesData data = new NotesData();
        data.setNote(note);
        data.setDate(date);

        return data;
    }
    private void addNotes () {
        // call data from category activity
        NotesData data = getNotesData();

        if (data == null){
            binding.swipetorefresh.setRefreshing(false);
            return;
        }
        if (isNetworkConnected()==false){
            Toast.makeText(getActivity(), "Please Connect Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        binding.progressCircular.setVisibility(View.VISIBLE);
        MyApplication.firebase.collection(MyReferance.NOTES).add(data).addOnSuccessListener(documentReference -> {
            binding.progressCircular.setVisibility(View.GONE);
            arrayList.clear();
            adapter.notifyDataSetChanged();
            loadNoteData();
            binding.swipetorefresh.setRefreshing(false);
            Toast.makeText(getActivity(), "Notes Added", Toast.LENGTH_SHORT).show();


        }).addOnFailureListener(e -> {
            binding.progressCircular.setVisibility(View.GONE);
            binding.swipetorefresh.setRefreshing(false);
            Toast.makeText(getActivity(), "Somethig Went Wrong", Toast.LENGTH_SHORT).show();

        });
    }
    // This method checks whether mobile is connected to internet and returns true if connected
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @Override
    public void OnEditClick(int position) {

        NotesData data = arrayList.get(position);
        newdata = data;
        NoteIdGlobal = data.getId();
        binding.etNotes.setText(data.getNote());

    }
    private void updateNote (String id, String note  ){
        if (id == null) {
            return;
        }
        MyApplication.firebase.collection(MyReferance.NOTES).document(id).update("note",note,"id",id).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();
                NoteIdGlobal = null ;
                arrayList.clear();
                adapter.notifyDataSetChanged();
                loadNoteData();
                binding.swipetorefresh.setRefreshing(false);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: ", e );
                binding.swipetorefresh.setRefreshing(false);
            }
        });
    }
    @Override
    public void OnDeleteClick(int position) {
        NotesData data = arrayList.get(position);

        MyApplication.firebase.collection(MyReferance.NOTES).document(data.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


}