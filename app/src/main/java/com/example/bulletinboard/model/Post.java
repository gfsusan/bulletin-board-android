package com.example.bulletinboard.model;

import org.json.JSONObject;

public class Post {
    public String number;
    public String title;
    public String content;

    public Post(String number, String title, String content) {
        this.number = number;
        this.title = title;
        this.content = content;
    }
}
