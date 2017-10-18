package com.erin.week02_demo.bean;

import java.util.List;

/**
 * Created by mamiaomiao on 2017/10/16.
 */

public class MusicBean {

    private List<SongListBean> song_list;

    public List<SongListBean> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<SongListBean> song_list) {
        this.song_list = song_list;
    }

    public static class SongListBean {
        /**
         * artist_id : 310838090
         * language : 国语
         * pic_big : http://musicdata.baidu.com/data2/pic/3b9383fd29bbf5ff3dd2b2e66fbf19be/559880021/559880021.jpg@s_1,w_150,h_150
         * pic_small : http://musicdata.baidu.com/data2/pic/3b9383fd29bbf5ff3dd2b2e66fbf19be/559880021/559880021.jpg@s_1,w_90,h_90
         * country : 内地
         * area : 0
         * publishtime : 2017-10-11
         * album_no : 3
         * lrclink : http://musicdata.baidu.com/data2/lrc/74da30df7989ef0957094446e178d602/557893656/557893656.lrc
         * copy_type : 1
         * hot : 130322
         * all_artist_ting_uid : 239907481
         * resource_type : 0
         * is_new : 1
         * rank_change : 0
         * rank : 1
         * all_artist_id : 310838090
         * style :
         * del_status : 0
         * relate_status : 0
         * toneid : 0
         * all_rate : 64,128,256,320,flac
         * file_duration : 266
         * has_mv_mobile : 0
         * versions :
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * biaoshi : first,lossless
         * info :
         * has_filmtv : 0
         * si_proxycompany : 华宇世博音乐文化（北 京）有限公司-普通代理
         * song_id : 557631688
         * title : 三角题
         * ting_uid : 239907481
         * author : 二珂
         * album_id : 555678187
         * album_title : 带着音乐去旅行
         * is_first_publish : 0
         * havehigh : 2
         * charge : 0
         * has_mv : 1
         * learn : 0
         * song_source : web
         * piao_id : 0
         * korean_bb_song : 0
         * resource_type_ext : 0
         * mv_provider : 0000000000
         * artist_name : 二珂
         */

        private String pic_small;
        private String title;
        private String album_title;

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }
    }
}
