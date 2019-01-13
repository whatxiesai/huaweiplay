package com.example.administrator.huawei.mvp.view.view;

import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.bean.TopBean;

public interface TopFragmentView extends BaseView {
    void onTopDataSuccess(TopBean topBean);
    void onTopDataError(String msg);
}
