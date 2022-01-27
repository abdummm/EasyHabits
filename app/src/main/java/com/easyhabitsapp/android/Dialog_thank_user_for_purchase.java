 package com.easyhabitsapp.android;

 import android.content.Context;
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
 import androidx.constraintlayout.widget.ConstraintLayout;
 import androidx.constraintlayout.widget.ConstraintSet;
 import androidx.fragment.app.DialogFragment;

 import nl.dionsegijn.konfetti.KonfettiView;
 import nl.dionsegijn.konfetti.emitters.StreamEmitter;
 import nl.dionsegijn.konfetti.models.Shape;
 import nl.dionsegijn.konfetti.models.Size;

 public class Dialog_thank_user_for_purchase extends DialogFragment {
    private View mview;
    private boolean is_this_an_award; // true of award
    private int award;
    private int gift;
    private boolean is_this_coin = false;
    private int coins;
    private boolean ready_to_dismiss = false;

    public Dialog_thank_user_for_purchase(int award, boolean is_this_an_award, int gift) {
        this.award = award;
        this.is_this_an_award = is_this_an_award;
        this.gift = gift;
    }

    public Dialog_thank_user_for_purchase(int coins) {
        is_this_coin = true;
        this.coins = coins;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_thank_user_for_purchase, container);
        this.mview = rootView;
        set_the_views(0);
        ok_button_listen();
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
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
        TextView text_saying_months_beside_award = mview.findViewById(R.id.text_saying_months_beside_award);
        TextView text_award_image = mview.findViewById(R.id.text_award_image);
        View show_the_award_image = mview.findViewById(R.id.show_the_award_image);
        TextView title_of_dialog = mview.findViewById(R.id.title_of_dialog);
        TextView text_saying_gift_is_sent = mview.findViewById(R.id.text_saying_gift_is_sent);
        if (is_this_coin) {
            show_the_award_image.setVisibility(View.GONE);
            text_award_image.setText(String.valueOf(coins));
            text_saying_months_beside_award.setText("coins");
            ConstraintLayout constraintLayout = mview.findViewById(R.id.dialog_to_thank_user_for_purchase);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.text_saying_gift_is_sent, ConstraintSet.TOP, R.id.text_award_image, ConstraintSet.BOTTOM, (int) convertDpToPixel(15f, getContext()));
            constraintSet.applyTo(constraintLayout);
            title_of_dialog.setText("Purchase Successful!!");
            text_saying_gift_is_sent.setText("Coins was added to your account");
        } else {
            if (is_this_an_award) {
                text_saying_months_beside_award.setVisibility(View.GONE);
                text_award_image.setVisibility(View.GONE);
                if (award == 1) {
                    show_the_award_image.setBackgroundResource(R.drawable.silver_award_png);
                } else if (award == 2) {
                    show_the_award_image.setBackgroundResource(R.drawable.gold_award_png);
                } else if (award == 3) {
                    show_the_award_image.setBackgroundResource(R.drawable.ic_plat_award_posts);
                }
            } else {
                show_the_award_image.setVisibility(View.GONE);
                text_award_image.setText(String.valueOf(gift));
                if (gift == 1) {
                    text_saying_months_beside_award.setText("month");
                } else {
                    text_saying_months_beside_award.setText("months");
                }
                ConstraintLayout constraintLayout = mview.findViewById(R.id.dialog_to_thank_user_for_purchase);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.text_saying_gift_is_sent, ConstraintSet.TOP, R.id.text_award_image, ConstraintSet.BOTTOM, (int) convertDpToPixel(15f, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void ok_button_listen() {
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready_to_dismiss) {
                    dismiss();
                } else {
                    if (is_this_coin) {
                        Toast.makeText(getContext(), "Please wait while your coins are being registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Please wait while gift is being sent", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

     private void set_konfetti() {
         final KonfettiView konfettiView = mview.findViewById(R.id.konfetti_in_gift_for_others);
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

     private void set_the_views(int mode) {
         View show_the_award_image = mview.findViewById(R.id.show_the_award_image);
         TextView text_saying_gift_is_sent = mview.findViewById(R.id.text_saying_gift_is_sent);
         TextView text_award_image = mview.findViewById(R.id.text_award_image);
         TextView text_saying_months_beside_award = mview.findViewById(R.id.text_saying_months_beside_award);
         ProgressBar progressBar_cyclic_for_the_gift_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_gift_dialog);
         if (mode == 0) {
             show_the_award_image.setVisibility(View.INVISIBLE);
             text_saying_gift_is_sent.setVisibility(View.INVISIBLE);
             text_award_image.setVisibility(View.INVISIBLE);
             text_saying_months_beside_award.setVisibility(View.INVISIBLE);
             progressBar_cyclic_for_the_gift_dialog.setVisibility(View.VISIBLE);
         } else if (mode == 1){
             show_the_award_image.setVisibility(View.VISIBLE);
             text_saying_gift_is_sent.setVisibility(View.VISIBLE);
             text_award_image.setVisibility(View.VISIBLE);
             text_saying_months_beside_award.setVisibility(View.VISIBLE);
             progressBar_cyclic_for_the_gift_dialog.setVisibility(View.INVISIBLE);
         }
     }
     public void loaded(){
         set_the_views(1);
         set_text_for_awawrd_or_gift();
         set_konfetti();
         ready_to_dismiss = true;
     }
}
