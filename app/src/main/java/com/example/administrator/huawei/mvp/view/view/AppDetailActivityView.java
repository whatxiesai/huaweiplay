package com.example.administrator.huawei.mvp.view.view;

import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.bean.AppDetailBean;

public interface AppDetailActivityView extends BaseView {
    void onAppDetailDataSuccess(AppDetailBean appDetailBean);
    void onAppDetailDataError(String msg);
}
