package com.example.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerActivity extends Activity {
    //初始化myApplication实例
    MyApplication instance;
    ImageView imageView;
    TextView songName;
    TextView singerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //初始化控件和实例
        imageView=findViewById(R.id.iv_music);
        songName=findViewById(R.id.song_name);
        singerName=findViewById(R.id.singer_name);
        instance=MyApplication.instance;

        //设置控件
        if(instance.currentSong!=null) {//如果当前有歌正在播放，则显示专辑封面和名称
            imageView.setImageBitmap(instance.getCurrentSong().albumBitmap);
            songName.setText(instance.getCurrentSong().songName);
            singerName.setText(instance.getCurrentSong().singerName);
        }else{//如果没歌播放则显示未在播放中
            imageView.setImageResource(R.drawable.singer);
            songName.setText("未在播放");
            singerName.setText("");
        }
    }

}
