package com.erin.week02_demo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.erin.week02_demo.MusicConstract;
import com.erin.week02_demo.R;
import com.erin.week02_demo.adapter.MusicAdapter;
import com.erin.week02_demo.bean.MusicBean;
import com.erin.week02_demo.presenter.MusicPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MusicConstract.IMusicView {
    private MusicPresenter presenter;
    private int offset = 0, size = 10;
    private RecyclerView recyclerView;
    private MusicAdapter adapter = new MusicAdapter(MainActivity.this);
    private boolean isScrollDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
        presenter = new MusicPresenter(this);
        presenter.getData(offset);
        setLoadMore();

    }

    @Override
    public void setData(List<MusicBean.SongListBean> list) {
        adapter.addAll(list);
    }

    private void setLoadMore() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//滚动停止
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int last = manager.findLastCompletelyVisibleItemPosition();
                    int count = manager.getItemCount();
                    if (isScrollDown && last == count - 1) {
                        presenter.getData(offset+=size);
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    isScrollDown=true;
                }else
                    isScrollDown=false;

            }
        });
    }
}
