package com.easyhabitsapp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.functions.FirebaseFunctions;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class Buy_premuim extends Fragment {
    private String feature;
    private boolean this_is_not_full;
    private Fragment fragment;
    private String monthly_price = "";
    private String yearly_price = "";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private show_the_activity show_the_activity_lsiten;
    private FirebaseFunctions firebaseFunctions;
    private Handler change_the_premuim_features = new Handler();
    private ArrayList<Example_item_buy_premuim_features> premuim_features = new ArrayList<>();
    private String last_color = "";
    private long last_swipe_premuim_features = 0;
    private int previousState;
    private boolean was_the_state_set_before = false;

    public Buy_premuim() {
        // Required empty public constructor
    }

    public Buy_premuim(String feature, boolean this_is_not_full, Fragment fragment) {
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
        //seven_day_free_trial();
        privacy_policy_click();
        terms_and_conditions();
        back_button_listen();
//        set_up_recylce_view();
        check_if_price_is_ready();
        buy_monthly_button_listen();
        buy_yearly_button_listen();
        close_the_fragment();
        set_up_view_pager();
        set_indicator();
        //change_the_premuim_features();
        set_dot_color();
        listen_to_view_page_listen();
        listen_to_start_14_days_free_trial();
        set_the_progress_bar();
        set_the_fade_in();
        start_changng_the_habits_after_animation();
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

    /*private void seven_day_free_trial() {
        if (getView() != null) {
            TextView seven_days_free_trial_text_in_buy_premium = getView().findViewById(R.id.seven_days_free_trial_text_in_buy_premium);
            Spannable spannable = new SpannableString(seven_days_free_trial_text_in_buy_premium.getText().toString());
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            seven_days_free_trial_text_in_buy_premium.setText(spannable, TextView.BufferType.SPANNABLE);
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
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "setting");
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.yearly_clicked, false, bundle);
                    Payment_processer.getInstance().launch_product_flow(getActivity(), "new_yearly_subscription", getContext(), 0, FirebaseAuth.getInstance().getUid());
                }
            });
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

    /*private void set_up_recylce_view() {
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
    }*/

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

    public void back_was_pressed() {
        if (getActivity() != null) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(fragment).commit();
            }
        }
        /*if (fragment.equals("home")) {
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
        } else if (fragment.equals("view home")) {
            if(getArguments()!=null){
                String tag = (String) getArguments().get("tag");
                Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
                View_home_habit new_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag(tag);
                if (old_fragment != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                }
            } else {
                Toast.makeText(getActivity(),"A problem happened, please try again later",Toast.LENGTH_SHORT).show();
            }
        } else if (fragment.equals("mood tracker")) {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            Mood_tracker new_fragment = (Mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        } else {
            Buy_premuim old_fragment = (Buy_premuim) getActivity().getSupportFragmentManager().findFragmentByTag("buy premium");
            Mood_tracker new_fragment = (Mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        }*/
    }

    public void go_back_to_activity() {
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

    private boolean am_i_sign_in_with_google() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }
    }

    private void check_if_price_is_ready() {
        if (getView() != null) {
            if (Payment_processer.getInstance().are_sub_prices_ready) {
                set_prices();
            } else {
                Payment_processer.getInstance().listen_for_sub(new Payment_processer.sub_prices_ready() {
                    @Override
                    public void sub_prices_are_ready() {
                        set_prices();
                    }
                });
            }
        }
    }

    private void set_prices() {
        if (getView() != null) {
            TextView text_saying_price_per_month_in_buy_month = getView().findViewById(R.id.text_saying_price_per_month_in_buy_month);
            TextView text_saying_price_per_year_in_buy_month = getView().findViewById(R.id.text_saying_price_per_year_in_buy_month);
            TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
            TextView text_saying_billed_year = getView().findViewById(R.id.text_saying_billed_year);
            text_saying_price_per_month_in_buy_month.setText(Payment_processer.getInstance().get_price_sub("new_monthly_subscription", 0).concat("/mo"));
            double old_price = (Payment_processer.getInstance().get_prices_sub_micros("new_yearly_subscription", 0) / 12D) / 1000000D;
            old_price = ((int) (old_price * 100)) / 100.00;
            String old_price_string = String.format("%.2f", old_price);
            text_saying_price_per_year_in_buy_month.setText(old_price_string.concat("/year"));
            text_saying_old_price.setText(Payment_processer.getInstance().get_price_sub("new_monthly_subscription", 0).concat("/mo"));
            text_saying_old_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            text_saying_billed_year.setText("billed annually\n at ".concat(Payment_processer.getInstance().get_price_sub("new_yearly_subscription", 0)));
        }
    }

    private void close_the_fragment() {
        Payment_processer.getInstance().set_up_purchase_completed(new Payment_processer.purchase_complete() {
            @Override
            public void purchase_is_complete() {
                back_was_pressed();
            }
        });
    }

    private void set_up_view_pager() {
        if (getView() != null && getContext() != null) {
            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            premuim_features.add(new Example_item_buy_premuim_features("\uD83C\uDFCB️", "Track more than three habits", get_a_random_color()));
            premuim_features.add(new Example_item_buy_premuim_features("\uD83D\uDE0A", "Track your mood accurately", get_a_random_color()));
            premuim_features.add(new Example_item_buy_premuim_features("\uD83C\uDFA8", "Access an unlimited selection of colors", get_a_random_color()));
            premuim_features.add(new Example_item_buy_premuim_features("\uD83E\uDE99", "Get free coins", get_a_random_color()));
            premuim_features.add(new Example_item_buy_premuim_features("\uD83E\uDD0C", "Access to all new and exciting features!", get_a_random_color()));
            Adapter_buy_premuim adapter_buy_premuim = new Adapter_buy_premuim(premuim_features);
            view_pager_to_show_premuim_features.setAdapter(adapter_buy_premuim);
            view_pager_to_show_premuim_features.setClipToPadding(false);
            view_pager_to_show_premuim_features.setPadding((int) convertPixelsToDp(15, getContext()), 0, (int) convertPixelsToDp(15, getContext()), 0);
        }
    }

    private static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_indicator() {
        if (getView() != null) {
            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            ScrollingPagerIndicator indicator = getView().findViewById(R.id.which_one_am_i_on_indicator);
            indicator.attachToPager(view_pager_to_show_premuim_features);
        }
    }

    @Override
    public void onDestroy() {
        destroy_the_handelr();
        super.onDestroy();
    }

    private void change_the_premuim_features() {
        if (getView() != null) {
            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            long delay = 2500;
            change_the_premuim_features.postDelayed(new Runnable() {
                public void run() {
                    if (System.currentTimeMillis() - last_swipe_premuim_features > 6000) {
                        if (view_pager_to_show_premuim_features.getAdapter() != null && view_pager_to_show_premuim_features.getAdapter().getItemCount() == view_pager_to_show_premuim_features.getCurrentItem() + 1) {
                            view_pager_to_show_premuim_features.setCurrentItem(0, true);
                        } else {
                            view_pager_to_show_premuim_features.setCurrentItem(view_pager_to_show_premuim_features.getCurrentItem() + 1, true);
                        }
                    }
                    change_the_premuim_features.postDelayed(this, delay);
                }
            }, delay);
        }
    }

    private void destroy_the_handelr() {
        if (change_the_premuim_features != null) {
            change_the_premuim_features.removeCallbacksAndMessages(null);
        }
    }

    private void set_dot_color() {
        if (getView() != null) {
            ScrollingPagerIndicator which_one_am_i_on_indicator = getView().findViewById(R.id.which_one_am_i_on_indicator);
            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            which_one_am_i_on_indicator.setSelectedDotColor(Color.parseColor(premuim_features.get(view_pager_to_show_premuim_features.getCurrentItem()).getColor()));
        }
    }

    private String get_a_random_color() {
        ArrayList<String> random_color_list = new ArrayList<>();
        random_color_list.add("#ed94c9");
        random_color_list.add("#e095ea");
        random_color_list.add("#ec8aad");
        random_color_list.add("#ea867c");
        random_color_list.add("#e7aa6d");
        random_color_list.add("#ddc652");
        random_color_list.add("#b3cd5f");
        random_color_list.add("#7fc5a0");
        random_color_list.add("#6ecabd");
        random_color_list.add("#77cebb");
        random_color_list.add("#6bb6db");
        random_color_list.add("#7ea8e5");
        random_color_list.add("#888ce3");
        String random_color = random_color_list.get(new Random().nextInt(random_color_list.size()));
        while (random_color.equals(last_color)) {
            random_color = random_color_list.get(new Random().nextInt(random_color_list.size()));
        }
        last_color = random_color;
        return random_color;
    }

    private void listen_to_view_page_listen() {
        if (getView() != null) {
            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            view_pager_to_show_premuim_features.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    set_dot_color();
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                    if (was_the_state_set_before) {
                        if (previousState == ViewPager.SCROLL_STATE_DRAGGING && state == ViewPager.SCROLL_STATE_SETTLING) {
                            //user scroll
                            last_swipe_premuim_features = System.currentTimeMillis();
                        } else if (previousState == ViewPager.SCROLL_STATE_SETTLING && state == ViewPager.SCROLL_STATE_IDLE) {
                            //programitic scroll
                        }
                    }
                    //set_dot_color();
                    previousState = state;
                    was_the_state_set_before = true;
                }
            });
        }
    }

    private void listen_to_start_14_days_free_trial() {
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

    private void set_the_progress_bar() {
        if (getView() != null) {
            CircularProgressBar progress_bar_for_buy_premuim_countdown = getView().findViewById(R.id.progress_bar_for_buy_premuim_countdown);
            View close_view_buy_premuim_close = getView().findViewById(R.id.close_view_buy_premuim_close);
            Button button_to_go_back_at_call_premium = getView().findViewById(R.id.button_to_go_back_at_call_premium);
            progress_bar_for_buy_premuim_countdown.setProgress(0);
            long time = 3000L;
            CountDownTimer countDownTimer = new CountDownTimer(time, 10) {

                @Override
                public void onTick(long millisUntilFinished) {
                    long elapsed_time = time - millisUntilFinished;
                    if (elapsed_time != 0) {
                        progress_bar_for_buy_premuim_countdown.setProgress((float) (-Math.pow(elapsed_time, 2) / Math.pow(3000, 2)) + 1);
                        //progress_bar_for_buy_premuim_countdown.setProgress(((float)(time - elapsed_time)/time));
                    }
                }

                @Override
                public void onFinish() {
                    progress_bar_for_buy_premuim_countdown.setProgress(0);
                    progress_bar_for_buy_premuim_countdown.setVisibility(View.INVISIBLE);
                    close_view_buy_premuim_close.setVisibility(View.VISIBLE);
                    button_to_go_back_at_call_premium.setVisibility(View.VISIBLE);
                }
            };
            countDownTimer.start();
        }
    }

    private void set_the_fade_in() {
        if (getView() != null) {
            CircularProgressBar progress_bar_for_buy_premuim_countdown = getView().findViewById(R.id.progress_bar_for_buy_premuim_countdown);

            TextView text_saying_you_need_premuim = getView().findViewById(R.id.text_saying_you_need_premuim);

            ViewPager2 view_pager_to_show_premuim_features = getView().findViewById(R.id.view_pager_to_show_premuim_features);
            ScrollingPagerIndicator which_one_am_i_on_indicator = getView().findViewById(R.id.which_one_am_i_on_indicator);

            Button monthly_subscription_button_in_buy_premuim = getView().findViewById(R.id.monthly_subscription_button_in_buy_premuim);
            TextView text_saying_monthly_in_buy_premium = getView().findViewById(R.id.text_saying_monthly_in_buy_premium);
            TextView text_saying_price_per_month_in_buy_month = getView().findViewById(R.id.text_saying_price_per_month_in_buy_month);
            TextView text_saying_billed_montly = getView().findViewById(R.id.text_saying_billed_montly);
            Button yearly_subscription_button_in_buy_premuim = getView().findViewById(R.id.yearly_subscription_button_in_buy_premuim);
            TextView text_saying_yearly_in_buy_premium = getView().findViewById(R.id.text_saying_yearly_in_buy_premium);
            TextView text_saying_price_per_year_in_buy_month = getView().findViewById(R.id.text_saying_price_per_year_in_buy_month);
            TextView text_saying_old_price = getView().findViewById(R.id.text_saying_old_price);
            TextView text_saying_billed_year = getView().findViewById(R.id.text_saying_billed_year);
            TextView text_saying_most_valuable_in_buy_premuim = getView().findViewById(R.id.text_saying_most_valuable_in_buy_premuim);
            View red_back_ground_behind_button_and_most_valuable = getView().findViewById(R.id.red_back_ground_behind_button_and_most_valuable);

            TextView text_view_saying_cancel_any_time_in_buy_premuim = getView().findViewById(R.id.text_view_saying_cancel_any_time_in_buy_premuim);
            ConstraintLayout layout_showing_privacy_policy_and_temrs_of_use = getView().findViewById(R.id.layout_showing_privacy_policy_and_temrs_of_use);
            TextView text_saying_made_with_tons_of_love = getView().findViewById(R.id.text_saying_made_with_tons_of_love);

            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);


            Animation fade_in_1 = new AlphaAnimation(0, 1);
            fade_in_1.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_1.setDuration(500);

            Animation fade_in_2 = new AlphaAnimation(0, 1);
            fade_in_2.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_2.setStartOffset(500);
            fade_in_2.setDuration(500);

            Animation fade_in_3 = new AlphaAnimation(0, 1);
            fade_in_3.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_3.setStartOffset(1000);
            fade_in_3.setDuration(500);

            Animation fade_in_4 = new AlphaAnimation(0, 1);
            fade_in_4.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_4.setStartOffset(1500);
            fade_in_4.setDuration(500);

            Animation fade_in_5 = new AlphaAnimation(0, 1);
            fade_in_5.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_5.setStartOffset(2000);
            fade_in_5.setDuration(500);

            Animation fade_in_6 = new AlphaAnimation(0, 1);
            fade_in_6.setInterpolator(new DecelerateInterpolator()); //add this
            fade_in_6.setStartOffset(2500);
            fade_in_6.setDuration(500);

            progress_bar_for_buy_premuim_countdown.setAnimation(fade_in_1);
            text_saying_you_need_premuim.setAnimation(fade_in_2);
            view_pager_to_show_premuim_features.setAnimation(fade_in_3);
            which_one_am_i_on_indicator.setAnimation(fade_in_3);
            monthly_subscription_button_in_buy_premuim.setAnimation(fade_in_4);
            text_saying_monthly_in_buy_premium.setAnimation(fade_in_4);
            text_saying_price_per_month_in_buy_month.setAnimation(fade_in_4);
            text_saying_billed_montly.setAnimation(fade_in_4);
            yearly_subscription_button_in_buy_premuim.setAnimation(fade_in_4);
            text_saying_yearly_in_buy_premium.setAnimation(fade_in_4);
            text_saying_price_per_year_in_buy_month.setAnimation(fade_in_4);
            text_saying_old_price.setAnimation(fade_in_4);
            text_saying_billed_year.setAnimation(fade_in_4);
            text_saying_most_valuable_in_buy_premuim.setAnimation(fade_in_4);
            red_back_ground_behind_button_and_most_valuable.setAnimation(fade_in_4);

            text_view_saying_cancel_any_time_in_buy_premuim.setAnimation(fade_in_5);
            layout_showing_privacy_policy_and_temrs_of_use.setAnimation(fade_in_5);
            text_saying_made_with_tons_of_love.setAnimation(fade_in_5);

            done_button_at_the_buttom_add_new_habit.setAnimation(fade_in_6);


        }
    }

    private void start_changng_the_habits_after_animation(){
        if(getView()!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    change_the_premuim_features();
                }
            }, 3000);
        }
    }
}