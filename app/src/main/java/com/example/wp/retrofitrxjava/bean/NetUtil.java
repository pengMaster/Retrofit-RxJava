package com.example.wp.retrofitrxjava.bean;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wp on 2017/10/19.
 */

public class NetUtil {
    public static NetUtil netUtil;
    public static NetUtil getInstance() {
        if (netUtil == null) {
            synchronized (NetUtil.class) {
                if (netUtil == null) {
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.douban.com/v2/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
            .build();

}
