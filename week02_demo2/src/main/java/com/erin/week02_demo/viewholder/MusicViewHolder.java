package com.erin.week02_demo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erin.week02_demo.R;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public class MusicViewHolder extends RecyclerView.ViewHolder {
public ImageView img;
    public TextView title,des;
    public MusicViewHolder(View itemView) {
        super(itemView);
        img=(ImageView)itemView.findViewById(R.id.music_item_img);
        title=(TextView) itemView.findViewById(R.id.music_item_title);
        des=(TextView) itemView.findViewById(R.id.music_item_des);
    }
}
