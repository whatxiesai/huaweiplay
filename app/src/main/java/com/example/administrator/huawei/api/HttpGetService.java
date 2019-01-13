package com.example.administrator.huawei.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface HttpGetService {
    @GET("recommend")
    Observable<ResponseBody> getRecommendData();

    @GET("category")
    Observable<ResponseBody> getCategoryData();

    @GET("top")
    Observable<ResponseBody> getTopData();

    @GET("categorydata/tool")
    Observable<ResponseBody> getCategoryToolData();

    @GET("categorydata/subject")
    Observable<ResponseBody> getCategorySubjectData();

    @GET("categorydata/subscribe")
    Observable<ResponseBody> getCategorySubscribeData();

    @GET("categorydata/new")
    Observable<ResponseBody> getCategoryNewData();

    @GET("categorydata/necessary")
    Observable<ResponseBody> getCategoryNecessaryData();

    @GET("app/recommend")
    Observable<ResponseBody> getAppRecommendData(@Query("packageName") String var1);

    @GET("app/{type}")
    Observable<ResponseBody> getAppMoreRecommendData(@Path("type") String var1, @Query("packageName") String var2);

    @GET("app/introduce")
    Observable<ResponseBody> getAppDetailData(@Query("packageName") String var1);

    @GET("app/comment")
    Observable<ResponseBody> getAppCommentData(@Query("packageName") String var1);
}
