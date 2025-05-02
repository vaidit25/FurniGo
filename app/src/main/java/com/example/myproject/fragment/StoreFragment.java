package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.adapter.StoreAdapter;
import com.example.myproject.model.StoreModel;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    List<StoreModel> storeList = new ArrayList<>();

    public StoreFragment(List<StoreModel> storeList) {
        this.storeList = storeList;
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_store, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rcylstore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        StoreAdapter storeAdapter = new StoreAdapter(getContext(), storeList);
        recyclerView.setAdapter(storeAdapter);

    }
}