package com.example.musicplayer.utils;

import com.example.musicplayer.MyApplication;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MusicOperation {
    //对音乐进行操作

    static MyApplication instance;
    static Timer timer = new Timer();
    static List<Song> recentList = new ArrayList<>();


    public static void nextSong() {
        //下一曲
        instance = MyApplication.instance;
        if (instance.getCurrentSong() != null) {
            instance.nowIndex++;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }

    public static void previousSong() {
        //上一曲
        instance = MyApplication.instance;
        if (instance.getCurrentSong() != null) {
            instance.nowIndex--;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }
}
