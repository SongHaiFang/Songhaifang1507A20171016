package demo.song.com.songhaifang1507a20171016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import demo.song.com.songhaifang1507a20171016.adapter.HomeAdapter;

import demo.song.com.songhaifang1507a20171016.bean.MyBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private MyBean bean;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        imageView = (ImageView) findViewById(R.id.img);
        loderImage("http://p1.music.126.net/G59q3aQBRCWts1pqoGf21Q==/19075427230674218.jpg",imageView);

        okGet();
        adapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("网络提示信息");
                builder.setMessage("网络不可用，如果继续，请先设置网络！");
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        Intent intent = null;
                        //Android系统的网络设置
                        intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create();//创建并且展示，很重要，不要忘记填了
                builder.show();
            }
        });
    }
    private void okGet() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        OkHttpUtils.initClient(client);

        OkHttpUtils.get()
                .url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
                .build()
                .execute(new StringCallback() {
                    @Override
//            }
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String string = response.toString();
                        bean = new Gson().fromJson(string, MyBean.class);
                        adapter = new HomeAdapter(bean, MainActivity.this);

                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);

                        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
                            @Override
                            public void onLoadMore(int currentPage) {
                                loadMoreData();
                            }
                        });

                    }
                });

    }
public  void loderImage(String url,ImageView imageView){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
    //            String path = Environment.getExternalStorageDirectory().getPath()+"/"+"Pictrues";
//            File file = new File(path);
//
//            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getActivity())
//                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
//                .memoryCacheSize(2 * 1024 * 1024)//配置内存缓存的大小  例如 : 2* 1024 * 1024 = 2MB
//                .build();//配置构建完成   修改缓存位置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader instance = ImageLoader.getInstance();
        instance.init(configuration);
        instance.displayImage(url, imageView ,options);

        //compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
    }


    public void sss(View v){
        finish();
    }
    //每次上拉加载的时候，给RecyclerView的后面添加了10条数据数据
    private void loadMoreData() {
        for (int i =0; i < 10; i++){
            bean.song_list.add(bean.song_list.get(i));
            adapter.notifyDataSetChanged();
        }
    }
}
