package com.example.musicplayer.ui.folder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.LocalMusicActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.adapter.FolderAdapter;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FolderFragment extends Fragment {
    /*调试用*/
    private final String TAG = "FolderFragment-ing";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*视图*/
        View view = inflater.inflate(R.layout.fragment_folder, container, false);

        // 得到所有歌曲
        List<Song> songList = new ArrayList<>();
        int songNum = ((LocalMusicActivity) getActivity()).getSongNum();
        List<Song> originSongList = ((LocalMusicActivity) getActivity()).getSongList();

        for (int i = 0; i < songNum; i++) {
            songList.add(originSongList.get(i));
        }

        // 按照所在目录排序
        songList.sort((Song song1, Song song2) -> {
            String folderPath1 = song1.getPath().toLowerCase(Locale.ROOT);
            String folderPath2 = song2.getPath().toLowerCase(Locale.ROOT);

            return folderPath1.compareTo(folderPath2);
        });

        // 按照所在目录进行分组
        List<Map<String, Object>> folderList = new ArrayList<>();
        String prevFolder = null;
        // 当前目录中的歌曲
        List<Song> songs;
        // 记录当前目录信息
        Map<String, Object> folderMap = new HashMap<>();
        for (int i = 0; i < songNum; i++) {
            Song song = songList.get(i);
            String curPath = song.getPath();
            // 得到路径中的倒数第二部分，即所在文件夹
            int end = curPath.lastIndexOf("/");
            int start = curPath.lastIndexOf("/", end - 1);
            String curFolder = curPath.substring(start + 1, end);
            if (prevFolder == null || !prevFolder.equals(curFolder)) {
                /*将之前的map加入数组中*/
                if (prevFolder != null) {
                    folderList.add(folderMap);
                }
                folderMap = new HashMap<>();
                songs = new ArrayList<>();
                folderMap.put("name", curFolder);
                folderMap.put("path", curPath);
                folderMap.put("songNum", 1);
                songs.add(song);
                folderMap.put("songs", songs);
                prevFolder = curFolder;
            } else {
                /*更新folderMap*/
                folderMap.put("songNum", ((int) folderMap.get("songNum")) + 1);
                ((List<Song>) folderMap.get("songs")).add(song);
            }
        }
        folderList.add(folderMap);
        // 得到RecyclerView
        RecyclerView folderRecyclerView = view.findViewById(R.id.folder_recycler_view);

        // 设置layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        folderRecyclerView.setLayoutManager(layoutManager);

        // 设置adapter
        FolderAdapter folderAdapter = new FolderAdapter(folderList, getActivity());
        folderRecyclerView.setAdapter(folderAdapter);

        return view;
    }
}