package com.easyhabitsapp.android;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bottom_sheet_to_give_coins extends BottomSheetDialogFragment {
    private View mview;
    private String user_id;
    private String type_of_thing_that_will_get_gift;
    private String post_id;
    private int comment_position;
    private int reply_position;
    private int coins = 0;
    private FirebaseFirestore m_firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser m_firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseFunctions firebaseFunctions;
    private update_gift_listen send_gift_listen_listener;
    private ArrayList<Long> arrayList_of_awards = new ArrayList<Long>();
    private int index;
    private String what_called_me;
    private boolean is_this_chat;
    private send_which_award_was_given send_which_award_was_given_listen;
    private ArrayList<Long> arrayList_of_gifts = new ArrayList<>();
    //private PurchasesResponseListener purchasesResponseListener;

    public void set_update_gift(update_gift_listen listen) {
        send_gift_listen_listener = listen;
    }

    public interface update_gift_listen {
        void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards);
    }

    public void set_gift_listen_which_one(send_which_award_was_given listen) {
        send_which_award_was_given_listen = listen;
    }

    public interface send_which_award_was_given {
        void gift_was_clicked(ArrayList<Long> arrayList_of_gifts);
    }

    public Bottom_sheet_to_give_coins(String user_id) {
        this.user_id = user_id;
        this.is_this_chat = true;
        arrayList_of_gifts.add(0L);
        arrayList_of_gifts.add(0L);
        arrayList_of_gifts.add(0L);
        arrayList_of_gifts.add(0L);
    }

    public Bottom_sheet_to_give_coins(int index, String user_id, String post_id, ArrayList<Long> arrayList_of_awards, String what_called_me) {
        this.index = index;
        this.user_id = user_id;
        this.post_id = post_id;
        type_of_thing_that_will_get_gift = "post";
        this.arrayList_of_awards = arrayList_of_awards;
        this.what_called_me = what_called_me;
    }

    public Bottom_sheet_to_give_coins(int index, String user_id, String post_id, int comment_position, ArrayList<Long> arrayList_of_awards, String what_called_me) {
        this.index = index;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_position = comment_position;
        type_of_thing_that_will_get_gift = "comment";
        this.arrayList_of_awards = arrayList_of_awards;
        this.what_called_me = what_called_me;
    }

    public Bottom_sheet_to_give_coins(int index, String user_id, String post_id, int comment_position, int reply_position, ArrayList<Long> arrayList_of_awards, String what_called_me) {
        this.index = index;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_position = comment_position;
        this.reply_position = reply_position;
        type_of_thing_that_will_get_gift = "reply";
        this.arrayList_of_awards = arrayList_of_awards;
        this.what_called_me = what_called_me;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_gift_awards, container, false);
        mview = view;
        mAuth = FirebaseAuth.getInstance();
        m_firebaseUser = mAuth.getCurrentUser();
        set_coins_from_fire_base();
        buttons_listen();
