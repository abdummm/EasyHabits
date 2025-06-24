package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;

public class Alert_dialog_show extends Application {
    private ok_button_clicked ok_button_clicked_listen;

    public void set_ok_button_listen(ok_button_clicked listener) {
        ok_button_clicked_listen = listener;
    }

    public interface ok_button_clicked {
        void ok_button_clicked();
    }

    public void show_alert_dialog(Context context,String title,String body){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(body)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ok_button_clicked_listen.ok_button_clicked();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}
