package com.example.administrator.huawei.api;

import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class RecommendApi extends BaseApi<RecommendBean> {

    public RecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/recommend");
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getRecommendData();
    }

    @Override
    public RecommendBean call(ResponseBody responseBody) {
        // 转换规则
        String result = "";
        try {
            result = responseBody.string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseRecommendBean(result);
    }
}
