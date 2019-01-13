package com.example.administrator.huawei.mvp.view.fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.bean.TopBean;
import com.example.administrator.huawei.mvp.presenter.impl.TopPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.TopFragmentView;
import com.example.administrator.huawei.view.LoadingPaper;

import javax.inject.Inject;

public class TopFragment extends BaseMvpFragment<TopPresenterImpl> implements TopFragmentView {

    private static final String TAG = "TopFragment";

    @Inject
    public TopPresenterImpl topPresenter;

    @Override
    protected void load() {
        // 网络请求操作
     topPresenter.getTopData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("加载成功");
        return tv;
    }

    @Override
    protected TopPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return topPresenter;
    }

    @Override
    public void onTopDataSuccess(TopBean topBean) {
        Log.d(TAG, "onTopDataSuccess: " + topBean.getTopTopBeanList().size());
        setState(LoadingPaper.LoadResult.success);
    }

    @Override
    public void onTopDataError(String msg) {
        setState(LoadingPaper.LoadResult.error);
    }
}
