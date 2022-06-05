package com.example.musicplayer.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Song implements Serializable {//歌曲类的数据类
    public String songName;//歌曲名
    public String singerName;//歌手名
    public String albumName; // 专辑名
    public Bitmap albumBitmap;//专辑封面图
    public String path;// 歌曲路径
    public int duration; // 时长
    public long size; // 大小

    public Song() {

    }

    public Song(String songName, String singerName, Bitmap albumBitmap, String path, int duration, long size) {
        this.songName = songName;
        this.singerName = singerName;
        this.albumBitmap = albumBitmap;
        this.path = path;
        this.duration = duration;
        this.size = size;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumBitmap(Bitmap albumBitmap) {
        this.albumBitmap = albumBitmap;
    }

    public Bitmap getAlbumBitmap() {
        return albumBitmap;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumBitmap=" + albumBitmap +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                '}';
    }
}
