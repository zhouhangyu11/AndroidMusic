package com.example.musicplayer.adapter;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.LocalMusicActivity;
import com.example.musicplayer.PlaybarFragment;
import com.example.musicplayer.R;
import com.example.musicplayer.bean.Song;

import java.io.IOException;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    /*调试用*/
    private static final String TAG = "SongAdapter";
    /*歌曲列表*/
    public List<Song> songList;
    /*所在Activity的播放器*/
    public MediaPlayer mediaPlayer;
    /*所在Activity*/
    LocalMusicActivity activity;

    public SongAdapter(List<Song> songList, MediaPlayer player, LocalMusicActivity activity) {
        this.songList = songList;
        this.mediaPlayer = player;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
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

        // 设置图片以及文本
        holder.albumImage.setImageBitmap(song.getAlbumBitmap());
        holder.songName.setText(song.getSongName());
        holder.singerName.setText(song.getSingerName());
    }

    @Override
    public int getItemCount() {
        return this.songList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View songView;
        public ImageView albumImage;
        public TextView songName;
        public TextView singerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songView = itemView;
            albumImage = songView.findViewById(R.id.songImg);
            songName = songView.findViewById(R.id.songName);
            singerName = songView.findViewById(R.id.singerNameOfSong);
        }
    }
}
