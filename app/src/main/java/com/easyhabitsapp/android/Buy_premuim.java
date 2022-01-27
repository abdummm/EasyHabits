package com.easyhabitsapp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buy_premuim extends Fragment {
    private String feature;
    private boolean this_is_not_full;
    private String fragment;
    private BillingClient billingClient;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private String monthly_price = "";
    private String yearly_price = "";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private show_the_activity show_the_activity_lsiten;
    private FirebaseFunctions firebaseFunctions;

    public Buy_premuim() {
        // Required empty public constructor
    }

    public Buy_premuim(String feature, boolean this_is_not_full, String fragment) {
        this.feature = feature;
        this.this_is_not_full = this_is_not_full;
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_premuim, container, false);
    }

    public void set_the_function(show_the_activity show_the_activity_lsiten) {
        this.show_the_activity_lsiten = show_the_activity_lsiten;
    }

    public interface show_the_activity {
        void show_the_activity_function();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        color_you_need_premium();
        seven_day_free_trial();
        privacy_policy_click();
        terms_and_conditions();
        set_up_billing();
        back_button_listen();
        set_up_recylce_view();
    }

    private void make_text_strike_through() {
        if (getView() != null) {
            TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
            text_saying_old_price.setPaintFlags(text_saying_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private void color_you_need_premium() {
        if (getView() != null) {
            TextView text_saying_you_need_premuim = getView().findViewById(R.id.text_saying_you_need_premuim);
            if (this_is_not_full) {
                text_saying_you_need_premuim.setText("You need premium to ".concat(feature));
                Spannable spannable = new SpannableString(text_saying_you_need_premuim.getText().toString());
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 20, text_saying_you_need_premuim.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_you_need_premuim.setText(spannable, TextView.BufferType.SPANNABLE);
            } else {
                text_saying_you_need_premuim.setText(feature);
            }
        }
    }

    private void seven_day_free_trial() {
        if (getView() != null) {
            TextView seven_days_free_trial_text_in_buy_premium = getView().findViewById(R.id.seven_days_free_trial_text_in_buy_premium);
            Spannable spannable = new SpannableString(seven_days_free_trial_text_in_buy_premium.getText().toString());
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            seven_days_free_trial_text_in_buy_premium.setText(spannable, TextView.BufferType.SPANNABLE);
        }
    }

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

    private void buy_monthly_button_listen() {
        if (getView() != null) {
            Button monthly_subscription_button_in_buy_premuim = getView().findViewById(R.id.monthly_subscription_button_in_buy_premuim);
            monthly_subscription_button_in_buy_premuim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_month_subscription"))
                            .setType(BillingClient.SkuType.SUBS)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(list.get(0))
                                        .build();
                                billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                            }
                        }
                    });
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
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_year_subscription"))
                            .setType(BillingClient.SkuType.SUBS)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(list.get(0))
                                        .build();
                                billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                            }
                        }
                    });
                }
            });
        }
    }

    private void set_up_billing() {
        acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                }
            }
        };

        purchasesUpdatedListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
                if (getActivity() != null) {
                    Buy_premuim buy_premium = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
                    if (purchasesUpdatedListener != null && purchases != null && buy_premium!=null&& buy_premium.isVisible()) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            handle_purchase(purchases);
                        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
        start_billing_connection(true, false);
    }

    private void start_billing_connection(boolean first_time, boolean special_mode) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (first_time) {
            billingClient = set_up_clint();
        }
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP,purchasesResponseListener);
                    if (special_mode) {
                        List<Purchase> purhcases = billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList();
                        if (purhcases != null && purhcases.size() > 0) {
                            myEdit.putBoolean("premuim_online", true);
                            myEdit.commit();
                            tell_the_user_if_we_found_something(true);
                        } else {
                            check_if_user_is_gifted(true);
                            myEdit.putBoolean("premuim_online", false);
                            myEdit.commit();
                        }
                    }
                    set_the_prices();
                    buy_monthly_button_listen();
                    buy_yearly_button_listen();
                } else {
                    Toast.makeText(getActivity(), "An error occurred. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                start_billing_connection(false, false);
            }
        });
    }

    private BillingClient set_up_clint() {
        BillingClient billingClient = BillingClient.newBuilder(getActivity())
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
        return billingClient;
    }

    private void set_the_prices() {
        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_year_subscription", "1_month_subscription"))
                .setType(BillingClient.SkuType.SUBS)
                .build();
        billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    TextView text_saying_price_per_month_in_buy_month = getView().findViewById(R.id.text_saying_price_per_month_in_buy_month);
                    TextView text_saying_price_per_year_in_buy_month = getView().findViewById(R.id.text_saying_price_per_year_in_buy_month);
                    TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
                    TextView text_saying_billed_year = getView().findViewById(R.id.text_saying_billed_year);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getSku().equals("1_month_subscription")) {
                            text_saying_price_per_month_in_buy_month.setText(list.get(i).getPrice().concat("/mo"));
                            text_saying_old_price.setText(list.get(i).getPrice().concat("/mo"));
                            monthly_price = list.get(i).getPrice();
                            make_text_strike_through();
                        } else if (list.get(i).getSku().equals("1_year_subscription")) {
                            Double price = Double.parseDouble(list.get(i).getPrice().replaceAll("[^0-9.]", "")) / 12d;
                            if (String.valueOf(price).length() >= 4) {
                                text_saying_price_per_year_in_buy_month.setText(String.valueOf(price).substring(0, 4).concat("/mo"));
                            } else {
                                text_saying_price_per_year_in_buy_month.setText(String.valueOf(price).concat("/mo"));
                            }
                            text_saying_billed_year.setText("Billed annually\n at ".concat(list.get(i).getPrice()));
                            yearly_price = list.get(i).getPrice();
                        }
                    }
                }
            }
        });
    }

    private void handle_purchase(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                if (purchase.getSku().equals("1_month_subscription")) {
                    if (!purchase.isAcknowledged()) {
                        set_buy_premuim_true();
                        AcknowledgePurchaseParams acknowledgePurchaseParams =
                                AcknowledgePurchaseParams.newBuilder()
                                        .setPurchaseToken(purchase.getPurchaseToken())
                                        .build();
                        if(am_i_sign_in_with_google()){
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month",false);
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
                                                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                                back_was_pressed();
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(getActivity(), "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();                                    }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        } else {
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month",true);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                        }
                    }
                } else if (purchase.getSku().equals("1_year_subscription")) {
                    if (!purchase.isAcknowledged()) {
                        set_buy_premuim_true();
                        AcknowledgePurchaseParams acknowledgePurchaseParams =
                                AcknowledgePurchaseParams.newBuilder()
                                        .setPurchaseToken(purchase.getPurchaseToken())
                                        .build();
                        if(am_i_sign_in_with_google()){
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year",false);
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
                                                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                                back_was_pressed();
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
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year",true);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(getParentFragmentManager(), "");
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
    }

    private void back_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_to_go_back_at_call_premium = getView().findViewById(R.id.button_to_go_back_at_call_premium);
            button_to_go_back_at_call_premium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    back_was_pressed();
                }
            });
        }
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

    /*private void purchase_button_listen() {
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

    private void set_up_recylce_view() {
        if (getView() != null) {
            ArrayList<Example_all_premuim_features> list = new ArrayList<>();
            list.add(new Example_all_premuim_features("500 coins/month or 7000 coins a year"));
            list.add(new Example_all_premuim_features("Access to premium charts and data about bad habits, good habits and mood"));
            list.add(new Example_all_premuim_features("The ability to write and save posts"));
            list.add(new Example_all_premuim_features("Unlimited daily chat sessions"));
            list.add(new Example_all_premuim_features("Unlimited number of journals and counters"));
            //list.add(new Example_all_premuim_features("The ability to whitelist apps before locking phone"));
            list.add(new Example_all_premuim_features("Access to premium charts and data about weight tracker"));
            RecyclerView recycle_view_to_show_the_premium_features = getView().findViewById(R.id.recycle_view_to_show_the_premium_features);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            Adapter_all_premium_features adapter = new Adapter_all_premium_features(list);
            recycle_view_to_show_the_premium_features.setLayoutManager(linearLayoutManager);
            recycle_view_to_show_the_premium_features.setAdapter(adapter);
            recycle_view_to_show_the_premium_features.setHasFixedSize(false);
            recycle_view_to_show_the_premium_features.setNestedScrollingEnabled(false);
        }
    }

    public String return_caller() {
        return fragment;
    }

    private void tell_the_user_if_we_found_something(boolean did_we_find_anyhting) {
        if (did_we_find_anyhting) {
            Toast.makeText(getActivity(), "restored purchases successfully", Toast.LENGTH_LONG).show();
            back_was_pressed();
        } else {
            Toast.makeText(getActivity(), "no purchases to restore", Toast.LENGTH_LONG).show();
        }
    }

    public void check_if_user_is_gifted(boolean run_anyways) {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        firebaseFirestore.setFirestoreSettings(settings);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", Context.MODE_PRIVATE);
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
    }

    private void back_was_pressed(){
        if (fragment.equals("home")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            home_fragment new_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if (fragment.equals("posts")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if (fragment.equals("show full post")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            Show_full_post new_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if (fragment.equals("chat")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            chat_fragment new_fragment = (chat_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("chat");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if (fragment.equals("good habits")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            habits_fragment new_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if (fragment.equals("activity")) {
            show_the_activity_lsiten.show_the_activity_function();
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
            }
        } else if(fragment.equals("view home")){
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            View_home_habit new_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else if(fragment.equals("mood tracker")){
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            mood_tracker new_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        }
    }
    public void go_back_to_activity(){
        show_the_activity_lsiten.show_the_activity_function();
        Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
        if (old_fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
        }
    }

    private void set_buy_premuim_true() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("premuim_online", true);
            myEdit.commit();
        }
    }

    private boolean am_i_sign_in_with_google(){
        if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }
    }
}