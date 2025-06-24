package com.easyhabitsapp.android;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Give_coins extends Fragment {

    private ArrayList<View> list_of_views = new ArrayList<>();
    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet;
    private ArrayList<View> list_of_views_first_row = new ArrayList<>();
    /*private BillingClient billingClient;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;*/
    private FirebaseFunctions firebaseFunctions;
    private String what_called_me;
    private boolean am_i_visble;

    public Give_coins() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            am_i_visble = false;
        } else {
            am_i_visble = true;
        }
    }

    public Give_coins(String what_called_me) {
        this.what_called_me = what_called_me;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_give_coins, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        am_i_visble = true;
        generate_the_views();
        button_listen();
        //set_up_billing();
        back_arrow_listen();
        are_prices_ready();
    }

    private void generate_the_views() {
        if (getActivity() != null && getContext() != null && getView() != null) {
            constraintLayout = getView().findViewById(R.id.give_coins_layout);
            constraintSet = new ConstraintSet();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            int how_many_width = (int) Math.ceil(convertPixelsToDp(width, getContext()) / 30d);
            int how_many_length = (int) Math.ceil(convertPixelsToDp(height, getContext()) / 30d);
            for (int i = 0; i < how_many_length; i++) {
                for (int j = 0; j < how_many_width; j++) {
                    View view = new View(getActivity());
                    view.setId(View.generateViewId());
                    constraintLayout.addView(view);
                    list_of_views.add(view);
                    Random rand = new Random();
                    int random_number = rand.nextInt(3);
                    if (random_number == 0) {
                        view.setBackgroundResource(R.drawable.silver_award_png);
                    } else if (random_number == 1) {
                        view.setBackgroundResource(R.drawable.gold_award_png);
                    } else {
                        view.setBackgroundResource(R.drawable.ic_plat_award_posts);
                    }
                    if (i == 0 && j == 0) {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int) convertDpToPixel(6, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, R.id.bar_at_the_top_of_give_coins, ConstraintSet.BOTTOM, (int) convertDpToPixel(6, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (i == 0) {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, list_of_views.get(list_of_views.size() - 2).getId(), ConstraintSet.END, (int) convertDpToPixel(6, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, R.id.bar_at_the_top_of_give_coins, ConstraintSet.BOTTOM, (int) convertDpToPixel(6, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (j == 0) {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int) convertDpToPixel(6, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, list_of_views_first_row.get(i - 1).getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(6, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, list_of_views.get(list_of_views.size() - 2).getId(), ConstraintSet.END, (int) convertDpToPixel(6, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, list_of_views_first_row.get(i - 1).getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(6, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    }
                    if (j == 0) {
                        list_of_views_first_row.add(view);
                    }
                }
            }
        }
    }

    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void button_listen() {
        if (getView() != null) {
            Button buy_first_thing_coins_buy_coins = getView().findViewById(R.id.buy_first_thing_coins_buy_coins);
            Button buy_second_thing_coins_buy_coins = getView().findViewById(R.id.buy_second_thing_coins_buy_coins);
            Button buy_third_thing_coins_buy_coins = getView().findViewById(R.id.buy_third_thing_coins_buy_coins);
            Button buy_fourth_thing_coins_buy_coins = getView().findViewById(R.id.buy_fourth_thing_coins_buy_coins);
            Button buy_fifth_thing_coins_buy_coins = getView().findViewById(R.id.buy_fifth_thing_coins_buy_coins);
            Button buy_sixth_thing_coins_buy_coins = getView().findViewById(R.id.buy_sixth_thing_coins_buy_coins);
            buy_first_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","400");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "400_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
            buy_second_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","1500");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "1500_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
            buy_third_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","3600");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "3600_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
            buy_fourth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","8400");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "8400_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
            buy_fifth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","16000");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "16000_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
            buy_sixth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("how_many_coins","40000");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.purchase_more_coins_clicked,false,bundle);
                    call_the_listen_in_settings();
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "40000_coins", getContext(),FirebaseAuth.getInstance().getUid());
                }
            });
        }
    }

    private void set_the_prices() {
        if (getActivity() != null) {
            Button buy_first_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_first_thing_coins_buy_coins);
            Button buy_second_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_second_thing_coins_buy_coins);
            Button buy_third_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_third_thing_coins_buy_coins);
            Button buy_fourth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_fourth_thing_coins_buy_coins);
            Button buy_fifth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_fifth_thing_coins_buy_coins);
            Button buy_sixth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_sixth_thing_coins_buy_coins);
            /*if(Payment_processer.getInstance().are_in_app_prices_ready){
                buy_first_thing_coins_buy_coins.setText("400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("400_coins")));
                buy_second_thing_coins_buy_coins.setText("1500 coins | ".concat(Payment_processer.getInstance().get_price_in_app("1500_coins")));
                buy_third_thing_coins_buy_coins.setText("3600 coins | ".concat(Payment_processer.getInstance().get_price_in_app("3600_coins")));
                buy_fourth_thing_coins_buy_coins.setText("8400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("8400_coins")));
                buy_fifth_thing_coins_buy_coins.setText("16000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("16000_coins")));
                buy_sixth_thing_coins_buy_coins.setText("40000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("40000_coins")));
            } else {
                Payment_processer.getInstance().listen_for_in_app_prices(new Payment_processer.in_app_prices_ready() {
                    @Override
                    public void in_app_prices_are_ready() {
                        buy_first_thing_coins_buy_coins.setText("400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("400_coins")));
                        buy_second_thing_coins_buy_coins.setText("1500 coins | ".concat(Payment_processer.getInstance().get_price_in_app("1500_coins")));
                        buy_third_thing_coins_buy_coins.setText("3600 coins | ".concat(Payment_processer.getInstance().get_price_in_app("3600_coins")));
                        buy_fourth_thing_coins_buy_coins.setText("8400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("8400_coins")));
                        buy_fifth_thing_coins_buy_coins.setText("16000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("16000_coins")));
                        buy_sixth_thing_coins_buy_coins.setText("40000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("40000_coins")));
                    }
                });
            }*/
            buy_first_thing_coins_buy_coins.setText("400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("400_coins")));
            buy_second_thing_coins_buy_coins.setText("1500 coins | ".concat(Payment_processer.getInstance().get_price_in_app("1500_coins")));
            buy_third_thing_coins_buy_coins.setText("3600 coins | ".concat(Payment_processer.getInstance().get_price_in_app("3600_coins")));
            buy_fourth_thing_coins_buy_coins.setText("8400 coins | ".concat(Payment_processer.getInstance().get_price_in_app("8400_coins")));
            buy_fifth_thing_coins_buy_coins.setText("16000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("16000_coins")));
            buy_sixth_thing_coins_buy_coins.setText("40000 coins | ".concat(Payment_processer.getInstance().get_price_in_app("40000_coins")));
        }
    }

    private void back_arrow_listen() {
        if (getActivity() != null) {
            Button button_to_back_in_buy_more_coins = getActivity().findViewById(R.id.button_to_back_in_buy_more_coins);
            button_to_back_in_buy_more_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    go_back();
                }
            });
        }
    }

    private void go_back() {
        if (what_called_me.equals("posts")) {
            Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        } else if (what_called_me.equals("show")) {
            Show_full_post new_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        } else if (what_called_me.equals("settings")) {
            Settings_fragment new_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        }
    }

    public String return_what_called_you() {
        return what_called_me;
    }

    private void are_prices_ready(){
       /* if (Payment_processer.getInstance().are_in_app_prices_ready){
            set_the_prices();
        } else {
            Payment_processer.getInstance().listen_for_in_app_prices(new Payment_processer.in_app_prices_ready() {
                @Override
                public void in_app_prices_are_ready() {
                    set_the_prices();
                }
            });
        }*/
        set_the_prices();
    }

    private void call_the_listen_in_settings() {
        Settings_fragment old_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
        if (old_fragment != null) {
            old_fragment.listen_to_coin_update();
        }
    }
}