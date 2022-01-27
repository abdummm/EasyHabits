package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class Adapter_for_emergency_options extends PagerAdapter {

    private Context context;
    private ArrayList<Model_for_emergency_options> model_for_emergency_optionsArrayList;

    public Adapter_for_emergency_options(Context context, ArrayList<Model_for_emergency_options>model_for_emergency_optionsArrayList){
        this.context = context;
        this.model_for_emergency_optionsArrayList = model_for_emergency_optionsArrayList;
    }

    @Override
    public int getCount() {
        return model_for_emergency_optionsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards_background_for_emergency,container,false);

        View showing_picture_in_middle_of_card_emergency = view.findViewById(R.id.showing_picture_in_middle_of_card_emergency);
        TextView text_to_say_title_of_the_emergency_option = view.findViewById(R.id.text_to_say_title_of_the_emergency_option);

        Model_for_emergency_options model_for_emergency_options = model_for_emergency_optionsArrayList.get(position);

        String title = model_for_emergency_options.getTitle();
        Drawable icon = model_for_emergency_options.getIcon();

        text_to_say_title_of_the_emergency_option.setText(title);
        showing_picture_in_middle_of_card_emergency.setBackground(icon);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "adsasdas", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
