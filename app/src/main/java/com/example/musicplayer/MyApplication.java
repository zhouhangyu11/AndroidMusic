package com.example.musicplayer;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.musicplayer.bean.Song;

import java.util.List;

public class MyApplication extends Application {
    /*维护一个全局的MediaPlayer*/
    private MediaPlayer mediaPlayer;
    /*静态属性instance，因为Application是单例*/
    public static MyApplication instance;

    //播放列表
    public List<Song>songList;
    //当前播放音乐
    public Song currentSong;
    //当前播放音乐的下标
    public int nowIndex;

    public int getNowIndex() {
        return nowIndex;
    }

    public void setNowIndex(int nowIndex) {
        this.nowIndex = nowIndex;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }


    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }
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
