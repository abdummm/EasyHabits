package com.easyhabitsapp.android;


/**
 * A simple {@link Fragment} subclass.
 */
/*public class submit_a_quote_fragment extends Fragment {
    private ClipboardManager clipboardManager;
    private String remember_me = "::::dont_remember_me_app_dev::::";
    public submit_a_quote_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_to_submit_quote_of_the_day, container, false);

    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        load_the_user_name();
        text_change_anonymous_watcher1();
        text_change_anonymous_watcher2();
        text_change_anonymous_watcher3();
        editing_the_text_by_adding_bold_and_colors();
        paste_button();
        setting_up_the_name_once();
        scrolling_for_quote();
        button_listen();
        dealing_with_buttons_once();
        waiting_for_clicks_buttons();
        color_the_last_sentence();
    }

    private void load_the_user_name() {
    }

    private void text_change_anonymous_watcher3() {
        EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
        enter_your_own_name_when_submitting_quote_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dealing_with_the_button();
                updating_name_for_text_view();
                color_the_last_sentence();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void editing_the_text_by_adding_bold_and_colors() {
        if (getActivity() != null) {
            TextView submit_a_quote_title_in_the_top_of_fragment_text_view = getActivity().findViewById(R.id.submit_a_quote_title_in_the_top_of_fragment_text_view);
            TextView warning_user_that_the_name_will_be_shown_to_all = getActivity().findViewById(R.id.warning_user_that_the_name_will_be_shown_to_all);
            String sentence = "Note: Entered name will be publicly shown.";
            SpannableString spannableString = new SpannableString(sentence);
            int color_for_streak = Color.rgb(96, 125, 139);
            ForegroundColorSpan black_color_to_span = new ForegroundColorSpan(color_for_streak);
            StyleSpan bold_span = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(bold_span, 0, spannableString.length(), 0);
            spannableString.setSpan(black_color_to_span, spannableString.length() - 15, spannableString.length() - 1, 0);
            warning_user_that_the_name_will_be_shown_to_all.setText(spannableString);
            SpannableString spannableString1 = new SpannableString("Submit a quote and get featured!");
            spannableString1.setSpan(black_color_to_span, spannableString1.length() - 9, spannableString1.length() - 1, 0);
            submit_a_quote_title_in_the_top_of_fragment_text_view.setText(spannableString1);
            TextView submit_and_review_qoute_of_the_day_title_text_view2 = getActivity().findViewById(R.id.submit_and_review_qoute_of_the_day_title_text_view2);
            SpannableString spannableString2 = new SpannableString("Quote of the day");
            spannableString2.setSpan(bold_span, 0, spannableString2.length(), 0);
            spannableString2.setSpan(black_color_to_span, spannableString2.length() - 3, spannableString2.length(), 0);
            submit_and_review_qoute_of_the_day_title_text_view2.setText(spannableString2);
        }
    }

    private void paste_button() {
        if (getActivity() != null) {
            Button paste_to_paste_quote_into_edit_text_button = getActivity().findViewById(R.id.paste_to_paste_quote_into_edit_text_button);
            paste_to_paste_quote_into_edit_text_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        if (clipboardManager != null) {
                            if (!(clipboardManager.hasPrimaryClip())) {
                                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No text found in clip board. PLease copy text first", Toast.LENGTH_LONG);
                                toast.show();
                            } else if (!(clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
                                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                                EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
                                String keep_me = large_text_box_to_insert_quote_edit_text.getText().toString();
                                SpannableString text_from_html = new SpannableString(Html.fromHtml(Html.fromHtml(item.getText().toString()).toString()));
                                large_text_box_to_insert_quote_edit_text.setText(keep_me + text_from_html);
                                large_text_box_to_insert_quote_edit_text.setSelection(keep_me.length() + text_from_html.length());
                            } else {
                                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                                EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
                                String keep_me = large_text_box_to_insert_quote_edit_text.getText().toString();
                                large_text_box_to_insert_quote_edit_text.setText(keep_me.concat(item.getText().toString()));
                                large_text_box_to_insert_quote_edit_text.setSelection(large_text_box_to_insert_quote_edit_text.length());
                            }
                        }
                    }
                }
            });
        }
    }

    private void text_change_anonymous_watcher1() {
        EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
        large_text_box_to_insert_quote_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
                TextView the_quote_itself_show_for_review_and_edit_review_text_view = getActivity().findViewById(R.id.the_quote_itself_show_for_review_and_edit_review_text_view);
                if (!large_text_box_to_insert_quote_edit_text.getText().toString().trim().equals("")) {
                    first_letter_of_sentence_uppercase((large_text_box_to_insert_quote_edit_text.getText().toString().trim().replaceAll("[^A-Za-z0-9.,'’ ]", "")));
                } else {
                    the_quote_itself_show_for_review_and_edit_review_text_view.setText("Please submit a quote");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void text_change_anonymous_watcher2() {
        EditText enter_the_name_of_the_person_who_said_the_quote_edit_text = getActivity().findViewById(R.id.enter_the_name_of_the_person_who_said_the_quote_edit_text);
        enter_the_name_of_the_person_who_said_the_quote_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (getActivity() != null) {
                    EditText enter_the_name_of_the_person_who_said_the_quote_edit_text = getActivity().findViewById(R.id.enter_the_name_of_the_person_who_said_the_quote_edit_text);
                    if (!enter_the_name_of_the_person_who_said_the_quote_edit_text.getText().toString().trim().equals("")) {
                        make_first_letter_uppercase(enter_the_name_of_the_person_who_said_the_quote_edit_text.getText().toString());
                    } else {
                        TextView the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view = getActivity().findViewById(R.id.the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view);
                        the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view.setText("- Please submit a name");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void make_first_letter_uppercase(String string) {
        if (getActivity() != null) {
            String final_text = "";
            if (string.trim().contains(" ") && string.trim().length() > 0) {
                String[] splitter = string.split(" ");
                for (int i = 0; i < splitter.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder(splitter[i]);
                    stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
                    final_text = final_text.concat(" ").concat(stringBuilder.toString());
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder(string);
                stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
                final_text = final_text.concat(stringBuilder.toString());
            }
            final_text = ("- ").concat(final_text);
            TextView the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view = getActivity().findViewById(R.id.the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view);
            the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view.setText(final_text);
        }
    }

    private void setting_up_the_name_once() {
        load_the_user_name();
        TextView name_of_the_submitter_of_the_quote_text_view = getActivity().findViewById(R.id.name_of_the_submitter_of_the_quote_text_view);
        name_of_the_submitter_of_the_quote_text_view.setText("Submitted by: ".concat(return_the_name()));
    }

    private void first_letter_of_sentence_uppercase(String string) {
        if (getActivity() != null) {
            string = string.replace("\"", "").replace("'", "").trim();
            String first_letter = string.substring(0, 1);
            if(string.substring(string.length()-1).equals(".")){
               string =  string.substring(0,string.length()-1);
            }
            string = string.substring(1);
            string = first_letter.concat(string);
            string = "\"".concat(string);
            String[] splitter = string.split("\\.");
            String collect_sentence = splitter[0];
            for(int i = 1; i< splitter.length;i++){
                String final_sentence = splitter[i].trim().substring(0, 1).toUpperCase() + splitter[i].trim().substring(1);
                collect_sentence = collect_sentence.concat(". ").concat(final_sentence);
            }
            collect_sentence = collect_sentence.concat(".").concat("\"");
            TextView the_quote_itself_show_for_review_and_edit_review_text_view = getActivity().findViewById(R.id.the_quote_itself_show_for_review_and_edit_review_text_view);
            the_quote_itself_show_for_review_and_edit_review_text_view.setText(collect_sentence);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void scrolling_for_quote() {
        EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
        large_text_box_to_insert_quote_edit_text.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
                if (large_text_box_to_insert_quote_edit_text.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });
    }

    private void button_listen() {
        Button submit_the_quote_after_done_reviewing_button = getActivity().findViewById(R.id.submit_the_quote_after_done_reviewing_button);
        submit_the_quote_after_done_reviewing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checking_errors();
            }
        });
    }

    private void checking_errors() {
        if (getActivity() != null) {
            boolean error1 = false;
            boolean error2 = false;
            boolean error3 = false;
            EditText large_text_box_to_insert_quote_edit_text = getActivity().findViewById(R.id.large_text_box_to_insert_quote_edit_text);
            EditText enter_the_name_of_the_person_who_said_the_quote_edit_text = getActivity().findViewById(R.id.enter_the_name_of_the_person_who_said_the_quote_edit_text);
            EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
            if (large_text_box_to_insert_quote_edit_text.getText().toString().trim().equals("")) {
                error1 = true;
                large_text_box_to_insert_quote_edit_text.setError("quote cant be empty");
            }
            if (enter_the_name_of_the_person_who_said_the_quote_edit_text.getText().toString().trim().equals("")) {
                error2 = true;
                enter_the_name_of_the_person_who_said_the_quote_edit_text.setError("Name of the person who said the quote can't be empty");
            }
            if (enter_your_own_name_when_submitting_quote_edit_text.getText().toString().trim().equals("")) {
                error3 = true;
                enter_your_own_name_when_submitting_quote_edit_text.setError("You must enter your name(or nickname).");
            }
            if (!error1 && !error2 && !error3) {
                if (check_for_internet()) {
                    save_the_data();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Must be connected to internet to submit a quote", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "1 or more entries were empty. Scroll up and check errors.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private void save_the_data() {
        if (getActivity() != null) {
            TextView the_quote_itself_show_for_review_and_edit_review_text_view = getActivity().findViewById(R.id.the_quote_itself_show_for_review_and_edit_review_text_view);
            TextView the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view = getActivity().findViewById(R.id.the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view);
            TextView name_of_the_submitter_of_the_quote_text_view = getActivity().findViewById(R.id.name_of_the_submitter_of_the_quote_text_view);
            String final_message = the_quote_itself_show_for_review_and_edit_review_text_view.getText().toString() + "::split_on_me_min::" + the_user_entered_name_of_the_guy_who_said_the_quote_review_text_view.getText().toString() + "::split_on_me_min::" + name_of_the_submitter_of_the_quote_text_view.getText().toString() + "::::split_on_string_max::::";
            send_the_email(final_message);
        }
    }

    private void send_the_email(String message) {
        String email = "submit.daily.app@gmail.com";
        String subject = "quote";
        SendMail sm = new SendMail(getActivity(), email, subject, message);
        sm.execute();
        if (getActivity() != null) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Thanks! Your quote was submitted successfully.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private boolean check_for_internet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else {
            return false;
        }
    }

    private void dealing_with_buttons_once() {
        if (getActivity() != null) {
            Button i_want_to_be_visible_button_name = getActivity().findViewById(R.id.i_want_to_be_visible_button_name);
            Button i_want_to_be_anonymous_button_name = getActivity().findViewById(R.id.i_want_to_be_anonymous_button_name);
            i_want_to_be_visible_button_name.setBackgroundResource(R.drawable.color_for_botton_on);
            i_want_to_be_visible_button_name.setTextColor(Color.WHITE);
            i_want_to_be_anonymous_button_name.setBackgroundResource(R.drawable.color_for_botton_off);
            i_want_to_be_anonymous_button_name.setTextColor(Color.parseColor("#607D8B"));
            load_the_user_name();
            EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
            if (return_the_name() != null) {
                enter_your_own_name_when_submitting_quote_edit_text.setText(return_the_name());
            }
        }
    }

    private void dealing_with_the_button() {
        if (getActivity() != null) {
            EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
            Button i_want_to_be_visible_button_name = getActivity().findViewById(R.id.i_want_to_be_visible_button_name);
            Button i_want_to_be_anonymous_button_name = getActivity().findViewById(R.id.i_want_to_be_anonymous_button_name);
            if (enter_your_own_name_when_submitting_quote_edit_text.getText().toString().trim().equals("Anonymous") || enter_your_own_name_when_submitting_quote_edit_text.getText().toString().trim().equals("anonymous")) {
                i_want_to_be_anonymous_button_name.setBackgroundResource(R.drawable.color_for_botton_on);
                i_want_to_be_anonymous_button_name.setTextColor(Color.WHITE);
                i_want_to_be_visible_button_name.setBackgroundResource(R.drawable.color_for_botton_off);
                i_want_to_be_visible_button_name.setTextColor(Color.parseColor("#607D8B"));
            } else {
                i_want_to_be_visible_button_name.setBackgroundResource(R.drawable.color_for_botton_on);
                i_want_to_be_visible_button_name.setTextColor(Color.WHITE);
                i_want_to_be_anonymous_button_name.setBackgroundResource(R.drawable.color_for_botton_off);
                i_want_to_be_anonymous_button_name.setTextColor(Color.parseColor("#607D8B"));
            }
        }
    }

    private void waiting_for_clicks_buttons() {
        if (getActivity() != null) {
            Button i_want_to_be_visible_button_name = getActivity().findViewById(R.id.i_want_to_be_visible_button_name);
            Button i_want_to_be_anonymous_button_name = getActivity().findViewById(R.id.i_want_to_be_anonymous_button_name);
            i_want_to_be_visible_button_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
                        Button i_want_to_be_visible_button_name = getActivity().findViewById(R.id.i_want_to_be_visible_button_name);
                        Button i_want_to_be_anonymous_button_name = getActivity().findViewById(R.id.i_want_to_be_anonymous_button_name);
                        i_want_to_be_visible_button_name.setBackgroundResource(R.drawable.color_for_botton_on);
                        i_want_to_be_visible_button_name.setTextColor(Color.WHITE);
                        i_want_to_be_anonymous_button_name.setBackgroundResource(R.drawable.color_for_botton_off);
                        i_want_to_be_anonymous_button_name.setTextColor(Color.parseColor("#607D8B"));
                        if (enter_your_own_name_when_submitting_quote_edit_text.getText().toString().equals("Anonymous") || enter_your_own_name_when_submitting_quote_edit_text.getText().toString().equals("anonymous")) {
                            if (remember_me != null) {
                                if (remember_me.equals("::::dont_remember_me_app_dev::::")) {
                                    enter_your_own_name_when_submitting_quote_edit_text.setText("");
                                    enter_your_own_name_when_submitting_quote_edit_text.setText(return_the_name());
                                    enter_your_own_name_when_submitting_quote_edit_text.setSelection(return_the_name().length());
                                } else {
                                    enter_your_own_name_when_submitting_quote_edit_text.setText("");
                                    enter_your_own_name_when_submitting_quote_edit_text.setText(remember_me);
                                    enter_your_own_name_when_submitting_quote_edit_text.setSelection(remember_me.length());

                                }
                            } else {
                                enter_your_own_name_when_submitting_quote_edit_text.setText("");
                                enter_your_own_name_when_submitting_quote_edit_text.setText(return_the_name());
                                enter_your_own_name_when_submitting_quote_edit_text.setSelection(return_the_name().length());
                            }
                        }
                    }
                }
            });
            i_want_to_be_anonymous_button_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
                        Button i_want_to_be_visible_button_name = getActivity().findViewById(R.id.i_want_to_be_visible_button_name);
                        Button i_want_to_be_anonymous_button_name = getActivity().findViewById(R.id.i_want_to_be_anonymous_button_name);
                        i_want_to_be_visible_button_name.setBackgroundResource(R.drawable.color_for_botton_off);
                        i_want_to_be_visible_button_name.setTextColor(Color.parseColor("#607D8B"));
                        i_want_to_be_anonymous_button_name.setBackgroundResource(R.drawable.color_for_botton_on);
                        i_want_to_be_anonymous_button_name.setTextColor(Color.WHITE);
                        if (!enter_your_own_name_when_submitting_quote_edit_text.getText().toString().equals(return_the_name()) && !enter_your_own_name_when_submitting_quote_edit_text.getText().toString().equals("Anonymous") && !enter_your_own_name_when_submitting_quote_edit_text.getText().toString().equals("anonymous")) {
                            remember_me = enter_your_own_name_when_submitting_quote_edit_text.getText().toString();
                        } else {
                            remember_me = "::::dont_remember_me_app_dev::::";
                        }
                        enter_your_own_name_when_submitting_quote_edit_text.setText("");
                        enter_your_own_name_when_submitting_quote_edit_text.setText("Anonymous");
                        enter_your_own_name_when_submitting_quote_edit_text.setSelection(9);
                    }
                }
            });
        }
    }
    private void updating_name_for_text_view(){
        if (getActivity() != null) {
            EditText enter_your_own_name_when_submitting_quote_edit_text = getActivity().findViewById(R.id.enter_your_own_name_when_submitting_quote_edit_text);
            TextView name_of_the_submitter_of_the_quote_text_view = getActivity().findViewById(R.id.name_of_the_submitter_of_the_quote_text_view);
            if (!enter_your_own_name_when_submitting_quote_edit_text.getText().toString().trim().equals("")) {
                name_of_the_submitter_of_the_quote_text_view.setText("Submitted by: ".concat(enter_your_own_name_when_submitting_quote_edit_text.getText().toString().trim()));
            } else {
                name_of_the_submitter_of_the_quote_text_view.setText("Submitted by:  Please submit your name");
            }
        }
    }
    private String return_the_name() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("main_name", getContext().MODE_PRIVATE);
            return sharedPreferences.getString("name", "");
        } else {
            return "";
        }
    }
    private void color_the_last_sentence() {
        if (getActivity() != null) {
            TextView name_of_the_submitter_of_the_quote_text_view = getActivity().findViewById(R.id.name_of_the_submitter_of_the_quote_text_view);
            int color_for_streak = Color.rgb(96, 125, 139);
            ForegroundColorSpan orange_color_to_span = new ForegroundColorSpan(color_for_streak);
            String text_with_out_submitted = name_of_the_submitter_of_the_quote_text_view.getText().toString().replace("Submitted by: ", "");
            SpannableString spannableString = new SpannableString(name_of_the_submitter_of_the_quote_text_view.getText().toString());
            spannableString.setSpan(orange_color_to_span, spannableString.length() - text_with_out_submitted.length(), spannableString.length(), 0);
            name_of_the_submitter_of_the_quote_text_view.setText(spannableString);
        }
    }
}*/
