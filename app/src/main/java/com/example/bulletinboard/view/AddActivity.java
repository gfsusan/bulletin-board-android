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
import android.widget.EditText;
import android.widget.Toast;

import com.example.bulletinboard.R;
import com.example.bulletinboard.controller.BulletinBoardClient;
import com.example.bulletinboard.controller.VolleyCallBack;

public class AddActivity extends AppCompatActivity {

    private final String TAG = "AddActivity";
    Context context;

    Toolbar toolbar;

    EditText title_tf;
    EditText content_tf;

    BulletinBoardClient bbc;

    // number for keeping track of "number" field
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        context = this;

        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        title_tf = findViewById(R.id.title_tf);
        content_tf = findViewById(R.id.content_tf);

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
                final String title = title_tf.getText().toString();
                final String content = content_tf.getText().toString();

                VolleyCallBack callBack = new VolleyCallBack() {
                    @Override
                    public void onError(Throwable t) {
                        Log.i(TAG, "BAD_REQUEST");
                        number ++;
                        bbc.addPost(number, title, content, this);
                        Log.i(TAG, "number : " + number);
                    }

                    @Override
                    public void onSuccess() {
                        navigateUpTo(new Intent(context, MainActivity.class));
                        number++;
                    }

                    @Override
                    public void onFailure() {

                    }
                };

                bbc.addPost(number, title, content, callBack);
                return true;
            default:
                navigateUpTo(new Intent(this, MainActivity.class));
        }

        return true;
    }
}
