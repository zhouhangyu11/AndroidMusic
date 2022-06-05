package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.bean.Song;

import java.util.List;
import java.util.Locale;

public class SingerActivity extends AppCompatActivity {
    public static void beginActivity(Context context, List<Song> songList, int singer_image) {
        Intent intent = new Intent(context, SingerActivity.class);
        // 将songList放入application中
        MyApplication.instance.setClassifiedSongs(songList);
        // 将照片放入intent中
        intent.putExtra("singer_image", singer_image);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer);

        // 从MyApplication中拿到SongList
        MyApplication instance = MyApplication.instance;
        List<Song> songList = instance.getClassifiedSongs();
        instance.setClassifiedSongs(null);

        // 将songList排序
        songList.sort((song, t1) -> {
            String songName1 = song.getSongName().toLowerCase(Locale.ROOT);
            String songName2 = t1.getSongName().toLowerCase(Locale.ROOT);
            return songName1.compareTo(songName2);
        });

        // 从intent中拿到singerImage
        Intent intent = getIntent();
        int singer_img_int = intent.getIntExtra("singer_image", R.drawable.singer);

        Song song = songList.get(0);
        //设置封面
        ImageView singerImage = findViewById(R.id.singer_image);
        singerImage.setImageResource(singer_img_int);
        // 设置歌手名
        TextView singerName = findViewById(R.id.singer_name);
        singerName.setText(song.getSingerName());

        /*得到RecyclerView*/
        RecyclerView songRecyclerView = findViewById(R.id.song_list);

        /*设置LayoutManager*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        songRecyclerView.setLayoutManager(layoutManager);

        /*设置Adapter*/
        SongAdapter songAdapter = new SongAdapter(songList, this, R.layout.singer_song_item);
        songRecyclerView.setAdapter(songAdapter);
    }
}