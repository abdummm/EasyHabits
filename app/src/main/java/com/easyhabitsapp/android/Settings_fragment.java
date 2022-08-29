package com.easyhabitsapp.android;


import static android.content.Context.MODE_PRIVATE;
import static com.android.billingclient.api.BillingFlowParams.ProrationMode.DEFERRED;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_fragment extends Fragment {
    private BillingClient billingClient;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private String monthly_price = "";
    private String yearly_price = "";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private boolean am_i_visible = false;
    private FirebaseFunctions firebaseFunctions;
    private String token;


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
        set_up_billing();
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
    }

    private void rate_app_button_listen() {
        if (getView() != null) {
            Button button_saying_rate_in_settings = getView().findViewById(R.id.button_saying_rate_in_settings);
            button_saying_rate_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
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
                    if (purchasesUpdatedListener != null && purchases != null && am_i_visible) {
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
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_user_gifted", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if (first_time) {
                billingClient = set_up_clint();
            }
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        // billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP,purchasesResponseListener);
                        hide_upgrade_subscription();
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
        } else {
            if (getContext() != null) {
                Toast.makeText(getContext(), "A problem happened please contact support", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BillingClient set_up_clint() {
        BillingClient billingClient = BillingClient.newBuilder(getActivity())
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
        return billingClient;
    }

    private void set_the_prices() {
        if (getView() != null) {
            TextView text_saying_price_per_month_in_buy_month = getView().findViewById(R.id.text_saying_price_per_month_in_buy_month);
            TextView text_saying_price_per_year_in_buy_month = getView().findViewById(R.id.text_saying_price_per_year_in_buy_month);
            TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
            TextView text_saying_billed_year = getView().findViewById(R.id.text_saying_billed_year);
            SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_year_subscription", "1_month_subscription"))
                    .setType(BillingClient.SkuType.SUBS)
                    .build();
            billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
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
    }

    private void handle_purchase(List<Purchase> purchases) {
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

    public void check_if_user_is_gifted(boolean run_anyways) {
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
    }

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
                    Give_coins give_coins = new Give_coins("settings");
                    Settings_fragment old_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, give_coins, "give_coins").show(give_coins).commit();
                    }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            am_i_visible = false;
        } else {
            am_i_visible = true;
            set_the_premuim_in_text();
        }
    }

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
            ConstraintLayout id_for_setting_did_not_buy_yet = getView().findViewById(R.id.id_for_setting_did_not_buy_yet);
            TextView buy_premium_button_in_setting = getView().findViewById(R.id.buy_premium_button_in_setting);

            ConstraintLayout setting_bought_yet_laready_layout = getView().findViewById(R.id.setting_bought_yet_laready_layout);
            TextView buy_premium_button_in_setting_in_already_bought = getView().findViewById(R.id.buy_premium_button_in_setting_in_already_bought);
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("is_user_gifted", MODE_PRIVATE);
            boolean online = sharedPreferences.getBoolean("premuim", false);
            boolean offline = sharedPreferences.getBoolean("premuim_online", false);
            long premuim_until = sharedPreferences.getLong("premuim_until", 0);
            if (offline) {
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
            }
        }
    }

    private void upgrade_subscription_button_listen() {
        if (getView() != null) {
            final Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            upgrade_susbcription_in_setting_premuim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (upgrade_susbcription_in_setting_premuim.getText().toString().equals("Downgrade to monthly billing")) {
                        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_month_subscription"))
                                .setType(BillingClient.SkuType.SUBS)
                                .build();
                        billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    BillingFlowParams purchaseParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(list.get(0))
                                            .setOldSku("1_year_subscription", token)
                                            .setReplaceSkusProrationMode(DEFERRED)
                                            .build();
                                    billingClient.launchBillingFlow(getActivity(), purchaseParams);
                                }
                            }
                        });
                    } else if (upgrade_susbcription_in_setting_premuim.getText().toString().equals("upgrade to yearly billing")) {
                        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1_year_subscription"))
                                .setType(BillingClient.SkuType.SUBS)
                                .build();
                        billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    BillingFlowParams purchaseParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(list.get(0))
                                            .setOldSku("1_month_subscription", token)
                                            .setReplaceSkusProrationMode(DEFERRED)
                                            .build();
                                    billingClient.launchBillingFlow(getActivity(), purchaseParams);
                                }
                            }
                        });
                    }
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

    private void hide_upgrade_subscription() {
        if (getView() != null) {
            Button upgrade_susbcription_in_setting_premuim = getView().findViewById(R.id.upgrade_susbcription_in_setting_premuim);
            List<Purchase> purhcases = billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList();
            if (purhcases != null && purhcases.size() > 0) {
                if (purhcases.get(0).getSku().contains("1_year_subscription")) {
                    //upgrade_susbcription_in_setting_premuim.setText("Downgrade to monthly billing");
                    upgrade_susbcription_in_setting_premuim.setVisibility(View.GONE);
                    token = purhcases.get(0).getPurchaseToken();
                } else if (purhcases.get(0).getSku().contains("1_month_subscription")) {
                    upgrade_susbcription_in_setting_premuim.setText("upgrade to yearly billing");
                    token = purhcases.get(0).getPurchaseToken();
                }
            }
        }
    }

    private void sign_in_setting() {
        if (getView() != null) {
            Button button_saying_sign_in_with_firebase_settings = getView().findViewById(R.id.button_saying_sign_in_with_firebase_settings);
            button_saying_sign_in_with_firebase_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(return_the_name().equals("no_name_found_135")){
                        Dialog_to_make_the_user_enter_their_name dialog_to_make_the_user_enter_their_name = new Dialog_to_make_the_user_enter_their_name();
                        dialog_to_make_the_user_enter_their_name.set_card_clicked_reply(new Dialog_to_make_the_user_enter_their_name.name_is_done() {
                            @Override
                            public void name_is_done() {
                                Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Settings_fragment.this, 254);
                                bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                            }
                        });
                        dialog_to_make_the_user_enter_their_name.show(getParentFragmentManager(),"");
                    } else {
                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Settings_fragment.this, 254);
                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                    }
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
            Button button_saying_rate_in_settings = getView().findViewById(R.id.button_saying_rate_in_settings);
            TextView text_saying_setting_in_setting_tab = getView().findViewById(R.id.text_saying_setting_in_setting_tab);
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
            }
        }
    }

    private void enter_invite_code_listen() {
        if (getView() != null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            button_saying_enter_invite_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_for_invite_card dialog_for_invite_card = new Dialog_for_invite_card("enter invite code",FirebaseAuth.getInstance().getCurrentUser().getUid());
                    dialog_for_invite_card.set_up_everything_is_ok(new Dialog_for_invite_card.everything_is_ok() {
                        @Override
                        public void everything_is_ok() {
                            button_saying_enter_invite_code.setVisibility(View.GONE);
                            dialog_for_invite_card.dismiss();
                            Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 6,true);
                            dialog_thank_user_for_purchase.show(getActivity().getSupportFragmentManager(), "");
                            check_the_gift_then_update_the_text();
                        }
                    });
                    dialog_for_invite_card.show(getParentFragmentManager(),"");
                }
            });
        }
    }

    private void get_invite_code_listen(){
        if(getView()!=null){
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            Button button_saying_get_my_invite_code = getView().findViewById(R.id.button_saying_get_my_invite_code);
            button_saying_get_my_invite_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_for_invite_card dialog_for_invite_card = new Dialog_for_invite_card("get your invite code",FirebaseAuth.getInstance().getCurrentUser().getUid());
                    dialog_for_invite_card.set_up_everything_is_ok(new Dialog_for_invite_card.everything_is_ok() {
                        @Override
                        public void everything_is_ok() {
                            button_saying_enter_invite_code.setVisibility(View.GONE);
                            dialog_for_invite_card.dismiss();
                            Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 6,true);
                            dialog_thank_user_for_purchase.show(getActivity().getSupportFragmentManager(), "");
                            check_the_gift_then_update_the_text();
                        }
                    });
                    dialog_for_invite_card.show(getParentFragmentManager(),"");
                }
            });
        }
    }

    private void hide_refer(){
        if(getView()!=null){
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            SharedPreferences sh = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
            if(sh.getBoolean("register",false)){
                button_saying_enter_invite_code.setVisibility(View.GONE);
            }
        }
    }

    private void check_refer_online() {
        if (getActivity() != null && getView()!=null) {
            Button button_saying_enter_invite_code = getView().findViewById(R.id.button_saying_enter_invite_code);
            SharedPreferences sh = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
            if (!sh.getBoolean("register",false)) {
                DocumentReference docRef = firebaseFirestore.collection("redeem_refer").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("did_i_register_my_code",MODE_PRIVATE);
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

    private void change_my_name_button_listen(){
        if(getView()!=null){
            Button button_to_change_name_in_settings = getView().findViewById(R.id.button_to_change_name_in_settings);
            button_to_change_name_in_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
                    String name = sharedPreferences.getString("name", "");
                    long last_attempt = sharedPreferences.getLong("last_changed",0);
                    if(name.equals("")){
                        Dialog_to_make_the_user_enter_their_name dialog_to_make_the_user_enter_their_name = new Dialog_to_make_the_user_enter_their_name();
                        dialog_to_make_the_user_enter_their_name.show(getParentFragmentManager(),"");
                    } else {
                        if((System.currentTimeMillis() - last_attempt) >= TimeUnit.DAYS.toMillis(3)){
                            Dialog_to_make_the_user_enter_their_name dialog_to_make_the_user_enter_their_name = new Dialog_to_make_the_user_enter_their_name("Note: You can only change it once every 3 days","Change your username");
                            dialog_to_make_the_user_enter_their_name.show(getParentFragmentManager(),"");
                        } else {
                            Toast.makeText(getContext(),"You can't change your name more than once every 3 days",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    private void set_the_text_of_change_my_name_button() {
        if (getView() != null) {
            Button button_to_change_name_in_settings = getView().findViewById(R.id.button_to_change_name_in_settings);
            if (return_the_name().equals("no_name_found_135")) {
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
                                myEdit.putLong("premuim_until", time+86400000L);
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
}
