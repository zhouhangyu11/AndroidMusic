package com.example.musicplayer.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.SingerActivity;
import com.example.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> implements Comparator {
    /*Singer数组，每个对象包括歌手名，以及对应的歌曲数目*/
    List<Map<String, Object>> singerList = new ArrayList<>();
    final String TAG = "SingerAdapter-ing";
    FragmentActivity activity;


    public SingerAdapter(List<Map<String, Object>> singers, FragmentActivity activity) {
        this.singerList = singers;
        Collections.sort(this.singerList, this);
        this.activity = activity;
    }

    @NonNull
    @Override
    public SingerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singer_item, parent, false);

        // 得到ViewHolder用于重用
        ViewHolder holder = new ViewHolder(view);

        // 点击歌手进入其主页
        view.setOnClickListener(view1 -> {
            int position = holder.getAdapterPosition();
            Map<String, Object> singer = singerList.get(position);
            List<Song> songList = (List<Song>) singer.get("songs");
            String singerName = ((String) singer.get("name")).toLowerCase();
            singerName = singerName.replace(" ", "");
            int singerImage = getSingerImage(singerName);
            SingerActivity.beginActivity(activity, songList, singerImage);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.ViewHolder holder, int position) {
        Map<String, Object> singer = singerList.get(position);
        holder.singerName.setText((String) singer.get("name"));
        holder.songNum.setText(singer.get("songNum").toString() + "首");
        Log.d(TAG, "onBindViewHolder: " + ((String) singer.get("name")));
        String name = ((String) singer.get("name")).toLowerCase();

        name = name.replace(" ", "");
        holder.singerImage.setImageResource(getSingerImage(name));
    }

    @Override
    public int getItemCount() {
        return this.singerList.size();
    }

    @Override
    public int compare(Object o, Object t1) {
        String name1 = (String) ((Map<String, Object>) o).get("name");
        String name2 = (String) ((Map<String, Object>) t1).get("name");

        return name1.compareTo(name2);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View singerView;
        ImageView singerImage;
        TextView singerName;
        TextView songNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singerView = itemView;
            singerImage = singerView.findViewById(R.id.singer_image);
            singerName = singerView.findViewById(R.id.singer_name);
            songNum = singerView.findViewById(R.id.song_number);
        }
    }

    public int getSingerImage(String singerName) {
        switch (singerName) {
            case ("lorde"): {
                return R.drawable.lorde;
            }
            case ("taylorswift"): {
                return R.drawable.taylorswift;
            }
            case ("misia"): {
                return R.drawable.misia;
            }
            case ("袁娅维tiaray"): {
                return R.drawable.tiaray;
            }
            default: {
                return R.drawable.singer;
            }
        }
    }
}
