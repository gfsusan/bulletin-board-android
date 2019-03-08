package com.example.bulletinboard.model;

import org.json.JSONObject;

public class Post {
    public int number;
    public String title;
    public String content;

    public Post(int number, String title, String content) {
        this.number = number;
        this.title = title;
        this.content = content;
    }
}
