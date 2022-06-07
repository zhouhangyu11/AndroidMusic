package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.bean.Song;

import java.util.List;

public class LikeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyApplication instance;
    List<Song>likeList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        recyclerView=findViewById(R.id.like_list);
        swipeRefreshLayout=findViewById(R.id.swipe_like);

        instance=MyApplication.instance;
        likeList=instance.getLikeList();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SongAdapter songAdapter=new SongAdapter(likeList,this,R.layout.song_item);
        recyclerView.setAdapter(songAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SongAdapter songAdapter=new SongAdapter(likeList,LikeActivity.this,R.layout.song_item);
                        recyclerView.setAdapter(songAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },200);
            }
        });


    }

    public static void beginActivity(Context context) {
        Intent intent = new Intent(context, LikeActivity.class);
        context.startActivity(intent);
    }
}