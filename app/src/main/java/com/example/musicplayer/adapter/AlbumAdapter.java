package com.example.musicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.AlbumActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public List<Song> albumList;
    public List<Song> songList;
    public FragmentActivity activity;

    /*构造方法*/

    public AlbumAdapter(List<Song> albumList, List<Song> songList, FragmentActivity activity) {
        this.albumList = albumList;
        this.songList = songList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);

        ViewHolder holder = new ViewHolder(view);

        // 点击专辑，跳转到AlbumActivity，并且传入对应歌曲列表
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 得到专辑名
                int position = holder.getAdapterPosition();
                String albumName = albumList.get(position).getAlbumName();
                // 过滤得到专辑中的歌
                List<Song> songs = new ArrayList<>();
                // 传入的songList是按照专辑名排序了的
                int size = songList.size();
                for (int i = 0; i < size; i++) {
                    Song song = songList.get(i);
                    if (!song.getAlbumName().equals(albumName)) {
                        continue;
                    }
                    for (int j = i; j < size; j++) {
                        song = songList.get(j);
                        if (song.getAlbumName().equals(albumName)) {
                            songs.add(song);
                        } else {
                            break;
                        }
                    }
                    break;
                }
                AlbumActivity.beginActivity(activity, songs);
            }
        });
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
