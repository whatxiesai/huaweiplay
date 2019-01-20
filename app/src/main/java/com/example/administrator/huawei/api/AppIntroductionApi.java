package com.example.administrator.huawei.api;

import com.example.administrator.huawei.bean.AppIntroductionBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class AppIntroductionApi extends BaseApi<AppIntroductionBean> {

    private String packageName;

    public AppIntroductionApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        this.packageName = packageName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppDetailData(packageName);
    }

    @Override
    public AppIntroductionBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseAppIntroductionBean(result);
    }
}
