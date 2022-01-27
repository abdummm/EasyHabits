package com.easyhabitsapp.android;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Billing_library_help {
    private String token;
    private String order_id;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private BillingClient billingClient;
    private send_billign_result send_billign_result_listen;
    private Context context;

    public void set_up_billing_library(send_billign_result listen) {
        send_billign_result_listen = listen;
    }

    public interface send_billign_result {
        void send_result(String result);
    }

    public Billing_library_help(Context context){
        this.context = context;
    }
    private BillingClient set_up_clint() {
        return BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(purchasesUpdatedListener)
                .build();
    }

    private void start_billing(){
        purchasesUpdatedListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<Purchase> list) {

            }
        };
        billingClient = set_up_clint();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    List<Purchase> purhcases = billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList();
                    if (purhcases !=null && purhcases.size() > 0) {
                        token = purhcases.get(0).getPurchaseToken();
                        order_id = purhcases.get(0).getOrderId();
                        if(purhcases.get(0).getSku().equals("1_year_subscription")){
                            send_billign_result_listen.send_result("1_year");
                        } else if(purhcases.get(0).getSku().equals("1_month_subscription")){
                            send_billign_result_listen.send_result("1_month");
                        }
                    } else {
                        send_billign_result_listen.send_result("none");
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
    public void am_i_monthyl_or_yearly(){
        start_billing();
    }

    public String return_token(){
        return token;
    }

    public String return_order_id(){
        return order_id;
    }
}
