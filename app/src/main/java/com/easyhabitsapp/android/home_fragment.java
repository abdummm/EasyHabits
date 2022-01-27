package com.easyhabitsapp.android;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return Math.round(pixels);
    }


    /*private void emergency_button_listener() {
        if (getActivity() != null) {
            Button emergency_button_home_fragment = getActivity().findViewById(R.id.emergency_button_home_fragment);
            emergency_button_home_fragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        emergency_button_fragment new_fragment = (emergency_button_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("emergency");
                        home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                        if (old_fragment != null && new_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).show(new_fragment).commit();
                        }
                    }
                }
            });
        }
    }*/

    private void set_up_recycle_view() {
        if (getActivity() != null) {
            TextView text_saying_you_dont_have_any_habits_in_home = getActivity().findViewById(R.id.text_saying_you_dont_have_any_habits_in_home);
            RecyclerView recycle_view_to_set_up_habits_in_home = getActivity().findViewById(R.id.recycle_view_to_set_up_habits_in_home);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
            if (!list.isEmpty()) {
                text_saying_you_dont_have_any_habits_in_home.setVisibility(View.INVISIBLE);
                recycle_view_to_set_up_habits_in_home.setVisibility(View.VISIBLE);
                ArrayList<Item_for_home_habits> list_for_habits = new ArrayList<>();
                for (habits_data_class big_split : list) {
                    list_for_habits.add(new Item_for_home_habits(return_the_icon(big_split.getIcon()), 10, big_split.getName_of_the_habit(), Color.parseColor(big_split.getColor())));
                }
                Item_for_bad_habit_adapter adapter = new Item_for_bad_habit_adapter(list_for_habits);
                recycle_view_to_set_up_habits_in_home.setLayoutManager(linearLayoutManager);
                recycle_view_to_set_up_habits_in_home.setAdapter(adapter);
                adapter.set_on_item_click_listener(new Item_for_bad_habit_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (getActivity() != null) {
                            Am_i_paid am_i_paid = new Am_i_paid(getContext());
                            if (am_i_paid.did_user_pay()) {
                                View_home_habit new_fragment = new View_home_habit();
                                home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                                View_home_habit check_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
                                if (check_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                                }
                                if (old_fragment != null) {
                                    Bundle args = new Bundle();
                                    args.putInt("which_position", position);
                                    new_fragment.setArguments(args);
                                    getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "view home").show(new_fragment).commit();
                                }
                            } else {
                                View_home_habit new_fragment = new View_home_habit();
                                home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                                View_home_habit check_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
                                if (check_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                                }
                                if (old_fragment != null) {
                                    Bundle args = new Bundle();
                                    args.putInt("which_position", position);
                                    new_fragment.setArguments(args);
                                    getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "view home").show(new_fragment).commit();
                                }
                                    /*Buy_premuim buy_premuim = new Buy_premuim("use more than 2 habits",true,"home");
                                    home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                                    if (old_fragment != null) {
                                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                                    }*/
                            }
                        }
                    }
                });
            } else {
                text_saying_you_dont_have_any_habits_in_home.setVisibility(View.VISIBLE);
                recycle_view_to_set_up_habits_in_home.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void floating_button_listen_and_long_listen() {
        if (getActivity() != null) {
            final FloatingActionButton floating_action_button_to_add_habits_home = getActivity().findViewById(R.id.floating_action_button_to_add_habits_home);
            floating_action_button_to_add_habits_home.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (getActivity() != null) {
                        vibrate_the_device();
                        Toast toast = Toast.makeText(getActivity(), "Add habit", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    return true;
                }
            });
            floating_action_button_to_add_habits_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Am_i_paid am_i_paid = new Am_i_paid(getContext());
                    if (am_i_paid.did_user_pay()) {
                        add_a_habit new_fragment = new add_a_habit();
                        home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                        add_a_habit check_fragment = (add_a_habit) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                        if (check_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                        }
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "add a habit").show(new_fragment).commit();
                        }
                    } else {
                        if (return_number_of_bad_habits() < 5) {
                            add_a_habit new_fragment = new add_a_habit();
                            home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                            add_a_habit check_fragment = (add_a_habit) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                            if (check_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                            }
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "add a habit").show(new_fragment).commit();
                            }
                        } else {
                            Buy_premuim buy_premuim = new Buy_premuim("add more than 2 habits", true, "home");
                            home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    }
                }
            });
        }
    }


    private void vibrate_the_device() {
        if (getActivity() != null) {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.EFFECT_TICK));
                } else {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        set_up_recycle_view();
        floating_button_listen_and_long_listen();
        /*put_the_name();
        color_the_name_top();*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            set_up_recycle_view();
        }
    }

    private int return_number_of_bad_habits() {
        Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.size();
        }
    }

    private Drawable return_the_icon(String name) {
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                getContext().getPackageName());
        return resources.getDrawable(resourceId, null);
    }

    private int return_the_streak(ArrayList<Long> relapse, Long start_date) {
        long time;
        if (relapse == null || relapse.isEmpty()) {
            time = System.currentTimeMillis() - start_date;
        } else {
            time = System.currentTimeMillis() - relapse.get(relapse.size() - 1);
        }
        if (time < 0) {
            time = 0;
        }
        return (int) TimeUnit.MILLISECONDS.toDays(time);
    }
}
