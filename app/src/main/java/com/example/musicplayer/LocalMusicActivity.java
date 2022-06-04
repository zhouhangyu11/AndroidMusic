package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicplayer.adapter.MusicFragmentPagerAdapter;
import com.example.musicplayer.bean.Song;
import com.example.musicplayer.utils.LoadSongsUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class LocalMusicActivity extends AppCompatActivity {

    private TabLayout musicTab;
    private final Context context = LocalMusicActivity.this;
    //创建tablayout和viewpaper的连接器
    private TabLayoutMediator tabLayoutMediator;
    // 全局的songList
    public List<Song> songList = new ArrayList<>();

    // 启动当前活动的方法
    public static void beginActivity(Context context) {
        Intent intent = new Intent(context, LocalMusicActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        // 从本地文件中得到歌曲列表
        songList = LoadSongsUtil.getSongs(context);
        // 将歌曲按照顺序排列
        Collections.sort(songList, new AlbumNameComparator());
        // 得到ViewPager对象
        //创建viewpager和tablayout
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        musicTab = findViewById(R.id.musicTab);
        MusicFragmentPagerAdapter musicFragmentPagerAdapter = new MusicFragmentPagerAdapter(this);
        viewPager.setAdapter(musicFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayoutMediator =
                new TabLayoutMediator(musicTab, viewPager, (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("单曲");
                            break;
                        case 1:
                            tab.setText("专辑");
                            break;
                        case 2:
                            tab.setText("歌手");
                            break;
                        case 3:
                            tab.setText("文件夹");
                            break;
                    }
                });
        tabLayoutMediator.attach();
    }

    @NonNull
    public List<Song> getSongList() {
        return songList;
    }

    public int getSongNum() {
        return this.songList.size();
    }

    class AlbumNameComparator implements Comparator {
        @Override
        public int compare(Object o, Object t1) {
            String album1 = ((Song) o).getAlbumName().toLowerCase(Locale.ROOT);
            String album2 = ((Song) t1).getAlbumName().toLowerCase(Locale.ROOT);
            return album1.compareTo(album2);
        }
    }
}