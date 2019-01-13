package com.example.administrator.huawei.mvp.interactor;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.api.RecommendApi;
import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

import retrofit2.http.PUT;

public class RecommendInteractor {

    IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendInteractor() {

    }

    public void loadRecommendData(BaseActivity baseActivity, IGetDataDelegate<RecommendBean> delegate) {
       this.mDelegate = delegate;
        RecommendApi api = new RecommendApi(listener, baseActivity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<RecommendBean> listener = new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
