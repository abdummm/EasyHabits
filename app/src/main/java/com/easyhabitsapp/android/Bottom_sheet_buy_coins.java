package com.easyhabitsapp.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

public class Bottom_sheet_buy_coins extends BottomSheetDialogFragment {

    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_buy_coins, container, false);
        mview = view;
        cancel_button_listen();
        all_buttons_listen();
        set_the_prices();
        return view;
    }

    private void cancel_button_listen() {
        Button dismiss_bottom_sheet_add_reminder = mview.findViewById(R.id.dismiss_bottom_sheet_add_reminder);
        dismiss_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void all_buttons_listen() {
        Button first_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.first_buy_button_in_coins_bottom_sheet);
        Button second_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.second_buy_button_in_coins_bottom_sheet);
        Button third_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.third_buy_button_in_coins_bottom_sheet);
        Button fourth_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.fourth_buy_button_in_coins_bottom_sheet);
        Button fifth_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.fifth_buy_button_in_coins_bottom_sheet);
        Button sixth_buy_button_in_coins_bottom_sheet = mview.findViewById(R.id.sixth_buy_button_in_coins_bottom_sheet);
        first_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","400");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "400_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
        second_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","1500");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "1500_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
        third_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","3600");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "3600_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
        fourth_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","8400");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "8400_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
        fifth_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","16000");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "16000_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
        sixth_buy_button_in_coins_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("how_many_coins","40000");
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                call_the_listen_in_settings();
                Payment_processer.getInstance().launch_product_flow(getActivity(), "40000_coins", getContext(), FirebaseAuth.getInstance().getUid());
                set_up_purchase_listen();
            }
        });
    }

    private void set_the_prices() {
        TextView first_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.first_text_view_with_price_in_bottom_sheet);
        TextView second_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.second_text_view_with_price_in_bottom_sheet);
        TextView third_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.third_text_view_with_price_in_bottom_sheet);
        TextView fourth_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.fourth_text_view_with_price_in_bottom_sheet);
        TextView fifth_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.fifth_text_view_with_price_in_bottom_sheet);
        TextView sixth_text_view_with_price_in_bottom_sheet = mview.findViewById(R.id.sixth_text_view_with_price_in_bottom_sheet);
        first_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("400_coins"));
        second_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("1500_coins"));
        third_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("3600_coins"));
        fourth_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("8400_coins"));
        fifth_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("16000_coins"));
        sixth_text_view_with_price_in_bottom_sheet.setText(Payment_processer.getInstance().get_price_in_app("40000_coins"));
    }

    private void call_the_listen_in_settings() {
        if (getActivity() != null) {
            Settings_fragment old_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
            if (old_fragment != null) {
                old_fragment.listen_to_coin_update();
            }
        }
    }

    private void set_up_purchase_listen(){
        Payment_processer.getInstance().set_up_purchase_is_donelisten(new Payment_processer.purchase_is_done_listen() {
            @Override
            public void purchase_is_done() {
                dismiss();
            }
        });
    }
}
