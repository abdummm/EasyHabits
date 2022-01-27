package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_ask_to_draw_over_other_apps extends DialogFragment {
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_get_screen_permission, container);
        this.mview = rootView;
        ok_for_permission_button();
        cancel_for_permission_button();
        return rootView;
    }
    @Override
    public void onResume() {
        if (getDialog().getWindow()!=null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (did_the_user_check()){
            dismiss();
        }
        super.onResume();
    }
    private void ok_for_permission_button(){
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    /*Uri uri = Uri.fromParts("package:", getActivity().getPackageName(), null);
                    intent.setData(uri);*/
                        startActivity(intent);
                    } catch (Exception e){
                        Toast.makeText(getActivity(), "Can;t open permission automatically. Go to setting and enable \"Screen overlay\". Search online for more details", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void cancel_for_permission_button(){
        Button cancel_for_permission = mview.findViewById(R.id.cancel_for_permission);
        cancel_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null) {
                    Intent intent = new Intent(getContext(), after_login.class);
                    intent.putExtra("Start_the_emergency_true",true);
                    getContext().startActivity(intent);
                }
            }
        });
    }
    private boolean did_the_user_check(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(getContext());
        }
        return true;
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}
