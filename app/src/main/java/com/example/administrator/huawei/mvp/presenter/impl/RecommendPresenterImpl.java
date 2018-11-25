package com.example.administrator.huawei.mvp.presenter.impl;

import android.os.SystemClock;

import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.mvp.presenter.RecommendFragmentPresenter;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;

import javax.inject.Inject;

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter {

    @Inject
    public RecommendPresenterImpl() {

    }

    @Override
    public void getRecommendData() {
        // 模拟网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                try {
                    mPresenterView.onRecommendDataError();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
