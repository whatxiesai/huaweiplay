package com.example.administrator.huawei.mvp.interactor;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.api.TopApi;
import com.example.administrator.huawei.bean.TopBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import java.util.ListIterator;

import javax.inject.Inject;

public class TopIntercator {

    private IGetDataDelegate<TopBean> mDelegate;
    @Inject
    public TopIntercator() {

    }

    public void loadTopData(BaseActivity activity, IGetDataDelegate<TopBean> delegate) {
        this.mDelegate = delegate;
        TopApi api = new TopApi(listener,activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<TopBean> listener = new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            TopBean topBean = JsonParseUtils.parseTopBean(string);
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
