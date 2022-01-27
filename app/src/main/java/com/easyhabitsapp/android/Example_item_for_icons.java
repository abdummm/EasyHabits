package com.easyhabitsapp.android;

public class Example_item_for_icons {
    String first_text;
    int first_id;
    String second_text;
    int second_id;
    String third_text;
    int third_id;

    public Example_item_for_icons(String first_text, int first_id, String second_text, int second_id, String third_text, int third_id){
        this.first_text = first_text;
        this.first_id = first_id;
        this.second_text = second_text;
        this.second_id = second_id;
        this.third_text = third_text;
        this.third_id = third_id;
    }

    public String getFirst_text() {
        return first_text;
    }

    public int getFirst_id() {
        return first_id;
    }

    public String getSecond_text() {
        return second_text;
    }

    public int getSecond_id() {
        return second_id;
    }

    public String getThird_text() {
        return third_text;
    }

    public int getThird_id() {
        return third_id;
    }
}
