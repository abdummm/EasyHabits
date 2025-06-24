package com.easyhabitsapp.android;

public class Example_item_add_new_habit {
    private String color;
    private String name;
    private String emoji;

    public Example_item_add_new_habit(String color, String name, String emoji) {
        this.color = color;
        this.name = name;
        this.emoji = emoji;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
