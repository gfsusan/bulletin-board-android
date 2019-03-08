package com.example.bulletinboard.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bulletinboard.R;
import com.example.bulletinboard.model.Post;

public class DetailActivity extends AppCompatActivity {

    Toolbar actionBar;

    TextView title_tv;
    TextView content_tv;

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        actionBar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title_tv = findViewById(R.id.detail_title_tv);
        content_tv = findViewById(R.id.detail_content_tv);

        Intent intent = getIntent();

        int number = intent.getIntExtra("number", 0);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        post = new Post(number, title, content);

        title_tv.setText(post.title);
        content_tv.setText(post.content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navigateUpTo(new Intent(this, MainActivity.class));

        return true;
    }

}
