package com.example.musicplayer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.AlbumSongAdapter;
import com.example.musicplayer.bean.Song;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    public static void beginActivity(Context context, List<Song> songList) {
        Intent intent = new Intent(context, AlbumActivity.class);
        // 将songList放入application中
        MyApplication.instance.setClassifiedSongs(songList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // 从MyApplication中拿到SongList
        MyApplication instance = MyApplication.instance;
        List<Song> songList = instance.getClassifiedSongs();
        instance.setClassifiedSongs(null);


        Song song = songList.get(0);
        //设置封面
        ImageView cover = findViewById(R.id.album_cover);
        cover.setImageBitmap(song.getAlbumBitmap());
        // 设置专辑名以及歌手名
        TextView albumName = findViewById(R.id.album_name);
        albumName.setText(song.getAlbumName());
        TextView singerName = findViewById(R.id.singer_name);
        singerName.setText(song.getSingerName());

        /*得到RecyclerView*/
        RecyclerView songRecyclerView = findViewById(R.id.song_list);

        /*设置LayoutManager*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        songRecyclerView.setLayoutManager(layoutManager);

        /*设置Adapter*/
        AlbumSongAdapter songAdapter = new AlbumSongAdapter(songList, this);
        songRecyclerView.setAdapter(songAdapter);
    }
}
