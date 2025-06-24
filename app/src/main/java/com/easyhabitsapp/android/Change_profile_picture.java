package com.easyhabitsapp.android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Change_profile_picture extends Fragment {

    public Change_profile_picture() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_profile_picture, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void set_up_recycle_view() {
        if (getView() != null) {
            /*RecyclerView recycle_view_buy_profile_pictures = getView().findViewById(R.id.recycle_view_buy_profile_pictures);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            ArrayList<Example_item_profile_picture_store_item> list_for_habits = new ArrayList<>();
            list_for_habits.add(new Example_item_profile_picture_store_item("A pineapple",R.drawable.pineapple_profile_pic,"Limited item",));
            Adapter_profile_picture_store adapter = new Adapter_profile_picture_store(list_for_habits);
            recycle_view_buy_profile_pictures.setLayoutManager(linearLayoutManager);
            recycle_view_buy_profile_pictures.setAdapter(adapter);*/
        }
    }


}