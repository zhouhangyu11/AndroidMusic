package com.example.musicplayer;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    /*维护一个全局的MediaPlayer*/
    private MediaPlayer mediaPlayer;
    /*静态属性instance，因为Application是单例*/
    public static MyApplication instance;

    // 存储第一次扫描得到的所有歌曲
    public List<Song> scannedSongs;

    //播放列表
    public List<Song> songList;
    //当前播放音乐
    public Song currentSong;
    //当前播放音乐的下标
    public int nowIndex;
    //当前音乐状态
    boolean isPlay = false;
    // 当前专辑列表
    public List<Song> classifiedSongs;

    //当前已经播放过的歌的列表
    public List<Song>recentList;

    //当前歌曲时长
    public int duration;

    //喜欢的歌曲的列表
    public List<Song>likeList;


    public List<Song> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Song> likeList) {
        this.likeList = likeList;
    }



    public List<Song> getRecentList() {
        return recentList;
    }

    public void setRecentList(List<Song> recentList) {
        this.recentList = recentList;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public boolean getPlay() {
        return isPlay;
    }

    public void setPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

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

    public List<Song> getClassifiedSongs() {
        return classifiedSongs;
    }

    public void setClassifiedSongs(List<Song> classifiedSongs) {
        this.classifiedSongs = classifiedSongs;
    }

    public List<Song> getScannedSongs() {
        return scannedSongs;
    }

    public void setScannedSongs(List<Song> scannedSongs) {
        this.scannedSongs = scannedSongs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*初始化mediaPlayer*/
        this.mediaPlayer = new MediaPlayer();
        instance = this;
        recentList=new ArrayList<>();
        likeList=new ArrayList<>();
    }

    public void addToRecent(Song song){
        if(recentList.isEmpty()){
            recentList.add(song);
        }else {
            int x=0;
            for(int i=0;i<recentList.size();i++){
                if(recentList.get(i)==song){
                    recentList.remove(i);
                    recentList.add(0,song);

                    x=1;
                    break;
                }
            }
            if(x==0)
                recentList.add(0,song);
        }
    }

}
