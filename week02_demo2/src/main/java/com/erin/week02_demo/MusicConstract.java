package com.erin.week02_demo;

import com.erin.week02_demo.bean.MusicBean;

import java.util.List;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public interface MusicConstract {
    interface IMusicView {
        void setData(List<MusicBean.SongListBean> list);
    }

    interface IMusicModel {
        void getMusicList(int offset,IMusicView view);
    }

    abstract class IMusicPresenter {
        public abstract void getData(int offset);

    }

}
