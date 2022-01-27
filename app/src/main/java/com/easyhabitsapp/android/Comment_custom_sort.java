package com.easyhabitsapp.android;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Comment_custom_sort implements Comparator<Map<String, Object>> {
    @Override
    public int compare(Map<String, Object> stringObjectHashMap, Map<String, Object> t1) {

        ArrayList<String> first_m_upvotes = (ArrayList<String>) stringObjectHashMap.get("up_vote_list");
        ArrayList<String> first_m_downvotes = (ArrayList<String>) stringObjectHashMap.get("down_vote_list");

        ArrayList<String> second_m_upvotes = (ArrayList<String>) t1.get("up_vote_list");
        ArrayList<String> second_m_downvotes = (ArrayList<String>) t1.get("down_vote_list");

        return Integer.compare(second_m_upvotes.size() - second_m_downvotes.size(), first_m_upvotes.size() - first_m_downvotes.size());
    }
}