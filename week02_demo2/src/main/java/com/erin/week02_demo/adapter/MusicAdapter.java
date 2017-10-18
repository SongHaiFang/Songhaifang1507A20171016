package com.erin.week02_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.erin.week02_demo.R;
import com.erin.week02_demo.bean.MusicBean;
import com.erin.week02_demo.viewholder.MusicViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {
    private List<MusicBean.SongListBean> list=new ArrayList<>();
    private Context context;
    public MusicAdapter(Context context){
        this.context=context;
    }
    public void addAll(List<MusicBean.SongListBean> list){
        this.list.addAll( list);
        notifyDataSetChanged();
    }
    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_music,null);

        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        MusicBean.SongListBean bean=list.get(position);
        holder.title.setText(bean.getTitle());
        holder.des.setText(bean.getAlbum_title());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
