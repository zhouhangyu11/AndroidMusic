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

import com.example.musicplayer.adapter.SingerAdapter;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingerFragment extends Fragment  {
    /*视图*/
    private View view;
    /*debug用的TAG*/
    private static final String TAG = "SingerFragment-ing";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_singer, container, false);

        /*初始化Map数组*/
        List<Map<String, Object>> singerList = new ArrayList<>();
        /*记录每个歌手在数组中的位置*/
        Map<String, Integer> singerLocationMap = new HashMap<>();
        LocalMusicActivity activity = (LocalMusicActivity) getActivity();
        List<Song> songList = activity.getSongList();
        int songNum = activity.getSongNum();

        /*遍历数组*/
        for (int i = 0; i < songNum; i++) {
            Song song = songList.get(i);
            String singerName = song.getSingerName();
            if (singerLocationMap.containsKey(singerName)) {
                int position = singerLocationMap.get(singerName);
                Map<String, Object> singerMap = singerList.get(position);
                singerMap.put("songNum", ((int) singerMap.get("songNum")) + 1);
                ((List<Song>) singerMap.get("songs")).add(song);
            } else {
                Map<String, Object> singerMap = new HashMap<>();
                singerMap.put("name", singerName);
                singerMap.put("image", song.getAlbumBitmap());
                singerMap.put("songNum", 1);
                List<Song> songs = new ArrayList<>();
                songs.add(song);
                singerMap.put("songs", songs);
                singerList.add(singerMap);
                singerLocationMap.put(singerName, singerList.size() - 1);
            }
        }

        // 得到RecyclerView
        RecyclerView singerRecyclerView = view.findViewById(R.id.singer_recycler_view);

        // 设置layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        singerRecyclerView.setLayoutManager(layoutManager);

        // 设置适配器
        SingerAdapter singerAdapter = new SingerAdapter(singerList);
        singerRecyclerView.setAdapter(singerAdapter);
        return view;
    }
}