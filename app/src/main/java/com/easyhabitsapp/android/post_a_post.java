package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class post_a_post extends Fragment {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private Bitmap public_bitmap;
    private FirebaseUser currentFirebaseUser;
    private String category_global = "All";
    private int streak_global;
    private FirebaseFunctions firebaseFunctions;

    public post_a_post() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_a_post_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        back_button_listen();
        long_press_of_the_button_listen();
        button_listen();
        post_button_listen();
        watching_text_title();
        add_a_photo_button_lsiten();
        make_keyboard_button_be_next();
        //choose_cat_button_listen();
    }

    private void back_button_listen() {
        if (getView() != null && getContext() != null) {
            Button button_shadow_for_back_button_post = getView().findViewById(R.id.button_shadow_for_back_button_post);
            button_shadow_for_back_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (should_i_remove_post_a_post_fragment()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Discard Post?")
                                .setMessage("Are you sure you want to discard this post?")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (getActivity() != null) {
                                            post_a_post old_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                                            Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                                            if (old_fragment != null && new_fragment != null) {
                                                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                                            }
                                        }
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    } else {
                        if (getActivity() != null) {
                            post_a_post old_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                            Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                            if (old_fragment != null && new_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                            }
                        }
                    }
                }
            });
        }
    }

    private void long_press_of_the_button_listen() {
        if (getView() != null) {
            final Button button_shadow_for_text_size_button_post = getView().findViewById(R.id.button_shadow_for_text_size_button_post);
            final Button button_shadow_for_color_button_post = getView().findViewById(R.id.button_shadow_for_color_button_post);
            final Button button_shadow_for_highlight_button_post = getView().findViewById(R.id.button_shadow_for_highlight_button_post);
            button_shadow_for_text_size_button_post.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate_the_device();
                    Toast toast = Toast.makeText(getActivity(), "Change text size", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
            });
            button_shadow_for_color_button_post.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate_the_device();
                    Toast toast = Toast.makeText(getActivity(), "Change text color", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
            });
            button_shadow_for_highlight_button_post.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate_the_device();
                    Toast toast = Toast.makeText(getActivity(), "Highlight text", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
            });
        }
    }

    private void vibrate_the_device() {
        if (getActivity() != null) {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.EFFECT_HEAVY_CLICK));
                } else {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        }
    }

    private boolean is_text_selected() {
        EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
        int startSelection = text_view_viewing_the_whole_post.getSelectionStart();
        int endSelection = text_view_viewing_the_whole_post.getSelectionEnd();
        if (startSelection == endSelection) {
            return false;
        } else {
            return true;
        }
    }

    private void button_listen() {
        if (getView() != null) {
            Button button_shadow_for_bold_button_post = getView().findViewById(R.id.button_shadow_for_bold_button_post);
            Button button_shadow_for_italic_button_post = getView().findViewById(R.id.button_shadow_for_italic_button_post);
            Button button_shadow_for_under_line_button_post = getView().findViewById(R.id.button_shadow_for_under_line_button_post);
            Button button_shadow_for_strike_through_button_post = getView().findViewById(R.id.button_shadow_for_strike_through_button_post);
            Button button_shadow_for_text_size_button_post = getView().findViewById(R.id.button_shadow_for_text_size_button_post);
            Button button_shadow_for_color_button_post = getView().findViewById(R.id.button_shadow_for_color_button_post);
            Button button_shadow_for_highlight_button_post = getView().findViewById(R.id.button_shadow_for_highlight_button_post);
            final EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            button_shadow_for_text_size_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            open_dialog();
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to change its size", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_color_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            final Bottom_sheet_color_post bottom_sheet_color_post = new Bottom_sheet_color_post();
                            bottom_sheet_color_post.setTargetFragment(post_a_post.this, 100);
                            bottom_sheet_color_post.show(getActivity().getSupportFragmentManager(), "post");
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.executePendingTransactions();
                            if (bottom_sheet_color_post.getDialog() != null) {
                                bottom_sheet_color_post.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        ;
                                        getActivity().getSupportFragmentManager().beginTransaction().remove(bottom_sheet_color_post).commitAllowingStateLoss();
                                    }
                                });
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to change its color", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_highlight_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            if (!check_if_text_contains_span(4)) {
                                apply_highlight();
                            } else {
                                remove_highlight();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to highlight it", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_bold_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            if (!check_if_text_contains_span(1)) {
                                apply_bold();
                            } else {
                                remove_bold();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to make it bold", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_italic_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            if (!check_if_text_contains_span(2)) {
                                apply_italic();
                            } else {
                                remove_italic();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to make it italic", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_under_line_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            if (!check_if_text_contains_span(3)) {
                                apply_under_line();
                            } else {
                                remove_underline();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to underline it", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
            button_shadow_for_strike_through_button_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_saying_title_of_the_post.hasFocus()) {
                        Toast toast = Toast.makeText(getActivity(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (is_text_selected()) {
                            if (!check_if_text_contains_span(5)) {
                                apply_strike_through();
                            } else {
                                remove_strike_through();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Please select text to strike through it", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
        }
    }

    private void open_dialog() {
        if (getActivity() != null) {
            final Alert_text_size_posts alert_text_size_posts = new Alert_text_size_posts();
            alert_text_size_posts.setTargetFragment(this, 1000);
            alert_text_size_posts.show(getActivity().getSupportFragmentManager(), "post");
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.executePendingTransactions();
            if (alert_text_size_posts.getDialog() != null) {
                alert_text_size_posts.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (getActivity() != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(alert_text_size_posts).commitAllowingStateLoss();
                        }
                    }
                });
            }
        }
    }

    private Spannable apply_past_spans() {
        EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
        Spannable spannable = new SpannableString(text_view_viewing_the_whole_post.getText().toString());
        ForegroundColorSpan[] mSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), ForegroundColorSpan.class);
        for (int i = 0; i < mSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(mSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(mSpans[i]);
            int span = mSpans[i].getForegroundColor();
            spannable.setSpan(new ForegroundColorSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(backgroundColorSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(backgroundColorSpans[i]);
            int span = backgroundColorSpans[i].getBackgroundColor();
            spannable.setSpan(new BackgroundColorSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(styleSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(styleSpans[i]);
            int span = styleSpans[i].getStyle();
            spannable.setSpan(new StyleSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        AbsoluteSizeSpan[] absoluteSizeSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), AbsoluteSizeSpan.class);
        for (int i = 0; i < absoluteSizeSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(absoluteSizeSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(absoluteSizeSpans[i]);
            int span = absoluteSizeSpans[i].getSize();
            spannable.setSpan(new AbsoluteSizeSpan(span, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), UnderlineSpan.class);
        for (int i = 0; i < underlineSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(underlineSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(underlineSpans[i]);
            spannable.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), StrikethroughSpan.class);
        for (int i = 0; i < strikethroughSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(strikethroughSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(strikethroughSpans[i]);
            spannable.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        /*ImageSpan[] imageSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), ImageSpan.class);
        for (int i = 0; i < imageSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(imageSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(imageSpans[i]);
            Drawable span = imageSpans[i].getDrawable();
            spannable.setSpan(new ImageSpan(span, ImageSpan.ALIGN_BASELINE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }*/
        return spannable;
    }

    private void apply_highlight() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            int before = text_view_viewing_the_whole_post.getSelectionEnd();
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new BackgroundColorSpan(Color.parseColor("#FFFF00")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_post.setText(spannable);
            text_view_viewing_the_whole_post.setSelection(before);
        }
    }

    private void apply_bold() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            int start = text_view_viewing_the_whole_post.getSelectionStart();
            int end = text_view_viewing_the_whole_post.getSelectionEnd();
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new StyleSpan(Typeface.BOLD), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_post.setText(spannable);
            text_view_viewing_the_whole_post.setSelection(start, end);
        }
    }

    private void apply_italic() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            int start = text_view_viewing_the_whole_post.getSelectionStart();
            int end = text_view_viewing_the_whole_post.getSelectionEnd();
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new StyleSpan(Typeface.ITALIC), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_post.setText(spannable);
            text_view_viewing_the_whole_post.setSelection(start, end);
        }
    }

    private void apply_under_line() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            int start = text_view_viewing_the_whole_post.getSelectionStart();
            int end = text_view_viewing_the_whole_post.getSelectionEnd();
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new UnderlineSpan(), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_post.setText(spannable);
            text_view_viewing_the_whole_post.setSelection(start, end);
        }
    }

    private void apply_strike_through() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            int start = text_view_viewing_the_whole_post.getSelectionStart();
            int end = text_view_viewing_the_whole_post.getSelectionEnd();
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new StrikethroughSpan(), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_post.setText(spannable);
            text_view_viewing_the_whole_post.setSelection(start, end);
        }
    }

    private boolean check_if_text_contains_span(int which) {
        EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
        if (which == 1) {
            StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                if (styleSpans[i].getStyle() == Typeface.BOLD) {
                    return true;
                }
            }
            return false;
        } else if (which == 2) {
            StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                if (styleSpans[i].getStyle() == Typeface.ITALIC) {
                    return true;
                }
            }
            return false;
        } else if (which == 3) {
            UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), UnderlineSpan.class);
            if (underlineSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        } else if (which == 4) {
            BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), BackgroundColorSpan.class);
            if (backgroundColorSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StrikethroughSpan.class);
            if (strikethroughSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void remove_bold() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                int span = styleSpans[i].getStyle();
                if (span == Typeface.BOLD) {
                    text_view_viewing_the_whole_post.getText().removeSpan(styleSpans[i]);
                }
            }
        }
    }

    private void remove_italic() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                int span = styleSpans[i].getStyle();
                if (span == Typeface.ITALIC) {
                    text_view_viewing_the_whole_post.getText().removeSpan(styleSpans[i]);
                }
            }
        }
    }

    private void remove_underline() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), UnderlineSpan.class);
            for (int i = 0; i < underlineSpans.length; i++) {
                text_view_viewing_the_whole_post.getText().removeSpan(underlineSpans[i]);
            }
        }
    }

    private void remove_strike_through() {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), StrikethroughSpan.class);
            for (int i = 0; i < strikethroughSpans.length; i++) {
                text_view_viewing_the_whole_post.getText().removeSpan(strikethroughSpans[i]);
            }
        }
    }

    private void remove_highlight() {
        EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_post.getText().getSpans(text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            text_view_viewing_the_whole_post.getText().removeSpan(backgroundColorSpans[i]);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == Activity.RESULT_OK) {
                    EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
                    int end = text_view_viewing_the_whole_post.getSelectionEnd();
                    Spannable spannableString = apply_past_spans();
                    int text_size = data.getIntExtra("text size", 20);
                    spannableString.setSpan(new AbsoluteSizeSpan(text_size, true), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannableString);
                    text_view_viewing_the_whole_post.setSelection(end);
                }
                break;
            case 100:
                EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
                int end = text_view_viewing_the_whole_post.getSelectionEnd();
                Spannable spannable = apply_past_spans();
                String text = data.getStringExtra("text color");
                if (text.equals("teal")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("red")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#e6194B")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("green")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3cb44b")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("blue")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#4363d8")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("orange")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f58231")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("navy")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9A6324")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                } else if (text.equals("black")) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), text_view_viewing_the_whole_post.getSelectionStart(), text_view_viewing_the_whole_post.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_viewing_the_whole_post.setText(spannable);
                }
                text_view_viewing_the_whole_post.setSelection(end);
                break;
            case 1001:
                category_global = data.getStringExtra("habit_name");
                //make_the_check_box_visisble_or_no();
                //rename_button();
                break;
            case 951:
                if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                    put_the_photo(data);
                }
                break;
        }
    }

    public boolean should_i_remove_post_a_post_fragment() {
        if (getView() != null) {
            EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            ImageView image_view_showing_the_photo_befoore_posting_it_in_posts = getView().findViewById(R.id.image_view_showing_the_photo_befoore_posting_it_in_posts);
            if (!text_saying_title_of_the_post.getText().toString().equals("") || !text_view_viewing_the_whole_post.getText().toString().equals("") || image_view_showing_the_photo_befoore_posting_it_in_posts.getVisibility() == View.VISIBLE) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void post_button_listen() {
        if (getView() != null) {
            Button post_button_int_the_bottom_right = getView().findViewById(R.id.post_button_int_the_bottom_right);
            final EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            final EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            post_button_int_the_bottom_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    post_button_was_clicked();
                }
            });
        }
    }

    private void post_button_was_clicked() {
        if (getView() != null) {
            final EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            final EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            if ((text_view_viewing_the_whole_post.getText().toString().equals("") && text_view_viewing_the_whole_post.getVisibility() == View.VISIBLE) || text_saying_title_of_the_post.getText().toString().equals("")) {
                if (text_saying_title_of_the_post.getText().toString().equals("")) {
                    text_saying_title_of_the_post.setError("Can't be empty");
                    Toast toast = Toast.makeText(getActivity(), "Title can't be empty", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (text_view_viewing_the_whole_post.getText().toString().equals("") && text_view_viewing_the_whole_post.getVisibility() == View.VISIBLE) {
                    text_view_viewing_the_whole_post.setError("Can't be empty");
                    Toast toast = Toast.makeText(getActivity(), "Body can't be empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else if (!isNetworkAvailable()) {
                Toast toast = Toast.makeText(getActivity(), "Phone not connected to the internet", Toast.LENGTH_SHORT);
                toast.show();
            }/* else if (return_the_name().equals("no_name_found_135")) {
                Dialog_to_make_the_user_enter_their_name dialog_to_make_the_user_enter_their_name = new Dialog_to_make_the_user_enter_their_name();
                dialog_to_make_the_user_enter_their_name.set_card_clicked_reply(new Dialog_to_make_the_user_enter_their_name.name_is_done() {
                    @Override
                    public void name_is_done() {
                        post_button_was_clicked();
                    }
                });
                dialog_to_make_the_user_enter_their_name.show(getParentFragmentManager(), "");
            }*/ else {
                Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_written_posted,false);
                post_the_information();
            }
        }
    }

    private boolean isNetworkAvailable() {
        if (getActivity() != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } else {
            return false;
        }
    }

    private void watching_text_title() {
        if (getView() != null && getContext() != null) {
            final EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            text_saying_title_of_the_post.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int text_length = text_saying_title_of_the_post.getText().toString().length();
                    if (text_length >= 300) {
                        Toast.makeText(getContext(), "Title can't be longer than 300 characters", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }


    private void post_the_information() {
        if (getView() != null) {
            EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            final ImageView image_view_showing_the_photo_befoore_posting_it_in_posts = getView().findViewById(R.id.image_view_showing_the_photo_befoore_posting_it_in_posts);
            //final CheckBox check_box_to_check_show_streak_or_no = getView().findViewById(R.id.check_box_to_check_show_streak_or_no);
            String body;
            final boolean image;
            if (image_view_showing_the_photo_befoore_posting_it_in_posts.getVisibility() == View.VISIBLE) {
                Bitmap bitmap = public_bitmap;
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();
                long lengthbmp = imageInByte.length;
                if (lengthbmp > 122880) {
                    byte[] byte_bitmap = compressImage(bitmap);
                    long previous_length = 0;
                    while (byte_bitmap.length > 122880) {
                        byte_bitmap = compressImage(bitmap);
                        bitmap = BitmapFactory.decodeByteArray(byte_bitmap, 0, byte_bitmap.length);
                        if (previous_length != 0 && Math.abs(previous_length - byte_bitmap.length) < 5000) {
                            break;
                        }
                        previous_length = byte_bitmap.length;
                    }
                    body = Base64.encodeToString(byte_bitmap, Base64.DEFAULT);
                } else {
                    body = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                }
                image = true;
            } else {
                body = text_view_viewing_the_whole_post.getText().toString().trim();
                image = false;
            }
            final String title = text_saying_title_of_the_post.getText().toString().trim();
            final String body_to_save = body;
            ArrayList<Long> awards = new ArrayList<>();
            awards.add(0L);
            awards.add(0L);
            awards.add(0L);
            final String span = save_all_the_spans();
            ArrayList<String> upvote_list = new ArrayList<>();
            upvote_list.add(currentFirebaseUser.getUid());
            ArrayList<String> down_vote_lsit = new ArrayList<>();
            ArrayList<HashMap<String, Object>> comments = new ArrayList<>();
            Map<String, Object> post_objects = new HashMap<>();
            ArrayList<String> report_list = new ArrayList<>();
            post_objects.put("userid", currentFirebaseUser.getUid());
            //post_objects.put("category", category);
            post_objects.put("title", title);
            post_objects.put("body", body);
            post_objects.put("span", span);
            //post_objects.put("time_posted", FieldValue.serverTimestamp());
            post_objects.put("reports", report_list);
            post_objects.put("comments", comments);
            post_objects.put("score", 0);
            post_objects.put("awards", awards);
            post_objects.put("up_vote_list", upvote_list);
            post_objects.put("down_vote_list", down_vote_lsit);
            post_objects.put("likes", 1);
            post_objects.put("number_of_reports", 0);
            post_objects.put("dev", false);
            post_objects.put("name", return_the_name());
            //post_objects.put("last_updated", FieldValue.serverTimestamp());
            /*if (check_box_to_check_show_streak_or_no.getVisibility() == View.VISIBLE && check_box_to_check_show_streak_or_no.isChecked()) {
                post_objects.put("streak", streak_global);
            } else {
                post_objects.put("streak", -1);
            }*/
            Dialog_custom_loading dialog_custom_loading = new Dialog_custom_loading("Posting...");
            dialog_custom_loading.show(getParentFragmentManager(), "");
            firebaseFunctions = FirebaseFunctions.getInstance();
            firebaseFunctions
                    .getHttpsCallable("put_a_post")
                    .call(post_objects)
                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                        @Override
                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                            if (String.valueOf(task.getResult().getData()).equals("error")) {
                                dialog_custom_loading.loaded();
                                Toast toast = Toast.makeText(getActivity(), "Error can't publish post", Toast.LENGTH_LONG);
                                toast.show();
                            } else if (String.valueOf(task.getResult().getData()).contains("success id: ")) {
                                dialog_custom_loading.loaded();
                                String id = String.valueOf(task.getResult().getData()).replace("success id: ", "");
                                Toast toast = Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT);
                                toast.show();
                                /*if (check_box_to_check_show_streak_or_no.getVisibility() == View.VISIBLE && check_box_to_check_show_streak_or_no.isChecked()) {
                                    save_the_id(title, body_to_save, span, System.currentTimeMillis(), "", streak_global, id);
                                } else {
                                    save_the_id(title, body_to_save, span, System.currentTimeMillis(), "", -1, id);
                                }*/
                                save_the_id(title, body_to_save, span, System.currentTimeMillis(), "", -1, id);
                                update_your_posts_from_another_fragment();
                                post_a_post old_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                                Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                                if (old_fragment != null && new_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                                }
                            }
                            return "result";
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast toast = Toast.makeText(getActivity(), "Error can't publish post", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });

            /*final String id = database.collection("posts").document().getId();
            database.collection("posts").document(id)
                    .set(post_objects)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            if (getActivity() != null) {
                                Toast toast = Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT);
                                toast.show();
                                if (check_box_to_check_show_streak_or_no.getVisibility() == View.VISIBLE && check_box_to_check_show_streak_or_no.isChecked()) {
                                    save_the_id(title, body_to_save, span, System.currentTimeMillis(), category, streak_global, id);
                                } else {
                                    save_the_id(title, body_to_save, span, System.currentTimeMillis(), category, -1, id);
                                }
                                update_your_posts_from_another_fragment();
                                post_a_post old_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                                Posts_fragment new_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                                if (old_fragment != null && new_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (image_view_showing_the_photo_befoore_posting_it_in_posts.getVisibility() == View.VISIBLE) {
                                Toast toast = Toast.makeText(getActivity(), "Error can't publish post. Maybe the image is too big?", Toast.LENGTH_LONG);
                                toast.show();
                            } else {
                                Toast toast = Toast.makeText(getActivity(), "Error can't publish post", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });*/
        }
    }

    private String save_all_the_spans() {
        EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
        String save_me = "";
        ForegroundColorSpan[] mSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), ForegroundColorSpan.class);
        for (int i = 0; i < mSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(mSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(mSpans[i]);
            int span = mSpans[i].getForegroundColor();
            if (span == Color.parseColor("#607D8B")) {
                save_me = save_me.concat("teal").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#e6194B")) {
                save_me = save_me.concat("red").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#3cb44b")) {
                save_me = save_me.concat("green").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#4363d8")) {
                save_me = save_me.concat("blue").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#f58231")) {
                save_me = save_me.concat("orange").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#9A6324")) {
                save_me = save_me.concat("navy").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Color.parseColor("#000000")) {
                save_me = save_me.concat("black").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            }
        }
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(backgroundColorSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(backgroundColorSpans[i]);
            save_me = save_me.concat("highlight").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        StyleSpan[] styleSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(styleSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(styleSpans[i]);
            int span = styleSpans[i].getStyle();
            if (span == Typeface.BOLD) {
                save_me = save_me.concat("bold").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Typeface.ITALIC) {
                save_me = save_me.concat("italic").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            }
        }
        AbsoluteSizeSpan[] absoluteSizeSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), AbsoluteSizeSpan.class);
        for (int i = 0; i < absoluteSizeSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(absoluteSizeSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(absoluteSizeSpans[i]);
            int span = absoluteSizeSpans[i].getSize();
            save_me = save_me.concat("size").concat("_").concat(String.valueOf(span)).concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), UnderlineSpan.class);
        for (int i = 0; i < underlineSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(underlineSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(underlineSpans[i]);
            save_me = save_me.concat("underline").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_post.getText().getSpans(0, text_view_viewing_the_whole_post.getText().toString().length(), StrikethroughSpan.class);
        for (int i = 0; i < strikethroughSpans.length; i++) {
            int start = text_view_viewing_the_whole_post.getText().getSpanStart(strikethroughSpans[i]);
            int end = text_view_viewing_the_whole_post.getText().getSpanEnd(strikethroughSpans[i]);
            save_me = save_me.concat("strikethrough").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        return save_me;
    }

    private void save_the_id(String title, String body, String span, long time, String category, int streak, String id) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("posted_posts", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("posts", "");
            title = title.replace("big_divide", "").replace("small_split", "");
            body = body.replace("big_divide", "").replace("small_split", "");
            if (old != null && !old.isEmpty()) {
                myEdit.putString("posts", old.concat(return_the_name()).concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(id).concat("big_divide"));
            } else {
                myEdit.putString("posts", return_the_name().concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(id).concat("big_divide"));
            }
            myEdit.commit();

        }
    }

    private void add_a_photo_button_lsiten() {
        if (getView() != null) {
            Button button_to_press_to_add_photo_in_post_a_post = getView().findViewById(R.id.button_to_press_to_add_photo_in_post_a_post);
            final EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            final ImageView image_view_showing_the_photo_befoore_posting_it_in_posts = getView().findViewById(R.id.image_view_showing_the_photo_befoore_posting_it_in_posts);
            button_to_press_to_add_photo_in_post_a_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_view_viewing_the_whole_post.getText().toString().equals("")) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);//
                        startActivityForResult(Intent.createChooser(intent, "Select File"), 951);
                        image_view_showing_the_photo_befoore_posting_it_in_posts.setVisibility(View.VISIBLE);

                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Do you want to add a photo?")
                                .setMessage("Adding a photo will remove text from body that you have written. Posts with photo are only allowed to have a title")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setType("image/*");
                                        intent.setAction(Intent.ACTION_GET_CONTENT);//
                                        startActivityForResult(Intent.createChooser(intent, "Select File"), 951);
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    }
                }
            });
        }
    }

    private void put_the_photo(Intent data) {
        if (getView() != null) {
            EditText text_view_viewing_the_whole_post = getView().findViewById(R.id.text_view_viewing_the_whole_post);
            ImageView image_view_showing_the_photo_befoore_posting_it_in_posts = getView().findViewById(R.id.image_view_showing_the_photo_befoore_posting_it_in_posts);
            Bitmap bitmap = null;
            if (data != null) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            text_view_viewing_the_whole_post.setText("");
            text_view_viewing_the_whole_post.setVisibility(View.INVISIBLE);
            image_view_showing_the_photo_befoore_posting_it_in_posts.setVisibility(View.VISIBLE);
            image_view_showing_the_photo_befoore_posting_it_in_posts.setImageBitmap(bitmap);
            public_bitmap = bitmap;
        }
    }

    private void make_keyboard_button_be_next() {
        if (getView() != null) {
            EditText text_saying_title_of_the_post = getView().findViewById(R.id.text_saying_title_of_the_post);
            text_saying_title_of_the_post.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            text_saying_title_of_the_post.setRawInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    private static byte[] compressImage(Bitmap bmp) {
        float maxHeight = 1280.0f;
        float maxWidth = 1280.0f;
        Bitmap scaledBitmap = null;


        int actualHeight = bmp.getHeight();
        int actualWidth = bmp.getWidth();
        float imgRatio = (float) actualWidth / (float) actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) bmp.getWidth();
        float ratioY = actualHeight / (float) bmp.getHeight();
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.WEBP, 70, out);
        return out.toByteArray();
    }

    /*private void choose_cat_button_listen() {
        if (getView() != null) {
            Button button_to_choose_category_in_the_place_where_you_write_the_post = getView().findViewById(R.id.button_to_choose_category_in_the_place_where_you_write_the_post);
            button_to_choose_category_in_the_place_where_you_write_the_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                    dialog_asking_which_habit.setTargetFragment(post_a_post.this, 1001);
                    dialog_asking_which_habit.show(getActivity().getSupportFragmentManager(), "post");
                }
            });
        }
    }*/

    /*private int check_if_cat_already_exists() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", getContext().MODE_PRIVATE);
            String bad_habits = sharedPreferences.getString("Bad_habits", "");
            if (bad_habits != null && !bad_habits.equals("") && bad_habits.contains(category_global)) {
                String[] split_bad_habits = bad_habits.split("spit_max_for_the_bad_habits");
                for (String big_split : split_bad_habits) {
                    String[] small_split = big_split.split("split_here_bad_habits");
                    if (small_split[0].equals(category_global)) {
                        return return_the_streak(small_split[1], small_split[2]);
                    }
                }
                return -1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }*/

    /*private int return_the_streak(String name, String real_time) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("bad_habits_relapses", MODE_PRIVATE);
        String old = sharedPreferences.getString(name.toLowerCase(), "");
        String[] split_the_dates = old.split("split_here_small");
        long old_time;
        if (!old.equals("")) {
            old_time = Long.parseLong(split_the_dates[split_the_dates.length - 1]);
        } else {
            old_time = Long.parseLong(real_time);
        }
        return (int) TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - old_time);
    }*/


    /*private void make_the_check_box_visisble_or_no() {
        if (getView() != null) {
            CheckBox check_box_to_check_show_streak_or_no = getView().findViewById(R.id.check_box_to_check_show_streak_or_no);
            int value = check_if_cat_already_exists();
            check_box_to_check_show_streak_or_no.setChecked(false);
            if (value != -1) {
                streak_global = value;
                check_box_to_check_show_streak_or_no.setVisibility(View.VISIBLE);
                if (value == 1) {
                    check_box_to_check_show_streak_or_no.setText("Show Streak? 1 day");
                } else {
                    check_box_to_check_show_streak_or_no.setText("Show Streak? ".concat(String.valueOf(value)).concat(" days"));
                }
            } else {
                check_box_to_check_show_streak_or_no.setVisibility(View.INVISIBLE);
            }
        }
    }*/

    /*private void rename_button() {
        if (getView() != null) {
            Button button_to_choose_category_in_the_place_where_you_write_the_post = getView().findViewById(R.id.button_to_choose_category_in_the_place_where_you_write_the_post);
            button_to_choose_category_in_the_place_where_you_write_the_post.setText(category_global);
        }
    }*/

    private void update_your_posts_from_another_fragment() {
        if (getActivity() != null) {
            Posts_fragment posts_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (posts_fragment != null) {
                posts_fragment.update_the_your_posts();
            }
        }
    }

    private String return_the_name() {
        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            return sh.getString("name", "no_name_found_135");
        } else {
            return "Name";
        }
    }
}