package com.miles.mvptest.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("toutiao/index?key=a548d0832f353b0441dcaf609fe7519a")
    Call<ResponseBody> getNews(@Query("type") String type);

    @GET("joke/content/list.from?sort=desc&key=3c1e3afca8d86c5ccba2f55a172aa360&pagesize=20")
    Call<ResponseBody> getJokes(@Query("page") int page, @Query("time") String time);

    @GET("weixin/query?ps=20&dtype=json&key=208c829a5e729c43ccf9ba8a11b6982f")
    Call<ResponseBody> getWeChatArticles(@Query("pno") int page);
}

