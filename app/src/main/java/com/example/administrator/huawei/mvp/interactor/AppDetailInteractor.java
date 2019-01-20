package com.example.administrator.huawei.mvp.interactor;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.AppDetailApi;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.bean.AppDetailBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

public class AppDetailInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate;

    @Inject
    public AppDetailInteractor() {

    }

    public void loadAppDetailData(BaseActivity baseActivity, String packageName, IGetDataDelegate<AppDetailBean>
            delegate) {
        this.mDelegate = delegate;
        AppDetailApi api = new AppDetailApi(listner, baseActivity, packageName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppDetailBean> listner = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean = JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
