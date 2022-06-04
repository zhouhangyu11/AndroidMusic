package com.example.musicplayer;

import android.content.Intent;
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

public class PlaybarFragment extends Fragment {
    /*视图*/
    public View view;
    /*调试用*/
    final String TAG = "PlaybarFragment-ing";
    /*明确当前状态*/
    public boolean isPlay = false;
    public ImageButton playBtn;
    public ImageButton nextBtn;
    public ImageView musicImg;
    public TextView playingHint;
    /*动画属性*/
    Animation animation;
    LinearInterpolator lin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playbar, container, false);

        // 初始化当前播放信息
        playingHint = view.findViewById(R.id.playing_hint);
        //获取btn的id并创建监听器
        playBtn = view.findViewById(R.id.playBtn);
        nextBtn = view.findViewById(R.id.nextBtn);

        //找到音乐图片，实现旋转
        musicImg = view.findViewById(R.id.musicImg);
        //设置动画效果，设置匀速转动
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.img_animation);
        lin = new LinearInterpolator();//设置动画匀速运动

        //播放按钮事件，实现音乐播放暂停和图片转换，和音乐图片的旋转
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlay) {
                    /*得到父Activity中的MediaPlayer并且开始播放*/
                    try {
                        LocalMusicActivity activity = (LocalMusicActivity) getActivity();
                        if (activity.mediaPlayer.getDuration() > 0) {
                            activity.mediaPlayer.start();
                            startPlaying();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    /*得到父Activity中的MediaPlayer并且暂停*/
                    try {
                        LocalMusicActivity activity = (LocalMusicActivity) getActivity();
                        activity.mediaPlayer.pause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    playBtn.setImageResource(R.drawable.ic_play);
                    isPlay = false;
                    musicImg.clearAnimation();
                }
            }
        });
        //下一曲按钮，实现下一首
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //点击图片进入播放界面
        musicImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void startPlaying() {
        playBtn.setImageResource(R.drawable.ic_pause);
        isPlay = true;
        animation.setInterpolator(lin);
        musicImg.startAnimation(animation);
    }
}
