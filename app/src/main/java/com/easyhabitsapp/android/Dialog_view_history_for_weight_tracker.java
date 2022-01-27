package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Dialog_view_history_for_weight_tracker extends DialogFragment {
    private View mview;
    private Recycle_view_view_history_adapter adapter;
    private ArrayList<Recycle_view_item_view_history> list = new ArrayList<>();
    private Dialog_history_dismmiss_listen listener;

    public interface Dialog_history_dismmiss_listen {
        void on_dialoge_gone_history();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_view_history_weight_tracker, container);
        this.mview = rootView;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }
        set_up_the_recycle_view();
        ok_button_listen();
        check_if_list_is_empty();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void set_up_the_recycle_view() {
        if (getActivity() != null && getContext() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String weight = sharedPreferences.getString("weight", "");
            String units = sharedPreferences.getString("units", "");
            if (weight != null) {
                Log.w("weight", weight);
                String[] weight_split = weight.split("split");
                list = new ArrayList<>();
                if (units != null && units.equals("metric")) {
                    for (int i = 0; i < weight_split.length; i++) {
                        String[] split_weight_to_small = weight_split[i].split("small_divide");
                        String[] split_to_days = return_month_and_day(Long.parseLong(split_weight_to_small[0])).split(" ");
                        String first_month = split_to_days[0].concat("\n").concat(split_to_days[1]);
                        list.add(new Recycle_view_item_view_history(split_weight_to_small[1].concat(" kg"), first_month));
                    }
                } else {
                    for (int i = 0; i < weight_split.length; i++) {
                        String[] split_weight_to_small = weight_split[i].split("small_divide");
                        String[] split_to_days = return_month_and_day(Long.parseLong(split_weight_to_small[0])).split(" ");
                        String first_month = split_to_days[0].concat("\n").concat(split_to_days[1]);
                        list.add(new Recycle_view_item_view_history(String.format("%.1f",Float.parseFloat(split_weight_to_small[1])*2.205f).concat(" lb"), first_month));
                    }
                }
                RecyclerView recycle_view_to_view_history = mview.findViewById(R.id.recycle_view_to_view_history);
                recycle_view_to_view_history.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                adapter = new Recycle_view_view_history_adapter(list);
                recycle_view_to_view_history.setLayoutManager(layoutManager);
                recycle_view_to_view_history.setAdapter(adapter);
                adapter.setOnItemClickListener(new Recycle_view_view_history_adapter.OnItemClickListener() {
                    @Override
                    public void onEditClick(int position) {
                        if (getActivity() != null) {
                            dismiss();
                            Bottom_sheet_to_edit_weight_view_history bottom_sheet_to_edit_weight_view_history = new Bottom_sheet_to_edit_weight_view_history();
                            bottom_sheet_to_edit_weight_view_history.show(getActivity().getSupportFragmentManager(), String.valueOf(position));
                        }
                    }

                    @Override
                    public void onDeleteClick(final int position) {
                        if (list.size() > 1) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Delete entry")
                                    .setMessage("Are you sure you want to delete this entry?")

                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            list.remove(position);
                                            adapter.notifyDataSetChanged();
                                            check_if_list_is_empty();
                                            delete_the_weight_from_shared(position);
                                        }
                                    })

                                    // A null listener allows the button to dismiss the dialog and take no further action.
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        } else {
                            Toast.makeText(getActivity(), "Cant delete: you must keep at least one entry ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        }
    }

    private String return_month_and_day(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        String month_and_day_first = formatter.format(calendar.getTime());
        String[] splitter = month_and_day_first.split("_");
        String month = return_month_short(Integer.parseInt(splitter[1]));
        String day = String.valueOf(Integer.parseInt(splitter[0]));
        return month.concat(" ").concat(day);
    }

    private String return_month_short(int month) {
        if (month == 1) {
            return "Jan";
        } else if (month == 2) {
            return "Feb.";
        } else if (month == 3) {
            return "Mar.";
        } else if (month == 4) {
            return "Apr.";
        } else if (month == 5) {
            return "May";
        } else if (month == 6) {
            return "Jun.";
        } else if (month == 7) {
            return "Jul.";
        } else if (month == 8) {
            return "Aug.";
        } else if (month == 9) {
            return "Sep.";
        } else if (month == 10) {
            return "Oct.";
        } else if (month == 11) {
            return "Nov.";
        } else {
            return "Dec.";
        }
    }

    private void ok_button_listen() {
        Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_dialoge_gone_history();
                dismiss();
            }
        });
    }

    private void check_if_list_is_empty() {
        TextView text_saying_no_history_available_in_dialog = mview.findViewById(R.id.text_saying_no_history_available_in_dialog);
        RecyclerView recycle_view_to_view_history = mview.findViewById(R.id.recycle_view_to_view_history);
        if (recycle_view_to_view_history.getAdapter() != null) {
            if (recycle_view_to_view_history.getAdapter().getItemCount() == 0) {
                text_saying_no_history_available_in_dialog.setVisibility(View.VISIBLE);
                recycle_view_to_view_history.setVisibility(View.INVISIBLE);
            } else {
                text_saying_no_history_available_in_dialog.setVisibility(View.INVISIBLE);
                recycle_view_to_view_history.setVisibility(View.VISIBLE);
            }
        } else {
            if (list.size() == 0) {
                text_saying_no_history_available_in_dialog.setVisibility(View.VISIBLE);
                recycle_view_to_view_history.setVisibility(View.INVISIBLE);
            } else {
                text_saying_no_history_available_in_dialog.setVisibility(View.INVISIBLE);
                recycle_view_to_view_history.setVisibility(View.VISIBLE);
            }
        }
    }

    private void delete_the_weight_from_shared(int position) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        if (weight != null) {
            String[] spliiter = weight.split("split");
            String save_me = "";
            for (int i = 0; i < spliiter.length; i++) {
                if (i != position) {
                    save_me = save_me.concat(spliiter[i]).concat("split");
                }
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("weight", save_me);
            myEdit.commit();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Dialog_history_dismmiss_listen) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement bottomsheetlistener");
        }
    }
}
