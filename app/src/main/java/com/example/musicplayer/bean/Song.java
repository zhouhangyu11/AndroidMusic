package com.example.musicplayer.bean;

public class Song {//歌曲类的数据类
    public String songName;//歌曲名
    public String singerName;//歌手名
    public String imgPath;//封面地址

    public Song() {

    }

    public Song(String songName, String singerName, String imgPath) {
        this.songName = songName;
        this.singerName = singerName;
        this.imgPath = imgPath;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
