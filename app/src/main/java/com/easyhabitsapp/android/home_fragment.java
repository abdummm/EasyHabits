package com.easyhabitsapp.android;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {

    private boolean was_the_view_drawn = false;
    private String last_motivation = "";


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmojiManager.install(new GoogleEmojiProvider());
        set_item_animotor_to_null();
    }

    @Override
    public void onResume() {
        super.onResume();
        call_the_recycle_view();
        button_to_add_new_habit_in_home();
        set_the_days();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            call_the_recycle_view();
        }
    }


    private void set_up_recycle_view(int height) {
        if (getView() != null) {
            TextView text_saying_you_dont_have_any_habits_in_home = getView().findViewById(R.id.text_saying_you_dont_have_any_habits_in_home);
            TextView firtst_day_date_recycle_view = getView().findViewById(R.id.firtst_day_date_recycle_view);
            TextView second_day_date_recycle_view = getView().findViewById(R.id.second_day_date_recycle_view);
            TextView third_day_date_recycle_view = getView().findViewById(R.id.third_day_date_recycle_view);
            TextView fourth_day_date_recycle_view = getView().findViewById(R.id.fourth_day_date_recycle_view);
            TextView fifth_day_date_recycle_view = getView().findViewById(R.id.fifth_day_date_recycle_view);
            RecyclerView recycle_view_to_set_up_habits_in_home = getView().findViewById(R.id.recycle_view_to_set_up_habits_in_home);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
            if (!list.isEmpty()) {
                text_saying_you_dont_have_any_habits_in_home.setVisibility(View.INVISIBLE);
                firtst_day_date_recycle_view.setVisibility(View.VISIBLE);
                second_day_date_recycle_view.setVisibility(View.VISIBLE);
                third_day_date_recycle_view.setVisibility(View.VISIBLE);
                fourth_day_date_recycle_view.setVisibility(View.VISIBLE);
                fifth_day_date_recycle_view.setVisibility(View.VISIBLE);
                recycle_view_to_set_up_habits_in_home.setVisibility(View.VISIBLE);
                ArrayList<Item_for_home_habits> list_for_habits = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Amount_object amount_object = new Amount_object();
                    if (list.get(i).getBad_or_good_habit().equals("Build_up")) {
                        amount_object.setTarget_amount(list.get(i).getHabits_freq_extra());
                        if (list.get(i).getRelapse_amount() != null && list.get(i).getRelapse_amount().containsKey(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))) {
                            amount_object.setReal_amount(list.get(i).getRelapse_amount().get(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())));
                        } else {
                            amount_object.setReal_amount(0);
                        }
                    } else {
                        amount_object.setTarget_amount(1);
                    }
                    list_for_habits.add(new Item_for_home_habits(list.get(i).getName_of_the_habit(), list.get(i).getIcon(), list.get(i).getColor(), amount_object, list.get(i).getId(), return_view_home_from_id(list.get(i).getId()).return_state_of_last_five_days(), height, list.get(i).getBad_or_good_habit()));
                }
                Item_for_bad_habit_adapter adapter = new Item_for_bad_habit_adapter(list_for_habits);
                recycle_view_to_set_up_habits_in_home.setLayoutManager(linearLayoutManager);
                recycle_view_to_set_up_habits_in_home.setAdapter(adapter);
                adapter.set_card_clicked_listen(new Item_for_bad_habit_adapter.Card_clicked_listen() {
                    @Override
                    public void card_just_got_clicked(int id) {
                        if (getActivity() != null) {
                            if (Payment_processer.getInstance().state_of_the_user()) {
                                open_a_habit(id);
                            } else {
                                open_a_habit(id);
                                /*Buy_premuim buy_premuim = new Buy_premuim("use more than 2 habits", true, "home");
                                home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                                if (old_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                                }*/
                            }
                        }
                    }
                });
                adapter.set_today_clicked_listen(new Item_for_bad_habit_adapter.today_clicked_listen() {
                    @Override
                    public void today_just_got_clicked(int id, int position) {
                        return_view_home_from_id(id).today_just_got_clicked();
                        Amount_object amount_object = new Amount_object();
                        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
                        if (list.get(position).getBad_or_good_habit().equals("Build_up")) {
                            amount_object.setTarget_amount(list.get(position).getHabits_freq_extra());
                            if (list.get(position).getRelapse_amount() != null && list.get(position).getRelapse_amount().containsKey(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))) {
                                amount_object.setReal_amount(list.get(position).getRelapse_amount().get(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())));
                            } else {
                                amount_object.setReal_amount(0);
                            }
                        } else {
                            amount_object.setTarget_amount(1);
                        }
                        String name_of_habit = list.get(position).getName_of_the_habit();
                        String icon = list.get(position).getIcon();
                        String color = list.get(position).getColor();
                        ArrayList<String> last_five_days = return_view_home_from_id(list.get(position).getId()).return_state_of_last_five_days();
                        String type = list.get(position).getBad_or_good_habit();
                        list_for_habits.set(position, new Item_for_home_habits(name_of_habit, icon, color, amount_object, list.get(position).getId(), last_five_days, height, type));
                        adapter.notifyItemChanged(position);
                        if (type.equals("Build_up") && last_five_days.get(4).equals("yes")) {
                            if (amount_object.getTarget_amount() == 1 || amount_object.getTarget_amount() == amount_object.getReal_amount()) {
                                play_the_confetti();
                                show_snack_bar();
                            }
                        }
                    }
                });
            } else {
                text_saying_you_dont_have_any_habits_in_home.setVisibility(View.VISIBLE);
                firtst_day_date_recycle_view.setVisibility(View.INVISIBLE);
                second_day_date_recycle_view.setVisibility(View.INVISIBLE);
                third_day_date_recycle_view.setVisibility(View.INVISIBLE);
                fourth_day_date_recycle_view.setVisibility(View.INVISIBLE);
                fifth_day_date_recycle_view.setVisibility(View.INVISIBLE);
                recycle_view_to_set_up_habits_in_home.setVisibility(View.INVISIBLE);
            }
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


    private String return_the_information_from_save(int which, int value_for_position) {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        if (which == 1) {
            return habits_data_class.getName_of_the_habit();
        }
        if (which == 2) {
            return habits_data_class.getIcon();
        }
        if (which == 3) {
            return habits_data_class.getColor();
        }
        return ""; // cant be otherwise
    }

    private ArrayList<Long> return_relapses(int value_for_position) {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        if (habits_data_class.getRelapse() == null) {
            return new ArrayList<>();
        } else {
            return habits_data_class.getRelapse();
        }
    }

    private ArrayList<Integer> add_the_days_per_month_to_the_calender(int value_for_position) {
        ArrayList<Integer> days_of_months_good_habit = new ArrayList<>();
        if (return_the_information_from_save(6, value_for_position).equals("good") && return_the_information_from_save(8, value_for_position).equals("dayspermonth")) {
            String[] split_numbers = return_the_information_from_save(10, value_for_position).split("_");
            for (int i = 0; i < split_numbers.length; i++) {
                days_of_months_good_habit.add(Integer.parseInt(split_numbers[i]));
            }
        }
        return days_of_months_good_habit;
    }

    public void open_a_habit(int id) {
        if (getActivity() != null) {
            View_home_habit view_home = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home: ".concat(String.valueOf(id)));
            home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            if (old_fragment != null) {
                                    /*Bundle args = new Bundle();
                                    args.putInt("which_id", id);
                                    view_home.setArguments(args);*/
                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).show(view_home).commit();
            }
        }
    }

    private void button_to_add_new_habit_in_home() {
        if (getView() != null) {
            Button button_to_add_new_habit_in_home = getView().findViewById(R.id.button_to_add_new_habit_in_home);
            button_to_add_new_habit_in_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.add_a_habit_clicked, false);
                    if (Payment_processer.getInstance().state_of_the_user()) {
                        open_add_new_habit();
                    } else {
                        if (return_number_of_bad_habits() < 5) {
                            open_add_new_habit();
                        } else {
                            home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                            Buy_premuim buy_premuim = new Buy_premuim("add more than 5 habits", true, old_fragment);
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    }
                }
            });
        }
    }

    private void open_add_new_habit() {
        if (getView() != null) {
            Add_new_habit_2 new_fragment = new Add_new_habit_2();
            home_fragment old_fragment = (home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            Add_new_habit_2 check_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
            if (check_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
            }
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "add a habit").show(new_fragment).commit();
            }
        }
    }

    private View_home_habit return_view_home_from_id(int id) {
        return (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home: ".concat(String.valueOf(id)));
    }

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void call_the_recycle_view() {
        if (getView() != null && getContext() != null) {
            TextView text_saying_habits_at_the_top_of_home = getView().findViewById(R.id.text_saying_habits_at_the_top_of_home);
            if (was_the_view_drawn) {
                set_up_recycle_view((int) (text_saying_habits_at_the_top_of_home.getHeight() + convertDpToPixel(25, getContext()) + convertDpToPixel(50, getContext())));
            } else {
                text_saying_habits_at_the_top_of_home.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (getContext() != null) {
                            text_saying_habits_at_the_top_of_home.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            set_up_recycle_view((int) (text_saying_habits_at_the_top_of_home.getHeight() + convertDpToPixel(25, getContext()) + convertDpToPixel(50, getContext())));
                            was_the_view_drawn = true;
                        }
                    }
                });
            }
        }
    }

    private void set_the_days() {
        if (getView() != null) {
            TextView firtst_day_date_recycle_view = getView().findViewById(R.id.firtst_day_date_recycle_view);
            TextView second_day_date_recycle_view = getView().findViewById(R.id.second_day_date_recycle_view);
            TextView third_day_date_recycle_view = getView().findViewById(R.id.third_day_date_recycle_view);
            TextView fourth_day_date_recycle_view = getView().findViewById(R.id.fourth_day_date_recycle_view);
            TextView fifth_day_date_recycle_view = getView().findViewById(R.id.fifth_day_date_recycle_view);
            TextView[] days = {firtst_day_date_recycle_view, second_day_date_recycle_view, third_day_date_recycle_view, fourth_day_date_recycle_view, fifth_day_date_recycle_view};
            long today = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - TimeUnit.DAYS.toMillis(5);
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < 5; i++) {
                today = today + TimeUnit.DAYS.toMillis(1);
                calendar.setTimeInMillis(today);
                SimpleDateFormat simpleformat = new SimpleDateFormat("MMM");
                days[i].setText(simpleformat.format(calendar.getTime()).concat("\n").concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))));
            }
        }
    }

    private void set_item_animotor_to_null() {
        if (getView() != null) {
            RecyclerView recycle_view_to_set_up_habits_in_home = getView().findViewById(R.id.recycle_view_to_set_up_habits_in_home);
            recycle_view_to_set_up_habits_in_home.setItemAnimator(null);
        }
    }

    private void play_the_confetti() {
        if (getView() != null) {
            KonfettiView konfetti_for_completing_the_habit = getView().findViewById(R.id.konfetti_for_completing_the_habit);
            if (!konfetti_for_completing_the_habit.isActive()) {
                EmitterConfig emitterConfig = new Emitter(2, TimeUnit.SECONDS).perSecond(30);
                konfetti_for_completing_the_habit.start(
                        new PartyFactory(emitterConfig)
                                .angle(Angle.RIGHT - 45)
                                .spread(Spread.SMALL)
                                //.shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                                .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                                .setSpeedBetween(10f, 30f)
                                .position(new Position.Relative(0.0, 0.5))
                                .build(),
                        new PartyFactory(emitterConfig)
                                .angle(Angle.LEFT + 45)
                                .spread(Spread.SMALL)
                                //.shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                                .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                                .setSpeedBetween(10f, 30f)
                                .position(new Position.Relative(1.0, 0.5))
                                .build()
                );
            }
        }
    }

    private void show_snack_bar() {
        if (getView() != null && getContext() != null && getActivity() != null) {
            ConstraintLayout home_fragment_id_for_layout = getView().findViewById(R.id.home_fragment_id_for_layout);
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
            Snackbar snackbar = Snackbar.make(home_fragment_id_for_layout, "", 5500);
            /*View snackView = getLayoutInflater().inflate(R.layout.snack_bar_backround, null);
            Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
            snackbar.getView().setBackgroundResource(R.drawable.snackbar_background);
            final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
            params.setMargins(params.leftMargin + (int) convertDpToPixel(15,getContext()), params.topMargin, params.rightMargin + (int) convertDpToPixel(15,getContext()), params.bottomMargin + (int) convertDpToPixel(15,getContext()));
            snackView.setLayoutParams(params);
            snackbar.setAnchorView(bottomNavigationView);
            snackBarView.addView(snackView, 0);
            snackbar.show();*/
            View snackView = getLayoutInflater().inflate(R.layout.snack_bar_backround, null);
            Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
            snackBarView.setBackgroundResource(R.drawable.snackbar_background);
            FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
            parentParams.setMargins((int) convertDpToPixel(15, getContext()), 0, (int) convertDpToPixel(15, getContext()), (int) convertDpToPixel(15, getContext()));
            parentParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
            parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
            snackBarView.setLayoutParams(parentParams);
            snackBarView.addView(snackView, 0);
            snackbar.setAnchorView(bottomNavigationView);
            snackbar.show();
            TextView text_view_inside_snack_bar = snackBarView.findViewById(R.id.text_view_inside_snack_bar);
            text_view_inside_snack_bar.setText(get_a_random_message());
        }
    }

    private String get_a_random_message() {
        ArrayList<String> motivation = new ArrayList<>();
        motivation.add("\"All our dreams can come true, if we have the courage to pursue them.\" -Walt Disney");
        motivation.add("\"The secret of getting ahead is getting started.\" -Mark Twain");
        motivation.add("\"I’ve missed more than 9,000 shots in my career. I’ve lost almost 300 games. 26 times I’ve been trusted to take the game winning shot and missed. I’ve failed over and over and over again in my life, and that is why I succeed.\" -Michael Jordan");
        motivation.add("\"Don’t limit yourself. Many people limit themselves to what they think they can do. You can go as far as your mind lets you. What you believe, remember, you can achieve.\" -Mary Kay Ash");
        motivation.add("\"The best time to plant a tree was 20 years ago. The second best time is now.\" -Chinese Proverb");
        motivation.add("\"It’s hard to beat a person who never gives up.\" -Babe Ruth");
        motivation.add("\"If people are doubting how far you can go, go so far that you can’t hear them anymore.\" -Michele Ruiz");
        motivation.add("\"We need to accept that we won’t always make the right decisions, that we’ll screw up royally sometimes-understanding that failure is not the opposite of success, it’s part of success.\" -Arianna Huffington");
        motivation.add("\"Write it. Shoot it. Publish it. Crochet it. Sauté it. Whatever. MAKE.\" -Joss Whedon");
        motivation.add("\"Fairy tales are more than true: not because they tell us that dragons exist, but because they tell us that dragons can be beaten.\"-Neil Gaiman");
        motivation.add("\"Everything you can imagine is real.\"-Pablo Picasso");
        motivation.add("\"When one door of happiness closes, another opens; but often we look so long at the closed door that we do not see the one which has been opened for us.\" -Helen Keller");
        motivation.add("\"Do one thing every day that scares you.\" -Eleanor Roosevelt");
        motivation.add("\"It’s no use going back to yesterday, because I was a different person then.\" -Lewis Carroll");
        motivation.add("\"Happiness is not something ready made. It comes from your own actions.\" -Dalai Lama XIV");
        motivation.add("\"Whatever you are, be a good one.\" -Abraham Lincoln");
        motivation.add("\"The same boiling water that softens the potato hardens the egg. It’s what you’re made of. Not the circumstances.\" -Unknown");
        motivation.add("\"If we have the attitude that it’s going to be a great day it usually is.\" -Catherine Pulsifier");
        motivation.add("\"You can either experience the pain of discipline or the pain of regret. The choice is yours.\" -Unknown");
        motivation.add("\"Impossible is just an opinion.\" -Paulo Coelho");
        motivation.add("\"Your passion is waiting for your courage to catch up.\" -Isabelle Lafleche");
        motivation.add("\"Magic is believing in yourself. If you can make that happen, you can make anything happen.\" -Johann Wolfgang Von Goethe");
        motivation.add("\"If something is important enough, even if the odds are stacked against you, you should still do it.\" -Elon Musk");
        motivation.add("\"Hold the vision, trust the process.\" -Unknown");
        motivation.add("\"Don’t be afraid to give up the good to go for the great.\" -John D. Rockefeller");
        motivation.add("\"People who wonder if the glass is half empty or full miss the point. The glass is refillable.\" -Unknown");
        motivation.add("\"One day or day one. You decide.\" -Unknown");
        motivation.add("\"It’s time to make dreams and goals happen. Let’s go!\" -Heather Stillufsen");
        motivation.add("\"When life gives you Monday, dip it in glitter and sparkle all day.\" -Ella Woodword");
        motivation.add("\"No one is to blame for your future situation but yourself. If you want to be successful, then become ‘Successful.’\" -Jaymin Shah");
        motivation.add("\"Things may come to those who wait, but only the things left by those who hustle.\" -Abraham Lincoln");
        motivation.add("\"Everything comes to him who hustles while he waits.\" -Thomas Edison");
        motivation.add("\"Every successful person in the world is a hustler one way or another. We all hustle to get where we need to be. Only a fool would sit around and wait on another man to feed him.\" -K’wan");
        motivation.add("\"Invest in your dreams. Grind now. Shine later.\" -Unknown");
        motivation.add("\"Hustlers don’t sleep, they nap.\" -Unknown");
        motivation.add("\"Greatness only comes before hustle in the dictionary.\" -Ross Simmonds");
        motivation.add("\"Without hustle, talent will only carry you so far.\" -Gary Vaynerchuk");
        motivation.add("\"Work like there is someone working 24 hours a day to take it away from you.\" -Mark Cuban");
        motivation.add("\"Hustle in silence and let your success make the noise.\" -Unknown");
        motivation.add("\"We are what we repeatedly do. Excellence, then, is not an act, but a habit.\" -Aristotle");
        motivation.add("\"If you’re offered a seat on a rocket ship, don’t ask what seat! Just get on.\" -Sheryl Sandberg");
        motivation.add("\"I always did something I was a little not ready to do. I think that’s how you grow. When there’s that moment of ‘Wow, I’m not really sure I can do this,’ and you push through those moments, that’s when you have a breakthrough.\" -Marissa Mayer");
        motivation.add("\"If you hear a voice within you say, ‘You cannot paint,’ then by all means paint, and that voice will be silenced.\" -Vincent Van Gogh");
        motivation.add("\"How wonderful it is that nobody need wait a single moment before starting to improve the world.\" -Anne Frank");
        motivation.add("\"Some people want it to happen, some wish it would happen, others make it happen.\" -Michael Jordan");
        motivation.add("\"Great things are done by a series of small things brought together.\" -Vincent Van Gogh");
        motivation.add("\"Very often, a change of self is needed more than a change of scene.’ -A.C. Benson");
        motivation.add("\"It’s not the load that breaks you down, it’s the way you carry it.\" -Lou Holtz");
        motivation.add("\"The hard days are what make you stronger.\" -Aly Raisman");
        motivation.add("\"If you believe it’ll work out, you’ll see opportunities. If you don’t believe it’ll work out, you’ll see obstacles.\" -Wayne Dyer");
        motivation.add("\"Keep your eyes on the stars, and your feet on the ground.\" -Theodore Roosevelt");
        motivation.add("\"You can waste your lives drawing lines. Or you can live your life crossing them.\" -Shonda Rhimes");
        motivation.add("\"You’ve got to get up every morning with determination if you’re going to go to bed with satisfaction.\" -George Lorimer");
        motivation.add("\"I now tried a new hypothesis: It was possible that I was more in charge of my happiness than I was allowing myself to be.\" -Michelle Obama");
        motivation.add("\"In a gentle way, you can shake the world.\" -Mahatma Gandhi");
        motivation.add("\"If opportunity doesn’t knock, build a door.\" -Kurt Cobain");
        motivation.add("\"Don’t be pushed around by the fears in your mind. Be led by the dreams in your heart.\" -Roy T. Bennett");
        motivation.add("\"Work hard in silence, let your success be the noise.\" -Frank Ocean");
        motivation.add("\"Don’t say you don’t have enough time. You have exactly the same number of hours per day that were given to Helen Keller, Pasteur, Michelangelo, Mother Teresa, Leonardo da Vinci, Thomas Jefferson, and Albert Einstein.\" -H. Jackson Brown Jr.");
        motivation.add("\"Hard work beats talent when talent doesn’t work hard.\" -Tim Notke");
        motivation.add("\"If everything seems to be under control, you’re not going fast enough.\" -Mario Andretti");
        motivation.add("\"The only difference between ordinary and extraordinary is that little extra.\" -Jimmy Johnson");
        motivation.add("\"Unsuccessful people make their decisions based on their current situations. Successful people make their decisions based on where they want to be.\" -Benjamin Hardy");
        motivation.add("\"Never stop doing your best just because someone doesn’t give you credit.\" -Kamari aka Lyrikal");
        motivation.add("\"Work hard for what you want because it won’t come to you without a fight. You have to be strong and courageous and know that you can do anything you put your mind to. If somebody puts you down or criticizes you, just keep on believing in yourself and turn it into something positive.\" -Leah LaBelle");
        motivation.add("\"The miracle is not that we do this work, but that we are happy to do it.\" -Mother Teresa");
        motivation.add("\"Never give up on a dream just because of the time it will take to accomplish it. The time will pass anyway.\" -Earl Nightingale");
        motivation.add("\"If you work on something a little bit every day, you end up with something that is massive.\" -Kenneth Goldsmith");
        motivation.add("\"The big secret in life is that there is no secret. Whatever your goal, you can get there if you’re willing to work.\" -Oprah Winfrey");
        motivation.add("\"If you cannot do great things, do small things in a great way.\" -Napoleon Hill");
        motivation.add("\"At any given moment you have the power to say: this is not how the story is going to end.\" -Unknown");
        motivation.add("\"Amateurs sit around and wait for inspiration. The rest of us just get up and go to work.\" -Stephen King");
        motivation.add("\"Nothing will work unless you do.\" -Maya Angelou");
        motivation.add("\"Sometimes when you’re in a dark place you think you’ve been buried but you’ve actually been planted.\" -Christine Caine");
        motivation.add("\"Don’t limit your challenges. Challenge your limits.\" -Unknown");
        motivation.add("\"Whenever you find yourself doubting how far you can go, just remember how far you have come.\" -Unknown");
        motivation.add("\"Everyone has inside them a piece of good news. The good news is you don’t know how great you can be! How much you can love! What you can accomplish! And what your potential is.\" -Anne Frank");
        motivation.add("\"Don’t quit yet, the worst moments are usually followed by the most beautiful silver linings. You have to stay strong, remember to keep your head up and remain hopeful.\" -Unknown");
        motivation.add("\"When written in Chinese the word \"crisis\" is composed of two characters-one represents danger and the other represents opportunity.\" -John F. Kennedy");
        motivation.add("\"Good. Better. Best. Never let it rest. ’Til your good is better and your better is best.\" -St. Jerome.");
        motivation.add("\"In the middle of every difficulty lies opportunity.\" -Albert Einstein");
        motivation.add("\"Start where you are. Use what you have. Do what you can.\" -Arthur Ashe");
        motivation.add("\"Dreams don’t work unless you do.\" -John C. Maxwell");
        motivation.add("\"Go the extra mile. It’s never crowded there.\" -Dr. Wayne D. Dyer");
        motivation.add("\"Keep your face always toward the sunshine-and shadows will fall behind you.\" -Walt Whitman");
        motivation.add("\"What defines us is how well we rise after falling.\" -Lionel");
        motivation.add("\"Make each day your masterpiece.\" -John Wooden");
        motivation.add("\"Wherever you go, go with all your heart.\" -Confucius");
        motivation.add("\"Turn your wounds into wisdom.\" -Oprah Winfrey");
        motivation.add("\"We can do anything we want to if we stick to it long enough.\" -Helen Keller");
        motivation.add("\"Begin anywhere.\" -John Cage");
        motivation.add("\"Success is no accident. It is hard work, perseverance, learning, studying, sacrifice and most of all, love of what you are doing or learning to do.\" -Pele");
        motivation.add("\"Every champion was once a contender that didn’t give up.\" -Gabby Douglas");
        motivation.add("\"To be a champion, I think you have to see the big picture. It’s not about winning and losing; it’s about every day hard work and about thriving on a challenge. It’s about embracing the pain that you’ll experience at the end of a race and not being afraid. I think people think too hard and get afraid of a certain challenge.\" -Summer Sanders");
        motivation.add("\"The difference between successful people and very successful people is that very successful people say no to almost everything.\" -Warren Buffett");
        motivation.add("\"You can cry, scream, and bang your head in frustration but keep pushing forward. It’s worth it.\"");
        motivation.add("\"I hated every minute of training, but I said, ‘Don’t quit. Suffer now and live the rest of your life as a champion.\" -Muhammad Ali");
        motivation.add("\"Opportunities don’t happen. You create them.\" -Chris Grosser");
        motivation.add("\"Success is liking yourself, liking what you do, and liking how you do it.\" -Maya Angelou");
        motivation.add("\"You were born to be a player. You were meant to be here. This moment is yours.\" -Herb Brooks");
        motivation.add("\"You must do the thing you think you cannot do.\" -Eleanor Roosevelt");
        motivation.add("\"If you want to fly, give up everything that weighs you down.\" -Buddha");
        motivation.add("\"Doubt kills more dreams than failure ever will.\" -Suzy Kassem");
        motivation.add("\"I never lose. Either I win or learn.\" -Nelson Mandela");
        motivation.add("\"Today is your opportunity to build the tomorrow you want.\" -Ken Poirot");
        motivation.add("\"Getting over a painful experience is much like crossing the monkey bars. You have to let go at some point in order to move forward.\" -C.S. Lewis");
        motivation.add("\"Focus on being productive instead of busy.\" -Tim Ferriss");
        motivation.add("\"You don’t need to see the whole staircase, just take the first step.\" -Martin Luther King Jr.");
        motivation.add("\"It’s not all sunshine and rainbows, but a good amount of it actually is.\" -Unknown");
        motivation.add("\"When someone says you can’t do it, do it twice and take pictures.\"");
        motivation.add("\"I didn’t get there by wishing for it, but by working for it.\" -Estée Lauder");
        motivation.add("\"If you’re too comfortable, it’s time to move on. Terrified of what’s next? You’re on the right track.\" -Susan Fales-Hill");
        motivation.add("\"Be happy with what you have while working for what you want.\" -Helen Keller");
        motivation.add("\"Sunshine all the time makes a desert.\" -Arabic proverb");
        motivation.add("\"The big lesson in life, baby, is never be scared of anyone or anything.\" -Frank Sinatra");
        motivation.add("\"You’re so much stronger than your excuses.\" -Unknown");
        motivation.add("\"Don’t compare yourself to others. Be like the sun and the moon and shine when it’s your time.\" -Unknown");
        motivation.add("\"Don’t tell everyone your plans, instead show them your results.\" -Unknown");
        motivation.add("\"I choose to make the rest of my life the best of my life.\" -Louise Hay");
        motivation.add("\"Nothing can dim the light that shines from within.\" -Maya Angelou");
        motivation.add("\"Be so good they can’t ignore you.\" -Steve Martin");
        motivation.add("\"Take criticism seriously, but not personally. If there is truth or merit in the criticism, try to learn from it. Otherwise, let it roll right off you.\" -Hillary Clinton");
        motivation.add("\"This is a reminder to you to create your own rule book, and live your life the way you want it.\" -Reese Evans");
        motivation.add("\"If you don’t get out of the box you’ve been raised in, you won’t understand how much bigger the world is.\" -Angelina Jolie");
        motivation.add("\"Do the best you can. No one can do more than that.\" -John Wooden");
        motivation.add("\"Do what you can, with what you have, where you are.\" -Theodore Roosevelt");
        motivation.add("\"If you can dream it, you can do it.\" -Walt Disney");
        motivation.add("\"Trust yourself that you can do it and get it.\" -Baz Luhrmann");
        motivation.add("\"Don’t let what you can’t do interfere with what you can do.\" -Unknown");
        motivation.add("\"You can do anything you set your mind to.\" -Benjamin Franklin");
        motivation.add("\"All we can do is the best we can do.\" -David Axelrod");
        motivation.add("\"You never know what you can do until you try.\" -William Cobbett");
        motivation.add("\"Twenty years from now you’ll be more disappointed by the things you did not do than the ones you did.\" -Mark Twain");
        motivation.add("\"Believe in yourself, take on your challenges, dig deep within yourself to conquer fears. Never let anyone bring you down. You got to keep going.\" -Chantal Sutherland");
        motivation.add("\"A walk to a nearby park may give you more energy and inspiration in life than spending two hours in front of a screen.\" -Tsang Lindsay");
        motivation.add("\"If you can’t do anything about it then let it go. Don’t be a prisoner to things you can’t change.\" -Tony Gaskins");
        motivation.add("\"You can’t go back and change the beginning, but you can start where you are and change the ending.\" -C.S. Lewis");
        motivation.add("\"Yesterday I was clever, so I wanted to change the world. Today I am wise, so I am changing myself.\" -Rumi");
        motivation.add("\"I can and I will. Watch me.\" -Carrie Green");
        motivation.add("\"Try not to become a man of success, but rather become a man of value.\" -Albert Einstein");
        motivation.add("\"A winner is a dreamer who never gives up.\" -Nelson Mandela");
        motivation.add("\"What is life without a little risk?\" -J.K. Rowling");
        motivation.add("\"Only do what your heart tells you.\" -Princess Diana");
        motivation.add("\"If it’s a good idea, go ahead and do it. It’s much easier to apologize than it is to get permission.\" -Grace Hopper");
        motivation.add("\"I attribute my success to this: I never gave or took an excuse.\" -Florence Nightingale");
        motivation.add("\"The question isn’t who is going to let me; it’s who is going to stop me.\" -Ayn Rand");
        motivation.add("\"A surplus of effort could overcome a deficit of confidence.\" -Sonia Sotomayer");
        motivation.add("\"I can be changed by what happens to me. But I refuse to be reduced by it.\" -Maya Angelou");
        motivation.add("\"Darkness cannot drive out darkness: only light can do that.\" -Martin Luther King Jr.");
        motivation.add("\"You have brains in your head. You have feet in your shoes. You can steer yourself any direction you choose. You’re on your own. And you know what you know. And YOU are the one who’ll decide where to go…\"-Dr. Seuss");
        motivation.add("\"It’s the possibility of having a dream come true that makes life interesting.\" -Paulo Coelho");
        motivation.add("\"There is some good in this world, and it’s worth fighting for.\" -J.R.R. Tolkien");
        motivation.add("\"On my own I will just create and if it works, it works. And if it doesn’t, I’ll just create something else. I don’t have any limitations on what I think I could do or be.\" -Oprah Winfrey");
        motivation.add("\"We need to accept that we won’t always make the right decisions, that we’ll screw up royally sometimes-understanding that failure is not the opposite of success, it’s part of success.\" -Arianna Huffington");
        motivation.add("\"Don’t compromise yourself. You’re all you’ve got.\" -Janis Joplin");
        motivation.add("\"When something I can’t control happens, I ask myself: Where is the hidden gift? Where is the positive in this?\" -Sara Blakely");
        motivation.add("\"Doubt is a killer. You just have to know who you are and what you stand for. \" -Jennifer Lopez");
        motivation.add("\"Be a first rate version of yourself, not a second rate version of someone else.\" -Judy Garland");
        motivation.add("\"Learn from the mistakes of others. You can’t live long enough to make them all yourself.\" -Eleanor Roosevelt");
        motivation.add("\"I was smart enough to go through any door that opened.\" -Joan Rivers");
        motivation.add("\"Done is better than perfect.\" -Sheryl Sandberg");
        motivation.add("\"If your dreams don’t scare you, they are too small.\" -Richard Branson");
        motivation.add("\"Today is your opportunity to build the tomorrow you want.\" -Ken Poirot");
        motivation.add("\"What hurts you blesses you.\" -Rumi");
        motivation.add("\"I always thought it was me against the world and then one day I realized it’s just me against me.\" -Kendrick Lamar");
        motivation.add("\"A man is not finished when he is defeated. He is finished when he quits.\" -Richard Nixon");
        motivation.add("\"The world is changed by your example, not by your opinion.\" -Paulo Coelho");
        motivation.add("\"I’m not in this world to live up to your expectations, and you’re not in this world to live up to mine.\" -Bruce Lee");
        motivation.add("\"Be a fruit loop in a world of Cheerios.\" -Unknown");
        motivation.add("\"Dream beautiful dreams, and then work to make those dreams come true.\" -Spencer W. Kimball");
        motivation.add("\"Be the change you want to see in the world.\" -Mahatma Gandhi");
        motivation.add("\"Believe you can and you will.\" -Unknown");
        motivation.add("\"Do the right thing even when no one is looking.\" -Unknown");
        motivation.add("\"Make today the day you learn something new.\" –Unknown");
        motivation.add("\"Be silly, be honest, be kind.’ -Ralph Waldo Emerson");
        motivation.add("\"It’s not what happens to you but how you react to it that matters.\" -Epictetus");
        motivation.add("\"You don’t have to be perfect to be amazing.\" -Unknown");
        motivation.add("\"The best way to predict your future is to create it.\" -Abraham Lincoln");
        motivation.add("\"Don’t watch the clock; do what it does. Keep going.\" -Sam Levenson");
        motivation.add("\"You must do the kind of things you think you cannot do.\" -Eleanor Roosevelt");
        motivation.add("\"It’s not what you do once in a while, it's what you do day in and day out that makes the difference.\" -Jenny Craig");
        motivation.add("\"Falling down is how we grow. Staying down is how we die.\" -Brian Vaszily");
        motivation.add("\"There may be people that have more talent than you, but there’s no excuse for anyone to work harder than you.\" -Derek Jeter");
        motivation.add("\"When Plan A doesn’t work, don’t worry, you still have 25 more letters to go through.\" -Unknown");
        motivation.add("\"A diamond is merely a lump of coal that did well under pressure.\" -Unknown");
        motivation.add("\"Opportunity does not knock, it presents itself when you beat down the door.\" -Kyle Chandler");
        motivation.add("\"I have not failed. I’ve just found 10,000 ways that won’t work.\" -Thomas A. Edison");
        motivation.add("\"‘You could rattle the stars,’ she whispered. ‘You could do anything, if only you dared. And deep down, you know it, too. That’s what scares you most.’\"-Sarah J. Maas");
        motivation.add("\"It is only when we take chances when our lives improve. The initial and the most difficult risk that we need to take is to become honest.\" -Walter Anderson");
        motivation.add("\"The adventure of life is to learn. The purpose of life is to grow. The nature of life is to change. The challenge of life is to overcome. The essence of life is to care. The opportunity of life is to serve. The secret of life is to dare. The spice of life is to befriend. The beauty of life is to give.\" -William Arthur Ward");
        motivation.add("\"When you know your worth, no one can make you feel worthless.\" -Unknown");
        motivation.add("\"Once you do know what the question actually is, you’ll know what the answer means.\"-Douglas Adams");
        motivation.add("\"The two most important days in your life are the day you’re born and the day you find out why.\" -Mark Twain");
        motivation.add("\"Nothing ever goes away until it teaches us what we need to know.\" -Pema Chodron");
        motivation.add("\"I choose to make the rest of my life the best of my life.\" -Louise Hay");
        motivation.add("\"Some people want it to happen, some wish it would happen, others make it happen.\" -Michael Jordan");
        motivation.add("\"If you don’t like the road you’re walking, start paving another one.\" -Dolly Parton");
        motivation.add("\"In order to be irreplaceable, one must always be different.\" -Coco Chanel");
        motivation.add("\"Doubt is a killer. You just have to know who you are and what you stand for.\" -Jennifer Lopez");
        motivation.add("\"No one changes the world who isn’t obsessed.\" -Billie Jean King");
        motivation.add("\"What you do makes a difference, and you have to decide what kind of difference you want to make.\" -Jane Goodall");
        motivation.add("\"Remember, you have been criticizing yourself for years and it hasn’t worked. Try approving of yourself and see what happens.\"  -Louise L. Hay");
        motivation.add("\"Work hard and don’t give up hope. Be open to criticism and keep learning. Surround yourself with happy, warm and genuine people.\" -Tena Desae");
        motivation.add("\"Stay true to yourself, yet always be open to learn. Work hard, and never give up on your dreams, even when nobody else believes they can come true but you. \" -Phillip Sweet");
        motivation.add("\"You can control two things: your work ethic and your attitude about anything.\" -Ali Krieger");
        motivation.add("\"Success isn’t always about greatness. It’s about consistency. Consistent hard work leads to success. Greatness will come.\" -Dwayne Johnson");
        motivation.add("\"I really appreciate people who correct me, because without them, I might have been repeating mistakes for a long time.\" -Mufti Menk");
        motivation.add("\"Motivation comes from working on things we care about.\" -Sheryl Sandberg");
        motivation.add("\"If today you are a little bit better than you were yesterday, then that’s enough.\" -David A. Bednar");
        motivation.add("\"If you can’t make a mistake you can’t make anything.\" -Marva Collin");
        motivation.add("\"Practice makes progress, not perfect.\" -Unknown");
        motivation.add("\"You may be disappointed if you fail, but you’ll be doomed if you don’t try.\" -Beverly Sills");
        motivation.add("\"Failure is the tuition you pay for success.\" -Walter Brunell");
        motivation.add("\"If we wait until we’re ready, we’ll be waiting for the rest of our lives.\" -Lemony Snicket");
        motivation.add("\"Study while others are sleeping; work while others are loafing; prepare while others are playing; and dream while others are wishing.\" -William Arthur Ward");
        motivation.add("\"The best revenge is massive success.\" -Frank Sinatra");
        motivation.add("\"What’s on the other side of fear? Nothing.\" -Jamie Foxx");
        motivation.add("\"Quitters never win. Winners never quit!\" -Dr. Irene C. Kassorla");
        motivation.add("\"If there is no wind, row.\" -Latin Proverb");
        motivation.add("\"It’s never too late for a new beginning in your life.\" -Joyce Meyers");
        motivation.add("\"Action is the foundational key to all success.\" -Pablo Picasso");
        motivation.add("\"I never dreamt of success. I worked for it.\" -Estée Lauder");
        motivation.add("\"A goal is a dream with a deadline.\" -Napoleon Hill");
        motivation.add("\"Change is painful, but nothing is as painful as staying stuck somewhere you don’t belong.\" -Mandy Hale");
        motivation.add("\"Those who cannot change their minds cannot change anything.\" -George Bernard Shaw");
        motivation.add("\"Everyone thinks of changing the world, but no one thinks of changing himself.\"");
        motivation.add("\"Change is the law of life. And those who look only to the past or present are certain to miss the future.\" -John F. Kennedy");
        motivation.add("\"We delight in the beauty of the butterfly, but rarely admit the changes it has gone through to achieve that beauty.\" -Maya Angelou");
        motivation.add("\"Dreams are the seeds of change. Nothing ever grows without a seed, and nothing ever changes without a dream.\" -Debby Boone");
        motivation.add("\"Don’t give up, don’t take anything personally, and don’t take no for an answer.\" -Sophia Amoruso");
        motivation.add("\"The secret of change is to focus all your energy, not on fighting the old, but on building the new.\" -Socrates");
        motivation.add("\"Your positive action combined with positive thinking results in success.\" -Shiv Khera");
        motivation.add("\"Success is no accident. It is hard work, perseverance, learning, studying, sacrifice and most of all, love of what you are doing or learning to do\" -Pele");
        motivation.add("\"If you talk about it, it’s a dream. If you envision it, it’s possible. If you schedule it, it’s real.\" -Tony Robbins");
        motivation.add("\"Forget your excuses. You either want it bad or don’t want it at all.\" -Unknown");
        motivation.add("\"The key to success is to start before you are ready.\" -Marie Forleo");
        motivation.add("\"I want to be remembered as the one who tried.\" -Dr. Dorothy Height");
        motivation.add("\"Punctuality is not just limited to arriving at a place at the right time, it is also about taking actions at the right time.\" -Amit Kalantri");
        motivation.add("\"You can’t let your failures define you. You have to let your failures teach you.\" -Barack Obama");
        motivation.add("\"Success is a lousy teacher. It seduces smart people into thinking they can’t lose.\" -Bill Gates");
        motivation.add("\"Stop being afraid of what could go wrong, and start being excited about what could go right.\" -Tony Robbins");
        motivation.add("\"Think like a queen. A queen is not afraid to fail. Failure is another stepping stone to greatness.\" -Oprah Winfrey");
        motivation.add("\"Defeat is a state of mind; no one is ever defeated until defeat is accepted as a reality.\" -Bruce Lee");
        motivation.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" -Confucius");
        motivation.add("\"It is impossible to live without failing at something, unless you live so cautiously that you might as well not have lived at all -in which case, you fail by default.\" -J.K. Rowling");
        motivation.add("\"Success is going from failure to failure without losing your enthusiasm\" -Winston Churchill");
        motivation.add("\"Why do we grieve failures longer than we celebrate wins?\" -Komal Kapoor");
        motivation.add("\"Failure isn’t the end of the road. It’s a big red flag saying to you ‘Wrong way. Turn around.’\" -Oprah Winfrey");
        motivation.add("\"No matter what people tell you, words and ideas can change the world.\" -Robin Williams");
        motivation.add("\"Life is like riding a bicycle. To keep your balance, you must keep moving.\" -Albert Einstein");
        motivation.add("\"You’re off to Great Places! Today is your day! Your mountain is waiting, so … get on your way!\" -Dr. Seuss");
        motivation.add("\"When thinking about life, remember this: no amount of guilt can change the past and no amount of anxiety can change the future.\" -Unknown");
        motivation.add("\"A negative mind will never give you a positive life.\" -Unknown");
        motivation.add("\"Everything is hard before it is easy.\" -Goethe");
        motivation.add("\"At the end of the day we can endure much more than we think we can.\" -Frida Kahlo");
        motivation.add("\"Whatever you do, never run back to what broke you.\" -Frank Ocean");
        motivation.add("\"Take the risk or lose the chance.\" -Unknown");
        motivation.add("\"Never regret a day in your life. Good days bring you happiness and bad days give you experience.\" -Unknown");
        motivation.add("\"Either you run the day, or the day runs you.\" -Jim Rohn");
        motivation.add("\"Only I can change my life. No one can do it for me.\" -Carol Burnett");
        motivation.add("\"Life is 10% what happens to you and 90% how you react to it.\" -Charles R. Swindoll");
        motivation.add("\"Act as if what you do makes a difference. It does.\" -William James");
        motivation.add("\"Even if you’re on the right track, you’ll get run over if you just sit there.\" -Will Rogers");
        motivation.add("\"Very little is needed to make a happy life; it is all within yourself, in your way of thinking.\" -Marcus Aurelius");
        motivation.add("\"Life is either a daring adventure or nothing at all.\" -Helen Keller");
        motivation.add("\"The person who follows the crowd will usually go no further than the crowd. The person who walks alone is likely to find themself in places no one has been before.’ -Albert Einstein");
        motivation.add("\"Life’s like a movie, write your own ending. Keep believing, keep pretending.\" -Jim Henson");
        motivation.add("\"Never stop learning because life never stops teaching.\" -Unknown");
        motivation.add("\"The capacity to learn is a gift; the ability to learn is a skill; the willingness to learn is a choice.\" -Brian Herbert");
        motivation.add("\"I’m still learning.\" -Michelangelo");
        motivation.add("\"What is coming is better than what is gone.\" -Unknown");
        motivation.add("\"Attitude is a choice. Happiness is a choice. Optimism is a choice. Kindness is a choice. Giving is a choice. Respect is a choice. Whatever choice you make makes you. Choose wisely.\" -Roy T. Bennett");
        motivation.add("\"Dwell on the beauty of life. Watch the stars, and see yourself running with them.\" -Marcus Aurelius");
        motivation.add("\"Do something today that your future self will thank you for.\" -Unknown");
        motivation.add("\"The greatest weapon against stress is the ability to choose one thought over another.\" -William James");
        motivation.add("\"It takes nothing to join the crowd. It takes everything to stand alone.\" -Hans F. Hansen");
        motivation.add("\"Your mind is powerful. When you fill it with positive thoughts your whole world will change.\" -Unknown");
        motivation.add("\"Your only limit is your mind.\" -Unknown");
        motivation.add("\"Stop being afraid of what can go wrong and start being positive about what can go right.\" -Unknown");
        motivation.add("\"The difference between who you are and who you want to be is what you do.\" -Unknown");
        motivation.add("\"You were born to win, but to be a winner, you must plan to win, prepare to win, and expect to win.\" -Zig Ziglar");
        motivation.add("\"One thing’s for sure, if you don’t play, you don’t win.\" -Kylie Francis");
        motivation.add("\"Winning means you’re willing to go longer, work harder, and give more than anyone else.\" -Vince Lombardi");
        motivation.add("\"When I win and when I lose, I take ownership of it, because I really am in charge of what I do.\" -Nicki Minaj");
        motivation.add("\"A champion is afraid of losing. Everyone else is afraid of winning.\" -Billie Jean King");
        motivation.add("\"Competing at the highest level is not about winning. It’s about preparation, courage, understanding, nurturing your people, and heart. Winning is the result.\" -Joe Torre");
        motivation.add("\"A winner is a dreamer who never gives up.\" -Nelson Mandela");
        motivation.add("\"The secret of your future is hidden in your daily routine.\" -Mike Murdock");
        motivation.add("\"Losers quit when they fail. Winners fail until they succeed.\" -Robert T. Kiyosaki");
        motivation.add("\"It is never too late to be what you might have been.\" -George Eliot");
        motivation.add("\"Words can inspire, thoughts can provoke, but only action truly brings you closer to your dreams.\" -Brad Sugars");
        motivation.add("\"Don’t stop when you are tired. Stop when you are done.\" -Unknown");
        motivation.add("\"Don’t tell people about your dreams. Show them your dreams.\" -Unknown");
        motivation.add("\"Motivation may be what starts you off, but it’s habit that keeps you going back for more.\"  -Miya Yamanouchi");
        motivation.add("\"I will not erase all my hard work this week because it’s the weekend.\" -Unknown");
        motivation.add("\"It might not be easy, but it’ll be worth it.\" -Unknown");
        motivation.add("\"Make your fear of losing your greatest motivator.\" -Unknown");
        motivation.add("\"You will never always be motivated, so you must learn to be disciplined.\" -Unknown");
        motivation.add("\"I’m not a product of my circumstances. I am a product of my decisions.\" -Stephen Covey");
        String this_motivation = motivation.get(new Random().nextInt(motivation.size()));
        while (last_motivation.equals(this_motivation)) {
            this_motivation = motivation.get(new Random().nextInt(motivation.size()));
        }
        last_motivation = this_motivation;
        return this_motivation;
    }

}
