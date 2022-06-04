package com.example.musicplayer.utils;

import com.example.musicplayer.MyApplication;

public class MusicOperation {//对音乐进行操作
    MyApplication instance;

    public void nextSong(){//上一曲
        instance=MyApplication.instance;
        if(instance.getCurrentSong()!=null) {
            instance.nowIndex++;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }
    public void previousSong(){//下一曲
        instance=MyApplication.instance;
        if(instance.getCurrentSong()!=null) {
            instance.nowIndex--;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }
}
