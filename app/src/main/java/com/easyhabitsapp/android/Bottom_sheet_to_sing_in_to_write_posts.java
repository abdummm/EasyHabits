package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Bottom_sheet_to_sing_in_to_write_posts extends BottomSheetDialogFragment {
    private View m_view;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 123;
    private String name;
    private FirebaseFunctions firebaseFunctions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_sign_posts, container, false);
        m_view = view;
        sign_in_google_lsiten();
        continue_without_signing_in_listen();
        make_the_checkBox_clickable();
        return view;
    }

    private void sign_in_google_lsiten() {
        final Button sign_in_with_google_in_posts = m_view.findViewById(R.id.sign_in_with_google_in_posts);
        final EditText edit_text_to_enter_name_for_account_in_online_post = m_view.findViewById(R.id.edit_text_to_enter_name_for_account_in_online_post);
        final CheckBox text_box_sating_i_agree_to_terms_and_services = m_view.findViewById(R.id.text_box_sating_i_agree_to_terms_and_services);
            sign_in_with_google_in_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_text_to_enter_name_for_account_in_online_post.getText().toString().trim().length() == 0) {
                        edit_text_to_enter_name_for_account_in_online_post.setError("Can't be empty");
                        Toast.makeText(getActivity(), "Name can't be empty", Toast.LENGTH_SHORT).show();
                    } else if (!text_box_sating_i_agree_to_terms_and_services.isChecked()) {
                        Toast.makeText(getActivity(), "Please agree to term and services", Toast.LENGTH_SHORT).show();
                    } else {
                        name = edit_text_to_enter_name_for_account_in_online_post.getText().toString().trim();
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null && firebaseUser.isAnonymous()) {
                            firebaseUser.delete().addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    create_google_request();
                                    sign_in_with_google();
                                }
                            }).addOnFailureListener(getActivity(), new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Log.w("failed", e);
                                }
                            });
                        } else {
                            create_google_request();
                            sign_in_with_google();
                        }
                    }
                }
            });
    }

    private void continue_without_signing_in_listen() {
        Button continue_without_signing_in_in_posts = m_view.findViewById(R.id.continue_without_signing_in_in_posts);
        continue_without_signing_in_in_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sign_in", "anonymous");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
    }

    private void create_google_request() {
        if (getActivity() != null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            // Build a GoogleSignInClient with the options specified by gso.
            mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        }
    }

    private void sign_in_with_google() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Toast.makeText(getActivity(), "Can't sign in. Please try again", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            save_name_online();
                            register_with_online();
                            Intent i = new Intent().putExtra("sign_in", "google");
                            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                            Toast.makeText(getActivity(), "Signed in", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Can't sign in. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void make_the_checkBox_clickable() {
        CheckBox text_box_sating_i_agree_to_terms_and_services = m_view.findViewById(R.id.text_box_sating_i_agree_to_terms_and_services);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Prevent CheckBox state from being toggled when link is clicked
                widget.cancelPendingInputEvents();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsprivacypolicy/home"));// change later
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // Show links with underlines (optional)
                ds.setUnderlineText(true);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Prevent CheckBox state from being toggled when link is clicked
                widget.cancelPendingInputEvents();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/easyhabitsterms/home"));// change later
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // Show links with underlines (optional)
                ds.setUnderlineText(true);
            }
        };

        SpannableString linkText = new SpannableString("Privacy Policy");
        linkText.setSpan(clickableSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString linkText2 = new SpannableString("Terms and Conditions");
        linkText2.setSpan(clickableSpan2, 0, linkText2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        CharSequence cs = TextUtils.expandTemplate("I agree to ^1 and ^2", linkText, linkText2);

        text_box_sating_i_agree_to_terms_and_services.setText(cs);
// Finally, make links clickable
        text_box_sating_i_agree_to_terms_and_services.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void save_name_online() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("name", name);
            myEdit.commit();
        }
    }

    private void register_with_online(){
        Am_i_paid am_i_paid = new Am_i_paid(getContext());
        if(am_i_paid.did_user_pay()){
            Billing_library_help billing_library_help = new Billing_library_help(getContext());
            billing_library_help.am_i_monthyl_or_yearly();
            Dialog_custom_loading dialog_custom_loading = new Dialog_custom_loading("Syncing account...");
            dialog_custom_loading.show(getParentFragmentManager(),"");
            billing_library_help.set_up_billing_library(new Billing_library_help.send_billign_result() {
                @Override
                public void send_result(String result) {
                    if(result.equals("1_year")){
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", billing_library_help.return_token());
                        data.put("orderId", billing_library_help.return_order_id());
                        firebaseFunctions = FirebaseFunctions.getInstance();
                        firebaseFunctions
                                .getHttpsCallable("yearly_sub_i_buy")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            Toast.makeText(getActivity(), "Verified purchase", Toast.LENGTH_LONG).show();
                                            dialog_custom_loading.loaded();
                                            dismiss();
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(getActivity(), "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact Support", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
                    } else if(result.equals("1_month")){
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", billing_library_help.return_token());
                        data.put("orderId", billing_library_help.return_order_id());
                        firebaseFunctions = FirebaseFunctions.getInstance();
                        firebaseFunctions
                                .getHttpsCallable("monthly_sub_i_buy")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            Toast.makeText(getActivity(), "Verified purchase", Toast.LENGTH_LONG).show();
                                            dialog_custom_loading.loaded();
                                            dismiss();
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(getActivity(), "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();                                    }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
                    } else if(result.equals("none")){
                        dialog_custom_loading.loaded();
                        dismiss();
                    }
                }
            });
        } else {
            dismiss();
        }
    }
}
