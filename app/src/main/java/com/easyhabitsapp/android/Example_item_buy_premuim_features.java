package com.easyhabitsapp.android;

public class Example_item_buy_premuim_features {
    private String emoji_icon;
    private String text;
    private String color;

    public Example_item_buy_premuim_features(String emoji_icon, String text, String color) {
        this.emoji_icon = emoji_icon;
        this.text = text;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getEmoji_icon() {
        return emoji_icon;
    }

    public String getText() {
        return text;
    }
}
