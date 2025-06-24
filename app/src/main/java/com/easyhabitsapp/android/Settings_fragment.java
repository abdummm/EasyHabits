package com.easyhabitsapp.android;


import static android.content.Context.MODE_PRIVATE;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.Purchase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_fragment extends Fragment {
    //private String monthly_price = "";
    //private String yearly_price = "";
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    /*private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private boolean am_i_visible = false;
    private FirebaseFunctions firebaseFunctions;*/
    private ListenerRegistration firestore_listener = null;
    //private String token;


    public Settings_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rate_app_button_listen();
        button_saying_report_a_bug();
        contact_developer();
        share_the_app_button_listen();
        show_privacy_policy();
        show_terms_and_conditions();
        version_code_button_listen();
        privacy_policy_click();
        terms_and_conditions();
//        set_up_billing();
        buy_coins_button_listen();
        set_the_version_number();
        set_the_premuim_in_text();
        upgrade_subscription_button_listen();
        sign_in_setting();
        hide_sign_in_button();
        enter_invite_code_listen();
        get_invite_code_listen();
        hide_refer();
        check_refer_online();
        change_my_name_button_listen();
        set_the_text_of_change_my_name_button();
        set_the_prices();
        buy_monthly_button_listen();
        buy_yearly_button_listen();
        listen_to_purchase_complete();
        manage_subscription_button_listen();
        set_up_coins_in_firebase();
        set_up_button_for_more_information_listen();
        listen_to_hide_upgrade_yearly();
        listen_to_purchase_list_for_upgrade();
        make_the_offer_text_bold();
        buy_coins_button_listen_under_profile();
        set_name_for_profile();
        start_fourteen_day_trial_button_listen();
        calculate_the_streak();
        set_the_streak();
        click_on_profile_picture_button_listen();
        change_profile_picture_listen();
    }

    private void rate_app_button_listen() {
        if (getView() != null) {
            Button button_saying_rate_in_settings = getView().findViewById(R.id.button_saying_rate_in_settings);
            button_saying_rate_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.rate_app_clicked, false);
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" + getActivity().getPackageName())));
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                        }
                    }
                }
            });
        }
    }

    private void button_saying_report_a_bug() {
        if (getView() != null) {
            Button button_saying_report_a_bug = getView().findViewById(R.id.button_saying_report_a_bug);
            button_saying_report_a_bug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.report_a_bug_clicked, false);
                    send_email();
                }
            });
        }
    }

    private void contact_developer() {
        if (getView() != null) {
            Button button_saying_contact_thje_developer = getView().findViewById(R.id.button_saying_contact_thje_developer);
            button_saying_contact_thje_developer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.contact_the_developer_clicked, false);
                    send_email();
                }
            });
        }
    }

    private void share_the_app_button_listen() {
        if (getView() != null) {
            Button button_saying_the_app_in_setting = getView().findViewById(R.id.button_saying_the_app_in_setting);
            button_saying_the_app_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.app_shared, false);
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    String shareBody = "Checkout this habit tracker that lets you track bad habits, good habits and mood. It also allows you to post, chat with random people and much more. Download it here \n https://play.google.com/store/apps/details?id=com.easyhabitsapp.android ";
                    intent.setType("text/plain");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this habit tracker");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    /*Fire!*/
                    startActivity(intent);
                }
            });
        }
    }

    private void show_privacy_policy() {
        if (getView() != null) {
            Button button_saying_the_privacy_policy_in_setting = getView().findViewById(R.id.button_saying_the_privacy_policy_in_setting);
            button_saying_the_privacy_policy_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsprivacypolicy/home"));// change later
                    startActivity(browserIntent);
                }
            });
        }
    }

    private void show_terms_and_conditions() {
        if (getView() != null) {
            Button button_saying_the_terms_and_conditions_in_setting = getView().findViewById(R.id.button_saying_the_terms_and_conditions_in_setting);
            button_saying_the_terms_and_conditions_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsterms/home"));// change later
                    startActivity(browserIntent);
                }
            });
        }
    }

    private void version_code_button_listen() {
        if (getView() != null) {
            Button button_saying_version_number_in_setting = getView().findViewById(R.id.button_saying_version_number_in_setting);
            button_saying_version_number_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private void send_email() {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); // only email apps should handle this
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"abdomakesappshelp@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Contact app support");
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            email.putExtra(Intent.EXTRA_TEXT, "My user id is: ".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()));
        } else {
            email.putExtra(Intent.EXTRA_TEXT, "");
        }
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    /*private void learn_more_button_listen() {
        if (getView() != null) {
            Button button_saying_learn_more_in_buy_premium = getView().findViewById(R.id.button_saying_learn_more_in_buy_premium);
            button_saying_learn_more_in_buy_premium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!monthly_price.equals("") && !yearly_price.equals("")) {
                        Dialog_to_more_information_buy_premium dialog_to_more_information_buy_premium = new Dialog_to_more_information_buy_premium(monthly_price, yearly_price);
                        dialog_to_more_information_buy_premium.show(getParentFragmentManager(), "");
                    }
                }
            });
        }
    }*/

    private void privacy_policy_click() {
        if (getView() != null) {
            TextView Privacy_policy_in_buy_premuim = getView().findViewById(R.id.Privacy_policy_in_buy_premuim);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // Prevent CheckBox state from being toggled when link is clicked
                    widget.cancelPendingInputEvents();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsprivacypolicy/home"));// change later
                    startActivity(browserIntent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    // Show links with underlines (optional)
                    ds.setUnderlineText(true);
                }
            };

            SpannableString linkText = new SpannableString("Privacy Policy");
            linkText.setSpan(clickableSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            CharSequence cs = TextUtils.expandTemplate("^1", linkText);

            Privacy_policy_in_buy_premuim.setText(cs);
// Finally, make links clickable
            Privacy_policy_in_buy_premuim.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void terms_and_conditions() {
        if (getView() != null) {
            TextView terms_and_conditions_in_buy_premuim = getView().findViewById(R.id.terms_and_conditions_in_buy_premuim);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // Prevent CheckBox state from being toggled when link is clicked
                    widget.cancelPendingInputEvents();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsterms/home"));// change later
                    startActivity(browserIntent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    // Show links with underlines (optional)
                    ds.setUnderlineText(true);
                }
            };

            SpannableString linkText = new SpannableString("Terms and conditions");
            linkText.setSpan(clickableSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            CharSequence cs = TextUtils.expandTemplate("^1", linkText);

            terms_and_conditions_in_buy_premuim.setText(cs);
// Finally, make links clickable
            terms_and_conditions_in_buy_premuim.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void set_the_prices() {
        if (getView() != null) {
            if (Payment_processer.getInstance().are_sub_prices_ready) {
                set_the_prices_real();
            } else {
                Payment_processer.getInstance().listen_for_sub_2(new Payment_processer.sub_prices_ready_2() {
                    @Override
                    public void sub_prices_are_ready_2() {
                        set_the_prices_real();
                    }
                });
            }
        }
    }

    private void set_the_prices_real() {
        TextView text_saying_price_per_month_in_buy_month = getView().findViewById(R.id.text_saying_price_per_month_in_buy_month);
        TextView text_saying_price_per_year_in_buy_month = getView().findViewById(R.id.text_saying_price_per_year_in_buy_month);
        TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
        TextView text_saying_billed_year = getView().findViewById(R.id.text_saying_billed_year);
        Payment_processer.getInstance().get_price_sub("new_monthly_subscription", 0);
        text_saying_price_per_month_in_buy_month.setText(Payment_processer.getInstance().get_price_sub("new_monthly_subscription", 0).concat("/mo"));
        double old_price = (Payment_processer.getInstance().get_prices_sub_micros("new_yearly_subscription", 0) / 12D) / 1000000D;
        old_price = ((int) (old_price * 100)) / 100.00;
        String old_price_string = String.format("%.2f", old_price);
        text_saying_price_per_year_in_buy_month.setText(old_price_string.concat("/mo"));
        text_saying_old_price.setText(Payment_processer.getInstance().get_price_sub("new_monthly_subscription", 0).concat("/mo"));
        text_saying_old_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        text_saying_billed_year.setText("billed annually\n at ".concat(Payment_processer.getInstance().get_price_sub("new_yearly_subscription", 0)));
    }

    /*private void handle_purchase(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                if (purchase.getSku().contains("1_month_subscription")) {
                    if (!purchase.isAcknowledged()) {
                        set_buy_premuim_true();
                        hide_upgrade_subscription();
                        AcknowledgePurchaseParams acknowledgePurchaseParams =
                                AcknowledgePurchaseParams.newBuilder()
                                        .setPurchaseToken(purchase.getPurchaseToken())
                                        .build();
                        if (am_i_sign_in_with_google()) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month", false);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("monthly_sub_i_buy")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_subscribe_to_premuim.loaded();
                                                set_the_premuim_in_text();
                                                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        } else {
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month", true);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
                            set_the_premuim_in_text();
                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                        }
                    }
                } else if (purchase.getSku().contains("1_year_subscription")) {
                    if (!purchase.isAcknowledged()) {
                        set_buy_premuim_true();
                        hide_upgrade_subscription();
                        AcknowledgePurchaseParams acknowledgePurchaseParams =
                                AcknowledgePurchaseParams.newBuilder()
                                        .setPurchaseToken(purchase.getPurchaseToken())
                                        .build();
                        if (am_i_sign_in_with_google()) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year", false);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("yearly_sub_i_buy")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_subscribe_to_premuim.loaded();
                                                set_the_premuim_in_text();
                                                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact Support", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        } else {
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year", true);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
                            set_the_premuim_in_text();
                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                        }
                    }
                }
            } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
                //Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private void buy_monthly_button_listen() {
        if (getView() != null) {
            Button monthly_subscription_button_in_buy_premuim = getView().findViewById(R.id.monthly_subscription_button_in_buy_premuim);
            monthly_subscription_button_in_buy_premuim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                    save_and_retrive_user_id.save_a_single_id_subs(getContext(),1,FirebaseAuth.getInstance().getUid());*/
                    listen_to_coin_update();
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "setting");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.monthly_clicked, false, bundle);
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "new_monthly_subscription", getContext(), 0, FirebaseAuth.getInstance().getUid());
                }
            });
        }
    }

    private void buy_yearly_button_listen() {
        if (getView() != null) {
            Button yearly_subscription_button_in_buy_premuim = getView().findViewById(R.id.yearly_subscription_button_in_buy_premuim);
            yearly_subscription_button_in_buy_premuim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                    save_and_retrive_user_id.save_a_single_id_subs(getContext(),12,FirebaseAuth.getInstance().getUid());*/
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "setting");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.yearly_clicked, false, bundle);
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "new_yearly_subscription", getContext(), 0, FirebaseAuth.getInstance().getUid());
                }
            });
        }
    }

    private void make_text_strike_through() {
        if (getView() != null) {
            TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
            text_saying_old_price.setPaintFlags(text_saying_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private void tell_the_user_if_we_found_something(boolean did_we_find_anyhting) {
        if (did_we_find_anyhting) {
            Toast.makeText(getActivity(), "restored purchases successfully", Toast.LENGTH_LONG).show();
        } else {
            ;
            Toast.makeText(getActivity(), "no purchases to restore", Toast.LENGTH_LONG).show();
        }
    }

   /* public void check_if_user_is_gifted(boolean run_anyways) {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        firebaseFirestore.setFirestoreSettings(settings);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            myEdit.putBoolean("premuim", false);
        } else {
            if (sharedPreferences.getLong("last_checked", 0) < System.currentTimeMillis() - 86400000L || run_anyways) {
                firebaseFirestore.collection("gifts").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                long time = document.getLong("time");
                                if (time > System.currentTimeMillis() - 86400000L) {
                                    myEdit.putBoolean("premuim", true);
                                    myEdit.commit();
                                    tell_the_user_if_we_found_something(true);
                                } else {
                                    myEdit.putBoolean("premuim", false);
                                    myEdit.commit();
                                    tell_the_user_if_we_found_something(false);
                                }
                            } else {
                                myEdit.putBoolean("premuim", false);
                                myEdit.commit();
                                tell_the_user_if_we_found_something(false);
                            }
                        } else {
                            //fail
                            tell_the_user_if_we_found_something(false);
                        }
                    }
                });
                myEdit.putLong("last_checked", System.currentTimeMillis());
            }
        }
    }*/

    /*private void restore_purchases_button_listen() {
        if (getView() != null) {
            Button restore_purchases_button_in_buy_premium = getView().findViewById(R.id.restore_purchases_button_in_buy_premium);
            restore_purchases_button_in_buy_premium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    start_billing_connection(false, true);
                }
            });
        }
    }*/

    private void buy_coins_button_listen() {
        if (getView() != null) {
            Button buy_first_thing_coins_in_setting = getView().findViewById(R.id.buy_first_thing_coins_in_setting);
            buy_first_thing_coins_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buy_coins_button_clicked();
                }
            });
        }
    }

    private void set_the_version_number() {
        if (getView() != null) {
            Button button_saying_version_number_in_setting = getView().findViewById(R.id.button_saying_version_number_in_setting);
            button_saying_version_number_in_setting.setText("Version number: ".concat(BuildConfig.VERSION_NAME));
        }
    }

   /* @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
        } else {
//            set_the_premuim_in_text();
        }
    }*/

    private void set_buy_premuim_true() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("premuim_online", true);
            myEdit.commit();
        }
    }

    private boolean am_i_sign_in_with_google() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }
    }

    private void set_the_premuim_in_text() {
        if (getView() != null && getContext() != null) {
    /*        ConstraintLayout id_for_setting_did_not_buy_yet = getView().findViewById(R.id.id_for_setting_did_not_buy_yet);
            TextView buy_premium_button_in_setting = getView().findViewById(R.id.buy_premium_button_in_setting);
            ConstraintLayout setting_bought_yet_laready_layout = getView().findViewById(R.id.setting_bought_yet_laready_layout);
            TextView buy_premium_button_in_setting_in_already_bought = getView().findViewById(R.id.buy_premium_button_in_setting_in_already_bought);*/
            /*if (Payment_processer.getInstance().are_sub_prices_ready) {
                set_up_purchase_screen();
            } else {
                Payment_processer.getInstance().listen_for_sub(new Payment_processer.sub_prices_ready() {
                    @Override
                    public void sub_prices_are_ready() {
                        set_up_purchase_screen();
                    }
                });
            }*/
            if (Payment_processer.getInstance().is_sub_purchase_list_ready) {
                set_up_purchase_screen();
            } else {
                Payment_processer.getInstance().listen_for_sub_list_ready_2(new Payment_processer.sub_purchase_ready_list_2() {
                    @Override
                    public void sub_list_is_ready_2() {
                        set_up_purchase_screen();
                    }
                });
            }
            /*if (offline) {
                // bought
                setting_bought_yet_laready_layout.setVisibility(View.VISIBLE);
                id_for_setting_did_not_buy_yet.setVisibility(View.GONE);
                buy_premium_button_in_setting_in_already_bought.setText("Premium status: premium");
            } else {
                // gift or didnt buy
                id_for_setting_did_not_buy_yet.setVisibility(View.VISIBLE);
                setting_bought_yet_laready_layout.setVisibility(View.GONE);
                if (online) {
                    buy_premium_button_in_setting.setText("Premium status: premium until ".concat(return_the_date(premuim_until)));
                } else {
                    buy_premium_button_in_setting.setText("Premium status: not premium");
                }
            }*/
        }
    }

    private void set_up_purchase_screen() {
        if (!Payment_processer.getInstance().sub_purchase_list.isEmpty()) {
            set_up_purchase_screen_helper("purchased", 0);
        } else {
            firebaseFirestore.collection("gifts").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long time = document.getLong("time");
                            if (time > System.currentTimeMillis() - 86400000L) {
                                set_up_purchase_screen_helper("gifted", time + 86400000L);
                            } else {
                                set_up_purchase_screen_helper("none", 0);
                            }
                        } else {
                            set_up_purchase_screen_helper("none", 0);
                        }
                    } else {
                        //fail
                    }
                }
            });
        }
    }

    private void set_up_purchase_screen_helper(String type, long time) {
        if (getView() != null) {
            /*ConstraintLayout id_for_setting_did_not_buy_yet = getView().findViewById(R.id.id_for_setting_did_not_buy_yet);
            TextView buy_premium_button_in_setting = getView().findViewById(R.id.buy_premium_button_in_setting);
            ConstraintLayout setting_bought_yet_laready_layout = getView().findViewById(R.id.setting_bought_yet_laready_layout);
            TextView buy_premium_button_in_setting_in_already_bought = getView().findViewById(R.id.buy_premium_button_in_setting_in_already_bought);*/
            TextView text_saying_pro_or_free_user = getView().findViewById(R.id.text_saying_pro_or_free_user);
            ConstraintLayout constraint_layout_with_everything_for_buy_premuim = getView().findViewById(R.id.constraint_layout_with_everything_for_buy_premuim);
            if (type.equals("purchased")) {
          /*      setting_bought_yet_laready_layout.setVisibility(View.VISIBLE);
                id_for_setting_did_not_buy_yet.setVisibility(View.GONE);*/
                text_saying_pro_or_free_user.setText("Pro User");
                text_saying_pro_or_free_user.setTextColor(Color.BLACK);
                constraint_layout_with_everything_for_buy_premuim.setVisibility(View.GONE);
            } else if (type.equals("gifted")) {
              /*  id_for_setting_did_not_buy_yet.setVisibility(View.VISIBLE);
                setting_bought_yet_laready_layout.setVisibility(View.GONE);*/
                text_saying_pro_or_free_user.setText("Pro until ".concat(return_the_date(time)));
                text_saying_pro_or_free_user.setTextColor(Color.BLACK);
                constraint_layout_with_everything_for_buy_premuim.setVisibility(View.VISIBLE);
            } else if (type.equals("none")) {
               /* id_for_setting_did_not_buy_yet.setVisibility(View.VISIBLE);
                setting_bought_yet_laready_layout.setVisibility(View.GONE);*/
                text_saying_pro_or_free_user.setText("Free User");
                text_saying_pro_or_free_user.setTextColor(Color.parseColor("#808080"));
                constraint_layout_with_everything_for_buy_premuim.setVisibility(View.VISIBLE);
            }
        }
    }

    private void upgrade_subscription_button_listen() {
        if (getView() != null) {
            final Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            upgrade_susbcription_in_setting_premuim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Payment_processer.getInstance().upgrade_to_yearly_billing(getActivity(), "new_yearly_subscription", getContext(), 0, FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
            });
        }
    }

    private String return_the_date(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(mDay).concat(" / ").concat(Simplify_the_time.return_month(String.valueOf(mMonth))).concat(" / ").concat(String.valueOf(mYear));
    }

    private void check_if_i_should_hide_upgrade_subscription(List<Purchase> purchases) {
        if (getView() != null) {
            Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            for (int i = 0; i < purchases.size(); i++) {
                FirebaseFirestore.getInstance().collection("tokens_and_user_ids").whereEqualTo("purchase_token", purchases.get(i).getPurchaseToken()).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                boolean deferred_or_yearly = snapshot.getBoolean("deferred_or_yearly");
                                if (deferred_or_yearly) {
                                    hide_upgrade_yearly_button();
                                    break;
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "A problem occurred, please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    private void hide_upgrade_yearly_button() {
        if (getView() != null) {
            Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            upgrade_susbcription_in_setting_premuim.setVisibility(View.GONE);
        }
    }

    private void show_upgrade_yearly_button() {
        if (getView() != null && getContext() != null) {
            Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            upgrade_susbcription_in_setting_premuim.setVisibility(View.VISIBLE);
            ConstraintLayout constraint_layout_for_easyhabits_pro = getView().findViewById(R.id.constraint_layout_for_easyhabits_pro);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraint_layout_for_easyhabits_pro);
            constraintSet.connect(R.id.pixel_at_the_end_of_the_pro_area, ConstraintSet.TOP, R.id.upgrade_susbcription_in_setting_premuim, ConstraintSet.BOTTOM, (int) convertDpToPixel(10, getContext()));
            constraintSet.applyTo(constraint_layout_for_easyhabits_pro);
        }
    }

    private void sign_in_setting() {
        if (getView() != null) {
            Button button_saying_sign_in_with_firebase_settings = getView().findViewById(R.id.button_saying_sign_in_with_firebase_settings);
            button_saying_sign_in_with_firebase_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                    bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Settings_fragment.this, 254);
                    bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                    //Toast.makeText(getContext(),"Coming soon...",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private String how_is_user_logged_in() {
        try {
            if ((FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com"))) {
                return "google";
            } else {
                return "anonymous";
            }
        } catch (Exception exception) {
            return "anonymous";
        }
    }

    private void hide_sign_in_button() {
        if (getView() != null && getContext() != null) {
            Button button_saying_sign_in_with_firebase_settings = getView().findViewById(R.id.button_saying_sign_in_with_firebase_settings);
            /*Button button_saying_rate_in_settings = getView().findViewById(R.id.button_saying_rate_in_settings);
            TextView text_saying_setting_in_setting_tab = getView().findViewById(R.id.text_saying_setting_in_setting_tab);*/
            if (how_is_user_logged_in().equals("google")) {
                button_saying_sign_in_with_firebase_settings.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.setting_parent_layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.button_saying_rate_in_settings, ConstraintSet.TOP, R.id.button_saying_enter_invite_code, ConstraintSet.BOTTOM, 0);
                constraintSet.applyTo(constraintLayout);
            } else {
                button_saying_sign_in_with_firebase_settings.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.setting_parent_layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.button_saying_sign_in_with_firebase_settings, ConstraintSet.TOP, R.id.button_saying_enter_invite_code, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.button_saying_rate_in_settings, ConstraintSet.TOP, R.id.button_saying_sign_in_with_firebase_settings, ConstraintSet.BOTTOM, 0);
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 254) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String text = bundle.getString("sign_in", "anonymous");
                hide_sign_in_button();
                check_the_gift_then_update_the_text();
                check_refer_online();
                Payment_processer.getInstance().set_up_gift_listen(getContext());
                set_up_coins_in_firebase();
            }
        }
    }

    private void enter_invite_code_listen() {
        if (getView() != null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            button_saying_enter_invite_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_for_invite_card dialog_for_invite_card = new Dialog_for_invite_card("enter invite code", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    dialog_for_invite_card.set_up_everything_is_ok(new Dialog_for_invite_card.everything_is_ok() {
                        @Override
                        public void everything_is_ok() {
                            button_saying_enter_invite_code.setVisibility(View.GONE);
                            dialog_for_invite_card.dismiss();
                            Dialog_gift_months dialog_gift_months = new Dialog_gift_months(6);
                            dialog_gift_months.show(getActivity().getSupportFragmentManager(), "");
                            dialog_gift_months.loaded();
                            check_the_gift_then_update_the_text();
                        }
                    });
                    dialog_for_invite_card.show(getParentFragmentManager(), "");
                }
            });
        }
    }

    private void get_invite_code_listen() {
        if (getView() != null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            Button button_saying_get_my_invite_code = getView().findViewById(R.id.button_saying_get_my_invite_code);
            button_saying_get_my_invite_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() != null && getActivity() != null) {
                        Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.get_my_referral_code_clicked, false);
                        Dialog_for_invite_card dialog_for_invite_card = new Dialog_for_invite_card("get your invite code", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        dialog_for_invite_card.set_up_everything_is_ok(new Dialog_for_invite_card.everything_is_ok() {
                            @Override
                            public void everything_is_ok() {
                                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.enter_code_successful, false);
                                button_saying_enter_invite_code.setVisibility(View.GONE);
                                dialog_for_invite_card.dismiss();
                                Dialog_gift_months dialog_gift_months = new Dialog_gift_months(6);
                                dialog_gift_months.show(getActivity().getSupportFragmentManager(), "");
                                dialog_gift_months.loaded();
                                check_the_gift_then_update_the_text();
                            }
                        });
                        dialog_for_invite_card.show(getParentFragmentManager(), "");
                    }
                }
            });
        }
    }

    private void hide_refer() {
        if (getView() != null && getActivity() != null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            SharedPreferences sh = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
            if (sh.getBoolean("register", false)) {
                button_saying_enter_invite_code.setVisibility(View.GONE);
            }
        }
    }

    private void check_refer_online() {
        if (getActivity() != null && getView() != null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            SharedPreferences sh = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
            if (!sh.getBoolean("register", false) && FirebaseAuth.getInstance().getCurrentUser() != null) {
                DocumentReference docRef = firebaseFirestore.collection("redeem_refer").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putBoolean("register", true);
                                myEdit.commit();
                                button_saying_enter_invite_code.setVisibility(View.GONE);
                            }
                        } else {
                            //error
                        }
                    }
                });
            }
        }
    }

    private String return_the_name() {
        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            return sh.getString("name", "no_name_found_135");
        } else {
            return "Name";
        }
    }

    private void change_my_name_button_listen() {
        if (getView() != null) {
            Button button_to_change_name_in_settings = getView().findViewById(R.id.button_to_change_name_in_settings);
            button_to_change_name_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
                    String name = sharedPreferences.getString("name", "");
                    long last_attempt = sharedPreferences.getLong("last_changed", 0);
                    if ((System.currentTimeMillis() - last_attempt) >= TimeUnit.DAYS.toMillis(3)) {
                        Dialog_to_make_the_user_enter_their_name dialog_to_make_the_user_enter_their_name = new Dialog_to_make_the_user_enter_their_name("Note: You can only change it once every 3 days", "Change your username");
                        dialog_to_make_the_user_enter_their_name.set_card_clicked_reply(new Dialog_to_make_the_user_enter_their_name.name_is_done() {
                            @Override
                            public void name_is_done() {
                                set_name_for_profile();
                                set_the_text_of_change_my_name_button();
                            }
                        });
                        dialog_to_make_the_user_enter_their_name.show(getParentFragmentManager(), "");
                    } else {
                        Toast.makeText(getContext(), "You can only change your name once every 3 days", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void set_the_text_of_change_my_name_button() {
        if (getView() != null) {
            Button button_to_change_name_in_settings = getView().findViewById(R.id.button_to_change_name_in_settings);
            if (Save_and_get.getInstance().get_this_boolean(getContext(), "name_online_for_post", "was_name_changed_before")) {
                button_to_change_name_in_settings.setText("Change my name");
            } else {
                button_to_change_name_in_settings.setText("Set my name");
            }
        }
    }

    private void check_the_gift_then_update_the_text() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        firebaseFirestore.setFirestoreSettings(settings);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            myEdit.putBoolean("premuim", false);
        } else {
            //if (sharedPreferences.getLong("last_checked", 0) < System.currentTimeMillis() - 86400000L || run_anyways) {
            firebaseFirestore.collection("gifts").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long time = document.getLong("time");
                            if (time > System.currentTimeMillis() - 86400000L) {
                                myEdit.putBoolean("premuim", true);
                                myEdit.putLong("premuim_until", time + 86400000L);
                                myEdit.commit();
                            } else {
                                myEdit.putBoolean("premuim", false);
                                myEdit.commit();
                            }
                        } else {
                            myEdit.putBoolean("premuim", false);
                            myEdit.commit();
                        }
                        set_the_premuim_in_text();
                    } else {
                        //fail
                    }
                }
            });
            //myEdit.putLong("last_checked", System.currentTimeMillis());
            //}
        }
    }

    private void listen_to_purchase_complete() {
        Payment_processer.getInstance().set_up_update_the_premuim_settings(new Payment_processer.update_the_premuim_settings() {
            @Override
            public void update_settings() {
                set_up_purchase_screen();
//                set_up_coins_in_firebase();
            }
        });
    }

    private void manage_subscription_button_listen() {
        if (getView() != null) {
            Button button_saying_the_manage_subscription_in_setting = getView().findViewById(R.id.button_saying_the_manage_subscription_in_setting);
            button_saying_the_manage_subscription_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        try {
                            if (Payment_processer.getInstance().is_sub_purchase_list_ready) {
                                mange_subscription_link();
                            } else {
                                Payment_processer.getInstance().listen_for_sub_list_ready(new Payment_processer.sub_purchase_ready_list() {
                                    @Override
                                    public void sub_list_is_ready() {
                                        mange_subscription_link();
                                    }
                                });
                            }

                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getContext(), "Can't open automatically, please goto the subscription center in the play store", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    private void mange_subscription_link() {
        if (Payment_processer.getInstance().sub_purchase_list.isEmpty()) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/account/subscriptions")));
        } else if (Payment_processer.getInstance().sub_purchase_list.get(0).getProducts().get(0).equals("new_monthly_subscription")) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/account/subscriptions?sku=new_monthly_subscription&package=" + getActivity().getPackageName())));
        } else if (Payment_processer.getInstance().sub_purchase_list.get(0).getProducts().get(0).equals("new_yearly_subscription")) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/account/subscriptions?sku=new_yearly_subscription&package=" + getActivity().getPackageName())));
        }
    }

    private void set_up_coins_in_firebase() {
        if (getView() != null && FirebaseAuth.getInstance().getUid() != null) {
            TextView text_view_saying_number_of_coins_in_setting = getView().findViewById(R.id.text_view_saying_number_of_coins_in_setting);
            ProgressBar progress_bar_to_show_that_coins_are_still_loading = getView().findViewById(R.id.progress_bar_to_show_that_coins_are_still_loading);
            final DocumentReference docIdRef = FirebaseFirestore.getInstance().collection("coins").document(FirebaseAuth.getInstance().getUid());
            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot snapshot = task.getResult();
                        int coins;
                        if (snapshot != null && snapshot.exists()) {
                            coins = (int) ((long) snapshot.get("coins"));
                        } else {
                            coins = 0;
                        }
                        text_view_saying_number_of_coins_in_setting.setText(String.valueOf(coins));
                        text_view_saying_number_of_coins_in_setting.setVisibility(View.VISIBLE);
                        progress_bar_to_show_that_coins_are_still_loading.setVisibility(View.GONE);
                        if(getView()!=null && getContext()!=null) {
                            ConstraintLayout constraint_layout_with_coin_image_and_coins = getView().findViewById(R.id.constraint_layout_with_coin_image_and_coins);
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(constraint_layout_with_coin_image_and_coins);
                            constraintSet.connect(R.id.coins_view_beside_the_numbers, ConstraintSet.END, R.id.text_view_saying_number_of_coins_in_setting, ConstraintSet.START, (int) convertDpToPixel(10, getContext()));
                            constraintSet.applyTo(constraint_layout_with_coin_image_and_coins);
                        }
                    } else {
                        if (getContext() != null) {
                            Toast toast = Toast.makeText(getContext(), "Cant load coins", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            });
        }
    }

    private void set_up_button_for_more_information_listen() {
        if (getView() != null) {
            Button button_to_request_more_billing_info_in_setting = getView().findViewById(R.id.button_to_request_more_billing_info_in_setting);
            button_to_request_more_billing_info_in_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String body = "Your Google Play Account will be charged after the free trial period ends. The monthly subscription renews automatically each month. The annual subscription renews automatically each year.\n\nSubscription will be auto-renewed within 24 hours before the current period ends. You can manage subscription & turn-off in Google Play Account Settings.";
                    Dialog_custom dialog_custom = new Dialog_custom("Subscription info.", body);
                    dialog_custom.show(getParentFragmentManager(), "dialog_custom");
                }
            });
        }
    }

    public void listen_to_coin_update() {
        if (firestore_listener == null) {
            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            firestore_listener = FirebaseFirestore.getInstance().collection("coins").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@com.google.firebase.database.annotations.Nullable DocumentSnapshot snapshot,
                                    @com.google.firebase.database.annotations.Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        return;
                    }
                    if (snapshot != null && snapshot.exists()) {
                        set_up_coins_in_firebase();
                    } else {

                    }
                }
            });
        }
    }

    private void listen_to_hide_upgrade_yearly() {
        Payment_processer.getInstance().set_up_hide_upgrade_yearly(new Payment_processer.hide_upgrade_yearly() {
            @Override
            public void hide_the_upgrade_yearly() {
                hide_upgrade_yearly_button();
            }
        });
    }

    private void listen_to_purchase_list_for_upgrade() {
        Payment_processer.getInstance().set_up_listen_for_purchase_for_hide_upgrade(new Payment_processer.sub_list_ready_for_hide_for_upgrade() {
            @Override
            public void hide_the_upgrade_after_list_is_ready() {
                if (!Payment_processer.getInstance().sub_purchase_list.isEmpty()) {
                    check_if_i_should_hide_upgrade_subscription(Payment_processer.getInstance().sub_purchase_list);
                }
            }
        });
    }

    private void make_the_offer_text_bold() {
        if (getView() != null) {
            TextView invite_your_friend_offer_to_get_premuim = getView().findViewById(R.id.invite_your_friend_offer_to_get_premuim);
            SpannableStringBuilder str = new SpannableStringBuilder("Limited time offer: Invite your friend to Easy Habits and both of you will get 6 months of premium for free. Get started by getting your referral code below, then make sure to enter the code by clicking \"enter referral code\" on your friend's phone ");
            str.setSpan(new ForegroundColorSpan(Color.parseColor("#DA142C")), 0, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            invite_your_friend_offer_to_get_premuim.setText(str);
        }
    }

    private void buy_coins_button_listen_under_profile() {
        if (getView() != null) {
            Button button_saying_buy_coins_in_profile_in_settings = getView().findViewById(R.id.button_saying_buy_coins_in_profile_in_settings);
            button_saying_buy_coins_in_profile_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buy_coins_button_clicked();
                }
            });
        }
    }

    private void buy_coins_button_clicked() {
        if (getActivity() != null) {
            Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.buy_more_coins_clicked, false);
            /*Give_coins give_coins = new Give_coins("settings");
            Settings_fragment old_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
            }*/
            Bottom_sheet_buy_coins bottom_sheet_buy_coins = new Bottom_sheet_buy_coins();
            bottom_sheet_buy_coins.show(getParentFragmentManager(),"buy coins");
        }
    }

    private void set_name_for_profile() {
        if (getView() != null && getActivity() != null) {
            TextView text_view_saying_name = getView().findViewById(R.id.text_view_saying_name);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "");
            text_view_saying_name.setText(name);
        }
    }

    private void start_fourteen_day_trial_button_listen() {
        if (getView() != null) {
            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);
            done_button_at_the_buttom_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "setting");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.yearly_clicked, false, bundle);
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "new_yearly_subscription", getContext(), 0, FirebaseAuth.getInstance().getUid());
                }
            });
        }
    }

    private void calculate_the_streak() {
        if (getContext() != null) {
            long last_login = Save_and_get.getInstance().get_this_long(getContext(), "track_last_login", "last_login", System.currentTimeMillis());
            if ((Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(last_login) >= TimeUnit.DAYS.toMillis(2))) {
                Save_and_get.getInstance().save_this(getContext(), 0, "track_last_login", "streak");
            } else {
                if (Simplify_the_time.return_time_in_midnight(last_login) < Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())) {
                    int old_streak = Save_and_get.getInstance().get_this_int(getContext(), "track_last_login", "streak", 1);
                    Save_and_get.getInstance().save_this(getContext(), old_streak + 1, "track_last_login", "streak");
                }
            }
            Save_and_get.getInstance().save_this(getContext(), System.currentTimeMillis(), "track_last_login", "last_login");
        }
    }

    private void set_the_streak() {
        if (getView() != null && getContext() != null) {
            TextView text_view_saying_the_streak_in_profile_in_settings = getView().findViewById(R.id.text_view_saying_the_streak_in_profile_in_settings);
            int streak = Save_and_get.getInstance().get_this_int(getContext(), "track_last_login", "streak", 1);
            text_view_saying_the_streak_in_profile_in_settings.setText(String.valueOf(streak));
        }
    }

    private void click_on_profile_picture_button_listen(){
        if(getView()!=null){
            Button profile_picture_in_settings = getView().findViewById(R.id.profile_picture_in_settings);
            profile_picture_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_profile_picture_was_clicked();
                }
            });
        }
    }

    private void change_profile_picture_listen(){
        if(getView()!=null){
            Button button_to_change_picture_in_settings = getView().findViewById(R.id.button_to_change_picture_in_settings);
            button_to_change_picture_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_profile_picture_was_clicked();
                }
            });
        }
    }

    private void change_profile_picture_was_clicked(){
        if(getView()!=null){
           /* Bottom_sheet_change_profile_picture bottom_sheet_change_profile_picture = new Bottom_sheet_change_profile_picture();
            bottom_sheet_change_profile_picture.show(getParentFragmentManager(),"Bottom sheet change profile picture");*/
            Change_profile_picture change_profile_picture = new Change_profile_picture();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, change_profile_picture, "change profile picture").show(change_profile_picture).commit();

        }
    }
}
