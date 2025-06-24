package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ImmutableList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payment_processer extends Application {
    private static final Payment_processer instance = new Payment_processer();
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    private ConsumeResponseListener consumeResponseListener;
    private BillingClient billingClient;
    private List<ProductDetails> productDetailsList = new ArrayList<>();
    private List<ProductDetails> subsDetailsList = new ArrayList<>();
    //private in_app_prices_ready_bottom_sheet_give_coins in_app_prices_ready_listen_bottom_sheet_give_coins;
    //private in_app_prices_ready_give_coins in_app_prices_ready_give_coins;
    private sub_prices_ready sub_prices_ready_listen;
    private sub_prices_ready_2 sub_prices_ready_listen_2;
    private update_the_gift update_the_gift_listen;
    //public boolean are_in_app_prices_ready = false;
    public boolean are_sub_prices_ready = false;
    public List<Purchase> sub_purchase_list = new ArrayList<>();
    public boolean is_sub_purchase_list_ready = false;
    private sub_purchase_ready_list sub_purchase_ready_list_listen;
    private sub_purchase_ready_list_2 sub_purchase_ready_list_listen_2;
    private purchase_complete purchase_complete_listen;
    private ListenerRegistration firestore_listener;
    private boolean user_is_bought_from_play_store = false;
    private boolean user_is_bought_as_a_gift = false;
    private update_the_premuim_settings update_the_premuim_settings_listen;
    private boolean is_this_an_upgrade = false;
    private hide_upgrade_yearly hide_upgrade_yearly_listen;
    private sub_list_ready_for_hide_for_upgrade sub_list_ready_for_hide_for_upgrade_listen;
    private sub_purchase_ready_3 sub_purchase_ready_3_listener;
    private purchase_is_done_listen purchase_is_done_listen_listenr;


    /*public void listen_for_in_app_prices_bottom_sheet_give_coins(in_app_prices_ready_bottom_sheet_give_coins listen) {
        in_app_prices_ready_listen_bottom_sheet_give_coins = listen;
    }

    public interface in_app_prices_ready_bottom_sheet_give_coins {
        void listen_for_in_app_prices_bottom_sheet_give_coins();
    }

    public void listen_for_in_app_prices_give_coins(in_app_prices_give_coins listen) {
        in_app_prices_give_coins = listen;
    }

    public interface in_app_prices_ready_bottom_sheet_give_coins {
        void listen_for_in_app_prices_bottom_sheet_give_coins();
    }*/

    public void listen_for_sub(sub_prices_ready listen) {
        sub_prices_ready_listen = listen;
    }

    public interface sub_prices_ready {
        void sub_prices_are_ready();
    }

    public void listen_for_sub_2(sub_prices_ready_2 listen) {
        sub_prices_ready_listen_2 = listen;
    }

    public interface sub_prices_ready_2 {
        void sub_prices_are_ready_2();
    }

    public void listen_for_gift(update_the_gift listen) {
        update_the_gift_listen = listen;
    }

    public interface update_the_gift {
        void how_many_month(int how_many);
    }

    public void listen_for_sub_list_ready(sub_purchase_ready_list listen) {
        sub_purchase_ready_list_listen = listen;
    }

    public interface sub_purchase_ready_list {
        void sub_list_is_ready();
    }

    public void listen_for_sub_list_ready_2(sub_purchase_ready_list_2 listen) {
        sub_purchase_ready_list_listen_2 = listen;
    }

    public interface sub_purchase_ready_list_2 {
        void sub_list_is_ready_2();
    }

    public void set_up_purchase_completed(purchase_complete listen) {
        purchase_complete_listen = listen;
    }

    public interface purchase_complete {
        void purchase_is_complete();
    }

    public void set_up_update_the_premuim_settings(update_the_premuim_settings listen) {
        update_the_premuim_settings_listen = listen;
    }

    public interface update_the_premuim_settings {
        void update_settings();
    }

    public void set_up_hide_upgrade_yearly(hide_upgrade_yearly listen) {
        hide_upgrade_yearly_listen = listen;
    }

    public interface hide_upgrade_yearly {
        void hide_the_upgrade_yearly();
    }

    public void set_up_listen_for_purchase_for_hide_upgrade(sub_list_ready_for_hide_for_upgrade listen) {
        sub_list_ready_for_hide_for_upgrade_listen = listen;
    }

    public interface sub_list_ready_for_hide_for_upgrade {
        void hide_the_upgrade_after_list_is_ready();
    }

    public void set_up_purchase_list_ready_3(sub_purchase_ready_3 listen) {
        sub_purchase_ready_3_listener = listen;
    }

    public interface sub_purchase_ready_3 {
        void sub_purchase_is_ready();
    }

    public void set_up_purchase_is_donelisten(purchase_is_done_listen listen) {
        purchase_is_done_listen_listenr = listen;
    }

    public interface purchase_is_done_listen {
        void purchase_is_done();
    }

    // Private constructor prevents instantiation from other classes
    private Payment_processer() {
    }

    public static Payment_processer getInstance() {
        return instance;
    }

    public void init(Context context, Activity activity, FragmentManager supportFragmentManager) {
        set_up_billing(context, activity,supportFragmentManager);
        start_billing_connection(context);
        set_up_gift_listen(context);
    }

    private void set_up_billing(Context context, Activity activity,FragmentManager supportFragmentManager) {
        consumeResponseListener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {

            }
        };
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
                /*if (context != null) {
                    if (purchasesUpdatedListener != null && purchases != null) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            handle_purchase(purchases);
                        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                }*/
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    update_state_of_user();
                    for (Purchase purchase : purchases) {
                        handle_purchase(purchase, context, activity,supportFragmentManager);
                    }
                    check_if_this_is_an_upgrade(context, purchases,supportFragmentManager);
                }
            }
        };

        billingClient = BillingClient.newBuilder(context)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
    }

    private void handle_purchase(Purchase purchase, Context context, Activity activity,FragmentManager supportFragmentManager) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
            if (purchase.getQuantity() == 1 && (purchase.getProducts().get(0).equals("new_monthly_subscription") || purchase.getProducts().get(0).equals("new_yearly_subscription"))) {
                /*activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        set_buy_premuim_true(context);
                        if (purchase.getProducts().get(0).equals("new_monthly_subscription")) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month", false);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(supportFragmentManager, "");
                            FirebaseFunctions.getInstance()
                                    .getHttpsCallable("monthly_sub_i_buy")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_subscribe_to_premuim.loaded();
                                                //set_the_premuim_in_text();
                                                set_buy_premuim_true(context);
                                                acknowledge_purchase(purchase);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(context, "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {

                                        }
                                    });
                        } else if (purchase.getProducts().get(0).equals("new_yearly_subscription")) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchase.getPurchaseToken());
                            data.put("orderId", purchase.getOrderId());
                            data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year", false);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(supportFragmentManager, "");
                            FirebaseFunctions.getInstance()
                                    .getHttpsCallable("yearly_sub_i_buy")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                dialog_subscribe_to_premuim.loaded();
                                                //set_the_premuim_in_text();
                                                set_buy_premuim_true(context);
                                                acknowledge_purchase(purchase);
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(context, "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {

                                        }
                                    });
                        }
                    }
                });*/
                sub_purchase_list.add(purchase);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (is_this_an_upgrade) {
                            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("upgrade", true);
                            dialog_subscribe_to_premuim.setCancelable(false);
                            dialog_subscribe_to_premuim.show(supportFragmentManager, "");
                            upgrade_to_yearly(context, purchase.getPurchaseToken());
                        } else {
                            if (purchase.getProducts().get(0).equals("new_monthly_subscription")) {
                                Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month", false);
                                dialog_subscribe_to_premuim.setCancelable(false);
                                dialog_subscribe_to_premuim.show(supportFragmentManager, "");
                                listen_to_subscribe_acknowledge(dialog_subscribe_to_premuim, purchase.getAccountIdentifiers().getObfuscatedAccountId(), context);
                            } else if (purchase.getProducts().get(0).equals("new_yearly_subscription")) {
                                Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year", false);
                                dialog_subscribe_to_premuim.setCancelable(false);
                                dialog_subscribe_to_premuim.show(supportFragmentManager, "");
                                listen_to_subscribe_acknowledge(dialog_subscribe_to_premuim, purchase.getAccountIdentifiers().getObfuscatedAccountId(), context);
                            }
                        }
                    }
                });
            } else {
                for (int i = 0; i < purchase.getProducts().size(); i++) {
                    if (purchase.getProducts().get(i).equals("400_coins")) {
                        Toast.makeText(context, "Buying 400 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(400);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager,"");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy400coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("1500_coins")) {
                        Toast.makeText(context, "Buying 1500 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(1500);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy1500coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("3600_coins")) {
                        Toast.makeText(context, "Buying 3600 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(3600);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy3600coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("8400_coins")) {
                        Toast.makeText(context, "Buying 8400 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(8400);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy8400coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("16000_coins")) {
                        Toast.makeText(context, "Buying 16000 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(16000);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy16000coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("40000_coins")) {
                        Toast.makeText(context, "Buying 40000 coins...", Toast.LENGTH_LONG).show();
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(40000);
                        dialog_thank_user_for_purchase.setCancelable(false);
                        dialog_thank_user_for_purchase.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("buy40000coins")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_thank_user_for_purchase.loaded();
                                            consume_purchase(purchase);
                                            if(purchase_is_done_listen_listenr!=null){
                                                purchase_is_done_listen_listenr.purchase_is_done();
                                            }
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("1_month_premium_gift")) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_gift_months dialog_gift_months = new Dialog_gift_months(1);
                        dialog_gift_months.setCancelable(false);
                        dialog_gift_months.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("purchaseValidationAndroid")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_gift_months.loaded();
                                            update_the_gift_listen.how_many_month(1);
                                            consume_purchase(purchase);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("3_month_premium_gift")) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_gift_months dialog_gift_months = new Dialog_gift_months(3);
                        dialog_gift_months.setCancelable(false);
                        dialog_gift_months.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("purchaseValidationAndroid_3_month")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_gift_months.loaded();
                                            update_the_gift_listen.how_many_month(3);
                                            consume_purchase(purchase);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("6_month_premium_gift")) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_gift_months dialog_gift_months = new Dialog_gift_months(6);
                        dialog_gift_months.setCancelable(false);
                        dialog_gift_months.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("purchaseValidationAndroid_6_month")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_gift_months.loaded();
                                            update_the_gift_listen.how_many_month(6);
                                            consume_purchase(purchase);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    } else if (purchase.getProducts().get(i).equals("12_month_premium_gift")) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_gift_months dialog_gift_months = new Dialog_gift_months(12);
                        dialog_gift_months.setCancelable(false);
                        dialog_gift_months.show(supportFragmentManager, "");
                     
                        FirebaseFunctions.getInstance()
                                .getHttpsCallable("purchaseValidationAndroid_12_month")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_gift_months.loaded();
                                            update_the_gift_listen.how_many_month(12);
                                            consume_purchase(purchase);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                    }
                                });
                    }
                }
            }
        }
    }

    public void start_billing_connection(Context context) {
            /*SharedPreferences sharedPreferences = context.getSharedPreferences("is_user_gifted", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();*/
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP,purchasesResponseListener);
                        /*hide_upgrade_subscription();
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
                        buy_yearly_button_listen();*/
                    billing_is_set_up(context);
                } else {
                    Toast.makeText(context, "An error occurred. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                start_billing_connection(context);
            }
        });
    }

    private void billing_is_set_up(Context context) {
        add_all_products_to_list(context);
        add_all_subs_to_list(context);
        return_list_of_purchases_subs(context);
        return_list_of_purchases_in_app(context);
        //set_up_bought_from_store();
        //update_state_of_user();
    }

    private void add_all_products_to_list(Context context) {
        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(
                                ImmutableList.of(
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("400_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("1500_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("3600_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("8400_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("16000_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("40000_coins")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("1_month_premium_gift")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("3_month_premium_gift")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("6_month_premium_gift")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("12_month_premium_gift")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build()
                                ))
                        .build();

        billingClient.queryProductDetailsAsync(
                queryProductDetailsParams,
                new ProductDetailsResponseListener() {
                    public void onProductDetailsResponse(BillingResult billingResult,
                                                         List<ProductDetails> productDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            instance.productDetailsList.addAll(productDetailsList);
                            /*are_in_app_prices_ready = true;
                            if (in_app_prices_ready_listen_bottom_sheet_give_coins != null) {
                                in_app_prices_ready_listen_bottom_sheet_give_coins.in_app_prices_are_ready();
                            }*/
                        } else {
                            Toast.makeText(context, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void add_all_subs_to_list(Context context) {
        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(
                                ImmutableList.of(
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("new_monthly_subscription")
                                                .setProductType(BillingClient.ProductType.SUBS)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("new_yearly_subscription")
                                                .setProductType(BillingClient.ProductType.SUBS)
                                                .build()
                                ))
                        .build();

        billingClient.queryProductDetailsAsync(
                queryProductDetailsParams,
                new ProductDetailsResponseListener() {
                    public void onProductDetailsResponse(BillingResult billingResult,
                                                         List<ProductDetails> productDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            subsDetailsList.addAll(productDetailsList);
                            are_sub_prices_ready = true;
                            if (sub_prices_ready_listen != null) {
                                sub_prices_ready_listen.sub_prices_are_ready();
                            }
                            if (sub_prices_ready_listen_2 != null) {
                                sub_prices_ready_listen_2.sub_prices_are_ready_2();
                            }
                        } else {
                            Toast.makeText(context, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public String get_price_in_app(String product_id) {
        for (int i = 0; i < productDetailsList.size(); i++) {
            if (productDetailsList.get(i).getProductId().equals(product_id)) {
                return productDetailsList.get(i).getOneTimePurchaseOfferDetails().getFormattedPrice();
            }
        }
        return "";
    }

    public String get_price_sub(String product_id, int offer) {
        for (int i = 0; i < subsDetailsList.size(); i++) {
            if (subsDetailsList.get(i).getProductId().equals(product_id)) {
                int list_size = subsDetailsList.get(i).getSubscriptionOfferDetails().get(offer).getPricingPhases().getPricingPhaseList().size();
                return subsDetailsList.get(i).getSubscriptionOfferDetails().get(offer).getPricingPhases().getPricingPhaseList().get(list_size - 1).getFormattedPrice();
            }
        }
        return "";
    }

    public long get_prices_sub_micros(String product_id, int offer) {
        for (int i = 0; i < subsDetailsList.size(); i++) {
            if (subsDetailsList.get(i).getProductId().equals(product_id)) {
                int list_size = subsDetailsList.get(i).getSubscriptionOfferDetails().get(offer).getPricingPhases().getPricingPhaseList().size();
                return subsDetailsList.get(i).getSubscriptionOfferDetails().get(offer).getPricingPhases().getPricingPhaseList().get(list_size - 1).getPriceAmountMicros();
            }
        }
        return 0;
    }

    public void launch_product_flow(Activity activity, String product_id, Context context, int offer, String userId) {
        ProductDetails productDetails = return_offer_details(product_id);
        if (productDetails != null) {
            ImmutableList productDetailsParamsList =
                    ImmutableList.of(
                            BillingFlowParams.ProductDetailsParams.newBuilder()
                                    // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                                    .setProductDetails(productDetails)
                                    // to get an offer token, call ProductDetails.getSubscriptionOfferDetails()
                                    // for a list of offers that are available to the user
                                    .setOfferToken(productDetails.getSubscriptionOfferDetails().get(offer).getOfferToken())
                                    .build()
                    );

            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .setObfuscatedAccountId(userId)
                    .build();

// Launch the billing flow
            BillingResult billingResult = billingClient.launchBillingFlow(activity, billingFlowParams);
            is_this_an_upgrade = false;
        } else {
            Toast.makeText(context, "An error happened, please try again later", Toast.LENGTH_SHORT).show();
        }
    }

    public void launch_product_flow(Activity activity, String product_id, Context context, String userId) {
        ProductDetails productDetails = return_offer_details(product_id);
        if (productDetails != null) {
            ImmutableList productDetailsParamsList =
                    ImmutableList.of(
                            BillingFlowParams.ProductDetailsParams.newBuilder()
                                    // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                                    .setProductDetails(productDetails)
                                    .build()
                    );

            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .setObfuscatedAccountId(userId)
                    .build();

// Launch the billing flow
            /*BillingResult billingResult = */
            billingClient.launchBillingFlow(activity, billingFlowParams);
        } else {
            Toast.makeText(context, "An error happened, please try again later", Toast.LENGTH_SHORT).show();
        }
    }

    private ProductDetails return_offer_details(String product_id) {
        for (int i = 0; i < subsDetailsList.size(); i++) {
            if (subsDetailsList.get(i).getProductId().equals(product_id)) {
                return subsDetailsList.get(i);
            }
        }
        for (int i = 0; i < productDetailsList.size(); i++) {
            if (productDetailsList.get(i).getProductId().equals(product_id)) {
                return productDetailsList.get(i);
            }
        }
        return null;
    }

/*    private void set_buy_premuim_true(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("is_user_gifted", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean("premuim_online", true);
        myEdit.commit();
    }*/

    private void return_list_of_purchases_subs(Context context) {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build(),
                new PurchasesResponseListener() {
                    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> purchases) {
                        sub_purchase_list.addAll(purchases);
                        is_sub_purchase_list_ready = true;
                        if(sub_purchase_list.size()>0) {
                            user_is_bought_from_play_store = true;
                        }
                        add_premuim_to_the_new_account(context);
                        if (sub_purchase_ready_list_listen != null) {
                            sub_purchase_ready_list_listen.sub_list_is_ready();
                        }
                        if (sub_purchase_ready_list_listen_2 != null) {
                            sub_purchase_ready_list_listen_2.sub_list_is_ready_2();
                        }
                        if(sub_purchase_ready_3_listener !=null){
                            sub_purchase_ready_3_listener.sub_purchase_is_ready();
                        }
                        if (sub_list_ready_for_hide_for_upgrade_listen != null) {
                            sub_list_ready_for_hide_for_upgrade_listen.hide_the_upgrade_after_list_is_ready();
                        }
                    }
                });
    }

    private void return_list_of_purchases_in_app(Context context) {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                new PurchasesResponseListener() {
                    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> purchases) {
                        check_old_purchases_in_app(purchases, context);
                    }
                });
    }



   /* private void check_old_purchases_subs(List<Purchase> purchases,Context context) {
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchases.get(i).isAcknowledged()) {
                check_if_purchase_is_old(purchases.get(i), context);
            }
        }
    }*/

    private void check_old_purchases_in_app(List<Purchase> purchases, Context context) {
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchases.get(i).isAcknowledged()) {
                check_if_purchase_is_old(purchases.get(i), context);
            }
        }
    }

    private void consume_purchase(Purchase purchase) {
        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();
        billingClient.consumeAsync(consumeParams, consumeResponseListener);
    }

/*    private void acknowledge_purchase(Purchase purchase){
        AcknowledgePurchaseParams acknowledgePurchaseParams =
                AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();
        billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
    }*/

    private void check_if_purchase_is_old(Purchase purchase, Context context) {
        FirebaseFirestore.getInstance().collection("old_purchase").whereEqualTo("purchase_token", purchase.getPurchaseToken()).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    handle_purchase(purchase, context);
                } else {
                        /*if (purchase.getQuantity() == 1 && (purchase.getProducts().get(0).equals("new_monthly_subscription") || purchase.getProducts().get(0).equals("new_yearly_subscription"))) {
                            acknowledge_purchase(purchase);
                        } else {
                            consume_purchase(purchase);
                        }*/
                    consume_purchase(purchase);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                handle_purchase(purchase, context);
            }
        });
    }

    private void handle_purchase(Purchase purchase, Context context) {
        for (int i = 0; i < purchase.getProducts().size(); i++) {
            if (purchase.getProducts().get(i).equals("400_coins")) {
                Toast.makeText(context, "Buying 400 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
               /* Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(400);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy400coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("1500_coins")) {
                Toast.makeText(context, "Buying 1500 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(1500);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy1500coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("3600_coins")) {
                Toast.makeText(context, "Buying 3600 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(3600);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy3600coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("8400_coins")) {
                Toast.makeText(context, "Buying 8400 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(8400);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy8400coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("16000_coins")) {
                Toast.makeText(context, "Buying 16000 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
            /*    Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(16000);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy16000coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("40000_coins")) {
                Toast.makeText(context, "Buying 40000 coins...", Toast.LENGTH_LONG).show();
                Map<String, Object> data = new HashMap<>();
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
               /* Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(40000);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("buy40000coins")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Buying failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("1_month_premium_gift")) {
                Map<String, Object> data = new HashMap<>();
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 1);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("purchaseValidationAndroid")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    update_the_gift_listen.how_many_month(1);
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("3_month_premium_gift")) {
                Map<String, Object> data = new HashMap<>();
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 3);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("purchaseValidationAndroid_3_month")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    update_the_gift_listen.how_many_month(3);
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("6_month_premium_gift")) {
                Map<String, Object> data = new HashMap<>();
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 6);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("purchaseValidationAndroid_6_month")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    update_the_gift_listen.how_many_month(6);
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            } else if (purchase.getProducts().get(i).equals("12_month_premium_gift")) {
                Map<String, Object> data = new HashMap<>();
                data.put("userId", purchase.getAccountIdentifiers().getObfuscatedAccountId());
                data.put("token", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
                /*Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 12);
                dialog_thank_user_for_purchase.setCancelable(false);
                dialog_thank_user_for_purchase.show(supportFragmentManager, "");*/
             
                FirebaseFunctions.getInstance()
                        .getHttpsCallable("purchaseValidationAndroid_12_month")
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                if (String.valueOf(task.getResult().getData()).equals("success")) {
//                                    dialog_thank_user_for_purchase.loaded();
                                    update_the_gift_listen.how_many_month(12);
                                    consume_purchase(purchase);
                                } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                    Toast.makeText(context, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                }
                                return "result";
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
            }
        }
    }

    private void listen_to_subscribe_acknowledge(Dialog_subscribe_to_premuim dialog_subscribe_to_premuim, String user_id, Context context) {
        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("gifts").document(user_id);
        firestore_listener = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    remove_the_subscription_screen(context, dialog_subscribe_to_premuim);
                } else {

                }
            }
        });
    }

    private void listen_to_upgrade_acknow(Dialog_subscribe_to_premuim dialog_subscribe_to_premuim, String user_id, Context context) {
        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("gifts").document(user_id);
        firestore_listener = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    remove_the_subscription_screen(context, dialog_subscribe_to_premuim);
                } else {

                }
            }
        });
    }

    private void remove_the_subscription_screen(Context context, Dialog_subscribe_to_premuim dialog_subscribe_to_premuim) {
        dialog_subscribe_to_premuim.loaded();
        //set_the_premuim_in_text();
        //set_buy_premuim_true(context);
        firestore_listener.remove();
        if (purchase_complete_listen != null) {
            purchase_complete_listen.purchase_is_complete();
        }
        if (update_the_premuim_settings_listen != null) {
            update_the_premuim_settings_listen.update_settings();
        }
    }

    /*private void set_up_bought_from_store(){
        if(is_sub_purchase_list_ready){
            if(sub_purchase_list.isEmpty()){
                user_is_bought_from_play_store = false;
            } else {
                user_is_bought_from_play_store = true;
            }
        } else {
            listen_for_sub_list_ready(new sub_purchase_ready_list() {
                @Override
                public void sub_list_is_ready() {
                    if(sub_purchase_list.isEmpty()){
                        user_is_bought_from_play_store = false;
                    } else {
                        user_is_bought_from_play_store = true;
                    }
                }
            });
        }
    }*/

    public void set_up_gift_listen(Context context) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
            firebaseFirestore.setFirestoreSettings(settings);
            firebaseFirestore.collection("gifts").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long time = document.getLong("time");
                            if (time > System.currentTimeMillis() - 86400000L) {
                                user_is_bought_as_a_gift = true;
                            } else {
                                user_is_bought_as_a_gift = false;
                            }
                        } else {
                            user_is_bought_as_a_gift = false;
                        }
                    } else {
                        Toast.makeText(context, "A problem happened please try again later", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public boolean state_of_the_user() {
        if (user_is_bought_from_play_store || user_is_bought_as_a_gift) {
            return true;
        } else {
            return false;
        }
    }

    private void update_state_of_user() {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build(),
                new PurchasesResponseListener() {
                    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> purchases) {
                        sub_purchase_list.addAll(purchases);
                        user_is_bought_from_play_store = true;
                        //update_the_premuim_settings_listen.update_settings();
                    }
                });
    }

    private void add_premuim_to_the_new_account(Context context) {
        if (!sub_purchase_list.isEmpty()) {
            check_if_user_is_in_premuim(context);
        }
    }

    private void check_if_user_is_in_premuim(Context context) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
            firebaseFirestore.setFirestoreSettings(settings);
            firebaseFirestore.collection("gifts").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long time = document.getLong("time");
                            if (time > System.currentTimeMillis() - 86400000L) {
                                // do nothing
                            } else {
                                add_new_account_to_premuim(context);
                            }
                        } else {
                            add_new_account_to_premuim(context);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "A problem happened please try again later", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void add_new_account_to_premuim(Context context) {
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < sub_purchase_list.size(); i++) {
            Purchase purchase = sub_purchase_list.get(i);
            if (sub_purchase_list.get(i).getProducts().get(0).equals("new_monthly_subscription")) {
                data.put("purchaseToken", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
            } else if (sub_purchase_list.get(i).getProducts().get(0).equals("new_yearly_subscription")) {
                data.put("purchaseToken", purchase.getPurchaseToken());
                data.put("orderId", purchase.getOrderId());
            }
            FirebaseFunctions.getInstance()
                    .getHttpsCallable("sync_purchase_with_new_account")
                    .call(data)
                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                        @Override
                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                            if (String.valueOf(task.getResult().getData()).equals("success")) {

                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {

                            } else if (String.valueOf(task.getResult().getData()).equals("too_many_change_attempts")) {
                                /*Dialog_custom dialog_custom = new Dialog_custom("Can't sync account","It appears that there has been to many sign ups using the same account. To prevent fraud, we are not able to sync premium features in your account right now and we will try again in 24 hours. If you feel this is a mistake, please don't hesitate to contact us ;)",16,false);
                                dialog_custom.show(((FragmentActivity) context).getSupportFragmentManager(), "tag");*/
                                if (Save_and_get.getInstance().get_this_long(context, "dont_remind_me_24h_too_many_sign_ups", "dont_remind_me_24h_too_many_sign_ups",0) < System.currentTimeMillis()-86400000) {
                                    Dialog_to_tell_user_too_many_sign_ups dialog_to_tell_user_too_many_sign_ups = new Dialog_to_tell_user_too_many_sign_ups();
                                    dialog_to_tell_user_too_many_sign_ups.show(((FragmentActivity) context).getSupportFragmentManager(), "dialog_to_tell_user_too_many_sign_ups");
                                }
                            }
                            return "result";
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {

                        }
                    });
        }
    }

    public void upgrade_to_yearly_billing(Activity activity, String product_id, Context context, int offer, String userId) {
        if (!sub_purchase_list.isEmpty()) {
            is_this_an_upgrade = true;
            ProductDetails productDetails = return_offer_details(product_id);
            ImmutableList productDetailsParamsList = ImmutableList.of(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                            // fetched via queryProductDetailsAsync
                            .setProductDetails(productDetails)
                            .setOfferToken(productDetails.getSubscriptionOfferDetails().get(offer).getOfferToken())
                            // offerToken can be found in
                            // ProductDetails=>SubscriptionOfferDetails
                            .build());
            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .setObfuscatedAccountId(userId)
                    .setSubscriptionUpdateParams(
                            BillingFlowParams.SubscriptionUpdateParams.newBuilder()
                                    .setReplaceProrationMode(BillingFlowParams.ProrationMode.DEFERRED)
                                    .setOldPurchaseToken(return_old_purchase_token())
                                    .build())
                    .build();
            BillingResult billingResult = billingClient.launchBillingFlow(activity, billingFlowParams);
        } else {
            Toast.makeText(context, "A problem happened, please try again later", Toast.LENGTH_SHORT).show();
        }
    }

    private String return_old_purchase_token() {
        for (int i = 0; i < sub_purchase_list.size(); i++) {
            if (sub_purchase_list.get(i).getProducts().get(0).equals("new_monthly_subscription")) {
                return sub_purchase_list.get(i).getPurchaseToken();
            }
        }
        return sub_purchase_list.get(0).getPurchaseToken();
    }

    private void upgrade_to_yearly(Context context, String purchase_token) {
        Map<String, Object> map = new HashMap<>();
        map.put("purchaseToken", purchase_token);
        FirebaseFunctions firebaseFunctions = FirebaseFunctions.getInstance();
        firebaseFunctions
                .getHttpsCallable("update_deffered_or_yearly")
                .call(map)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        if (String.valueOf(task.getResult().getData()).equals("error")) {
                            Toast toast = Toast.makeText(context, "An error happened, please contact support", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        return "result";
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast toast = Toast.makeText(context, "An error happened, please contact support", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
    }

    private void check_if_this_is_an_upgrade(Context context, List<Purchase> purchases,FragmentManager supportFragmentManager) {
        if (is_this_an_upgrade && purchases.size() > 0 && purchases.get(0).getProducts().get(0).equals("new_monthly_subscription")) {
            Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("upgrade", true);
            dialog_subscribe_to_premuim.setCancelable(false);
            dialog_subscribe_to_premuim.show(supportFragmentManager, "");
            upgrade_to_yearly(context, purchases.get(0).getPurchaseToken());
            if (hide_upgrade_yearly_listen != null) {
                hide_upgrade_yearly_listen.hide_the_upgrade_yearly();
            }
        }
    }
}
