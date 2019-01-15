package com.example.administrator.huawei.mvp.presenter;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {

    void getRecommendData(BaseActivity activity);

    void getMoreRecommendData(BaseActivity baseActivity);
}
