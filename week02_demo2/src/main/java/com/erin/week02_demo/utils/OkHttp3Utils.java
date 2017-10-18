package com.erin.week02_demo.utils;

import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 1. 类的用途 封装OkHttp3的工具类 用单例设计模式
 * 2. @author forever
 * 3. @date 2017/9/6 09:19
 */

public class OkHttp3Utils {
    /**
     * 懒汉 安全 加同步
     * 私有的静态成员变量 只声明不创建
     * 私有的构造方法
     * 提供返回实例的静态方法
     */
    private static OkHttp3Utils okHttp3Utils = null;

    private OkHttp3Utils() {
    }

    public static OkHttp3Utils getInstance() {
        if (okHttp3Utils == null) {
            //加同步安全
            synchronized (OkHttp3Utils.class) {
                if (okHttp3Utils == null) {
                    okHttp3Utils = new OkHttp3Utils();
                }
            }

        }

        return okHttp3Utils;
    }

    private static OkHttpClient okHttpClient = null;

    public synchronized static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
            int cacheSize = 10 * 1024 * 1024;
            //拦截器
            LoggingInterceptor interceptor=new LoggingInterceptor();

            okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                    //添加OkHttp3的拦截器
                    .addInterceptor(interceptor)
                    .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                    .build();
        }
        return okHttpClient;
    }

    private static Handler mHandler = null;

    public synchronized static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }

        return mHandler;
    }

    /**
     * get请求
     * 参数1 url
     * 参数2 回调Callback
     */

    public static void doGet(String url, Callback callback) {

        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getOkHttpClient();
        //创建Request
        Request request = new Request.Builder().url(url).build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);


    }


}
