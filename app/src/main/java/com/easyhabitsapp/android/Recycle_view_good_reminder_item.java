package com.easyhabitsapp.android;

public class Recycle_view_good_reminder_item {
    private String m_time;
    private String m_day;

    public Recycle_view_good_reminder_item(String time, String day){
        m_time = time;
        m_day = day;
    }
    public String get_the_time(){
        return m_time;
    }
    public String get_the_date(){
        return m_day;
    }
}
