package com.example.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class PlaybarFragment extends Fragment {


    /*视图*/
    public View view;
    /*调试用*/
    final String TAG = "PlaybarFragment-ing";
    public ImageButton playBtn;
    public ImageButton nextBtn;
    public ImageView musicImg;
    public TextView playingHint;
    FragmentActivity mActivity;
    /*动画属性*/
    Animation animation;
    LinearInterpolator lin;
    MediaPlayer mediaPlayer;

    //初始化myApplication实例
    MyApplication instance;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playbar, container, false);
        // 获得绑定的Activity
        mActivity = getActivity();

        /*拿到mediaPlayer*/
        instance = MyApplication.instance;
        mediaPlayer = instance.getMediaPlayer();

        // 初始化当前播放信息
        playingHint = view.findViewById(R.id.playing_hint);
        //获取btn的id并创建监听器
        playBtn = view.findViewById(R.id.playBtn);
        nextBtn = view.findViewById(R.id.nextBtn);

        //找到音乐图片，实现旋转
        musicImg = view.findViewById(R.id.musicImg);
        //设置动画效果，设置匀速转动
        animation = AnimationUtils.loadAnimation(mActivity, R.anim.img_animation);
        lin = new LinearInterpolator();//设置动画匀速运动

        //播放按钮事件，实现音乐播放暂停和图片转换，和音乐图片的旋转
        playBtn.setOnClickListener(view -> {
            if (!instance.isPlay) {
                /*得到父Activity中的MediaPlayer并且开始播放*/
                try {
                    if (mediaPlayer.getDuration() > 0) {
                        mediaPlayer.start();
                        startPlaying();
                        instance.setPlay(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                /*得到父Activity中的MediaPlayer并且暂停*/
                try {
                    mediaPlayer.pause();
                    stopPlaying();
                    instance.setPlay(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //下一曲按钮，实现下一首
        nextBtn.setOnClickListener(view -> {
            if (instance.getCurrentSong() != null) {
                instance.nowIndex++;
                instance.setCurrentSong(instance.songList.get(instance.nowIndex));
                instance.addToRecent(instance.currentSong);//加入最近播放列表
                try {
                    /*首先初始化*/
                    mediaPlayer.reset();
                    //设置资源
                    mediaPlayer.setDataSource(instance.currentSong.getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    //设置封面和名称
                    musicImg.setImageBitmap(instance.getCurrentSong().albumBitmap);
                    playingHint.setText(instance.getCurrentSong().songName);
                    //开始播放
                    startPlaying();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //点击进入播放界面
        view.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PlayerActivity.class);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 通过mediaPlayer来判断是否正在播放
        if (instance.isPlay) {
            startPlaying();
        } else {
            stopPlaying();
        }

        // 设置图片
        if (instance.getCurrentSong() != null) {
            musicImg.setImageBitmap(instance.getCurrentSong().albumBitmap);
            playingHint.setText(instance.getCurrentSong().songName);
        }
    }

    /*开始播放设置状态*/
    public void startPlaying() {
        playBtn.setImageResource(R.drawable.ic_pause);
        animation.setInterpolator(lin);
        musicImg.startAnimation(animation);
    }

    /*暂停播放设置状态*/
    public void stopPlaying() {
        playBtn.setImageResource(R.drawable.ic_play);
        musicImg.clearAnimation();
    }
}
