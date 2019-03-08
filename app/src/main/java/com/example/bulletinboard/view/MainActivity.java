package com.example.bulletinboard.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.bulletinboard.R;
import com.example.bulletinboard.controller.BulletinBoardClient;
import com.example.bulletinboard.controller.VolleyCallBack;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter myAdapter;

    private BulletinBoardClient bbc;
    private VolleyCallBack callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

        callBack = new VolleyCallBack() {
            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onSuccess() {
                // posts 초기화했기 때문에 Adapter 다시 생성
//                myAdapter.notifyDataSetChanged();
                myAdapter = new MyAdapter(BulletinBoardClient.getPosts());
                mRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure() {

            }
        };

        bbc = BulletinBoardClient.getInstance(this);
        bbc.loadPosts(callBack);

        myAdapter = new MyAdapter(BulletinBoardClient.getPosts());
        mRecyclerView.setAdapter(myAdapter);

    }
}
