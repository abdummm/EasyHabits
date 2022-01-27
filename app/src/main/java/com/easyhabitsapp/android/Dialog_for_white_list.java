package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyhabitsapp.android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dialog_for_white_list extends DialogFragment {
    RecyclerView recyclerView_of_apps;
    List<String> list_for_names_of_apps;
    List<String> list_copy;
    Whitelist_adapter adapter_of_apps;
    WhiteList_old_apps adapter_old_apps;
    List<Drawable> image_list_of_apps;
    List<Drawable> image_copy;
    List<String> list_check_box_for_apps;
    private View mview;
    private Handler handler = new Handler();
    RecyclerView apps_recycle_view_for_allowed_apps;
    List<String> list_for_old_copies;
    List<Drawable> list_immage_for_old_copy;
    private Boolean special_mode_for_app = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_for_white_list, container);
        recyclerView_of_apps = rootView.findViewById(R.id.apps_recycle_view_white_list);
        apps_recycle_view_for_allowed_apps = rootView.findViewById(R.id.apps_recycle_view_for_allowed_apps);
        this.mview = rootView;
        EditText enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
        enter_name_to_search_app_white_list_edit_text.setFocusable(false);
        cancel_button_listen(rootView);
        save_button_listen_for_dialog(rootView);
        if (loading_the_white_list().equals("")) {
            ProgressBar progressBar_cyclic_for_the_white_list_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_white_list_dialog);
            progressBar_cyclic_for_the_white_list_dialog.setVisibility(View.VISIBLE);
            call_the_method_every_second();
        } else {
            special_mode_for_app = true;
        }
        list_check_box_for_apps = new ArrayList<>();
        searching_the_apps();
        two_buttons();
        which_button_to_open();
        load_the_recycle_view();
        ok_button_listen();
        clear_list_button();
        if(getDialog()!=null) {
            getDialog().setCanceledOnTouchOutside(false);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity()!=null && getDialog().getWindow()!=null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }


    private void cancel_button_listen(View root_view) {
        if (getActivity() != null) {
            Button cancel_button_for_white_list_dialog = root_view.findViewById(R.id.cancel_button_for_white_list_dialog);
            cancel_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDialog().dismiss();
                }
            });
        }
    }

    private void list_of_apps() {
        if (getActivity() != null) {
            if (mview != null) {
                final PackageManager packageManager = getActivity().getPackageManager(); //get a list of installed apps.
                List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
                list_for_names_of_apps = new ArrayList<>();
                image_list_of_apps = new ArrayList<>();
                ProgressBar progressBar_cyclic_for_the_white_list_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_white_list_dialog);
                progressBar_cyclic_for_the_white_list_dialog.setVisibility(View.VISIBLE);
                for (ApplicationInfo packageInfo : packages) {
                    try {
                        ApplicationInfo applicationInfo;
                        applicationInfo = packageManager.getApplicationInfo(packageInfo.packageName, PackageManager.GET_META_DATA);
                        if (packageManager.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                            list_for_names_of_apps.add(packageManager.getApplicationLabel(applicationInfo).toString().concat("_split_here_".concat(packageInfo.packageName)));
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Collections.sort(list_for_names_of_apps);
                for (int i = 0; i < list_for_names_of_apps.size(); i++) {
                    String[] splitter = list_for_names_of_apps.get(i).split("_split_here_");
                    try {
                        image_list_of_apps.add(packageManager.getApplicationIcon(splitter[1]));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                image_copy = new ArrayList<>();
                image_copy.addAll(image_list_of_apps);
                list_copy = new ArrayList<>();
                list_copy.addAll(list_for_names_of_apps);
                progressBar_cyclic_for_the_white_list_dialog.setVisibility(View.INVISIBLE);
                EditText enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
                enter_name_to_search_app_white_list_edit_text.setFocusableInTouchMode(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView_of_apps.setLayoutManager(layoutManager);
                adapter_of_apps = new Whitelist_adapter(this.getContext(), list_for_names_of_apps, image_list_of_apps, list_check_box_for_apps);
                recyclerView_of_apps.setAdapter(adapter_of_apps);
            }
        }
    }

    private void call_the_method_every_second() {
        handler.postDelayed(new Runnable() {
            public void run() {
                if (mview != null) {
                    ProgressBar progressBar_cyclic_for_the_white_list_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_white_list_dialog);
                    if (progressBar_cyclic_for_the_white_list_dialog.getVisibility() == View.INVISIBLE) {
                        handler.postDelayed(this, 1000);
                    } else {
                        list_of_apps();
                    }
                }
            }
        }, 1000);
    }

    private void save_button_listen_for_dialog(View root_view) {
        if (getActivity() != null) {
            Button save_button_for_white_list_dialog = root_view.findViewById(R.id.save_button_for_white_list_dialog);
            save_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String saving_the_names_with_split = "";
                    for (int i = 0; i < list_check_box_for_apps.size(); i++) {
                        saving_the_names_with_split = saving_the_names_with_split.concat(list_check_box_for_apps.get(i)).concat("_split_max_here_");
                    }
                    if (saving_the_names_with_split.equals("")) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No apps selected", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        saving_file_for_white_list(saving_the_names_with_split);
                        load_the_recycle_view();
                    }
                }
            });
        }
    }

    private void searching_the_apps() {
        if (mview != null) {
            EditText enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
            enter_name_to_search_app_white_list_edit_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    EditText enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
                    filter(enter_name_to_search_app_white_list_edit_text.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void filter(String text) {
        list_for_names_of_apps.clear();
        image_list_of_apps.clear();
        if (text.isEmpty()) {
            list_for_names_of_apps.addAll(list_copy);
            image_list_of_apps.addAll(image_copy);
        } else {
            text = text.toLowerCase();
            for (int i = 0; i < list_copy.size(); i++) {
                String[] splitter = list_copy.get(i).split("_split_here_");
                if (splitter[0].toLowerCase().substring(0,text.length()).matches(text)) {
                    list_for_names_of_apps.add(list_copy.get(i));
                    image_list_of_apps.add(image_copy.get(i));
                }
            }
            for (int i = 0; i < list_copy.size(); i++) {
                String[] splitter = list_copy.get(i).split("_split_here_");
                if (splitter[0].toLowerCase().contains(text) && !list_for_names_of_apps.contains(list_copy.get(i))) {
                    list_for_names_of_apps.add(list_copy.get(i));
                    image_list_of_apps.add(image_copy.get(i));
                }
            }
        }
        adapter_of_apps.notifyDataSetChanged();
    }

    private void two_buttons() {
        Button new_button_to_create_white_list = mview.findViewById(R.id.new_button_to_create_white_list);
        Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
        new_button_to_create_white_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button new_button_to_create_white_list = mview.findViewById(R.id.new_button_to_create_white_list);
                Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
                TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
                TextView this_is_your_list = mview.findViewById(R.id.this_is_your_list);
                Button save_button_for_white_list_dialog = mview.findViewById(R.id.save_button_for_white_list_dialog);
                new_button_to_create_white_list.setBackgroundResource(R.drawable.color_for_botton_on);
                new_button_to_create_white_list.setTextColor(Color.WHITE);
                custom_button_to_use_white_list.setBackgroundResource(R.drawable.color_for_botton_off);
                custom_button_to_use_white_list.setTextColor(Color.parseColor("#607D8B"));
                TextView search_text_beside_search_app_edit_text = mview.findViewById(R.id.search_text_beside_search_app_edit_text);
                TextView enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
                search_text_beside_search_app_edit_text.setVisibility(View.VISIBLE);
                enter_name_to_search_app_white_list_edit_text.setVisibility(View.VISIBLE);
                recyclerView_of_apps.setVisibility(View.VISIBLE);
                saying_that_there_is_no_lists_text_view.setVisibility(View.INVISIBLE);
                apps_recycle_view_for_allowed_apps.setVisibility(View.INVISIBLE);
                if (special_mode_for_app) {
                    ProgressBar progressBar_cyclic_for_the_white_list_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_white_list_dialog);
                    progressBar_cyclic_for_the_white_list_dialog.setVisibility(View.VISIBLE);
                    call_the_method_every_second();
                    special_mode_for_app = false;
                }
                this_is_your_list.setVisibility(View.INVISIBLE);
                save_button_for_white_list_dialog.setVisibility(View.VISIBLE);
                Button clear_button_for_white_list_dialog = mview.findViewById(R.id.clear_button_for_white_list_dialog);
                clear_button_for_white_list_dialog.setVisibility(View.INVISIBLE);
            }
        });
        custom_button_to_use_white_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBar progressBar_cyclic_for_the_white_list_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_white_list_dialog);
                if (progressBar_cyclic_for_the_white_list_dialog.getVisibility() != View.VISIBLE) {
                    Button new_button_to_create_white_list = mview.findViewById(R.id.new_button_to_create_white_list);
                    Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
                    TextView this_is_your_list = mview.findViewById(R.id.this_is_your_list);
                    TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
                    Button save_button_for_white_list_dialog = mview.findViewById(R.id.save_button_for_white_list_dialog);
                    new_button_to_create_white_list.setBackgroundResource(R.drawable.color_for_botton_off);
                    new_button_to_create_white_list.setTextColor(Color.parseColor("#607D8B"));
                    custom_button_to_use_white_list.setBackgroundResource(R.drawable.color_for_botton_on);
                    custom_button_to_use_white_list.setTextColor(Color.WHITE);
                    TextView search_text_beside_search_app_edit_text = mview.findViewById(R.id.search_text_beside_search_app_edit_text);
                    TextView enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
                    search_text_beside_search_app_edit_text.setVisibility(View.INVISIBLE);
                    enter_name_to_search_app_white_list_edit_text.setVisibility(View.INVISIBLE);
                    recyclerView_of_apps.setVisibility(View.INVISIBLE);
                    if (loading_the_white_list().equals("")) {
                        saying_that_there_is_no_lists_text_view.setVisibility(View.VISIBLE);
                    } else {
                        apps_recycle_view_for_allowed_apps.setVisibility(View.VISIBLE);
                        this_is_your_list.setVisibility(View.VISIBLE);
                    }
                    save_button_for_white_list_dialog.setVisibility(View.INVISIBLE);
                    Button clear_button_for_white_list_dialog = mview.findViewById(R.id.clear_button_for_white_list_dialog);
                    clear_button_for_white_list_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private String loading_the_white_list() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("allowed_apps_list", getContext().MODE_PRIVATE);
            return sharedPreferences.getString("allowed_apps", "");

        }
        return "";
    }

    private void which_button_to_open() {
        if (loading_the_white_list().equals("")) {
            Button save_button_for_white_list_dialog = mview.findViewById(R.id.save_button_for_white_list_dialog);
            Button new_button_to_create_white_list = mview.findViewById(R.id.new_button_to_create_white_list);
            Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
            TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
            TextView this_is_your_list = mview.findViewById(R.id.this_is_your_list);
            new_button_to_create_white_list.setBackgroundResource(R.drawable.color_for_botton_on);
            new_button_to_create_white_list.setTextColor(Color.WHITE);
            custom_button_to_use_white_list.setBackgroundResource(R.drawable.color_for_botton_off);
            custom_button_to_use_white_list.setTextColor(Color.parseColor("#607D8B"));
            TextView search_text_beside_search_app_edit_text = mview.findViewById(R.id.search_text_beside_search_app_edit_text);
            TextView enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
            search_text_beside_search_app_edit_text.setVisibility(View.VISIBLE);
            enter_name_to_search_app_white_list_edit_text.setVisibility(View.VISIBLE);
            recyclerView_of_apps.setVisibility(View.VISIBLE);
            apps_recycle_view_for_allowed_apps.setVisibility(View.INVISIBLE);
            saying_that_there_is_no_lists_text_view.setVisibility(View.INVISIBLE);
            this_is_your_list.setVisibility(View.INVISIBLE);
            save_button_for_white_list_dialog.setVisibility(View.VISIBLE);
            Button clear_button_for_white_list_dialog = mview.findViewById(R.id.clear_button_for_white_list_dialog);
            clear_button_for_white_list_dialog.setVisibility(View.INVISIBLE);
        } else {
            Button new_button_to_create_white_list = mview.findViewById(R.id.new_button_to_create_white_list);
            Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
            TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
            TextView this_is_your_list = mview.findViewById(R.id.this_is_your_list);
            Button save_button_for_white_list_dialog = mview.findViewById(R.id.save_button_for_white_list_dialog);
            new_button_to_create_white_list.setBackgroundResource(R.drawable.color_for_botton_off);
            new_button_to_create_white_list.setTextColor(Color.parseColor("#607D8B"));
            custom_button_to_use_white_list.setBackgroundResource(R.drawable.color_for_botton_on);
            custom_button_to_use_white_list.setTextColor(Color.WHITE);
            TextView search_text_beside_search_app_edit_text = mview.findViewById(R.id.search_text_beside_search_app_edit_text);
            TextView enter_name_to_search_app_white_list_edit_text = mview.findViewById(R.id.enter_name_to_search_app_white_list_edit_text);
            search_text_beside_search_app_edit_text.setVisibility(View.INVISIBLE);
            enter_name_to_search_app_white_list_edit_text.setVisibility(View.INVISIBLE);
            recyclerView_of_apps.setVisibility(View.INVISIBLE);
            if (loading_the_white_list().equals("")) {
                saying_that_there_is_no_lists_text_view.setVisibility(View.VISIBLE);
            } else {
                apps_recycle_view_for_allowed_apps.setVisibility(View.VISIBLE);
                this_is_your_list.setVisibility(View.VISIBLE);
            }
            save_button_for_white_list_dialog.setVisibility(View.INVISIBLE);
            Button clear_button_for_white_list_dialog = mview.findViewById(R.id.clear_button_for_white_list_dialog);
            clear_button_for_white_list_dialog.setVisibility(View.VISIBLE);
        }
    }

    private void saving_file_for_white_list(String save_me) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("allowed_apps_list", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("allowed_apps", save_me);
            myEdit.commit();
        }
    }

    private void load_the_recycle_view() {
        if (!loading_the_white_list().equals("")) {
            if (getActivity() != null) {
                list_for_old_copies = new ArrayList<>();
                list_immage_for_old_copy = new ArrayList<>();
                final PackageManager packageManager = getActivity().getPackageManager(); //get a list of installed apps.
                String[] split_me = loading_the_white_list().split("_split_max_here_");
                for (int i = 0; i < split_me.length; i++) {
                    String[] split_me_twice = split_me[i].split("_split_here_");
                    if (isPackageInstalled(split_me_twice[1], packageManager)) {
                        list_for_old_copies.add(split_me[i]);
                    }
                }
                Collections.sort(list_for_old_copies);
                for (int i = 0; i < list_for_old_copies.size(); i++) {
                    String[] splitter = list_for_old_copies.get(i).split("_split_here_");
                    try {
                        list_immage_for_old_copy.add(packageManager.getApplicationIcon(splitter[1]));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
                apps_recycle_view_for_allowed_apps.setLayoutManager(layoutManager);
                adapter_old_apps = new WhiteList_old_apps(this.getContext(), list_for_old_copies, list_immage_for_old_copy);
                apps_recycle_view_for_allowed_apps.setAdapter(adapter_old_apps);
            }
        } else {
            list_for_old_copies = new ArrayList<>();
            list_immage_for_old_copy = new ArrayList<>();
            list_for_old_copies.clear();
            list_immage_for_old_copy.clear();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
            apps_recycle_view_for_allowed_apps.setLayoutManager(layoutManager);
            adapter_old_apps = new WhiteList_old_apps(this.getContext(), list_for_old_copies, list_immage_for_old_copy);
            apps_recycle_view_for_allowed_apps.setAdapter(adapter_old_apps);
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void ok_button_listen() {
        Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button custom_button_to_use_white_list = mview.findViewById(R.id.custom_button_to_use_white_list);
                TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
                if (custom_button_to_use_white_list.getCurrentTextColor() == -1) {
                    if (saying_that_there_is_no_lists_text_view.getVisibility() != View.VISIBLE) {
                        saving_only_to_send(loading_the_white_list());
                        if (getContext() != null && getActivity()!=null) {
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("white_or_purple", getContext().MODE_PRIVATE).edit();
                            editor.putBoolean("color_it_white", true);
                            editor.apply();
                        }
                    } else {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No apps chosen", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    boolean there_is_a_true = false;
                    if (!list_check_box_for_apps.isEmpty()) {
                        there_is_a_true = true;
                    }
                    if (there_is_a_true) {
                        saving_only_to_send(list_to_string(list_check_box_for_apps));
                        if (getContext() != null && getActivity()!=null) {
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("white_or_purple", getContext().MODE_PRIVATE).edit();
                            editor.putBoolean("color_it_white", true);
                            editor.apply();
                        }
                    } else {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No apps chosen", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                String saving_the_names_with_split = "";
                if (!list_check_box_for_apps.isEmpty()) {
                    for (int i = 0; i < list_check_box_for_apps.size(); i++) {
                        saving_the_names_with_split = saving_the_names_with_split.concat(list_check_box_for_apps.get(i)).concat("_split_max_here_");
                    }
                }
                getDialog().dismiss();
            }
        });
    }

    private void clear_list_button() {
        Button clear_button_for_white_list_dialog = mview.findViewById(R.id.clear_button_for_white_list_dialog);
        clear_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loading_the_white_list().equals("")) {
                    saving_file_for_white_list("");
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "list cleared", Toast.LENGTH_SHORT);
                    toast.show();
                    load_the_recycle_view();
                    TextView this_is_your_list = mview.findViewById(R.id.this_is_your_list);
                    this_is_your_list.setVisibility(View.INVISIBLE);
                    TextView saying_that_there_is_no_lists_text_view = mview.findViewById(R.id.saying_that_there_is_no_lists_text_view);
                    saying_that_there_is_no_lists_text_view.setVisibility(View.VISIBLE);
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "There are no lists", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void saving_only_to_send(String save_me) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("send_the_apps", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("send_apps", save_me);
            Log.w("see", save_me);
            myEdit.apply();
        }
    }

    private String list_to_string(List<String> list_me) {
        String return_me = "";
        for (int i = 0; i < list_me.size(); i++) {
            return_me = return_me.concat(list_me.get(i)).concat("_split_max_here_");
        }
        return return_me;
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
