package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.emitters.StreamEmitter;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Dialog_subscribe_to_premuim extends DialogFragment {
    private View mview;
    private String mode;
    private boolean loaded;

    public Dialog_subscribe_to_premuim(String mode,boolean loaded) {
        this.mode = mode;
        this.loaded = loaded;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_subscribe_to_premuim, container);
        this.mview = rootView;
        ok_button_listen();
        set_the_text();
        hide_text_show_progress();
        if(loaded){
            loaded();
        }
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setCancelable(false);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels / 100) * 90;
            int height = (displayMetrics.heightPixels / 100) * 40;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void ok_button_listen() {
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loaded){
                    dismiss();
                } else {
                    Toast.makeText(getContext(),"Please wait while subscription is being registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void set_the_text() {
        TextView text_award_image = mview.findViewById(R.id.text_award_image);
        if (mode.equals("month")) {
            text_award_image.setText("you have successfully subscribed to monthly premium!!");
        } else if (mode.equals("year")) {
            text_award_image.setText("you have successfully subscribed to yearly premium!!");
        }
    }

    private void set_konfetti() {
        final KonfettiView konfettiView = mview.findViewById(R.id.konfetti_in_subscribe_to_premuim);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, StreamEmitter.INDEFINITE);
    }

    public void loaded(){
        TextView text_award_image = mview.findViewById(R.id.text_award_image);
        ProgressBar progressBar_cyclic_for_the_subscribe_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_subscribe_dialog);
        loaded = true;
        set_konfetti();
        text_award_image.setVisibility(View.VISIBLE);
        progressBar_cyclic_for_the_subscribe_dialog.setVisibility(View.INVISIBLE);
    }
    private void hide_text_show_progress(){
        TextView text_award_image = mview.findViewById(R.id.text_award_image);
        ProgressBar progressBar_cyclic_for_the_subscribe_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_subscribe_dialog);
        text_award_image.setVisibility(View.INVISIBLE);
        progressBar_cyclic_for_the_subscribe_dialog.setVisibility(View.VISIBLE);
    }
}
