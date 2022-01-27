package com.easyhabitsapp.android;

public class Example_item_for_journal {
    private String m_title;
    private String m_body;
    public Example_item_for_journal(String title,String body){
        m_body = body;
        m_title = title;
    }
    public String get_title(){
        return m_title;
    }
    public String get_body(){
        return m_body;
    }
}
