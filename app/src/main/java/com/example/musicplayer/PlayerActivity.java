package com.example.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.utils.MusicOperation;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends Activity {
    //初始化myApplication实例和各个组件
    MyApplication instance;
    ImageView imageView;
    TextView songName;
    TextView singerName;
    ImageButton play_btn;
    ImageButton next_btn;
    ImageButton previous_btn;
    SeekBar seekBar;
    TextView now_position;
    TextView total;

    MusicOperation musicOperation=new MusicOperation();

    //定义一个Timer作为定时器
    private Timer timer;
    private TimerTask timerTask;
    private boolean isSeekBarChanging;//互斥变量，防止进度条和定时器冲突

    public static String formatTime(String pattern, long milli) {
        int m = (int) (milli / DateUtils.MINUTE_IN_MILLIS);
        int s = (int) ((milli / DateUtils.SECOND_IN_MILLIS) % 60);
        String mm = String.format(Locale.getDefault(), "%02d", m);
        String ss = String.format(Locale.getDefault(), "%02d", s);
        return pattern.replace("mm", mm).replace("ss", ss);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //初始化控件和实例
        instance = MyApplication.instance;
        imageView = findViewById(R.id.iv_music);
        songName = findViewById(R.id.song_name);
        singerName = findViewById(R.id.singer_name);
        play_btn = findViewById(R.id.btn_play);
        next_btn = findViewById(R.id.btn_next);
        previous_btn = findViewById(R.id.btn_previous);
        seekBar=findViewById(R.id.progress);
        now_position=findViewById(R.id.now_position);
        total=findViewById(R.id.total);

        //获取播放器
        MediaPlayer mediaPlayer = instance.getMediaPlayer();
        //导入操作类
        MusicOperation musicOperation = new MusicOperation();

        if (instance.isPlay) {
            play_btn.setImageResource(R.drawable.pausing);
        } else {
            play_btn.setImageResource(R.drawable.start);
        }


        //设置控件
        if (instance.currentSong != null) {//如果当前有歌正在播放，则显示专辑封面和名称
            imageView.setImageBitmap(instance.getCurrentSong().albumBitmap);
            songName.setText(instance.getCurrentSong().songName);
            singerName.setText(instance.getCurrentSong().singerName);
            //设置seekBar的最大长度
            seekBar.setMax(instance.getMediaPlayer().getDuration());
            total.setText(formatTime("mm:ss",instance.getMediaPlayer().getDuration()));

            timerTask=new TimerTask() {
                @Override
                public void run() {
                    if(isSeekBarChanging==true){
                        return;
                    }
                    seekBar.setProgress(instance.getMediaPlayer().getCurrentPosition());
                    now_position.setText(formatTime("mm:ss",instance.getMediaPlayer().getCurrentPosition()));

                }
            };
            timer = new Timer();
            timer.schedule(timerTask,0,1);


            play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (instance.isPlay) {//如果这个歌曲正在播放中,点击播放按钮让他暂停
                        mediaPlayer.pause();
                        instance.setPlay(false);//将播放状态设置为停止
                        play_btn.setImageResource(R.drawable.start);
                    } else {//如果在播放，点击播放让他播放
                        mediaPlayer.start();
                        instance.setPlay(true);//将播放状态设置为停止
                        play_btn.setImageResource(R.drawable.pausing);
                    }
                }
            });
            next_btn.setOnClickListener(new View.OnClickListener() {//点击下一曲按钮进行下一首歌
                @Override
                public void onClick(View view) {
                    musicOperation.nextSong();
                    imageView.setImageBitmap(instance.getCurrentSong().albumBitmap);
                    songName.setText(instance.getCurrentSong().songName);
                    singerName.setText(instance.getCurrentSong().singerName);
                    seekBar.setProgress(0);
//                    now_position.setText(formatTime("mm:ss",instance.getMediaPlayer().getCurrentPosition()));
                    try {
                        //设置资源
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(instance.currentSong.getPath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        //设置seekBar的最大长度和重新至0

                        seekBar.setMax(instance.getMediaPlayer().getDuration());
                        total.setText(formatTime("mm:ss",instance.getMediaPlayer().getDuration()));
                        play_btn.setImageResource(R.drawable.pausing);

                    } catch (Exception e) {

                    }
                }
            });
            previous_btn.setOnClickListener((new View.OnClickListener() {//点击上一曲进行上一首歌
                @Override
                public void onClick(View view) {
                    musicOperation.previousSong();
                    imageView.setImageBitmap(instance.getCurrentSong().albumBitmap);
                    songName.setText(instance.getCurrentSong().songName);
                    singerName.setText(instance.getCurrentSong().singerName);
                    seekBar.setProgress(0);
                    try {
                        //设置资源
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(instance.currentSong.getPath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        //设置seekBar的最大长度和重新至0

                        seekBar.setMax(instance.getMediaPlayer().getDuration());
                        total.setText(formatTime("mm:ss",instance.getMediaPlayer().getDuration()));
                        play_btn.setImageResource(R.drawable.pausing);

                    } catch (Exception e) {

                    }
                }
            }));
        } else {//如果没歌播放则显示未在播放中
            imageView.setImageResource(R.drawable.singer);
            songName.setText("未在播放");
            singerName.setText("");
        }


        //对seekbar进行自动化运行，随着歌曲进行运作


        //对seekbar进行监控
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                instance.getMediaPlayer().seekTo(seekBar.getProgress());


            }
        });
    }

}
