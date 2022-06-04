package com.example.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicplayer.utils.MusicOperation;

public class PlayerActivity extends Activity {
    //初始化myApplication实例和各个组件
    MyApplication instance;
    ImageView imageView;
    TextView songName;
    TextView singerName;
    ImageButton play_btn;
    ImageButton next_btn;
    ImageButton previous_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //初始化控件和实例
        instance=MyApplication.instance;
        imageView=findViewById(R.id.iv_music);
        songName=findViewById(R.id.song_name);
        singerName=findViewById(R.id.singer_name);
        play_btn=findViewById(R.id.btn_play);
        next_btn=findViewById(R.id.btn_next);
        previous_btn=findViewById(R.id.btn_previous);
        //获取播放器
        MediaPlayer mediaPlayer=instance.getMediaPlayer();
        //导入操作类
        MusicOperation musicOperation=new MusicOperation();

        if(instance.isPlay){
            play_btn.setImageResource(R.drawable.pausing);
        }
        else {
            play_btn.setImageResource(R.drawable.start);
        }


        //设置控件
        if(instance.currentSong!=null) {//如果当前有歌正在播放，则显示专辑封面和名称
            imageView.setImageBitmap(instance.getCurrentSong().albumBitmap);
            songName.setText(instance.getCurrentSong().songName);
            singerName.setText(instance.getCurrentSong().singerName);

                play_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(instance.isPlay) {//如果这个歌曲正在播放中,点击播放按钮让他暂停
                            mediaPlayer.pause();
                            instance.setPlay(false);//将播放状态设置为停止
                            play_btn.setImageResource(R.drawable.start);
                        }else {//如果在播放，点击播放让他播放
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
                        try{
                            //设置资源
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(instance.currentSong.getPath());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            play_btn.setImageResource(R.drawable.pausing);

                        }catch (Exception e){

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
                        try{
                            //设置资源
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(instance.currentSong.getPath());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            play_btn.setImageResource(R.drawable.pausing);

                        }catch (Exception e){

                        }
                    }
                }));

        }else{//如果没歌播放则显示未在播放中
            imageView.setImageResource(R.drawable.singer);
            songName.setText("未在播放");
            singerName.setText("");
        }



    }

}
