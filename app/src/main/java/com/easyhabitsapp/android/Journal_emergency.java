package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
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
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class Journal_emergency extends AppCompatActivity implements Alert_dialog_to_edit_journal_name.Alert_dialog_to_edit_journal_name_listener, Bottom_sheet_to_change_color.BottomSheetListener, Bottom_sheet_three_dots.BottomSheetListener_for_three_dots {

    private boolean did_i_change_anything = false;
    private boolean is_fragment_visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_emergency);
        check_if_empty();
        back_button_listen();
        long_press_of_the_button_listen();
        hide_top_bar();
        button_listen();
        three_dots_button_listen();
        open_the_journal();
        text_watcher();
    }

    /* private void test() {
         final Button change_size_in_the_journal_emergency = findViewById(R.id.change_size_in_the_journal_emergency);
         change_size_in_the_journal_emergency.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 change_size_in_the_journal_emergency.performHapticFeedback(
                         HapticFeedbackConstants.VIRTUAL_KEY,
                         HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING  // Ignore device's setting. Otherwise, you can use FLAG_IGNORE_VIEW_SETTING to ignore view's setting.
                 );
             }
         });
     }*/
    private void back_button_listen() {
        Button button_shadow_for_back_button_journal = findViewById(R.id.button_shadow_for_back_button_journal);
        button_shadow_for_back_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Journal_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void long_press_of_the_button_listen() {
        final Button button_shadow_for_text_size_button_journal = findViewById(R.id.button_shadow_for_text_size_button_journal);
        final Button button_shadow_for_color_button_journal = findViewById(R.id.button_shadow_for_color_button_journal);
        final Button button_shadow_for_highlight_button_journal = findViewById(R.id.button_shadow_for_highlight_button_journal);
        button_shadow_for_text_size_button_journal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vibrate_the_device();
                Toast toast = Toast.makeText(Journal_emergency.this, "Change text size", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
        button_shadow_for_color_button_journal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vibrate_the_device();
                Toast toast = Toast.makeText(Journal_emergency.this, "Change text color", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
        button_shadow_for_highlight_button_journal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vibrate_the_device();
                Toast toast = Toast.makeText(Journal_emergency.this, "Highlight text", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
    }

    private void vibrate_the_device() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private boolean is_text_selected() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int startSelection = text_view_viewing_the_whole_journal.getSelectionStart();
        int endSelection = text_view_viewing_the_whole_journal.getSelectionEnd();
        if (startSelection == endSelection) {
            return false;
        } else {
            return true;
        }
    }

    private void button_listen() {
        Button button_shadow_for_bold_button_journal = findViewById(R.id.button_shadow_for_bold_button_journal);
        Button button_shadow_for_italic_button_journal = findViewById(R.id.button_shadow_for_italic_button_journal);
        Button button_shadow_for_under_line_button_journal = findViewById(R.id.button_shadow_for_under_line_button_journal);
        Button button_shadow_for_strike_through_button_journal = findViewById(R.id.button_shadow_for_strike_through_button_journal);
        Button button_shadow_for_text_size_button_journal = findViewById(R.id.button_shadow_for_text_size_button_journal);
        Button button_shadow_for_color_button_journal = findViewById(R.id.button_shadow_for_color_button_journal);
        Button button_shadow_for_highlight_button_journal = findViewById(R.id.button_shadow_for_highlight_button_journal);
        final EditText text_saying_title_of_the_journal = findViewById(R.id.text_saying_title_of_the_journal);
        button_shadow_for_text_size_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        open_dialog();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to change its size", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_color_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        Bottom_sheet_to_change_color bottom_sheet_to_change_color = new Bottom_sheet_to_change_color();
                        bottom_sheet_to_change_color.show(getSupportFragmentManager(), "sheet_tag");
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to change its color", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_highlight_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        if (!check_if_text_contains_span(4)) {
                            apply_highlight();
                        } else {
                            remove_highlight();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to highlight it", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_bold_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        if (!check_if_text_contains_span(1)) {
                            apply_bold();
                        } else {
                            remove_bold();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to make it bold", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_italic_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        if (!check_if_text_contains_span(2)) {
                            apply_italic();
                        } else {
                            remove_italic();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to make it italic", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_under_line_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        if (!check_if_text_contains_span(3)) {
                            apply_under_line();
                        } else {
                            remove_underline();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to underline it", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        button_shadow_for_strike_through_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_saying_title_of_the_journal.hasFocus()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't apply styles to title", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (is_text_selected()) {
                        if (!check_if_text_contains_span(5)) {
                            apply_strike_through();
                        } else {
                            remove_strike_through();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select text to strike through it", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    @Override
    public void copy_text(int text_size) {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannableString = apply_past_spans();
        spannableString.setSpan(new AbsoluteSizeSpan(text_size, true), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannableString);
        text_view_viewing_the_whole_journal.setSelection(end);
    }

    private void open_dialog() {
        Alert_dialog_to_edit_journal_name alert_dialog_to_edit_journal_name = new Alert_dialog_to_edit_journal_name();
        alert_dialog_to_edit_journal_name.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void on_color_choose(String text) {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        if (text.equals("teal")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("red")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#e6194B")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("green")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3cb44b")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("blue")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#4363d8")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("orange")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f58231")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("navy")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9A6324")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        } else if (text.equals("black")) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
        }
        text_view_viewing_the_whole_journal.setSelection(end);
    }

    private Spannable apply_past_spans() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        Spannable spannable = new SpannableString(text_view_viewing_the_whole_journal.getText().toString());
        ForegroundColorSpan[] mSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), ForegroundColorSpan.class);
        for (int i = 0; i < mSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(mSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(mSpans[i]);
            int span = mSpans[i].getForegroundColor();
            spannable.setSpan(new ForegroundColorSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(backgroundColorSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(backgroundColorSpans[i]);
            int span = backgroundColorSpans[i].getBackgroundColor();
            spannable.setSpan(new BackgroundColorSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(styleSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(styleSpans[i]);
            int span = styleSpans[i].getStyle();
            spannable.setSpan(new StyleSpan(span), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        AbsoluteSizeSpan[] absoluteSizeSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), AbsoluteSizeSpan.class);
        for (int i = 0; i < absoluteSizeSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(absoluteSizeSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(absoluteSizeSpans[i]);
            int span = absoluteSizeSpans[i].getSize();
            spannable.setSpan(new AbsoluteSizeSpan(span, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), UnderlineSpan.class);
        for (int i = 0; i < underlineSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(underlineSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(underlineSpans[i]);
            spannable.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), StrikethroughSpan.class);
        for (int i = 0; i < strikethroughSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(strikethroughSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(strikethroughSpans[i]);
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
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int before = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        spannable.setSpan(new BackgroundColorSpan(Color.parseColor("#FFFF00")), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannable);
        text_view_viewing_the_whole_journal.setSelection(before);
    }

    private void apply_bold() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int start = text_view_viewing_the_whole_journal.getSelectionStart();
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        spannable.setSpan(new StyleSpan(Typeface.BOLD), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannable);
        text_view_viewing_the_whole_journal.setSelection(start, end);
    }

    private void apply_italic() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int start = text_view_viewing_the_whole_journal.getSelectionStart();
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        spannable.setSpan(new StyleSpan(Typeface.ITALIC), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannable);
        text_view_viewing_the_whole_journal.setSelection(start, end);
    }

    private void apply_under_line() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int start = text_view_viewing_the_whole_journal.getSelectionStart();
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        spannable.setSpan(new UnderlineSpan(), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannable);
        text_view_viewing_the_whole_journal.setSelection(start, end);
    }

    private void apply_strike_through() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        int start = text_view_viewing_the_whole_journal.getSelectionStart();
        int end = text_view_viewing_the_whole_journal.getSelectionEnd();
        Spannable spannable = apply_past_spans();
        spannable.setSpan(new StrikethroughSpan(), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_viewing_the_whole_journal.setText(spannable);
        text_view_viewing_the_whole_journal.setSelection(start, end);
    }

    private boolean check_if_text_contains_span(int which) {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        if (which == 1) {
            StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                if (styleSpans[i].getStyle() == Typeface.BOLD) {
                    return true;
                }
            }
            return false;
        } else if (which == 2) {
            StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StyleSpan.class);
            for (int i = 0; i < styleSpans.length; i++) {
                if (styleSpans[i].getStyle() == Typeface.ITALIC) {
                    return true;
                }
            }
            return false;
        } else if (which == 3) {
            UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), UnderlineSpan.class);
            if (underlineSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        } else if (which == 4) {
            BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), BackgroundColorSpan.class);
            if (backgroundColorSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StrikethroughSpan.class);
            if (strikethroughSpans.length > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void remove_bold() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int span = styleSpans[i].getStyle();
            if (span == Typeface.BOLD) {
                text_view_viewing_the_whole_journal.getText().removeSpan(styleSpans[i]);
            }
        }
    }

    private void remove_italic() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int span = styleSpans[i].getStyle();
            if (span == Typeface.ITALIC) {
                text_view_viewing_the_whole_journal.getText().removeSpan(styleSpans[i]);
            }
        }
    }

    private void remove_underline() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), UnderlineSpan.class);
        for (int i = 0; i < underlineSpans.length; i++) {
            text_view_viewing_the_whole_journal.getText().removeSpan(underlineSpans[i]);
        }
    }

    private void remove_strike_through() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), StrikethroughSpan.class);
        for (int i = 0; i < strikethroughSpans.length; i++) {
            text_view_viewing_the_whole_journal.getText().removeSpan(strikethroughSpans[i]);
        }
    }

    private void remove_highlight() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_journal.getText().getSpans(text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionEnd(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            text_view_viewing_the_whole_journal.getText().removeSpan(backgroundColorSpans[i]);
        }
    }

    private void three_dots_button_listen() {
        Button button_shadow_for_3_dots_button_journal = findViewById(R.id.button_shadow_for_3_dots_button_journal);
        button_shadow_for_3_dots_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_there_is_one_file()) {
                    Bottom_sheet_three_dots bottom_sheet_three_dots = new Bottom_sheet_three_dots();
                    bottom_sheet_three_dots.show(getSupportFragmentManager(),"no_delete");
                } else {
                    Bottom_sheet_three_dots bottom_sheet_three_dots = new Bottom_sheet_three_dots();
                    bottom_sheet_three_dots.show(getSupportFragmentManager(), "three_dots");

                }
            }
        });
    }

    /*private void take_photo_button_listen() {
        Button button_shadow_for_camera_button_journal = findViewById(R.id.button_shadow_for_camera_button_journal);
        button_shadow_for_camera_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_for_camera_journal bottom_sheet_for_camera_journal = new Bottom_sheet_for_camera_journal();
                bottom_sheet_for_camera_journal.show(getSupportFragmentManager(), "camera");
            }
        });
    }*/

    @Override
    public void onButtonChoose(String text) {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        EditText text_saying_title_of_the_journal = findViewById(R.id.text_saying_title_of_the_journal);
        if (text.equals("select_all")) {
            text_view_viewing_the_whole_journal.setSelection(0, text_view_viewing_the_whole_journal.getText().toString().length());
        } else if (text.equals("copy_all")) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text_view_viewing_the_whole_journal.getText().toString());
            clipboard.setPrimaryClip(clip);
        } else if (text.equals("add_another_journal")) {
            save_the_journal();
            create_new_journal();
            open_the_journal();
        } else if (text.equals("delete_journal")) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Journal")
                    .setMessage("Are you sure you want to delete ".concat(text_saying_title_of_the_journal.getText().toString().trim()))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            delete_journal();
                            open_the_journal();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        } else if(text.equals("switch_journal")){
            save_the_journal();
            final Dialog_to_show_journals_to_switch dialog_to_show_journals_to_switch = new Dialog_to_show_journals_to_switch();
            dialog_to_show_journals_to_switch.show(getSupportFragmentManager(),"tag");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.executePendingTransactions();
            if (dialog_to_show_journals_to_switch.getDialog() != null) {
                dialog_to_show_journals_to_switch.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        open_the_journal();
                        getSupportFragmentManager().beginTransaction().remove(dialog_to_show_journals_to_switch).commitAllowingStateLoss();
                    }
                });
            }
        }
    }

    /*@Override
    public void onButtonChoose_camera(String text) {
        if (text.equals("take_photo")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.CAMERA};
                    requestPermissions(permission, 1000);
                } else {
                    open_camera();
                }
            } else {
                open_camera();
            }
        } else {

        }
    }

    private void open_camera() {
        String file_name = "photograph";
        File storage_directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File image_file = File.createTempFile(file_name, ".jpg", storage_directory);
            current_photo_bath = image_file.getAbsolutePath();
            Uri uri = FileProvider.getUriForFile(this, "com.example.learn1.file_provider", image_file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    open_camera();
                } else {
                    Toast.makeText(this, "Please accept permission in order to be able to add photos", Toast.LENGTH_LONG).show();
                }
            }
        }
    }*/

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = (displayMetrics.widthPixels - (int) dip_to_pixels(40)) / 2;
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap photo = BitmapFactory.decodeFile(current_photo_bath);
            ExifInterface ei = null;
            try {
                ei = new ExifInterface(current_photo_bath);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                Log.w("asdas",String.valueOf(orientation));
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        Log.w("asdas",String.valueOf(orientation));
                        photo = rotate(photo, 90);

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        photo = rotate(photo, 180);

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        photo = rotate(photo, 270);

                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        photo = flip(photo, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        photo = flip(photo, false, true);

                }
            } catch (IOException e) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
            }
            //saveToInternalStorage(photo);
            float ratio = (float) photo.getHeight() / photo.getWidth();
            Drawable drawable = new BitmapDrawable(getResources(), photo);
            drawable.setBounds(0, 0, width, (int) (width * ratio));
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text_view_viewing_the_whole_journal.getText());
            spannableStringBuilder.append("\n\n");
            int value_for_for = (int) ((width / 2) / width_of_space());
            for (int value = 0; value < value_for_for; value++) {
                spannableStringBuilder.append(" ");
            }
            text_view_viewing_the_whole_journal.setText(spannableStringBuilder);
            text_view_viewing_the_whole_journal.setSelection(text_view_viewing_the_whole_journal.getText().toString().length() - 1, text_view_viewing_the_whole_journal.getText().toString().length());
            Spannable spannable = apply_past_spans();
            spannable.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), text_view_viewing_the_whole_journal.getSelectionStart(), text_view_viewing_the_whole_journal.getSelectionStart() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_viewing_the_whole_journal.setText(spannable);
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(text_view_viewing_the_whole_journal.getText());
            spannableStringBuilder2.append("\n\n");
            text_view_viewing_the_whole_journal.setText(spannableStringBuilder2);
            text_view_viewing_the_whole_journal.requestFocus();
            text_view_viewing_the_whole_journal.setSelection(text_view_viewing_the_whole_journal.getText().toString().length());
        }
    }*/

    private float dip_to_pixels(float dip) {
        Resources r = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    /*private float width_of_space() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        TextPaint paint = new TextPaint();
        paint.setTextSize(text_view_viewing_the_whole_journal.getTextSize());
        return paint.measureText(" ");
    }

    public int spToPx(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }*/

    private void save_the_journal() {
        EditText text_saying_title_of_the_journal = findViewById(R.id.text_saying_title_of_the_journal);
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        String name;
        String body;
        name = text_saying_title_of_the_journal.getText().toString();
        body = text_view_viewing_the_whole_journal.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        String shared = sharedPreferences.getString("journal", "");
        String save_me = "";
        if (shared != null) {
            String[] max_split = shared.split("##&&&&max_divide&&&&##");
            for (int i = 0; i < max_split.length; i++) {
                if (max_split[i].contains("@#$$#@i_am_the_last_edit_for_journal@#$$#@")) {
                    save_me = save_me.concat(name.concat("!!@@##%%&&split_small!!@@##%%&&").concat(body).concat("!!@@##%%&&split_small!!@@##%%&&").concat(save_all_the_spans()).concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@").concat("!!@@##%%&&split_small!!@@##%%&&").concat(String.valueOf(System.currentTimeMillis())).concat("##&&&&max_divide&&&&##"));
                } else {
                    save_me = save_me.concat(max_split[i]).concat("##&&&&max_divide&&&&##");
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("journal", save_me);
            edit.commit();
        }
    }

    private String save_all_the_spans() {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        String save_me = "";
        ForegroundColorSpan[] mSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), ForegroundColorSpan.class);
        for (int i = 0; i < mSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(mSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(mSpans[i]);
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
        BackgroundColorSpan[] backgroundColorSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), BackgroundColorSpan.class);
        for (int i = 0; i < backgroundColorSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(backgroundColorSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(backgroundColorSpans[i]);
            save_me = save_me.concat("highlight").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        StyleSpan[] styleSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), StyleSpan.class);
        for (int i = 0; i < styleSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(styleSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(styleSpans[i]);
            int span = styleSpans[i].getStyle();
            if (span == Typeface.BOLD) {
                save_me = save_me.concat("bold").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            } else if (span == Typeface.ITALIC) {
                save_me = save_me.concat("italic").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
            }
        }
        AbsoluteSizeSpan[] absoluteSizeSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), AbsoluteSizeSpan.class);
        for (int i = 0; i < absoluteSizeSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(absoluteSizeSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(absoluteSizeSpans[i]);
            int span = absoluteSizeSpans[i].getSize();
            save_me = save_me.concat("size").concat("_").concat(String.valueOf(span)).concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        UnderlineSpan[] underlineSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), UnderlineSpan.class);
        for (int i = 0; i < underlineSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(underlineSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(underlineSpans[i]);
            save_me = save_me.concat("underline").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        StrikethroughSpan[] strikethroughSpans = text_view_viewing_the_whole_journal.getText().getSpans(0, text_view_viewing_the_whole_journal.getText().toString().length(), StrikethroughSpan.class);
        for (int i = 0; i < strikethroughSpans.length; i++) {
            int start = text_view_viewing_the_whole_journal.getText().getSpanStart(strikethroughSpans[i]);
            int end = text_view_viewing_the_whole_journal.getText().getSpanEnd(strikethroughSpans[i]);
            save_me = save_me.concat("strikethrough").concat("_").concat(String.valueOf(start)).concat("_").concat(String.valueOf(end)).concat("##split_split_split_for_styles_split_split_split##");
        }
        return save_me;
    }

    private void open_the_journal() {
        EditText text_saying_title_of_the_journal = findViewById(R.id.text_saying_title_of_the_journal);
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        SharedPreferences sharedPreferences = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        String shared = sharedPreferences.getString("journal", "");
        if (shared != null) {
            String[] max_split = shared.split("##&&&&max_divide&&&&##");
            if (shared.contains("##&&&&max_divide&&&&##")) {
                for (int i = 0; i < max_split.length; i++) {
                    if (max_split[i].contains("@#$$#@i_am_the_last_edit_for_journal@#$$#@")) {
                        max_split[i] = max_split[i].replace("@#$$#@i_am_the_last_edit_for_journal@#$$#@", "");
                        String[] splitter_inside_split = max_split[i].split("!!@@##%%&&split_small!!@@##%%&&",-1);
                        text_saying_title_of_the_journal.setText(splitter_inside_split[0]);
                        set_the_span(splitter_inside_split[1], splitter_inside_split[2]);
                        update_the_text_edit_at_bottom(Long.parseLong( splitter_inside_split[3]));
                    }
                }
            } else {
                String[] splitter_inside_split = max_split[0].split("!!@@##%%&&split_small!!@@##%%&&",-1);
                text_saying_title_of_the_journal.setText(splitter_inside_split[0]);
                text_view_viewing_the_whole_journal.setText(splitter_inside_split[1]);
                set_the_span(splitter_inside_split[1], splitter_inside_split[2]);
                update_the_text_edit_at_bottom(Long.parseLong( splitter_inside_split[3]));
            }
        }
    }

    private void set_the_span(String before_text, String past_spans) {
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        if (before_text.equals("")) {
            text_view_viewing_the_whole_journal.setText("");
        } else {
                String[] big_split = past_spans.split("##split_split_split_for_styles_split_split_split##");
                Spannable spannable = new SpannableString(before_text);
                for (int i = 0; i < big_split.length; i++) {
                    String[] start_end_split = big_split[i].split("_");
                    if (big_split[i].contains("teal")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("red")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#e6194B")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("green")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3cb44b")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("blue")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#4363d8")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("orange")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f58231")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("navy")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9A6324")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("black")) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("highlight")) {
                        spannable.setSpan(new BackgroundColorSpan(Color.parseColor("#FFFF00")), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("bold")) {
                        spannable.setSpan(new StyleSpan(Typeface.BOLD), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("italic")) {
                        spannable.setSpan(new StyleSpan(Typeface.ITALIC), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("size")) {
                        spannable.setSpan(new AbsoluteSizeSpan(Integer.parseInt(start_end_split[1]), true), Integer.parseInt(start_end_split[2]), Integer.parseInt(start_end_split[3]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("underline")) {
                        spannable.setSpan(new UnderlineSpan(), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else if (big_split[i].contains("strikethrough")) {
                        spannable.setSpan(new StrikethroughSpan(), Integer.parseInt(start_end_split[1]), Integer.parseInt(start_end_split[2]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                text_view_viewing_the_whole_journal.setText(spannable);
                    text_view_viewing_the_whole_journal.setSelection(text_view_viewing_the_whole_journal.getText().toString().length());
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(did_i_change_anything) {
            save_the_journal();
        }
    }
    private boolean is_there_is_one_file(){
        SharedPreferences sharedPreferences = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        String shared = sharedPreferences.getString("journal", "");
        if(shared!=null){
            String[] counter = shared.split("##&&&&max_divide&&&&##");
            if(counter.length>1){
                return false;
            } else {
                return true;
            }
        }
        return true;
    }
    private void create_new_journal() {
        Am_i_paid am_i_paid = new Am_i_paid(this);
        if (am_i_paid.did_user_pay()) {
            SharedPreferences shared = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            String old_string = shared.getString("journal", "");
            if (old_string != null) {
                old_string = old_string.replace("@#$$#@i_am_the_last_edit_for_journal@#$$#@", "");
                edit.putString("journal", old_string.concat("My Journal").concat("!!@@##%%&&split_small!!@@##%%&&").concat("!!@@##%%&&split_small!!@@##%%&&").concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@").concat("!!@@##%%&&split_small!!@@##%%&&").concat(String.valueOf(System.currentTimeMillis())).concat("##&&&&max_divide&&&&##"));
                edit.commit();
            } else {
                edit.putString("journal", "My Journal".concat("!!@@##%%&&split_small!!@@##%%&&").concat("!!@@##%%&&split_small!!@@##%%&&").concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@").concat("!!@@##%%&&split_small!!@@##%%&&").concat(String.valueOf(System.currentTimeMillis())).concat("##&&&&max_divide&&&&##"));
                edit.commit();
            }
        } else {
            Buy_premuim buy_premuim = new Buy_premuim("add more than 1 counter", true, "activity");
            hide_activity();
            buy_premuim.set_the_function(new Buy_premuim.show_the_activity() {
                @Override
                public void show_the_activity_function() {
                    show_activity();
                }
            });
            getSupportFragmentManager().beginTransaction().add(R.id.hold_buy_premuim_fragment_container_in_journal, buy_premuim, "buy premium").show(buy_premuim).commit();
        }
    }
    private void delete_journal(){
        SharedPreferences shared = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        String old_string = shared.getString("journal","");
        if(old_string!=null) {
            String[] max_split = old_string.split("##&&&&max_divide&&&&##");
            String save_me = "";
            for (int i = 0; i < max_split.length; i++) {
                if(!max_split[i].contains("@#$$#@i_am_the_last_edit_for_journal@#$$#@")){
                    save_me = save_me.concat(max_split[i]).concat("##&&&&max_divide&&&&##");
                }
            }
            String[] second_split = save_me.split("##&&&&max_divide&&&&##");
            second_split[second_split.length-1] = second_split[second_split.length-1].concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@");
            String final_save="";
            for (int i = 0;i<second_split.length;i++){
                final_save = final_save.concat(second_split[i]).concat("##&&&&max_divide&&&&##");
            }
            edit.putString("journal",final_save);
            edit.commit();
        }
    }
    private void update_the_text_edit_at_bottom(long time){
        TextView last_edit_for_notes = findViewById(R.id.last_edit_for_notes);
        Calendar real_calendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String set_me;
        String month = Simplify_the_time.return_month(String.valueOf(calendar.get(Calendar.MONTH)));
        month = month.substring(0,3);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        set_me = month.concat(".").concat(" ").concat(day);
        if(calendar.get(Calendar.YEAR) != real_calendar.get((Calendar.YEAR))){
            set_me = set_me.concat(", ").concat(String.valueOf(calendar.get(Calendar.YEAR)));
        }
        last_edit_for_notes.setText("last Edited: ".concat(set_me));
    }
    private void text_watcher(){
        EditText text_view_viewing_the_whole_journal = findViewById(R.id.text_view_viewing_the_whole_journal);
        text_view_viewing_the_whole_journal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                update_the_text_edit_at_bottom(System.currentTimeMillis());
                did_i_change_anything = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void check_if_empty() {
        SharedPreferences shared = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        if (shared.getString("journal","").equals("")) {
            edit.putString("journal", "My Journal".concat("!!@@##%%&&split_small!!@@##%%&&").concat("!!@@##%%&&split_small!!@@##%%&&").concat("!!@@##%%&&split_small!!@@##%%&&").concat(String.valueOf(System.currentTimeMillis())).concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@").concat("##&&&&max_divide&&&&##"));
            edit.commit();
        }
    }

    private void hide_activity(){
        is_fragment_visible = true;
        ConstraintLayout hold_buy_premuim_fragment_container_in_journal = findViewById(R.id.hold_buy_premuim_fragment_container_in_journal);
        hold_buy_premuim_fragment_container_in_journal.setVisibility(View.INVISIBLE);
    }

    private void show_activity(){
        is_fragment_visible = true;
        ConstraintLayout hold_buy_premuim_fragment_container_in_journal = findViewById(R.id.hold_buy_premuim_fragment_container_in_journal);
        hold_buy_premuim_fragment_container_in_journal.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(is_fragment_visible){
            show_activity();
            hide_premuim();

        } else {
            this.finish();
        }
    }

    private void hide_premuim(){
        Buy_premuim old_fragment = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (old_fragment != null) {
             getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
        }
    }
}