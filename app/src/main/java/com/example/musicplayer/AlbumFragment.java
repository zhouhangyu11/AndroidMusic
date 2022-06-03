package com.example.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.AlbumAdapter;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.List;

public class AlbumFragment extends Fragment {
    // 视图
    public View view;
    // 调试用的TAG
    public final String TAG = "AlbumFragment_ing";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);

        // 得到专辑列表
        LocalMusicActivity activity = (LocalMusicActivity) getActivity();
        List<Song> albumList = new ArrayList<>();
        // 默认是按照专辑名字升序排列的
        List<Song> songList = activity.getSongList();
        int songNum = activity.getSongNum();
        String prevAlbumName = null;

        // 得到每张专辑的第一首
        for (int i = 0; i < songNum; i++) {
            Song song = songList.get(i);
            if (prevAlbumName == null || !prevAlbumName.equals(song.getAlbumName())) {
                albumList.add(song);
                prevAlbumName = song.getAlbumName();
            }
        }

        // 得到RecyclerView
        RecyclerView albumRecyclerView = view.findViewById(R.id.album_recycler_view);

        // 设置layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        albumRecyclerView.setLayoutManager(layoutManager);

        // 设置Adapter
        AlbumAdapter albumAdapter = new AlbumAdapter(albumList);
        albumRecyclerView.setAdapter(albumAdapter);

        return view;
    }
}