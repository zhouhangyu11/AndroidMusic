package com.example.musicplayer;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApplication extends Application {
    /*维护一个全局的MediaPlayer*/
    private MediaPlayer mediaPlayer;
    /*静态属性instance，因为Application是单例*/
    public static MyApplication instance;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*初始化mediaPlayer*/
        this.mediaPlayer = new MediaPlayer();
        instance = this;
    }

}
