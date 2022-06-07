package com.example.musicplayer.utils;

import com.example.musicplayer.MyApplication;

import java.util.Timer;

public class MusicOperation {//对音乐进行操作


    MyApplication instance;
    Timer timer=new Timer();


    public void nextSong(){//下一曲
        instance=MyApplication.instance;
        if(instance.getCurrentSong()!=null) {
            instance.nowIndex++;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }
    public void previousSong(){//上一曲
        instance=MyApplication.instance;
        if(instance.getCurrentSong()!=null) {
            instance.nowIndex--;
            instance.setCurrentSong(instance.songList.get(instance.nowIndex));
        }
    }

    public void timeOut(){//歌曲结束

    }
}
