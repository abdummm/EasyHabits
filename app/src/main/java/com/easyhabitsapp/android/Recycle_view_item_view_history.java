package com.easyhabitsapp.android;

public class Recycle_view_item_view_history {
    private String m_weight;
    private String m_date;

    public Recycle_view_item_view_history(String weight, String date){
        m_date = date;
        m_weight = weight;
    }
    public String get_the_weight(){
        return m_weight;
    }
    public String get_the_date(){
        return m_date;
    }
}
