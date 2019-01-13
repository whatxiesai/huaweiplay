package com.example.administrator.huawei.mvp.presenter.impl;

import android.os.SystemClock;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.mvp.interactor.RecommendInteractor;
import com.example.administrator.huawei.mvp.presenter.RecommendFragmentPresenter;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;

import java.net.MulticastSocket;

import javax.inject.Inject;

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter {

    @Inject
    RecommendInteractor recommendInteractor;

    @Inject
    public RecommendPresenterImpl() {

    }

    @Override
    public void getRecommendData(BaseActivity activity) {
        // 模拟网络请求
        recommendInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onRecommendDataError(msg);
            }
        });
    }
}
