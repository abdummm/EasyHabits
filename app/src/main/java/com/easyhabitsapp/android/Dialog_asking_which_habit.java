package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dialog_asking_which_habit extends DialogFragment {
    private View mview;

    private dialog_choosing_habit_listen_cancel listener;

    public interface dialog_choosing_habit_listen_cancel {
        void on_habit_choose(String text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_to_show_list_of_habits, container);
        this.mview = rootView;
        /*if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }*/
        set_up_the_recycle();
        cancel_button_listen();
        return rootView;
    }

    private void cancel_button_listen() {
        Button button_saying_cancel_in_corner_of_dialog = mview.findViewById(R.id.button_saying_cancel_in_corner_of_dialog);
        button_saying_cancel_in_corner_of_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    dismiss();
                }
            }
        });
    }

    private void set_up_the_recycle() {
        final String tag = getTag();
        final ArrayList<Bad_habit_example_item> bad_habits = new ArrayList<>();
        if (tag == null || tag.equals("tag")) {
            bad_habits.add(new Bad_habit_example_item("Custom"));
        }
        bad_habits.add(new Bad_habit_example_item("All"));
        bad_habits.add(new Bad_habit_example_item("Video games"));
        bad_habits.add(new Bad_habit_example_item("Social media"));
        bad_habits.add(new Bad_habit_example_item("Impulsive shopping"));
        bad_habits.add(new Bad_habit_example_item("Porn / masturbating"));
        bad_habits.add(new Bad_habit_example_item("Smoking / Vaping"));
        bad_habits.add(new Bad_habit_example_item("Weed / Marijuana"));
        bad_habits.add(new Bad_habit_example_item("Alcohol"));
        bad_habits.add(new Bad_habit_example_item("Drugs"));
        bad_habits.add(new Bad_habit_example_item("Cursing"));
        bad_habits.add(new Bad_habit_example_item("Procrastination"));
        bad_habits.add(new Bad_habit_example_item("Lying"));
        bad_habits.add(new Bad_habit_example_item("Coffee"));
        bad_habits.add(new Bad_habit_example_item("Fast food"));
        bad_habits.add(new Bad_habit_example_item("Sugar"));
        bad_habits.add(new Bad_habit_example_item("Over eating"));
        bad_habits.add(new Bad_habit_example_item("Gambling"));
        bad_habits.add(new Bad_habit_example_item("Self harm"));
        bad_habits.add(new Bad_habit_example_item("Over working"));
        bad_habits.add(new Bad_habit_example_item("Over sleeping"));
        if (tag != null && tag.equals("post")) {
            bad_habits.add(new Bad_habit_example_item("Other"));
        }
        RecyclerView recycle_to_show_bad_habits_only = mview.findViewById(R.id.recycle_to_show_bad_habits_only);
        recycle_to_show_bad_habits_only.setHasFixedSize(true);
        Bad_habit_adapter adapter = new Bad_habit_adapter(bad_habits);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycle_to_show_bad_habits_only.setLayoutManager(layoutManager);
        recycle_to_show_bad_habits_only.setAdapter(adapter);
        adapter.setOnItemClickListener(new Bad_habit_adapter.OnItemClickListener2() {
            @Override
            public void onItemClick(String habit) {
                if (getActivity() != null) {
                    Intent i = new Intent().putExtra("habit_name", habit);
                    if (getTargetFragment() != null) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                    }
                    if (getDialog() != null) {
                        getDialog().dismiss();
                    } else {
                        dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int real_width = (displayMetrics.widthPixels / 100) * 80;
            getDialog().getWindow().setLayout(real_width, height);
            //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }
}
