package com.easyhabitsapp.android;

public class Item_for_chat {
    private String m_got;
    private String m_sent;
    private String mode;

    public Item_for_chat(String got, String sent, String mode){
        m_got = got;
        m_sent = sent;
        this.mode = mode;
    }

    public String get_got_messages(){
        return m_got;
    }
    public String get_send_messages(){
        return m_sent;
    }
    public String getMode() {return mode;}

}
