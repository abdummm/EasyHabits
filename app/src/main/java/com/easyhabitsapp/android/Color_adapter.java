package com.easyhabitsapp.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Color_adapter extends ArrayAdapter<Color_item> {

    public Color_adapter(Context context, ArrayList<Color_item> color_list){
        super(context,0,color_list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       return initview(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView,parent);
    }
    private View initview(int position,View convert_view,ViewGroup parent){
        if(convert_view == null){
            convert_view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_for_color_layout,parent,false);
        }
        View view_to_show_circle_color = convert_view.findViewById(R.id.view_to_show_circle_color);
        TextView text_beside_circle_color = convert_view.findViewById(R.id.text_beside_circle_color);

        Color_item color_item = getItem(position);
        if(color_item!=null) {
            view_to_show_circle_color.setBackgroundResource(color_item.get_color_image());
            text_beside_circle_color.setText(color_item.get_color_name());
        }
        return convert_view;
    }
}
