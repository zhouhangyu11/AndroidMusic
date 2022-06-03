package com.example.musicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;

import java.util.List;
import java.util.Map;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    /*每个文件夹作为一个map的列表*/
    private List<Map<String, Object>> folderList;
    /*调试用*/
    private final String TAG = "FolderAdapter-ing";

    public FolderAdapter(List<Map<String, Object>> folders) {
        this.folderList = folders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> folder = folderList.get(position);
        holder.folderPath.setText((String) folder.get("path"));
        holder.folderName.setText((String) folder.get("name"));
        holder.songNum.setText(folder.get("songNum") + "首");
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View folderView;
        TextView folderName;
        TextView folderPath;
        TextView songNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderView = itemView;
            folderName = itemView.findViewById(R.id.folder_name);
            folderPath = itemView.findViewById(R.id.folder_path);
            songNum = itemView.findViewById(R.id.song_number);
        }
    }
}
