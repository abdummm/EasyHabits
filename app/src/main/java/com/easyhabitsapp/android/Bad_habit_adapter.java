package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Bad_habit_adapter extends RecyclerView.Adapter<Bad_habit_adapter.ExampleViewHolder> {
    private ArrayList<Bad_habit_example_item> mExampleList;
    private OnItemClickListener2  mListener;

    public interface OnItemClickListener2 {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener2 listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public Button bad_habit_button;

        public ExampleViewHolder(View itemView, final OnItemClickListener2 listener) {
            super(itemView);
            bad_habit_button = itemView.findViewById(R.id.button_displaying_each_bad_habit);
            bad_habit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public Bad_habit_adapter(ArrayList<Bad_habit_example_item> exampleList) {
        mExampleList = exampleList;
    }

    @NotNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_bad_habit_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Bad_habit_example_item currentItem = mExampleList.get(position);
        holder.bad_habit_button.setText(currentItem.get_button_text());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
