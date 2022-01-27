package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_all_premium_features extends RecyclerView.Adapter<Adapter_all_premium_features.ExampleViewHolder> {
    private ArrayList<Example_all_premuim_features> m_example_list;

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_premium_features, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_all_premium_features(ArrayList<Example_all_premuim_features> Example_all_premuim_features) {
        m_example_list = Example_all_premuim_features;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_all_premuim_features current_item = m_example_list.get(position);
        set_the_text(holder,current_item);
    }

    @Override
    public int getItemCount() {
        return m_example_list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView text_saying_the_name_of_the_feature_in_buy_premium_recycle_view;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_saying_the_name_of_the_feature_in_buy_premium_recycle_view = itemView.findViewById(R.id.text_saying_the_name_of_the_feature_in_buy_premium_recycle_view);
        }
    }
    private void set_the_text(final ExampleViewHolder holder,Example_all_premuim_features current_item){
        holder.text_saying_the_name_of_the_feature_in_buy_premium_recycle_view.setText(current_item.getFeature());
    }
}
