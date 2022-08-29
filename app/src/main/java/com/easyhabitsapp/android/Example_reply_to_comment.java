package com.easyhabitsapp.android;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Example_reply_to_comment {
    private String name;
    private ArrayList<Long> awards;
    private String body;
    private String category;
    private Date date;
    private boolean dev;
    private ArrayList<String> up_vote_list;
    private ArrayList<String> down_vote_list;
    private ArrayList<String> reports;
    private int streak;
    private String user_id;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private int comment_position;
    private int reply_position;
    private int replies_size;
    private int likes;
    private boolean is_this_reported = false;
    private boolean up_vote_clicked;
    private boolean down_vote_clicked;
    private boolean am_i_loading = false;
    private String document_id;
    private boolean is_this_saved;
    private String my_user_id;

    private String title_of_the_main_post;
    private String body_of_the_main_post;
    private String span_of_the_main_post;
    private Date time_of_the_main_post;
    private String category_of_the_main_post;
    private long streak_of_the_main_post;
    private String name_of_the_main_post;

    private String body_of_the_comment_post;
    private Date time_of_the_comment_post;
    private String category_of_the_comment_post;
    private long streak_of_the_comment_post;
    private String name_of_the_comment_post;

    private boolean is_this_archived = false;

    public Example_reply_to_comment (HashMap<String,Object> map, FirebaseFirestore firebaseFirestore, FirebaseUser firebaseUser, int position, int reply_size, int reply_position, String document_id,String title_of_the_main_post,String body_of_the_main_post,String span_of_the_main_post,Date time_of_the_main_post,String category_of_the_main_post,long streak_of_the_main_post,String name_of_the_main_post,String body_of_the_comment_post,Date time_of_the_comment_post,String category_of_the_comment_post,long streak_of_the_comment_post,String name_of_the_comment_post,boolean is_this_archived){
        this.is_this_archived = is_this_archived;
        if(is_this_archived){
            this.name = (String) map.get("name");
            body = (String) map.get("body");
            date =  ((Timestamp) map.get("date")).toDate();
            category = (String) map.get("category");
            streak =  (int) map.get("streak");
            comment_position = position;
            replies_size = 1;
            is_this_saved = true;
            this.reply_position = reply_position;
            this.document_id = document_id;
            this.title_of_the_main_post = title_of_the_main_post;
            this.body_of_the_main_post = body_of_the_main_post;
            this.span_of_the_main_post = span_of_the_main_post;
            this.time_of_the_main_post = time_of_the_main_post;
            this.category_of_the_main_post= category_of_the_main_post;
            this.streak_of_the_main_post = streak_of_the_main_post;
            this.name_of_the_main_post = name_of_the_main_post;
            this.body_of_the_comment_post = body_of_the_comment_post;
            this.time_of_the_comment_post = time_of_the_comment_post;
            this.category_of_the_comment_post = category_of_the_comment_post;
            this.streak_of_the_comment_post = streak_of_the_comment_post;
            this.name_of_the_comment_post = name_of_the_comment_post;

        } else {
            this.name = (String) map.get("name");
            awards = (ArrayList<Long>) map.get("awards");
            body = (String) map.get("body");
            category = (String) map.get("category");
            date =  ((Timestamp) map.get("date")).toDate();
            dev = (boolean) map.get("dev");
            up_vote_list = (ArrayList<String>) map.get("up_vote_list");
            down_vote_list = (ArrayList<String>) map.get("down_vote_list");
            reports = (ArrayList<String>) map.get("reports");
            streak =  -1;
            user_id = (String) map.get("user_id");
            this.firebaseFirestore = firebaseFirestore;
            this.firebaseUser = firebaseUser;
            comment_position = position;
            replies_size = reply_size;
            this.reply_position = reply_position;
            this.document_id = document_id;
            this.title_of_the_main_post = title_of_the_main_post;
            this.body_of_the_main_post = body_of_the_main_post;
            this.span_of_the_main_post = span_of_the_main_post;
            this.time_of_the_main_post = time_of_the_main_post;
            this.category_of_the_main_post= category_of_the_main_post;
            this.streak_of_the_main_post = streak_of_the_main_post;
            this.name_of_the_main_post = name_of_the_main_post;
            this.body_of_the_comment_post = body_of_the_comment_post;
            this.time_of_the_comment_post = time_of_the_comment_post;
            this.category_of_the_comment_post = category_of_the_comment_post;
            this.streak_of_the_comment_post = streak_of_the_comment_post;
            this.name_of_the_comment_post = name_of_the_comment_post;
        }
    }

    public boolean isIs_this_archived() {
        return is_this_archived;
    }

    public String getBody_of_the_comment_post() {
        return body_of_the_comment_post;
    }

    public Date getTime_of_the_comment_post() {
        return time_of_the_comment_post;
    }

    public String getCategory_of_the_comment_post() {
        return category_of_the_comment_post;
    }

    public long getStreak_of_the_comment_post() {
        return streak_of_the_comment_post;
    }

    public String getName_of_the_comment_post() {
        return name_of_the_comment_post;
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

    public String getName_of_the_main_post() {
        return name_of_the_main_post;
    }

    public boolean isIs_this_saved() {
        return is_this_saved;
    }

    public void setIs_this_saved(boolean is_this_saved) {
        this.is_this_saved = is_this_saved;
    }

    public boolean isIs_this_reported() {
        return is_this_reported;
    }

    public void setIs_this_reported(boolean is_this_reported) {
        this.is_this_reported = is_this_reported;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isUp_vote_clicked() {
        return up_vote_clicked;
    }

    public void setUp_vote_clicked(boolean up_vote_clicked) {
        this.up_vote_clicked = up_vote_clicked;
    }

    public boolean isDown_vote_clicked() {
        return down_vote_clicked;
    }

    public void setDown_vote_clicked(boolean down_vote_clicked) {
        this.down_vote_clicked = down_vote_clicked;
    }

    public boolean isAm_i_loading() {
        return am_i_loading;
    }

    public void setAm_i_loading(boolean am_i_loading) {
        this.am_i_loading = am_i_loading;
    }

    public String getDocument_id() {
        return document_id;
    }

    public int getReplies_size() {
        return replies_size;
    }

    public void setReplies_size(int replies_size) {
        this.replies_size = replies_size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Long> awards) {
        this.awards = awards;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public ArrayList<String> getReports() {
        return reports;
    }

    public void setReports(ArrayList<String> reports) {
        this.reports = reports;
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

    public FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public void setFirebaseFirestore(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    public int getComment_position() {
        return comment_position;
    }

    public void setComment_position(int comment_position) {
        this.comment_position = comment_position;
    }

    public int getReply_position() {
        return reply_position;
    }

    public void setReply_position(int reply_position) {
        this.reply_position = reply_position;
    }

    public String return_my_user_id(){
        return firebaseUser.getUid();
    }
}
