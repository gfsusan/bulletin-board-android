package com.example.bulletinboard.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.bulletinboard.R;
import com.example.bulletinboard.controller.BulletinBoardClient;
import com.example.bulletinboard.model.Post;

public class ModifyActivity extends AppCompatActivity {

    private final String TAG = "AddActivity";
    Context context;

    Toolbar toolbar;

    EditText title_tf;
    EditText content_tf;

    BulletinBoardClient bbc;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        context = this;

        toolbar = findViewById(R.id.modify_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        title_tf = findViewById(R.id.modify_title_tf);
        content_tf = findViewById(R.id.modify_content_tf);

        Intent intent = getIntent();

        int number = intent.getIntExtra("number", 0);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        post = new Post(number, title, content);
        title_tf.setText(post.title);
        content_tf.setText(post.content);

        bbc = BulletinBoardClient.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_action_save:
                // bbc.putRequest();
                return true;
            default:
                navigateUpTo(new Intent(this, MainActivity.class));
                return true;

        }
    }
}
