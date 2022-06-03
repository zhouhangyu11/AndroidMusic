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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public List<Song> albumList;

    /*构造方法*/

    public AlbumAdapter(List<Song> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = this.albumList.get(position);
        holder.albumImage.setImageBitmap(song.getAlbumBitmap());
        holder.albumName.setText(song.getAlbumName());
        holder.singer.setText(song.getSingerName());
    }

    @Override
    public int getItemCount() {
        return this.albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View albumView;
        public ImageView albumImage;
        public TextView albumName;
        public TextView singer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumView = itemView;
            albumImage = albumView.findViewById(R.id.album_image);
            albumName = albumView.findViewById(R.id.album_name);
            singer = albumView.findViewById(R.id.singer);
        }
    }
}