//        set_up_billing();
        button_listen();
        hide_the_buttons();
        are_prices_ready();
        listen_for_gift();
        return view;
    }

    private void buttons_listen() {
        Button button_to_give_silver_award = mview.findViewById(R.id.button_to_give_silver_award);
        Button button_to_give_gold_award = mview.findViewById(R.id.button_to_give_gold_award);
        Button button_to_give_plat_award = mview.findViewById(R.id.button_to_give_plat_award);
        Button button_to_give_one_month_award = mview.findViewById(R.id.button_to_give_one_month_award);
        Button button_to_give_three_month_award = mview.findViewById(R.id.button_to_give_three_month_award);
        Button button_to_give_six_month_award = mview.findViewById(R.id.button_to_give_six_month_award);
        Button button_to_give_twelve_month_award = mview.findViewById(R.id.button_to_give_twelve_month_award);
        button_to_give_silver_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coins < 100) {
                    if (what_called_me.equals("posts")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    } else if (what_called_me.equals("show")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    }
                } else {
                    Dialog_to_ask_if_user_wants_to_give_this_award dialog_to_ask_if_user_wants_to_give_this_award = new Dialog_to_ask_if_user_wants_to_give_this_award(0, type_of_thing_that_will_get_gift);
                    dialog_to_ask_if_user_wants_to_give_this_award.set_give_award_clicked_listen(new Dialog_to_ask_if_user_wants_to_give_this_award.give_award_listen() {
                        @Override
                        public void give_award_clicked() {
                            Toast.makeText(getActivity(), "Sending Silver award...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("howmuchcoins", 100);
                            data.put("type_of_thing_that_will_get_gift", type_of_thing_that_will_get_gift);
                            if (type_of_thing_that_will_get_gift.equals("post")) {
                                data.put("post", post_id);
                            } else if (type_of_thing_that_will_get_gift.equals("comment")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                            } else if (type_of_thing_that_will_get_gift.equals("reply")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                                data.put("reply", reply_position);
                            }
                            Dialog_gift_award dialog_gift_award = new Dialog_gift_award(1);
                            dialog_gift_award.show(getParentFragmentManager(), "");
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("givecoinshttps")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_gift_award.loaded();
                                                coins = coins - 100;
                                                set_coins_text();
                                                arrayList_of_awards.set(2, arrayList_of_awards.get(2) + 1);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                        }
                                    });
                        }
                    });
                    dialog_to_ask_if_user_wants_to_give_this_award.show(getParentFragmentManager(), "");
                }
            }
        });
        button_to_give_gold_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coins < 500) {
                    if (what_called_me.equals("posts")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    } else if (what_called_me.equals("show")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    }
                } else {
                    Dialog_to_ask_if_user_wants_to_give_this_award dialog_to_ask_if_user_wants_to_give_this_award = new Dialog_to_ask_if_user_wants_to_give_this_award(1, type_of_thing_that_will_get_gift);
                    dialog_to_ask_if_user_wants_to_give_this_award.set_give_award_clicked_listen(new Dialog_to_ask_if_user_wants_to_give_this_award.give_award_listen() {
                        @Override
                        public void give_award_clicked() {
                            Toast.makeText(getActivity(), "Sending Gold award...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("howmuchcoins", 500);
                            data.put("type_of_thing_that_will_get_gift", type_of_thing_that_will_get_gift);
                            if (type_of_thing_that_will_get_gift.equals("post")) {
                                data.put("post", post_id);
                            } else if (type_of_thing_that_will_get_gift.equals("comment")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                            } else if (type_of_thing_that_will_get_gift.equals("reply")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                                data.put("reply", reply_position);
                            }
                            Dialog_gift_award dialog_gift_award = new Dialog_gift_award(2);
                            dialog_gift_award.show(getParentFragmentManager(), "");
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("givecoinshttps")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_gift_award.loaded();
                                                coins = coins - 500;
                                                set_coins_text();
                                                arrayList_of_awards.set(1, arrayList_of_awards.get(1) + 1);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {

                                        }
                                    });
                        }
                    });
                    dialog_to_ask_if_user_wants_to_give_this_award.show(getParentFragmentManager(), "");
                }
            }
        });
        button_to_give_plat_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coins < 1200) {
                    if (what_called_me.equals("posts")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    } else if (what_called_me.equals("show")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    }
                } else {
                    Dialog_to_ask_if_user_wants_to_give_this_award dialog_to_ask_if_user_wants_to_give_this_award = new Dialog_to_ask_if_user_wants_to_give_this_award(2, type_of_thing_that_will_get_gift);
                    dialog_to_ask_if_user_wants_to_give_this_award.set_give_award_clicked_listen(new Dialog_to_ask_if_user_wants_to_give_this_award.give_award_listen() {
                        @Override
                        public void give_award_clicked() {
                            Toast.makeText(getActivity(), "Sending Platinum award...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("howmuchcoins", 1200);
                            data.put("type_of_thing_that_will_get_gift", type_of_thing_that_will_get_gift);
                            if (type_of_thing_that_will_get_gift.equals("post")) {
                                data.put("post", post_id);
                            } else if (type_of_thing_that_will_get_gift.equals("comment")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                            } else if (type_of_thing_that_will_get_gift.equals("reply")) {
                                data.put("post", post_id);
                                data.put("comment", comment_position);
                                data.put("reply", reply_position);
                            }
                            Dialog_gift_award dialog_gift_award = new Dialog_gift_award(3);
                            dialog_gift_award.show(getParentFragmentManager(), "");
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("givecoinshttps")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_gift_award.loaded();
                                                coins = coins - 1200;
                                                set_coins_text();
                                                arrayList_of_awards.set(0, arrayList_of_awards.get(0) + 1);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {

                                        }
                                    });
                        }
                    });
                    dialog_to_ask_if_user_wants_to_give_this_award.show(getParentFragmentManager(), "");
                }
            }
        });
        button_to_give_one_month_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                save_and_retrive_user_id.save_a_single_id(getContext(),1,user_id);*/
                Payment_processer.getInstance().launch_product_flow(getActivity(),"1_month_premium_gift",getContext(),user_id);
            }
        });
        button_to_give_three_month_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                //save_and_retrive_user_id.save_the_id(getContext(), null, "back_up", user_id, 3);
                save_and_retrive_user_id.save_a_single_id(getContext(),3,user_id);*/
                Payment_processer.getInstance().launch_product_flow(getActivity(),"3_month_premium_gift",getContext(),user_id);

            }
        });
        button_to_give_six_month_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                //save_and_retrive_user_id.save_the_id(getContext(), null, "back_up", user_id, 6);
                save_and_retrive_user_id.save_a_single_id(getContext(),6,user_id);*/
                Payment_processer.getInstance().launch_product_flow(getActivity(),"6_month_premium_gift",getContext(),user_id);

            }
        });
        button_to_give_twelve_month_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                //save_and_retrive_user_id.save_the_id(getContext(), null, "back_up", user_id, 12);
                save_and_retrive_user_id.save_a_single_id(getContext(),12,user_id);*/
                Payment_processer.getInstance().launch_product_flow(getActivity(),"12_month_premium_gift",getContext(),user_id);

            }
        });
    }

    private void set_coins_from_fire_base() {
        final DocumentReference docIdRef = m_firebaseFirestore.collection("coins").document(m_firebaseUser.getUid());
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot != null && snapshot.exists()) {
                        coins = (int) ((long) snapshot.get("coins"));
                        set_coins_text();
                    } else {
                        coins = 0;
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cant load coins", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void set_coins_text() {
        TextView text_saying_number_of_coins = mview.findViewById(R.id.text_saying_number_of_coins);
        text_saying_number_of_coins.setText(String.valueOf(coins).concat(" coins"));
    }

    private void set_the_prices() {
        TextView text_saying_teh_price_of_one_month = mview.findViewById(R.id.text_saying_teh_price_of_one_month);
        TextView text_saying_teh_price_of_three_month = mview.findViewById(R.id.text_saying_teh_price_of_three_month);
        TextView text_saying_teh_price_of_six_month = mview.findViewById(R.id.text_saying_teh_price_of_six_month);
        TextView text_saying_teh_price_of_twelve_month = mview.findViewById(R.id.text_saying_teh_price_of_twelve_month);
        text_saying_teh_price_of_one_month.setText(Payment_processer.getInstance().get_price_in_app("1_month_premium_gift"));
        text_saying_teh_price_of_three_month.setText(Payment_processer.getInstance().get_price_in_app("3_month_premium_gift"));
        text_saying_teh_price_of_six_month.setText(Payment_processer.getInstance().get_price_in_app("6_month_premium_gift"));
        text_saying_teh_price_of_twelve_month.setText(Payment_processer.getInstance().get_price_in_app("12_month_premium_gift"));
    }

    @Override
    public void onDismiss(@NonNull @NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (is_this_chat) {
            send_which_award_was_given_listen.gift_was_clicked(arrayList_of_gifts);
        } else {
            send_gift_listen_listener.gift_was_clicked(index, arrayList_of_awards);
        }
    }

    private void button_listen() {
        if (getActivity() != null) {
            Button button_to_buy_more_coins_in_awards = mview.findViewById(R.id.button_to_buy_more_coins_in_awards);
            button_to_buy_more_coins_in_awards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (what_called_me.equals("posts")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    } else if (what_called_me.equals("show")) {
                        Give_coins give_coins = new Give_coins(what_called_me);
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                        }
                        dismiss();
                    }
                }
            });
        }
    }

    private void hide_the_buttons() {
        if (is_this_chat) {
            Button button_to_give_silver_award = mview.findViewById(R.id.button_to_give_silver_award);
            TextView text_saying_silver_month = mview.findViewById(R.id.text_saying_silver_month);
            TextView text_saying_teh_price_of_silver = mview.findViewById(R.id.text_saying_teh_price_of_silver);
            View silver_award_image = mview.findViewById(R.id.silver_award_image);
            Button button_to_give_gold_award = mview.findViewById(R.id.button_to_give_gold_award);
            TextView text_saying_gold_month = mview.findViewById(R.id.text_saying_gold_month);
            View gold_award_image = mview.findViewById(R.id.gold_award_image);
            TextView text_saying_teh_price_of_gold = mview.findViewById(R.id.text_saying_teh_price_of_gold);
            Button button_to_give_plat_award = mview.findViewById(R.id.button_to_give_plat_award);
            TextView text_saying_plat_month = mview.findViewById(R.id.text_saying_plat_month);
            View plat_award_image = mview.findViewById(R.id.plat_award_image);
            TextView text_saying_teh_price_of_plat = mview.findViewById(R.id.text_saying_teh_price_of_plat);
            Button button_to_buy_more_coins_in_awards = mview.findViewById(R.id.button_to_buy_more_coins_in_awards);
            View start_drawable_for_number_of_coins = mview.findViewById(R.id.start_drawable_for_number_of_coins);
            View middle_drawable_for_number_of_coins = mview.findViewById(R.id.middle_drawable_for_number_of_coins);
            View end_drawable_for_number_of_coins = mview.findViewById(R.id.end_drawable_for_number_of_coins);
            TextView text_saying_number_of_coins = mview.findViewById(R.id.text_saying_number_of_coins);
            button_to_buy_more_coins_in_awards.setVisibility(View.GONE);
            start_drawable_for_number_of_coins.setVisibility(View.GONE);
            middle_drawable_for_number_of_coins.setVisibility(View.GONE);
            end_drawable_for_number_of_coins.setVisibility(View.GONE);
            text_saying_number_of_coins.setVisibility(View.GONE);
            button_to_give_silver_award.setVisibility(View.GONE);
            text_saying_silver_month.setVisibility(View.GONE);
            text_saying_teh_price_of_silver.setVisibility(View.GONE);
            silver_award_image.setVisibility(View.GONE);
            button_to_give_gold_award.setVisibility(View.GONE);
            text_saying_gold_month.setVisibility(View.GONE);
            gold_award_image.setVisibility(View.GONE);
            text_saying_teh_price_of_gold.setVisibility(View.GONE);
            button_to_give_plat_award.setVisibility(View.GONE);
            text_saying_plat_month.setVisibility(View.GONE);
            plat_award_image.setVisibility(View.GONE);
            text_saying_teh_price_of_plat.setVisibility(View.GONE);
        }
    }

    private void are_prices_ready(){
       /* if(Payment_processer.getInstance().are_in_app_prices_ready){
            set_the_prices();
        } else {
            Payment_processer.getInstance().listen_for_in_app_prices_bottom_sheet_give_coins(new Payment_processer.in_app_prices_ready_bottom_sheet_give_coins() {
                @Override
                public void listen_for_in_app_prices_bottom_sheet_give_coins() {

                }
            });
        }*/
        set_the_prices();
    }

    private void listen_for_gift(){
        Payment_processer.getInstance().listen_for_gift(new Payment_processer.update_the_gift() {
            @Override
            public void how_many_month(int how_many) {
                if (arrayList_of_gifts.size() == 4) {
                    if (how_many == 1) {
                        arrayList_of_gifts.set(0, arrayList_of_gifts.get(0) + 1);
                    } else if (how_many == 3) {
                        arrayList_of_gifts.set(1, arrayList_of_gifts.get(1) + 1);
                    } else if (how_many == 6) {
                        arrayList_of_gifts.set(2, arrayList_of_gifts.get(2) + 1);
                    } else if (how_many == 12) {
                        arrayList_of_gifts.set(3, arrayList_of_gifts.get(3) + 1);
                    }
                }
            }
        });
    }
}
