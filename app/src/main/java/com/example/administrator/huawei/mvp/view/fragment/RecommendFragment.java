package com.example.administrator.huawei.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;
import com.example.administrator.huawei.view.LoadingPaper;

import javax.inject.Inject;

public class RecommendFragment extends BaseMvpFragment<BasePresenterImpl> implements RecommendFragmentView {

    private static final String TAG = "RecommendFragment";

    @Inject
    public RecommendPresenterImpl recommendPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected void load() {
        // 网络请求操作
        recommendPresenter.getRecommendData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("加载成功");
        return tv;
    }

    @Override
    protected BasePresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return recommendPresenter;
    }

    @Override
    public void onRecommendDataSuccess(RecommendBean recommendBean) {
        Log.i(TAG, recommendBean.getBannerList().size() + " ");
        Log.i(TAG, recommendBean.getRecommendAppBeanList().size() + " ");
        setState(LoadingPaper.LoadResult.success);
    }

    @Override
    public void onRecommendDataError(String msg) {
        setState(LoadingPaper.LoadResult.error);
    }
}
