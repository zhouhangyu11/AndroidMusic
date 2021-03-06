package com.example.musicplayer.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplayer.ui.album.AlbumFragment;
import com.example.musicplayer.ui.folder.FolderFragment;
import com.example.musicplayer.ui.singer.SingerFragment;
import com.example.musicplayer.ui.singlesong.SingleSongFragment;

public class MusicFragmentPagerAdapter extends FragmentStateAdapter {


    public MusicFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new AlbumFragment();
            case 2:
                return new SingerFragment();
            case 3:
                return new FolderFragment();
            default:
                return new SingleSongFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
