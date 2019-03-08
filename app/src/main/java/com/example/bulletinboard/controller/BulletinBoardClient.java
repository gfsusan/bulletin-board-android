package com.example.bulletinboard.controller;

import android.content.Context;
import android.media.MediaDrm;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bulletinboard.model.Post;
import com.example.bulletinboard.view.MyAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class BulletinBoardClient {
    private static final String TAG = "BulletinBoardClient";
    private static final String baseURL = "http://hulk.zeyo.co.kr:5002/api/documents";

    public static BulletinBoardClient instance;
    private static ArrayList<Post> posts;

    private static Context mContext;
    private RequestQueue queue;

    public  BulletinBoardClient(Context context) {
        this.mContext = context;
        queue = Volley.newRequestQueue(this.mContext);
        posts = new ArrayList<>();
    }

    public static synchronized BulletinBoardClient getInstance(Context context) {
        if (instance == null) {
            instance = new BulletinBoardClient(context);
        }

        return instance;
    }

    public void loadPosts(final VolleyCallBack callback) {
        StringRequest stringRequest= new StringRequest(Request.Method.GET, baseURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonResponse = jsonParser.parse(response);

                Log.d(TAG, "response: " + response);

                JsonArray jsonArray = (JsonArray) jsonResponse;
                posts = new ArrayList<>();
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject jo = (JsonObject) jsonElement;

                    String number = jo.get("number").toString();
                    String title = jo.get("title").toString();
                    String content = jo.get("content").toString();

                    posts.add(new Post(number, title, content));
                }

                callback.onSuccess();
                Log.d(TAG, "Get successful.");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do this on error
                callback.onFailure();
            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }


    public static ArrayList<Post> getPosts() {
        return posts;
    }
}
