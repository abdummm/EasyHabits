package com.easyhabitsapp.android;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Example_item_comments_feed {
    private String body;
    private ArrayList<Long> awards;
    private boolean dev;
    private ArrayList<String> up_vote_list;
    private ArrayList<String> down_vote_list;
    private ArrayList<String> report_list;
    private int streak;
    private String user_id;
    private ArrayList<HashMap<String, Object>> replies;
    private FirebaseFirestore firebaseFirestore;
    private String document_id;
    private int position;
    private boolean was_this_touched = false;
    private int plus_or_minus = 0;
    private boolean upvoted = false;
    private boolean down_vote = false;
    private boolean am_i_loading_for_up_vote_and_down_vote = false;
    private FirebaseUser firebaseUser;
    private String category;
    private boolean saved;
    private boolean reported = false;
    private Date date_of_comment;
    private String name;
    private boolean is_this_switched = false;
    private int index_of_comment_switched;
    private boolean is_this_an_archived_comment = false;
    private String my_user_id;

    private String title_of_the_main_post;
    private String body_of_the_main_post;
    private String span_of_the_main_post;
    private Date time_of_the_main_post;
    private String category_of_the_main_post;
    private long streak_of_the_main_post;
    private String name_of_the_main_post;

    public Example_item_comments_feed(String document_id,String title_of_the_main_post,String body_of_the_main_post,String span_of_the_main_post,Date time_of_the_main_post,String category_of_the_main_post,long streak_of_the_main_post,String name_of_the_main_post,int position,String body,String name,Date time_of_the_comment,String category_comment,int streak_of_the_comment,ArrayList<HashMap<String,Object>> hashMap){
        this.document_id = document_id;
        this.title_of_the_main_post = title_of_the_main_post;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_of_the_main_post = span_of_the_main_post;
        this.time_of_the_main_post = time_of_the_main_post;
        this.category_of_the_main_post = category_of_the_main_post;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.name_of_the_main_post = name_of_the_main_post;
        this.position = position;
        this.body = body;
        this.name = name;
        this.date_of_comment = time_of_the_comment;
        this.category = category_comment;
        this.streak = streak_of_the_comment;
        is_this_an_archived_comment = true;
        this.replies = hashMap;
    }


    public Example_item_comments_feed(String document_id,String title_of_the_main_post,String body_of_the_main_post,String span_of_the_main_post,Date time_of_the_main_post,String category_of_the_main_post,long streak_of_the_main_post,String name_of_the_main_post,int position,String body,String name,Date time_of_the_comment,String category_comment,int streak_of_the_comment,boolean saved){
        this.document_id = document_id;
        this.title_of_the_main_post = title_of_the_main_post;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_of_the_main_post = span_of_the_main_post;
        this.time_of_the_main_post = time_of_the_main_post;
        this.category_of_the_main_post = category_of_the_main_post;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.name_of_the_main_post = name_of_the_main_post;
        this.position = position;
        this.body = body;
        this.name = name;
        this.date_of_comment = time_of_the_comment;
        this.category = category_comment;
        this.streak = streak_of_the_comment;
        this.saved = saved;
        is_this_an_archived_comment = true;
    }

    public Example_item_comments_feed(String body, ArrayList<Long> awards, boolean dev, ArrayList<String> up_vote_list, ArrayList<String> down_vote_list, ArrayList<String> report_list, int streak, String user_id, ArrayList<HashMap<String, Object>> replies, FirebaseFirestore firebaseFirestore, int position, String document_id, Date date_of_comment, String category, FirebaseUser firebaseUser, String name,String title_of_the_main_post,String body_of_the_main_post,String span_of_the_main_post,Date time_of_the_main_post,String category_of_the_main_post,long streak_of_the_main_post,String name_of_the_main_post) {
        this.body = body;
        this.awards = awards;
        this.dev = dev;
        this.up_vote_list = up_vote_list;
        this.down_vote_list = down_vote_list;
        this.report_list = report_list;
        this.streak = streak;
        this.user_id = user_id;
        this.replies = replies;
        this.firebaseFirestore = firebaseFirestore;
        this.document_id = document_id;
        this.position = position;
        this.date_of_comment = date_of_comment;
        this.category = category;
        this.firebaseUser = firebaseUser;
        this.name = name;
        this.title_of_the_main_post = title_of_the_main_post;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_of_the_main_post = span_of_the_main_post;
        this.time_of_the_main_post = time_of_the_main_post;
        this.category_of_the_main_post = category_of_the_main_post;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.name_of_the_main_post = name_of_the_main_post;
    }

    public Example_item_comments_feed(String body, ArrayList<Long> awards, boolean dev, ArrayList<String> up_vote_list, ArrayList<String> down_vote_list, ArrayList<String> report_list, int streak, String user_id, ArrayList<HashMap<String, Object>> replies, FirebaseFirestore firebaseFirestore, int position, String document_id, Date date_of_comment, String category, FirebaseUser firebaseUser, String name, String title_of_the_main_post, String body_of_the_main_post, String span_of_the_main_post, Date time_of_the_main_post, String category_of_the_main_post, long streak_of_the_main_post, String name_of_the_main_post, int index_of_comment_switched) {
        this.body = body;
        this.awards = awards;
        this.dev = dev;
        this.up_vote_list = up_vote_list;
        this.down_vote_list = down_vote_list;
        this.report_list = report_list;
        this.streak = streak;
        this.user_id = user_id;
        this.replies = replies;
        this.firebaseFirestore = firebaseFirestore;
        this.document_id = document_id;
        this.position = position;
        this.date_of_comment = date_of_comment;
        this.category = category;
        this.firebaseUser = firebaseUser;
        this.name = name;
        this.title_of_the_main_post = title_of_the_main_post;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_of_the_main_post = span_of_the_main_post;
        this.time_of_the_main_post = time_of_the_main_post;
        this.category_of_the_main_post = category_of_the_main_post;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.name_of_the_main_post = name_of_the_main_post;
        this.is_this_switched = true;
        this.index_of_comment_switched = index_of_comment_switched;
    }

    public boolean isIs_this_an_archived_comment() {
        return is_this_an_archived_comment;
    }

    public boolean isIs_this_switched() {
        return is_this_switched;
    }

    public int getIndex_of_comment_switched() {
        return index_of_comment_switched;
    }

    public String getName_of_the_main_post() {
        return name_of_the_main_post;
    }

    public String getTitle_of_the_main_post() {
        return title_of_the_main_post;
    }

    public String getBody_of_the_main_post() {
        return body_of_the_main_post;
    }

    public String getSpan_of_the_main_post() {
        return span_of_the_main_post;
    }

    public Date getTime_of_the_main_post() {
        return time_of_the_main_post;
    }

    public String getCategory_of_the_main_post() {
        return category_of_the_main_post;
    }

    public long getStreak_of_the_main_post() {
        return streak_of_the_main_post;
    }

    public String getName() {
        return name;
    }

    public boolean isAm_i_loading_for_up_vote_and_down_vote() {
        return am_i_loading_for_up_vote_and_down_vote;
    }

    public void setAm_i_loading_for_up_vote_and_down_vote(boolean am_i_loading_for_up_vote_and_down_vote) {
        this.am_i_loading_for_up_vote_and_down_vote = am_i_loading_for_up_vote_and_down_vote;
    }

    public boolean isUpvoted() {
        return upvoted;
    }

    public void setUpvoted(boolean upvoted) {
        this.upvoted = upvoted;
    }

    public boolean isDown_vote() {
        return down_vote;
    }

    public void setDown_vote(boolean down_vote) {
        this.down_vote = down_vote;
    }

    public int getPlus_or_minus() {
        return plus_or_minus;
    }

    public void setPlus_or_minus(int plus_or_minus) {
        this.plus_or_minus = plus_or_minus;
    }

    public boolean isWas_this_touched() {
        return was_this_touched;
    }

    public void setWas_this_touched(boolean was_this_touched) {
        this.was_this_touched = was_this_touched;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public Date getTime() {
        return date_of_comment;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public String getDocument_id() {
        return document_id;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public int getPosition() {
        return position;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Long> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Long> awards) {
        this.awards = awards;
    }

    public boolean isDev() {
        return dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }

    public ArrayList<String> getUp_vote_list() {
        return up_vote_list;
    }

    public void setUp_vote_list(ArrayList<String> up_vote_list) {
        this.up_vote_list = up_vote_list;
    }

    public ArrayList<String> getDown_vote_list() {
        return down_vote_list;
    }

    public void setDown_vote_list(ArrayList<String> down_vote_list) {
        this.down_vote_list = down_vote_list;
    }

    public ArrayList<String> getReport_list() {
        return report_list;
    }

    public void setReport_list(ArrayList<String> report_list) {
        this.report_list = report_list;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<HashMap<String, Object>> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<HashMap<String, Object>> replies) {
        this.replies = replies;
    }

    public String return_my_user_id(){
        return firebaseUser.getUid();
    }
}
