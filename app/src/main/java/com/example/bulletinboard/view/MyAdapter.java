package com.example.bulletinboard.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bulletinboard.model.Post;
import com.example.bulletinboard.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Post> posts;

    public MyAdapter(ArrayList<Post> posts){
        this.posts= posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myViewHolder = (MyHolder) holder;

        myViewHolder.tvTitle.setText(posts.get(position).title);
        myViewHolder.tvContent.setText(posts.get(position).content);


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
