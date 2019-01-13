package com.example.administrator.huawei.mvp.presenter;

import android.view.View;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.mvp.view.view.TopFragmentView;

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView> {

    /**
     * 获取排行数据
     * @param baseActivity
     */
    void getTopData(BaseActivity baseActivity);
}
