package com.example.administrator.huawei.mvp.view.view;

import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.bean.RecommendBean;

public interface RecommendFragmentView extends BaseView {

    void onRecommendDataSuccess(RecommendBean recommendBean);
    void onMoreRecommendDataSuccess(RecommendBean bean);
    void onRecommendDataError(String msg);
}
