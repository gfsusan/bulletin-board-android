package com.example.bulletinboard.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bulletinboard.R;
import com.example.bulletinboard.controller.BulletinBoardClient;
import com.example.bulletinboard.controller.VolleyCallBack;
import com.example.bulletinboard.model.Post;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "DetailActivity";

    private Context context;

    private Toolbar actionBar;

    private TextView title_tv;
    private TextView content_tv;

    private BulletinBoardClient bbc;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;

        actionBar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        title_tv = findViewById(R.id.detail_title_tv);
        content_tv = findViewById(R.id.detail_content_tv);

        Intent intent = getIntent();

        int number = intent.getIntExtra("number", 0);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        bbc = BulletinBoardClient.getInstance(context);
        post = new Post(number, title, content);

        title_tv.setText(post.title);
        content_tv.setText(post.content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_action_modify:
                Intent intent = new Intent(this, ModifyActivity.class);
                intent.putExtra("number", post.number);
                intent.putExtra("title", post.title);
                intent.putExtra("content", post.content);
                context.startActivity(intent);
                return true;
            case R.id.detail_action_delete:
                Log.i(TAG, "Delete button pressed.");
                bbc.deletePst(post.number, new VolleyCallBack() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(context, "Could not delete post. Please try again.", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onSuccess() {
                        navigateUpTo(new Intent(context, MainActivity.class));
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(context, "Could not delete post. Please try again.", Toast.LENGTH_SHORT);

                    }
                });
                return true;
            default:
                navigateUpTo(new Intent(this, MainActivity.class));
                return true;
        }
    }

}
