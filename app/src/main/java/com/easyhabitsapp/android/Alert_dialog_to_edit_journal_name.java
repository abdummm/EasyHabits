package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class Alert_dialog_to_edit_journal_name extends AppCompatDialogFragment {
    private Alert_dialog_to_edit_journal_name_listener listener;
    Spinner spinner_to_choose_size;
    EditText edit_text_for_new_journal_name;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_to_edit_name_of_journal, null);
        edit_text_for_new_journal_name = view.findViewById(R.id.edit_text_for_new_journal_name);
        spinner_to_choose_size = view.findViewById(R.id.spinner_to_choose_size);
        builder.setView(view)
                .setTitle("Change Text Size")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(check_the_size()) {
                    if(!edit_text_for_new_journal_name.getText().toString().equals("")) {
                        int text_size = Integer.parseInt(edit_text_for_new_journal_name.getText().toString());
                        listener.copy_text(text_size);
                    } else {
                        int text_size = Integer.parseInt(spinner_to_choose_size.getSelectedItem().toString());
                        listener.copy_text(text_size);
                    }
                }
            }
        });
        set_up_spinner();
        determine_the_size();
        return builder.create();
    }

    public interface Alert_dialog_to_edit_journal_name_listener{
        void copy_text(int journal_name);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Alert_dialog_to_edit_journal_name_listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    private void determine_the_size(){
        edit_text_for_new_journal_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edit_text_for_new_journal_name.hasFocus()) {
                    spinner_to_choose_size.setSelection(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void set_up_spinner(){
        final ArrayList<String> spinner_list = new ArrayList<>();
        spinner_list.add("");
        spinner_list.add("16");
        spinner_list.add("20");
        spinner_list.add("24");
        spinner_list.add("28");
        spinner_list.add("32");
        spinner_list.add("36");
        spinner_list.add("40");
        spinner_list.add("45");
        spinner_list.add("50");
        spinner_list.add("60");
        spinner_list.add("75");
        spinner_list.add("90");
        spinner_list.add("100");
        spinner_list.add("120");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_for_reason, spinner_list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_to_choose_size.setAdapter(arrayAdapter);
        spinner_to_choose_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(spinner_to_choose_size.getSelectedItemPosition()!=0) {
                    edit_text_for_new_journal_name.clearFocus();
                    edit_text_for_new_journal_name.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private boolean check_the_size(){
        if(spinner_to_choose_size.getSelectedItemPosition()==0 && edit_text_for_new_journal_name.getText().toString().equals("")){
            return false;
        } else {
            return true;
        }
    }
}
