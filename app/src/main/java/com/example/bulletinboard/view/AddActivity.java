package com.example.bulletinboard.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.bulletinboard.R;

public class AddActivity extends AppCompatActivity {

    Toolbar toolbar;

    EditText title_tf;
    EditText content_tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        title_tf = findViewById(R.id.title_tf);
        content_tf = findViewById(R.id.content_tf);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navigateUpTo(new Intent(this, MainActivity.class));

        return true;
    }
}
