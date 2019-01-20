package com.example.administrator.huawei.api;

import com.example.administrator.huawei.bean.AppDetailBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class AppDetailApi extends BaseApi<AppDetailBean> {

    public String pacakgeName;

    public AppDetailApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity
    , String pacakgeName) {
        super(listener, rxAppCompatActivity);
        this.pacakgeName = pacakgeName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppDetailData(pacakgeName);
    }

    @Override
    public AppDetailBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppDetailBean(result);
    }
}
