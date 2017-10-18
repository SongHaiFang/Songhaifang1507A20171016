package com.erin.week02_demo.presenter;

import com.erin.week02_demo.MusicConstract;
import com.erin.week02_demo.model.MusicModel;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public class MusicPresenter extends MusicConstract.IMusicPresenter {
    private MusicModel model;
    private MusicConstract.IMusicView view;
    public MusicPresenter(MusicConstract.IMusicView view){
        model=new MusicModel();
        this.view=view;
    }
    @Override
    public void getData(int offset) {
        model.getMusicList(offset,view);
    }
}
