package com.example.musicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.bean.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    /*歌曲列表*/
    public List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
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
