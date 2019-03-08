package com.example.bulletinboard.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bulletinboard.R;
import com.example.bulletinboard.controller.ItemClickListener;
import com.example.bulletinboard.model.Post;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Post post;

    TextView tvTitle;
    TextView tvContent;

    private ItemClickListener itemClickListener;

    MyHolder(View view){
        super(view);

        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
