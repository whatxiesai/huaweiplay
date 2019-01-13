package com.example.administrator.huawei.mvp.presenter.impl;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.TopBean;
import com.example.administrator.huawei.mvp.interactor.TopIntercator;
import com.example.administrator.huawei.mvp.presenter.TopFragmentPresenter;
import com.example.administrator.huawei.mvp.view.view.TopFragmentView;

import javax.inject.Inject;

public class TopPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    public TopIntercator topIntercator;

    @Inject
    public TopPresenterImpl() {

    }

    @Override
    public void getTopData(BaseActivity baseActivity) {
        topIntercator.loadTopData(baseActivity, new IGetDataDelegate<TopBean>() {
            @Override
            public void getDataSuccess(TopBean topBean) {
                mPresenterView.onTopDataSuccess(topBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onTopDataError(msg);
            }
        });
    }
}
