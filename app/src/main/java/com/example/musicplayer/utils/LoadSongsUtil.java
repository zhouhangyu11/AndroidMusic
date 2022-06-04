package com.example.musicplayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.util.Log;

import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.List;

public class LoadSongsUtil {

    public static List<Song> getSongs(Context context) {
        final String TAG = "LoadSong-ing";
        List<Song> songList = new ArrayList<>();
        String postfix = ".mp3";

        // 初始化cursor
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        // 初始化专辑封面的bitMap
        Bitmap prevBitmap = null;
        String prevAlbumName = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                //去除800k以下歌曲
                if (cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.SIZE)) < 1000 * 800) {
                    continue;
                }
                Song song = new Song();
                // 得到歌曲名，去除后缀
                String songName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DISPLAY_NAME));
                songName = songName.replace(postfix, "");
                if (songName.indexOf("-") != -1) {
                    song.setSingerName(songName.split("-")[0].trim());
                    song.setSongName(songName.split("-")[1].trim());
                } else {
                    song.setSongName(songName);
                    song.setSingerName("未知歌手");
                }
//                 得到歌曲专辑名
                song.setAlbumName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)).trim());
                Log.d(TAG, "getSongs: "+cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM));
                // 得到时长
                song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)));
                // 大小
                song.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.SIZE)));
                //设置歌曲路径
                song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)).trim());
                // 设置图片的位图
                if (prevAlbumName == null || !prevAlbumName.equals(song.getAlbumName())) {
                    prevAlbumName = song.getAlbumName();
                    prevBitmap = loadAlbumBitmap(song.getPath());
                }


                song.setAlbumBitmap(prevBitmap);
                // 打印信息
                // Log.d(TAG, song.toString());
                songList.add(song);
            }
        }
        cursor.close();
        return songList;
    }


    public static Bitmap loadAlbumBitmap(String imagePath) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(imagePath);
        byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
        return bitmap;
    }

}
