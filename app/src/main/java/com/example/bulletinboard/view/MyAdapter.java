package com.example.bulletinboard.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bulletinboard.controller.ItemClickListener;
import com.example.bulletinboard.model.Post;
import com.example.bulletinboard.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context context;
    private ArrayList<Post> posts;

    public MyAdapter(Context ctx, ArrayList<Post> posts){
        this.context = ctx;
        this.posts= posts;
    }

    public void showPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Post post = posts.get(position);

        holder.tvTitle.setText(post.title);
        holder.tvContent.setText(post.content);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(context, DetailActivity.class);

                Post clickedPost = posts.get(pos);
                intent.putExtra("number", clickedPost.number);
                intent.putExtra("title", clickedPost.title);
                intent.putExtra("content", clickedPost.content);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    //    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle;
//        TextView tvContent;
//
//        MyViewHolder(View view){
//            super(view);
//            tvTitle = view.findViewById(R.id.tv_title);
//            tvContent = view.findViewById(R.id.tv_content);
//        }
//    }

}
