package com.example.administrator.huawei.mvp.presenter.impl;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.AppDetailBean;
import com.example.administrator.huawei.mvp.interactor.AppDetailInteractor;
import com.example.administrator.huawei.mvp.presenter.AppDetailActivityPresenter;
import com.example.administrator.huawei.mvp.view.view.AppDetailActivityView;

import javax.inject.Inject;

public class AppDetailPresenterImpl extends BasePresenterImpl<AppDetailActivityView> implements
        AppDetailActivityPresenter {

    @Inject
    public AppDetailInteractor appDetailInteractor;

    @Inject
    public AppDetailPresenterImpl() {

    }


    @Override
    public void getAppDetaiData(BaseActivity baseActivity, String packageName) {
        appDetailInteractor.loadAppDetailData(baseActivity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppDetailDataError(msg);
            }
        });
    }
}
