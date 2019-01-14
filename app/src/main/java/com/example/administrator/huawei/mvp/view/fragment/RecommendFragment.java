package com.example.administrator.huawei.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.RecommendAdapter;
import com.example.administrator.huawei.adapter.top.RecommendTopWrapper;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.LoadingPaper;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendFragment extends BaseMvpFragment<BasePresenterImpl> implements RecommendFragmentView {

    private static final String TAG = "RecommendFragment";
    @BindView(R.id.rv_recommend)
    RecyclerView mRv ;
    @BindView(R.id.ptr)
    PullToRefreshView ptr ;

    @Inject
    public RecommendPresenterImpl recommendPresenter;

    private RecommendBean recommendBean;

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
       View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        RecommendAdapter adapter = new RecommendAdapter(getContext(), recommendBean.getRecommendAppBeanList());
        RecommendTopWrapper topWrapper = new RecommendTopWrapper(getContext(), adapter);
        topWrapper.addData(recommendBean.getBannerList());
        mRv.setAdapter(topWrapper);


        return view;

    }

    @Override
    protected BasePresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return recommendPresenter;
    }

    @Override
    public void onRecommendDataSuccess(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        setState(LoadingPaper.LoadResult.success);
    }

    @Override
    public void onRecommendDataError(String msg) {
        setState(LoadingPaper.LoadResult.error);
    }
}
