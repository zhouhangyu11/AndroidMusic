package com.example.musicplayer;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.musicplayer.databinding.ActivityMainBinding;
import com.example.musicplayer.utils.PermissionUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public MediaPlayer mediaPlayer;
    public Context context = this;
    private String[] List = {"本地", "最近播放", "我的收藏"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化全局的MediaPlayer对象
        mediaPlayer = new MediaPlayer();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 请求权限
        PermissionUtils.initCheckSelfPermission(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //设置创建列表
        ListView functionList = findViewById(R.id.functionList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.array_adapter, List);
        functionList.setAdapter(adapter);


        //为listview绑定监听，点击不同的功能进入不同的界面
        functionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        LocalMusicActivity.beginActivity(context);
                        break;
                    case 1:
                    case 2:
                        Toast.makeText(MainActivity.this, "当前点击" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showDialog();
                }
                break;
        }
    }

    private void showDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setMessage("请授权必要权限")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        PermissionUtils.initCheckSelfPermission(MainActivity.this);
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);//点击屏幕不消失
        dialog.setCancelable(false);//点击返回键不消失
        dialog.show();
    }

}