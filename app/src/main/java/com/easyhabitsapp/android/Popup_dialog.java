package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

public class Popup_dialog extends AppCompatDialogFragment {
    private EditText editText_streak;
    private Popup_dialogListener listener;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_custom_pop_for_button_fragment, null);
        builder.setView(view).setTitle("Enter Custom Number").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Button custom_masturbate =  getActivity().findViewById(R.id.custom_masturbate);
                custom_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                custom_masturbate.setTextColor(Color.parseColor("#607D8B"));
                custom_masturbate.setText("Custom");
            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String times_massturbate_from_custom = editText_streak.getText().toString();
                if (times_massturbate_from_custom.trim().equals("")){
                    Toast toast = Toast.makeText(getActivity(), "Input can't be empty", Toast.LENGTH_SHORT);
                    toast.show();
                    Button custom_masturbate = getActivity().findViewById(R.id.custom_masturbate);
                    custom_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                    custom_masturbate.setTextColor(Color.parseColor("#607D8B"));
                } else {
                    if (Integer.parseInt(times_massturbate_from_custom) < 1000){
                        listener.send_data(times_massturbate_from_custom);

                    } else {
                        Button custom_masturbate = getActivity().findViewById(R.id.custom_masturbate);
                        custom_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                        custom_masturbate.setTextColor(Color.parseColor("#607D8B"));
                        custom_masturbate.setText("Custom");
                        Toast toast = Toast.makeText(getActivity(), "Input can't be greater than '999.' ;-)", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
        editText_streak = view.findViewById(R.id.edit_number_for_custom_streak);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (Popup_dialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface Popup_dialogListener {
        void send_data(String custom_masturbate_send_data);
    }
}
