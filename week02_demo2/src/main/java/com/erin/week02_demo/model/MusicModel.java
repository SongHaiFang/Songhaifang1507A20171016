package com.erin.week02_demo.model;

import com.erin.week02_demo.MusicConstract;
import com.erin.week02_demo.bean.MusicBean;
import com.erin.week02_demo.utils.GsonObjectCallback;
import com.erin.week02_demo.utils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public class MusicModel implements MusicConstract.IMusicModel {

    @Override
    public void getMusicList(int offset, final MusicConstract.IMusicView view) {
        OkHttp3Utils.doGet("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=" + offset, new GsonObjectCallback<MusicBean>() {
            @Override
            public void onUi(MusicBean musicBean) {
                view.setData(musicBean.getSong_list());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
