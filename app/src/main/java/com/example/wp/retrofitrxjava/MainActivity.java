package com.example.wp.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wp.retrofitrxjava.bean.Book;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * RetrofitRxJava
 * Created by Wp on 2017/10/19.
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvInfo;
    private Button btnDownLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        tvInfo = (TextView) findViewById(R.id.tv_info);
        btnDownLoad = (Button) findViewById(R.id.btn_downLoad);
    }

    private void initData() {
        btnDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRxJavaDownLoadData();

            }
        });

    }

    private void RetrofitRxJavaDownLoadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getSearchBook("金瓶梅", null, 0, 1).subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Book book) {
                        tvInfo.setText(book.toString());
                    }
                });
    }
}