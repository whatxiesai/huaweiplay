package com.example.administrator.huawei.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.RecommendAdapter;
import com.example.administrator.huawei.adapter.top.RecommendTopWrapper;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.RecommendBean;
import com.example.administrator.huawei.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.RecommendFragmentView;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.LoadingPager;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

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

    private List<RecommendBean.RecommendAppBean> appBeanList = new ArrayList<>();
    private RecommendAdapter adapter;
    private RecommendTopWrapper topWrapper;

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
        adapter = new RecommendAdapter(getContext(), recommendBean.getRecommendAppBeanList());
        topWrapper = new RecommendTopWrapper(getContext(), adapter);
        topWrapper.addData(recommendBean.getBannerList());
        mRv.setAdapter(topWrapper);

        // 禁用下拉加载
        ptr.setPullDownEnable(false);
        ptr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新
            }

            @Override
            public void onLoadMore() {
                // 上拉加载更多
                recommendPresenter.getMoreRecommendData(mActivity);
            }
        });
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
        appBeanList = recommendBean.getRecommendAppBeanList();
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onMoreRecommendDataSuccess(RecommendBean bean) {
        adapter.clearData();
        adapter.addDataAll(bean.getRecommendAppBeanList());
        topWrapper.notifyDataSetChanged();
        ptr.onFinishLoading();
    }

    @Override
    public void onRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }
}
