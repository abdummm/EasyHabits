package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class Dialog_thank_user_for_purchase extends DialogFragment {
    private View mview;
    private int coins;
    private boolean ready_to_dismiss = false;

    /*public Dialog_thank_user_for_purchase(int award, boolean is_this_an_award, int gift) {
        this.award = award;
        this.is_this_an_award = is_this_an_award;
        this.gift = gift;
    }*/

    public Dialog_thank_user_for_purchase(int coins) {
        this.coins = coins;
    }

     /*public Dialog_thank_user_for_purchase(int award, boolean is_this_an_award, int gift,boolean ready) {
         this.award = award;
         this.is_this_an_award = is_this_an_award;
         this.gift = gift;
         this.ready = ready;
     }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_thank_user_for_purchase, container);
        this.mview = rootView;
        ok_button_listen();
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
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void set_text_for_awawrd_or_gift() {
        TextView title_of_the_dialog = mview.findViewById(R.id.title_of_the_dialog);
        TextView text_saying_months_beside_award = mview.findViewById(R.id.text_saying_months_beside_award);
        TextView text_award_image = mview.findViewById(R.id.text_award_image);
        TextView text_saying_gift_is_sent = mview.findViewById(R.id.text_saying_gift_is_sent);
        ProgressBar progressBar_cyclic_for_the_gift_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_gift_dialog);
        text_saying_months_beside_award.setVisibility(View.VISIBLE);
        text_award_image.setVisibility(View.VISIBLE);
        text_saying_gift_is_sent.setVisibility(View.VISIBLE);
        progressBar_cyclic_for_the_gift_dialog.setVisibility(View.INVISIBLE);
        text_award_image.setText(String.valueOf(coins));
        text_saying_months_beside_award.setText("coins");
        title_of_the_dialog.setText("Purchase Successful");
        text_saying_gift_is_sent.setText("Coins were credited to your account");
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void ok_button_listen() {
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ready_to_dismiss) {
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Please wait while your coins are being registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void set_konfetti() {
        KonfettiView konfetti_in_gift_for_others = mview.findViewById(R.id.konfetti_in_gift_for_others);
        EmitterConfig emitterConfig = new Emitter(1, TimeUnit.HOURS).perSecond(100);
        konfetti_in_gift_for_others.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.BOTTOM)
                        .spread(Spread.ROUND)
                        //.shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 15f)
                        .position(new Position.Relative(0.0, 0.0).between(new Position.Relative(1.0, 0.0)))
                        .build());
    }

    public void loaded() {
        set_text_for_awawrd_or_gift();
        set_konfetti();
        ready_to_dismiss = true;
    }
}
