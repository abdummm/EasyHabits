package com.easyhabitsapp.android;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Example_item_feed_posts {
    private String m_title;
    private String m_body;
    private String m_category;
    private Date m_time;
    private Long m_streak;
    private String m_span;
    private boolean m_image;
    private String m_user_id;
    private ArrayList<HashMap<String, Object>> m_comments;
    private ArrayList<Long> m_awards;
    private boolean is_this_upvote = false;
    private boolean is_this_down_vote = false;
    private String m_document_id;
    private boolean m_is_this_from_fire_base;
    private ArrayList<String> m_upvotes;
    private ArrayList<String> m_downvotes;
    private boolean post_saved = false;
    private int plus_or_minus = 0;
    private boolean m_is_this_laoding;
    private boolean i_already_loaded = false;
    private boolean upvote_or_down_vote_was_clicked = false;
    private FirebaseFirestore m_firebaseFirestore;
    private FirebaseUser m_firebaseUser;
    private boolean was_this_reported=false;
    private ArrayList<String> m_list_of_reports;
    private boolean am_i_loading_up_voote_down_vote_from_fire_base = false;
    private boolean did_i_see_this_tem = false;
    private ArrayList<String> m_list_of_seen_posts;
    private boolean m_is_post_by_dev;
    private String name_of_the_poster;

    public Example_item_feed_posts(String document_id,String name, String title, String body, String span, Date time, String category, long streak,boolean post_saved) {
        this.name_of_the_poster = name;
        m_title = title;
        m_body = body;
        m_span = span;
        m_time = time;
        m_category = category;
        m_streak = streak;
        m_is_this_from_fire_base = false;
        this.post_saved = post_saved;
        m_document_id = document_id;
    }

    public String getName_of_the_poster() {
        return name_of_the_poster;
    }

    public String getM_category() {
        return m_category;
    }

    public String getM_user_id() {
        return m_user_id;
    }

    public boolean isIs_this_upvote() {
        return is_this_upvote;
    }

    public boolean isIs_this_down_vote() {
        return is_this_down_vote;
    }

    public String getM_document_id() {
        return m_document_id;
    }

    public boolean isM_is_this_from_fire_base() {
        return m_is_this_from_fire_base;
    }

    public boolean isPost_saved() {
        return post_saved;
    }

    public int getPlus_or_minus() {
        return plus_or_minus;
    }

    public boolean isI_already_loaded() {
        return i_already_loaded;
    }

    public boolean isUpvote_or_down_vote_was_clicked() {
        return upvote_or_down_vote_was_clicked;
    }

    public FirebaseFirestore getM_firebaseFirestore() {
        return m_firebaseFirestore;
    }

    public FirebaseUser getM_firebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public boolean isWas_this_reported() {
        return was_this_reported;
    }

    public ArrayList<String> getM_list_of_reports() {
        return m_list_of_reports;
    }

    public ArrayList<String> getM_list_of_seen_posts() {
        return m_list_of_seen_posts;
    }

    public boolean isM_is_post_by_dev() {
        return m_is_post_by_dev;
    }

    public Example_item_feed_posts(String title, String body, Long streak, boolean image, String category, Date time, String user_id, String span, ArrayList<HashMap<String, Object>> comments, ArrayList<Long> awards,String document_id,boolean is_this_from_fire_base,ArrayList<String> upvote_list,ArrayList<String> downvotes_list,FirebaseUser firebaseUser,boolean is_this_laoding,FirebaseFirestore firebaseFirestore, ArrayList<String> list_of_reports,ArrayList<String> list_of_seen_posts,boolean is_post_by_dev,Map<String, Timestamp> map_with_time_for_comments,String name_of_the_poster) {
        m_title = title;
        m_body = body;
        m_image = image;
        m_streak = streak;
        m_category = category;
        m_time = time;
        m_user_id = user_id;
        m_span = span;
        m_comments = comments;
        m_awards = awards;
        m_document_id = document_id;
        m_is_this_from_fire_base = is_this_from_fire_base;
        m_upvotes = upvote_list;
        m_downvotes = downvotes_list;
        m_firebaseUser = firebaseUser;
        m_is_this_laoding = is_this_laoding;
        m_firebaseFirestore = firebaseFirestore;
        m_list_of_reports = list_of_reports;
        m_list_of_seen_posts = list_of_seen_posts;
        m_is_post_by_dev = is_post_by_dev;
        this.name_of_the_poster = name_of_the_poster;
    }

    public Example_item_feed_posts(String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev,String name_of_the_poster) {
        this.m_title = m_title;
        this.m_body = m_body;
        this.m_category = m_category;
        this.m_time = m_time;
        this.m_streak = m_streak;
        this.m_span = m_span;
        this.m_image = m_image;
        this.m_user_id = m_user_id;
        this.m_comments = m_comments;
        this.m_awards = m_awards;
        this.is_this_upvote = is_this_upvote;
        this.is_this_down_vote = is_this_down_vote;
        this.m_document_id = m_document_id;
        this.m_is_this_from_fire_base = m_is_this_from_fire_base;
        this.m_upvotes = m_upvotes;
        this.m_downvotes = m_downvotes;
        this.post_saved = post_saved;
        this.plus_or_minus = plus_or_minus;
        this.m_is_this_laoding = m_is_this_laoding;
        this.i_already_loaded = i_already_loaded;
        this.upvote_or_down_vote_was_clicked = upvote_or_down_vote_was_clicked;
        this.m_firebaseFirestore = m_firebaseFirestore;
        this.m_firebaseUser = m_firebaseUser;
        this.was_this_reported = was_this_reported;
        this.m_list_of_reports = m_list_of_reports;
        this.am_i_loading_up_voote_down_vote_from_fire_base = am_i_loading_up_voote_down_vote_from_fire_base;
        this.did_i_see_this_tem = did_i_see_this_tem;
        this.m_list_of_seen_posts = m_list_of_seen_posts;
        this.m_is_post_by_dev = m_is_post_by_dev;
        this.name_of_the_poster = name_of_the_poster;
    }

    public String getM_title() {
        return m_title;
    }

    public String getM_body() {
        return m_body;
    }

    public long getM_streak() {
        return m_streak;
    }

    public boolean isM_image() {
        return m_image;
    }

    public String get_m_category() {
        return m_category;
    }

    public Date getM_time() {
        return m_time;
    }

    public String get_user_id() {
        return m_user_id;
    }

    public String getM_span() {
        return m_span;
    }

    public ArrayList<HashMap<String, Object>> getM_comments() {
        return m_comments;
    }

    public long getM_upvotes() {
        return ((m_upvotes.size() - m_downvotes.size()) + plus_or_minus) ;
    }

    public ArrayList<Long> getM_awards() {
        return m_awards;
    }

    public long getM_number_of_comments() {
        return m_comments.size();
    }

    public void set_is_this_upvoted(boolean mode){
        is_this_upvote = mode;
    }

    public boolean get_is_this_upvote(){
        return is_this_upvote;
    }

    public void set_the_down_vote(boolean mode){
        is_this_down_vote = mode;
    }
    public boolean get_the_down_vote(){
        return is_this_down_vote;
    }

    public String return_the_document_id(){
        return  m_document_id;
    }

    public boolean get_is_this_from_firebase(){
        return m_is_this_from_fire_base;
    }

    public ArrayList<String> getM_downvotes(){
        return m_downvotes;
    }

    public ArrayList<String> get_upvote_list(){
        return m_upvotes;
    }

    public String getM_my_user_id(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void set_saved(boolean mode){
        post_saved = mode;
    }
    public boolean get_m_saved(){
        return post_saved;
    }

    public void set_plus_or_minus(int value){
        plus_or_minus = value;
    }

    public boolean isM_is_this_laoding(){
        return m_is_this_laoding;
    }

    public boolean get_i_already_laoded(){
        return i_already_loaded;
    }
    public void set_i_already_load(boolean mode){
        i_already_loaded = mode;
    }

    public void code_was_touched(boolean mode){
        upvote_or_down_vote_was_clicked = mode;
    }
    public boolean return_state_of_touch(){
        return upvote_or_down_vote_was_clicked;
    }

    public FirebaseFirestore get_the_data_base(){
        return m_firebaseFirestore;
    }
    public boolean are_me_signed_with_google(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")){
            return true;
        } else {
            return false;
        }
    }
    public boolean is_this_reported(){
        return was_this_reported;
    }
    public void set_the_report(boolean mode){
        was_this_reported = mode;
    }

    public ArrayList<String> return_list_of_reports(){
        return m_list_of_reports;
    }
    public void setAm_i_loading_up_voote_down_vote_from_fire_base(boolean mode){
        am_i_loading_up_voote_down_vote_from_fire_base = mode;
    }
    public boolean isAm_i_loading_up_voote_down_vote_from_fire_base(){
        return am_i_loading_up_voote_down_vote_from_fire_base;
    }
    public boolean isDid_i_see_this_tem(){
        return did_i_see_this_tem;
    }
    public void setDid_i_see_this_tem(boolean mode){
        did_i_see_this_tem = mode;
    }
    public ArrayList<String> return_lsit_of_seen_posts(){
        return m_list_of_seen_posts;
    }
    public boolean return_is_post_by_dev(){
        return m_is_post_by_dev;
    }
}
