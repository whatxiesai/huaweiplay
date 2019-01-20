package com.example.administrator.huawei.mvp.view.view;

import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.bean.AppIntroductionBean;

public interface AppIntroductionFragmentView extends BaseView {
    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);
    void onAppIntroductionDataError(String msg);
}
