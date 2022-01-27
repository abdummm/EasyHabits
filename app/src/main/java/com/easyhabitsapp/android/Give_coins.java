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
    private BillingClient billingClient;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    private FirebaseFunctions firebaseFunctions;
    private String what_called_me;
    private boolean am_i_visble;

    public Give_coins() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
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
        am_i_visble= true;
        generate_the_views();
        button_listen();
        set_up_billing();
        back_arrow_listen();
    }

    private void generate_the_views() {
        if (getActivity() != null && getContext() != null && getView()!=null) {
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
                        constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int) convertDpToPixel(6,getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, R.id.bar_at_the_top_of_give_coins, ConstraintSet.BOTTOM, (int) convertDpToPixel(6,getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (i == 0) {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, list_of_views.get(list_of_views.size() - 2).getId(), ConstraintSet.END, (int) convertDpToPixel(6,getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, R.id.bar_at_the_top_of_give_coins, ConstraintSet.BOTTOM, (int) convertDpToPixel(6,getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (j == 0) {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int) convertDpToPixel(6,getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, list_of_views_first_row.get(i - 1).getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(6,getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else {
                        constraintSet.clone(constraintLayout);
                        constraintSet.constrainWidth(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.constrainHeight(view.getId(), (int) convertDpToPixel(30, getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.START, list_of_views.get(list_of_views.size() - 2).getId(), ConstraintSet.END, (int) convertDpToPixel(6,getContext()));
                        constraintSet.connect(view.getId(), ConstraintSet.TOP, list_of_views_first_row.get(i - 1).getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(6,getContext()));
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
            public void onPurchasesUpdated(@NotNull BillingResult billingResult, List<Purchase> purchases) {
                if (purchasesUpdatedListener != null) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                        handle_purchase(purchases);
                    } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.w("stupasdasd", String.valueOf(billingResult.getResponseCode()));
                    }
                }
            }
        };
        start_billing_connection(true);
    }

    private void start_billing_connection(boolean first_time) {
        if (first_time) {
            billingClient = set_up_clint();
        }
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                if (am_i_visble) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        set_the_prices();
                    } else {
                        Toast.makeText(getActivity(), "An error occurred. Please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                start_billing_connection(false);
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

    private void handle_purchase(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                if (purchase.getSku().equals("400_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 400 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(400);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy400coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };
                    billingClient.consumeAsync(consumeParams, listener);
                } else if (purchase.getSku().equals("1500_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 1500 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(1500);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy1500coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };

                    billingClient.consumeAsync(consumeParams, listener);
                } else if (purchase.getSku().equals("3600_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();

                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 3600 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(3600);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy3600coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };

                    billingClient.consumeAsync(consumeParams, listener);
                } else if (purchase.getSku().equals("8400_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();

                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 8400 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(8400);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy8400coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };
                    billingClient.consumeAsync(consumeParams, listener);
                } else if (purchase.getSku().equals("16000_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();

                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 16000 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(16000);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy16000coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };

                    billingClient.consumeAsync(consumeParams, listener);
                } else if (purchase.getSku().equals("40000_coins")) {
                    ConsumeParams consumeParams =
                            ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();

                    ConsumeResponseListener listener = new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                Toast.makeText(getActivity(), "Buying 40000 coins...", Toast.LENGTH_LONG).show();
                                Map<String, Object> data = new HashMap<>();
                                data.put("token", purchaseToken);
                                data.put("orderId", purchase.getOrderId());
                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(40000);
                                dialog_thank_user_for_purchase.setCancelable(false);
                                dialog_thank_user_for_purchase.show(getParentFragmentManager(),"");
                                firebaseFunctions = FirebaseFunctions.getInstance();
                                firebaseFunctions
                                        .getHttpsCallable("buy40000coins")
                                        .call(data)
                                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                                            @Override
                                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                                Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                                if(String.valueOf(task.getResult().getData()).equals("success")){
                                                    dialog_thank_user_for_purchase.loaded();
                                                } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                    Toast.makeText(getActivity(), "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
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
                    };

                    billingClient.consumeAsync(consumeParams, listener);
                }
            } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
                Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
            }
        }
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
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("400_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
            buy_second_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("1500_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
            buy_third_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("3600_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
            buy_fourth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("8400_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
            buy_fifth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("16000_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
            buy_sixth_thing_coins_buy_coins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("40000_coins"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
                        }
                    });
                }
            });
        }
    }

    private void set_the_prices() {
        if (getActivity() != null) {
            SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("400_coins", "1500_coins", "3600_coins", "8400_coins", "16000_coins", "40000_coins"))
                    .setType(BillingClient.SkuType.INAPP)
                    .build();
            billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<SkuDetails> list) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        Button buy_first_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_first_thing_coins_buy_coins);
                        Button buy_second_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_second_thing_coins_buy_coins);
                        Button buy_third_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_third_thing_coins_buy_coins);
                        Button buy_fourth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_fourth_thing_coins_buy_coins);
                        Button buy_fifth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_fifth_thing_coins_buy_coins);
                        Button buy_sixth_thing_coins_buy_coins = getActivity().findViewById(R.id.buy_sixth_thing_coins_buy_coins);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getSku().equals("400_coins")) {
                                buy_first_thing_coins_buy_coins.setText("400 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            } else if (list.get(i).getSku().equals("1500_coins")) {
                                buy_second_thing_coins_buy_coins.setText("1500 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            } else if (list.get(i).getSku().equals("3600_coins")) {
                                buy_third_thing_coins_buy_coins.setText("3600 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            } else if (list.get(i).getSku().equals("8400_coins")) {
                                buy_fourth_thing_coins_buy_coins.setText("8400 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            } else if (list.get(i).getSku().equals("16000_coins")) {
                                buy_fifth_thing_coins_buy_coins.setText("16000 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            } else if (list.get(i).getSku().equals("40000_coins")) {
                                buy_sixth_thing_coins_buy_coins.setText("40000 coins | ".concat(list.get(i).getPrice()).concat(" ").concat(list.get(i).getPriceCurrencyCode()));
                            }
                        }
                    }
                }
            });
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

    private void go_back(){
        if(what_called_me.equals("posts")){
            purchasesUpdatedListener = null;
            Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        } else if(what_called_me.equals("show")){
            purchasesUpdatedListener = null;
            Show_full_post new_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        } else if (what_called_me.equals("settings")){
            purchasesUpdatedListener = null;
            Settings_fragment new_fragment = (Settings_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("setting");
            if (new_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(new_fragment).remove(this).commit();
            }
        }
    }
    public String return_what_called_you(){
        return what_called_me;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        purchasesUpdatedListener = null;
    }
}