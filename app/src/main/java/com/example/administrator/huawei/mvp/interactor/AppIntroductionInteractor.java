package com.example.administrator.huawei.mvp.interactor;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.AppIntroductionApi;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.bean.AppIntroductionBean;
import com.example.administrator.huawei.di.component.ActivityComponent;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import java.util.jar.Manifest;

import javax.inject.Inject;

public class AppIntroductionInteractor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate;

    @Inject
    public AppIntroductionInteractor() {

    }

    public void loadAppIntroducitonData(BaseActivity baseActivity, String packageName,
                                        IGetDataDelegate<AppIntroductionBean> delegate) {
        this.mDelegate = delegate;
        AppIntroductionApi api = new AppIntroductionApi(listener, baseActivity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }


    private HttpOnNextListener<AppIntroductionBean> listener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean = JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
