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

public class RecentPlay extends AppCompatActivity {
    RecyclerView recyclerView;
    MyApplication instance;
    List<Song>recentList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_play);
        recyclerView=findViewById(R.id.recent_list);
        swipeRefreshLayout=findViewById(R.id.swipe);

        instance=MyApplication.instance;
        recentList=instance.getRecentList();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SongAdapter songAdapter=new SongAdapter(recentList,this,R.layout.song_item);
        recyclerView.setAdapter(songAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SongAdapter songAdapter=new SongAdapter(recentList,RecentPlay.this,R.layout.song_item);
                        recyclerView.setAdapter(songAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },200);
            }
        });

    }

    public static void beginActivity(Context context) {
        Intent intent = new Intent(context, RecentPlay.class);
        context.startActivity(intent);
    }
}