package com.example.administrator.huawei;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.huawei.view.LoadingPager;

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;
    protected BaseActivity mActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (loadingPager == null) {
            loadingPager = new LoadingPager(getContext()) {
                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected void load() {
                    BaseFragment.this.load();
                }
            };
        }

        return loadingPager;
    }

    public void show() {
        if (loadingPager != null) {
            loadingPager.show();
        }
    }

    public void setState(LoadingPager.LoadResult result) {
        if (loadingPager != null) {
            loadingPager.setState(result);
        }
    }

    protected abstract View createSuccessView();

    /**
     * 请求网络修改状态
     */
    protected abstract void load();
}
