package com.example.musicplayer;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.databinding.ActivityMainBinding;
import com.example.musicplayer.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {
    public Context context = this;
    private final String[] List = {"本地", "最近播放", "我的收藏"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.musicplayer.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 请求权限
        PermissionUtils.initCheckSelfPermission(this);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_dashboard).build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

        //设置创建列表
        ListView functionList = findViewById(R.id.functionList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.array_adapter, List);
        functionList.setAdapter(adapter);


        //为listview绑定监听，点击不同的功能进入不同的界面
        functionList.setOnItemClickListener((adapterView, view, position, id) -> {
            switch ((int) id) {
                case 0:
                    LocalMusicActivity.beginActivity(context);
                    break;
                case 1:
                    RecentPlay.beginActivity(context);
                    break;
                case 2:
                    LikeActivity.beginActivity(context);
                    break;
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showDialog();
            }
        }
    }

    private void showDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setMessage("请授权必要权限")
                .setPositiveButton("好的", (dialog1, which) -> PermissionUtils.initCheckSelfPermission(MainActivity.this))
                .setNegativeButton("拒绝", (dialog12, which) -> finish()).create();
        dialog.setCanceledOnTouchOutside(false);//点击屏幕不消失
        dialog.setCancelable(false);//点击返回键不消失
        dialog.show();
    }

}