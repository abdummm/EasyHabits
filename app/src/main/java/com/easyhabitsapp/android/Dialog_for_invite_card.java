package com.easyhabitsapp.android;

import static android.content.Context.MODE_PRIVATE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Dialog_for_invite_card extends DialogFragment {
    private View mview;
    private String type;
    private String user_id;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private boolean am_i_loading = false;
    private everything_is_ok everything_is_ok;

    public void set_up_everything_is_ok(everything_is_ok listen) {
        everything_is_ok = listen;
    }

    public interface everything_is_ok {
        void everything_is_ok();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_for_invite_card, container);
        this.mview = rootView;
        get_your_invite_code_button_listen();
        enter_invite_code_button_listen();
        if(type.equals("get your invite code")){
            get_your_invite_code();
        } else if(type.equals("enter invite code")){
            enter_invite_code();
        }
        cancel_button_listen();
        ok_button_listen();
        copy_button_listen();
        set_code();
        if(getDialog()!=null){
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
        hide_refer();
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels / 100) * 90;
            //int height = (displayMetrics.heightPixels / 100) * 40;
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    public Dialog_for_invite_card(String type,String user_id) {
        this.type = type;
        this.user_id = user_id;
    }

    private void get_your_invite_code_button_listen(){
        final Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
        get_your_invite_code_button_in_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_your_invite_code();
                type = "get your invite code";
            }
        });
    }

    private void enter_invite_code_button_listen(){
        Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
        enter_friend_invite_code_button_in_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_invite_code();
                type = "enter invite code";
            }
        });
    }

    private void get_your_invite_code(){
        final Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
        final TextView text_saying_your_invite_code_in_dialog_setting = mview.findViewById(R.id.text_saying_your_invite_code_in_dialog_setting);
        final Button copy_code_button_in_get_or_send_invite_code_dialog = mview.findViewById(R.id.copy_code_button_in_get_or_send_invite_code_dialog);
        final TextView text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog = mview.findViewById(R.id.text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog);
        final Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
        final EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
        final TextView text_saying_get_premuim_if_you_enter_friend_invite_code_dialog = mview.findViewById(R.id.text_saying_get_premuim_if_you_enter_friend_invite_code_dialog);
        final TextView title_of_dialog = mview.findViewById(R.id.title_of_dialog);

        get_your_invite_code_button_in_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
        get_your_invite_code_button_in_dialog.setTextColor(Color.WHITE);
        enter_friend_invite_code_button_in_dialog.setBackgroundResource(R.drawable.color_for_botton_off);
        enter_friend_invite_code_button_in_dialog.setTextColor(Color.parseColor("#607D8B"));
        text_saying_your_invite_code_in_dialog_setting.setVisibility(View.VISIBLE);
        copy_code_button_in_get_or_send_invite_code_dialog.setVisibility(View.VISIBLE);
        text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog.setVisibility(View.VISIBLE);
        edit_text_to_enter_the_invite_code_in_dialog.setVisibility(View.GONE);
        text_saying_get_premuim_if_you_enter_friend_invite_code_dialog.setVisibility(View.GONE);

        ConstraintLayout constraintLayout = mview.findViewById(R.id.dialog_to_invite_or_enter_invite);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.ok_for_permission,ConstraintSet.TOP,R.id.text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog,ConstraintSet.BOTTOM, (int) convertDpToPixel(10, mview.getContext()));
        constraintSet.applyTo(constraintLayout);

        title_of_dialog.setText("Get your invite code");
    }

    private void enter_invite_code(){
        final Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
        final TextView text_saying_your_invite_code_in_dialog_setting = mview.findViewById(R.id.text_saying_your_invite_code_in_dialog_setting);
        final Button copy_code_button_in_get_or_send_invite_code_dialog = mview.findViewById(R.id.copy_code_button_in_get_or_send_invite_code_dialog);
        final TextView text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog = mview.findViewById(R.id.text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog);
        final Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
        final EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
        final TextView text_saying_get_premuim_if_you_enter_friend_invite_code_dialog = mview.findViewById(R.id.text_saying_get_premuim_if_you_enter_friend_invite_code_dialog);
        TextView title_of_dialog = mview.findViewById(R.id.title_of_dialog);

        enter_friend_invite_code_button_in_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
        enter_friend_invite_code_button_in_dialog.setTextColor(Color.WHITE);
        get_your_invite_code_button_in_dialog.setBackgroundResource(R.drawable.color_for_botton_off);
        get_your_invite_code_button_in_dialog.setTextColor(Color.parseColor("#607D8B"));
        text_saying_your_invite_code_in_dialog_setting.setVisibility(View.GONE);
        copy_code_button_in_get_or_send_invite_code_dialog.setVisibility(View.GONE);
        text_view_saying_you_will_get_premuim_in_get_your_invite_code_dialog.setVisibility(View.GONE);
        edit_text_to_enter_the_invite_code_in_dialog.setVisibility(View.VISIBLE);
        text_saying_get_premuim_if_you_enter_friend_invite_code_dialog.setVisibility(View.VISIBLE);

        ConstraintLayout constraintLayout = mview.findViewById(R.id.dialog_to_invite_or_enter_invite);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.ok_for_permission,ConstraintSet.TOP,R.id.text_saying_get_premuim_if_you_enter_friend_invite_code_dialog,ConstraintSet.BOTTOM, (int) convertDpToPixel(10, mview.getContext()));
        constraintSet.applyTo(constraintLayout);

        title_of_dialog.setText("Enter invite code");
    }

    private static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void cancel_button_listen(){
        Button cancel_for_permission = mview.findViewById(R.id.cancel_for_permission);
        cancel_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(am_i_loading){
                 Toast.makeText(mview.getContext(), "Please wait while referral code is being processed",Toast.LENGTH_LONG).show();
                } else {
                    dismiss();
                }
            }
        });
    }

    private void ok_button_listen(){
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        final EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(am_i_loading){
                    Toast.makeText(mview.getContext(), "Your referral code is being processed :)",Toast.LENGTH_LONG).show();
                } else {
                    if(type.equals("get your invite code")){
                        dismiss();
                    } else if(type.equals("enter invite code")){
                        if(edit_text_to_enter_the_invite_code_in_dialog.getText().toString().trim().equals("")){
                            Toast.makeText(mview.getContext(),"Referral code can't be empty",Toast.LENGTH_SHORT).show();
                        } else {
                            call_the_function_to_reward_both(edit_text_to_enter_the_invite_code_in_dialog.getText().toString().trim());
                            activate_loading_mode();
                            hide_keyboard();
                        }
                    }
                }
            }
        });
    }

    private void copy_button_listen(){
        Button copy_code_button_in_get_or_send_invite_code_dialog = mview.findViewById(R.id.copy_code_button_in_get_or_send_invite_code_dialog);
        copy_code_button_in_get_or_send_invite_code_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)mview.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null,user_id);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mview.getContext(),"Code copied",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void set_code(){
        TextView text_saying_your_invite_code_in_dialog_setting = mview.findViewById(R.id.text_saying_your_invite_code_in_dialog_setting);
        text_saying_your_invite_code_in_dialog_setting.setText(user_id);
    }



    private void call_the_function_to_reward_both(String other_user_id){
        Map<String, Object> map = new HashMap<>();
        map.put("other_user_id",other_user_id);
        FirebaseFunctions.getInstance() // Optional region: .getInstance("europe-west1")
                .getHttpsCallable("gift_both_me_and_the_other_person_referell_code")
                .call(map)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        if(task.getResult().getData().equals("ok")){
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("did_i_register_my_code",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putBoolean("register", true);
                            myEdit.commit();
                            everything_is_ok.everything_is_ok();
                        } else if(task.getResult().getData().equals("account is not correct")){
                            Toast.makeText(getActivity(), "Please make sure that the referral code is correct", Toast.LENGTH_LONG).show();
                        } else if(task.getResult().getData().equals("same_id")){
                            Toast.makeText(getActivity(), "You can't refer yourself!", Toast.LENGTH_LONG).show();
                        } else if (task.getResult().getData().equals("already_reedeem")){
                            Toast.makeText(getActivity(), "You already redeemed your reward", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "An error happened, if the issue persists, contact support", Toast.LENGTH_LONG).show();
                        }
                        turn_off_loading_mode();
                        return "";
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        if (getActivity() != null) {
                            turn_off_loading_mode();
                            Toast.makeText(getActivity(), "An error happened, please contact support if it persists", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void activate_loading_mode(){
        ProgressBar progressBar_cyclic_for_the_gift_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_gift_dialog);
        Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
        Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
        EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
        TextView text_saying_get_premuim_if_you_enter_friend_invite_code_dialog = mview.findViewById(R.id.text_saying_get_premuim_if_you_enter_friend_invite_code_dialog);
        progressBar_cyclic_for_the_gift_dialog.setVisibility(View.VISIBLE);
        get_your_invite_code_button_in_dialog.setVisibility(View.INVISIBLE);
        enter_friend_invite_code_button_in_dialog.setVisibility(View.INVISIBLE);
        edit_text_to_enter_the_invite_code_in_dialog.setVisibility(View.INVISIBLE);
        text_saying_get_premuim_if_you_enter_friend_invite_code_dialog.setVisibility(View.INVISIBLE);
        am_i_loading = true;
    }

    private void turn_off_loading_mode(){
        ProgressBar progressBar_cyclic_for_the_gift_dialog = mview.findViewById(R.id.progressBar_cyclic_for_the_gift_dialog);
        Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
        Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
        EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
        TextView text_saying_get_premuim_if_you_enter_friend_invite_code_dialog = mview.findViewById(R.id.text_saying_get_premuim_if_you_enter_friend_invite_code_dialog);
        progressBar_cyclic_for_the_gift_dialog.setVisibility(View.INVISIBLE);
        get_your_invite_code_button_in_dialog.setVisibility(View.VISIBLE);
        enter_friend_invite_code_button_in_dialog.setVisibility(View.VISIBLE);
        edit_text_to_enter_the_invite_code_in_dialog.setVisibility(View.VISIBLE);
        text_saying_get_premuim_if_you_enter_friend_invite_code_dialog.setVisibility(View.VISIBLE);
        am_i_loading = false;
    }
    private void hide_keyboard() {
        if (getDialog() != null) {
            EditText edit_text_to_enter_the_invite_code_in_dialog = mview.findViewById(R.id.edit_text_to_enter_the_invite_code_in_dialog);
                InputMethodManager imm = (InputMethodManager) mview.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_text_to_enter_the_invite_code_in_dialog.getWindowToken(), 0);
        }
    }

    private void hide_refer(){
            Button enter_friend_invite_code_button_in_dialog = mview.findViewById(R.id.enter_friend_invite_code_button_in_dialog);
            Button get_your_invite_code_button_in_dialog = mview.findViewById(R.id.get_your_invite_code_button_in_dialog);
            SharedPreferences sh = getActivity().getSharedPreferences("did_i_register_my_code", MODE_PRIVATE);
            if(sh.getBoolean("register",false)){
                enter_friend_invite_code_button_in_dialog.setVisibility(View.GONE);
                get_your_invite_code_button_in_dialog.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = mview.findViewById(R.id.dialog_to_invite_or_enter_invite);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.text_saying_your_invite_code_in_dialog_setting,ConstraintSet.TOP,R.id.view_that_contains_title_of_dialog,ConstraintSet.BOTTOM, (int) convertDpToPixel(30, mview.getContext()));
                constraintSet.applyTo(constraintLayout);
            }
    }
}
