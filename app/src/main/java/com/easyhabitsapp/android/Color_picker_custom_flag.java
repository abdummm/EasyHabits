package com.easyhabitsapp.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.flag.FlagView;

public class Color_picker_custom_flag extends FlagView {

    private View View;

    public Color_picker_custom_flag(Context context, int layout) {
        super(context, layout);
        View = findViewById(R.id.color_of_picker_flag);
    }

    @Override
    public void onRefresh(ColorEnvelope colorEnvelope) {
        View.setBackgroundTintList(ColorStateList.valueOf(colorEnvelope.getColor()));
    }
}