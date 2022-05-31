package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.musicplayer.adapter.MusicFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LocalMusicActivity extends AppCompatActivity {

    //创建viewpager和tablayout
    ViewPager2 viewPager;
    TabLayout musicTab;
    //创建tablayout和viewpaper的连接器
    TabLayoutMediator tabLayoutMediator;
    //创建ContentResolver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        viewPager=findViewById(R.id.viewPager);
        musicTab=findViewById(R.id.musicTab);
        MusicFragmentPagerAdapter musicFragmentPagerAdapter=new MusicFragmentPagerAdapter(this);
        viewPager.setAdapter(musicFragmentPagerAdapter);
        new TabLayoutMediator(musicTab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
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
        }).attach();
    }

    //创建viewpager

}