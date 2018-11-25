package com.example.administrator.huawei.mvp.view.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;
import com.example.administrator.huawei.view.LoadingPaper;

import javax.inject.Inject;

public class RecommendFragment extends BaseMvpFragment<BasePresenterImpl> implements RecommendFragmentView {

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
        recommendPresenter.getRecommendData();
//        new Thread(new Runnable() {
//            @Override
////            public void run() {
////                SystemClock.sleep(2000);
////                setState(LoadingPaper.LoadResult.success);
////            }
//        }).start();
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("加载成功");
        return tv;
    }

    @Override
    protected BasePresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return recommendPresenter;
    }

    @Override
    public void onRecommendDataSuccess() {
        setState(LoadingPaper.LoadResult.success);
    }

    @Override
    public void onRecommendDataError() {
        setState(LoadingPaper.LoadResult.error);
    }
}
