package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_more_information_buy_premium extends DialogFragment {
    private View mview;
    private String monthly_price;
    private String yearly_price;

    public Dialog_to_more_information_buy_premium(String monthly_price,String yearly_price){
        this.monthly_price = monthly_price;
        this.yearly_price = yearly_price;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_show_learn_more_buy_premium, container);
        this.mview = rootView;
        ok_button_listen();
        set_the_text();
        return rootView;
    }


    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity()!=null && getDialog().getWindow()!=null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels / 100) * 80;
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void ok_button_listen(){
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void set_the_text(){
        TextView text_view_with_learn_more_infoprmation = mview.findViewById(R.id.text_view_with_learn_more_infoprmation);
        text_view_with_learn_more_infoprmation.setText("Your Google Play Account will be charged after the free trial period ends. The monthly subscription is ".concat(monthly_price).concat(" and automatically renews each month. The annual subscription, charged as one upfront payment, is ").concat(yearly_price).concat(" and automatically renews each year. Subscription will be auto-renewed within 24 hours before the current period ends. You can manage subscription & turn-off in Google Play Account Settings."));
    }
}
