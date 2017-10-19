package com.example.wp.retrofitrxjava;


import com.example.wp.retrofitrxjava.bean.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 需要定义一个接口，取名RetrofitService
 * Created by Wp on 2017/10/19.
 */
public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBook(@Query("q") String name,
                                   @Query("tag") String tag, @Query("start") int start,
                                   @Query("count") int count);

}
