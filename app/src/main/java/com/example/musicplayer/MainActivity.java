package com.example.musicplayer;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.musicplayer.databinding.ActivityMainBinding;

import java.util.ListResourceBundle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isPlay=false;
    private String[]List = {"本地","最近播放","我的收藏"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //获取btn的id并创建监听器
        ImageButton playBtn = findViewById(R.id.playBtn);
        ImageButton nextBtn = findViewById(R.id.nextBtn);

        //找到音乐图片，实现旋转
        ImageView musicImg = findViewById(R.id.musicImg);
        //设置动画效果，设置匀速转动
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        //播放按钮事件，实现音乐播放暂停和图片转换，和音乐图片的旋转
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlay){
                    playBtn.setImageResource(R.drawable.ic_pause);
                    isPlay=true;
                    animation.setInterpolator(lin);
                    musicImg.startAnimation(animation);
                }
                else{
                    playBtn.setImageResource(R.drawable.ic_play);
                    isPlay=false;
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

        //设置创建列表
        ListView functionList=findViewById(R.id.functionList);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, R.layout.array_adapter,List);
        functionList.setAdapter(adapter);
    }



}