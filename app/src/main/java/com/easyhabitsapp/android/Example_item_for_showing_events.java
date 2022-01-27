package com.easyhabitsapp.android;

public class Example_item_for_showing_events {
    private int mdrawable_resource;
    private int mdrawable_trash_can;
    private String mevent_name;
    private String mevent_description;
    private String mevent_time;
    private String mevent_am_or_pm;
    public Example_item_for_showing_events(int drawable_resource, String event_name, String event_description,int drawable_trash_can, String event_time, String event_am_or_pm){
        mdrawable_resource = drawable_resource;
        mevent_name = event_name;
        mevent_description = event_description;
        mdrawable_trash_can = drawable_trash_can;
        mevent_time = event_time;
        mevent_am_or_pm = event_am_or_pm;
    }
    public int getMdrawable_resource(){
        return mdrawable_resource;
    }
    public String getMevent_name(){
        return mevent_name;
    }
    public String getMevent_description(){
        return mevent_description;
    }
    public int getmdrawable_trash_can(){
        return mdrawable_trash_can;
    }
    public String getMevent_time(){
        return mevent_time;
    }
    public String getMevent_am_or_pm(){
        return mevent_am_or_pm;
    }
}
