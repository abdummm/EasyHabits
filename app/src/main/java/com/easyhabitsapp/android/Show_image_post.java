package com.easyhabitsapp.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Show_image_post extends Fragment {

    public Show_image_post() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back_button_click_lsitener();
        set_the_image();
        hide_bottom();
        change_the_back_ground_color();
        click_on_the_image_lsiten();
        make_status_bar_black();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_image_post, container, false);
    }

    private void set_the_image() {
        if (getView() != null) {
            Bundle bundle = getArguments();
            String base64 = bundle.getString("base64");
            SubsamplingScaleImageView image_base_64_base_for_post_show_image = getView().findViewById(R.id.image_base_64_base_for_post_show_image);
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image_base_64_base_for_post_show_image.setImage(ImageSource.bitmap(decodedByte));
        }
    }

    private void back_button_click_lsitener() {
        if (getView() != null) {
            Button back_button_in_base_64_image_view = getView().findViewById(R.id.back_button_in_base_64_image_view);
            back_button_in_base_64_image_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
                    navView.setVisibility(View.VISIBLE);
                    color_bar_normal_again();
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    getActivity().getSupportFragmentManager().beginTransaction().remove(Show_image_post.this).show(old_fragment).commit();
                }
            });
        }
    }

    private void hide_bottom() {
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);
    }

    private void change_the_back_ground_color() {
        Button back_button_in_base_64_image_view = getView().findViewById(R.id.back_button_in_base_64_image_view);
        Drawable drawable_for_buttons_three = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
        drawable_for_buttons_three = DrawableCompat.wrap(drawable_for_buttons_three);
        DrawableCompat.setTint(drawable_for_buttons_three, Color.parseColor("#59000000"));
        back_button_in_base_64_image_view.setBackground(drawable_for_buttons_three);
    }

    private void click_on_the_image_lsiten() {
        if (getView() != null) {
            SubsamplingScaleImageView image_base_64_base_for_post_show_image = getView().findViewById(R.id.image_base_64_base_for_post_show_image);
            final View back_button_in_photo_for_base_64_post = getView().findViewById(R.id.back_button_in_photo_for_base_64_post);
            final Button back_button_in_base_64_image_view = getView().findViewById(R.id.back_button_in_base_64_image_view);
            image_base_64_base_for_post_show_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (back_button_in_photo_for_base_64_post.getVisibility() == View.VISIBLE) {
                        back_button_in_photo_for_base_64_post.setVisibility(View.INVISIBLE);
                        back_button_in_base_64_image_view.setVisibility(View.INVISIBLE);

                    } else {
                        back_button_in_photo_for_base_64_post.setVisibility(View.VISIBLE);
                        back_button_in_base_64_image_view.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void make_status_bar_black() {
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.black));
    }
    public void color_bar_normal_again(){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    }
}