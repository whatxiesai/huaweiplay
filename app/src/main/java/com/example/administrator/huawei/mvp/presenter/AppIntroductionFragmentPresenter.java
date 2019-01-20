package com.example.administrator.huawei.mvp.presenter;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.mvp.view.view.AppIntroductionFragmentView;

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView> {
    void getAppIntroductionData(BaseActivity baseActivity, String packageName);
}
