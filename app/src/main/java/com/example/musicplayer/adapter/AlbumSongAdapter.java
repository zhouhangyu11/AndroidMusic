package com.example.musicplayer.adapter;

import static java.lang.String.valueOf;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.MyApplication;
import com.example.musicplayer.PlaybarFragment;
import com.example.musicplayer.R;
import com.example.musicplayer.bean.Song;

import java.io.IOException;
import java.util.List;

public class AlbumSongAdapter extends RecyclerView.Adapter<AlbumSongAdapter.ViewHolder> {
    /*调试用*/
    private static final String TAG = "SongAdapter";
    /*歌曲列表*/
    public List<Song> songList;
    /*所在Activity的播放器*/
    public MediaPlayer mediaPlayer;
    /*所在Activity*/
    FragmentActivity activity;

    public AlbumSongAdapter(List<Song> songList, FragmentActivity activity) {
        this.songList = songList;
        this.activity = activity;
        /*从Application中拿到mediaPlayer*/
        MyApplication instance = MyApplication.instance;
        this.mediaPlayer = instance.getMediaPlayer();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_song_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        /*设置事件监听*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*设置mediaPlayer的source*/
                try {
                    /*拿到所在位置, 需要在点击了过后才能拿得到*/
                    int position = holder.getAdapterPosition();
                    /*拿到歌曲实例*/
                    Song song = songList.get(position);
                    //创建myApplication单例，并将当前播放的音乐写入myApplication的单例中
                    MyApplication instance = MyApplication.instance;
                    instance.setSongList(songList);
                    instance.nowIndex = position;
                    instance.setCurrentSong(song);
                    instance.setPlay(true);
                    /*首先初始化*/
                    mediaPlayer.reset();
                    /*设置路径并且开始播放*/
                    mediaPlayer.setDataSource(song.getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    /*修改playbarFragment的样子*/
                    PlaybarFragment playbarFragment = (PlaybarFragment) activity.getSupportFragmentManager().findFragmentById(R.id.playbar_fragment);
                    playbarFragment.musicImg.setImageBitmap(song.getAlbumBitmap());
                    playbarFragment.playingHint.setText(song.getSongName());
                    playbarFragment.startPlaying();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = this.songList.get(position);

        // 设置编号以及文本
        holder.songId.setText(valueOf(position));
        holder.songName.setText(song.getSongName());
    }

    @Override
    public int getItemCount() {
        return this.songList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View songView;
        public TextView songName;
        public TextView songId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songView = itemView;
            songName = songView.findViewById(R.id.songName);
            songId = songView.findViewById(R.id.song_id);
        }
    }
}
