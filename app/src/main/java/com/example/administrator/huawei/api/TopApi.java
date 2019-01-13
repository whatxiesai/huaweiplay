package com.example.administrator.huawei.api;

import com.example.administrator.huawei.bean.TopBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class TopApi extends BaseApi<TopBean> {
    public TopApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getTopData();
    }

    @Override
    public TopBean call(ResponseBody responseBody) {
        String resutlt = "";
        try {
            resutlt = responseBody.string();
        } catch (Exception e) {

        }
        return JsonParseUtils.parseTopBean(resutlt);
    }
}
