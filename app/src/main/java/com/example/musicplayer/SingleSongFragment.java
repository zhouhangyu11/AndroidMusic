package com.example.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SingleSongFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private View view;
    private final String TAG = "SingleSongFragment-ing";

    public SingleSongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_single_song, container, false);

        // 得到RecyclerView
        RecyclerView songRecyclerView = view.findViewById(R.id.song_list);

        // 从所在Activity中得到songList
        LocalMusicActivity activity = (LocalMusicActivity) getActivity();

        // 设置layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        songRecyclerView.setLayoutManager(layoutManager);

        // 浅克隆一下
        List<Song> songList = new ArrayList<>();
        List<Song> originalSongList = activity.getSongList();
        int songNum = activity.getSongNum();
        for (int i = 0; i < songNum; i++) {
            songList.add(originalSongList.get(i));
        }
        Collections.sort(songList, new Comparator<Song>() {
            @Override
            public int compare(Song song, Song t1) {
                String songName1 = song.getSongName().toLowerCase(Locale.ROOT);
                String songName2 = t1.getSongName().toLowerCase(Locale.ROOT);
                return songName1.compareTo(songName2);
            }
        });
        SongAdapter songAdapter = new SongAdapter(songList, activity, R.layout.song_item);
        songRecyclerView.setAdapter(songAdapter);
        return view;
    }
}