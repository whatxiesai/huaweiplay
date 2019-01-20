package com.example.administrator.huawei.mvp.presenter.impl;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.AppIntroductionBean;
import com.example.administrator.huawei.mvp.interactor.AppIntroductionInteractor;
import com.example.administrator.huawei.mvp.presenter.AppIntroductionFragmentPresenter;
import com.example.administrator.huawei.mvp.view.view.AppIntroductionFragmentView;

import javax.inject.Inject;

public class AppIntroductionPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView> implements AppIntroductionFragmentPresenter {

    @Inject
    public AppIntroductionInteractor appIntroductionInteractor;

    @Inject
    public AppIntroductionPresenterImpl() {

    }

    @Override
    public void getAppIntroductionData(BaseActivity baseActivity, String packageName) {
        appIntroductionInteractor.loadAppIntroducitonData(baseActivity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppIntroductionDataError(msg);
            }
        });
    }
}
