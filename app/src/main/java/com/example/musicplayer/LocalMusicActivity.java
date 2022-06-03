package com.example.musicplayer;

import android.content.Context;
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

public class LocalMusicActivity extends AppCompatActivity {

    //创建viewpager和tablayout
    private ViewPager2 viewPager;
    private TabLayout musicTab;
    private Context context = LocalMusicActivity.this;
    //创建tablayout和viewpaper的连接器
    private TabLayoutMediator tabLayoutMediator;
    // 全局的songList
    public List<Song> songList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        // 从本地文件中得到歌曲列表
        songList = LoadSongsUtil.getSongs(context);
        // 将歌曲按照顺序排列
        Collections.sort(songList, new AlbumNameComparator());
        // 得到ViewPager对象
        viewPager = findViewById(R.id.viewPager);
        musicTab = findViewById(R.id.musicTab);
        MusicFragmentPagerAdapter musicFragmentPagerAdapter = new MusicFragmentPagerAdapter(this);
        viewPager.setAdapter(musicFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayoutMediator =
                new TabLayoutMediator(musicTab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
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
            String album1 = ((Song) o).getAlbumName();
            String album2 = ((Song) t1).getAlbumName();
            return album1.compareTo(album2);
        }
    }
}