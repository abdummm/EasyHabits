package com.easyhabitsapp.android;

public class Example_item_for_comments_replies {

    private String document_id;
    private String name_in_the_main;
    private String title_of_the_main;
    private String body_of_the_main_post;
    private String span_the_main;
    private long time_the_main;
    private String category_of_the_main;
    private long streak_of_the_main_post;

    // comment
    private String body_of_the_comment;
    private int position_of_the_comment;
    private String name_of_the_comment;
    private long time_of_the_comment;
    private String category;
    private long streak_of_the_comment;

    // reply
    private String body_of_the_reply;
    private int position_of_the_reply;
    private String name_of_the_reply;
    private long time_of_the_reply;
    private String category_reply;
    private long streak_of_the_reply;

    private String comment_or_reply;

    public Example_item_for_comments_replies(String document_id,String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment, String body_of_the_reply, int position_of_the_reply, String name_of_the_reply, long time_of_the_reply, String category_reply, long streak_of_the_reply) {
        this.document_id = document_id;
        this.name_in_the_main = name_in_the_main;
        this.title_of_the_main = title_of_the_main;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_the_main = span_the_main;
        this.time_the_main = time_the_main;
        this.category_of_the_main = category_of_the_main;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.body_of_the_comment = body_of_the_comment;
        this.position_of_the_comment = position_of_the_comment;
        this.name_of_the_comment = name_of_the_comment;
        this.time_of_the_comment = time_of_the_comment;
        this.category = category;
        this.streak_of_the_comment = streak_of_the_comment;
        this.body_of_the_reply = body_of_the_reply;
        this.position_of_the_reply = position_of_the_reply;
        this.name_of_the_reply = name_of_the_reply;
        this.time_of_the_reply = time_of_the_reply;
        this.category_reply = category_reply;
        this.streak_of_the_reply = streak_of_the_reply;
        comment_or_reply = "reply";
    }

    public Example_item_for_comments_replies(String document_id,String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment) {
        this.document_id = document_id;
        this.name_in_the_main = name_in_the_main;
        this.title_of_the_main = title_of_the_main;
        this.body_of_the_main_post = body_of_the_main_post;
        this.span_the_main = span_the_main;
        this.time_the_main = time_the_main;
        this.category_of_the_main = category_of_the_main;
        this.streak_of_the_main_post = streak_of_the_main_post;
        this.body_of_the_comment = body_of_the_comment;
        this.position_of_the_comment = position_of_the_comment;
        this.name_of_the_comment = name_of_the_comment;
        this.time_of_the_comment = time_of_the_comment;
        this.category = category;
        this.streak_of_the_comment = streak_of_the_comment;
        comment_or_reply = "comment";
    }

    public String getComment_or_reply() {
        return comment_or_reply;
    }


    public String getName_in_the_main() {
        return name_in_the_main;
    }

    public String getTitle_of_the_main() {
        return title_of_the_main;
    }

    public String getBody_of_the_main_post() {
        return body_of_the_main_post;
    }

    public String getSpan_the_main() {
        return span_the_main;
    }

    public long getTime_the_main() {
        return time_the_main;
    }

    public String getCategory_of_the_main() {
        return category_of_the_main;
    }

    public long getStreak_of_the_main_post() {
        return streak_of_the_main_post;
    }

    public String getBody_of_the_comment() {
        return body_of_the_comment;
    }

    public int getPosition_of_the_comment() {
        return position_of_the_comment;
    }

    public String getName_of_the_comment() {
        return name_of_the_comment;
    }

    public long getTime_of_the_comment() {
        return time_of_the_comment;
    }

    public String getCategory() {
        return category;
    }

    public long getStreak_of_the_comment() {
        return streak_of_the_comment;
    }

    public String getBody_of_the_reply() {
        return body_of_the_reply;
    }

    public int getPosition_of_the_reply() {
        return position_of_the_reply;
    }

    public String getName_of_the_reply() {
        return name_of_the_reply;
    }

    public long getTime_of_the_reply() {
        return time_of_the_reply;
    }

    public String getCategory_reply() {
        return category_reply;
    }

    public long getStreak_of_the_reply() {
        return streak_of_the_reply;
    }

    public String getDocument_id() {
        return document_id;
    }
}
