package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.bean.Song;

import java.util.List;

public class FolderActivity extends AppCompatActivity {
    public static void beginActivity(Context context, List<Song> songList, String folderName, String folderPath) {
        Intent intent = new Intent(context, FolderActivity.class);
        // 将songList放入application中
        MyApplication.instance.setClassifiedSongs(songList);
        // 将文件夹名字以及路径放入intent
        intent.putExtra("folderName", folderName);
        intent.putExtra("folderPath", folderPath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        // 从intent中拿到文件夹名字以及路径
        Intent intent = getIntent();
        String folderName = intent.getStringExtra("folderName");
        String folderPath = intent.getStringExtra("folderPath");

        // 从MyApplication中拿到songList
        MyApplication instance = MyApplication.instance;
        List<Song> songList = instance.getClassifiedSongs();
        instance.setClassifiedSongs(null);

        // 设置路径以及文件夹名字
        TextView folderNameView = findViewById(R.id.folder_name);
        TextView folderPathView = findViewById(R.id.folder_path);
        folderNameView.setText(folderName);
        folderPathView.setText(folderPath);

        /*得到RecyclerView*/
        RecyclerView songRecyclerView = findViewById(R.id.song_list);

        /*设置LayoutManager*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        songRecyclerView.setLayoutManager(layoutManager);

        /*设置Adapter*/
        SongAdapter songAdapter = new SongAdapter(songList, this, R.layout.song_item);
        songRecyclerView.setAdapter(songAdapter);
    }
}