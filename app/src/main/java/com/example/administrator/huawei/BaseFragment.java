package com.example.administrator.huawei;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.huawei.view.LoadingPaper;

public abstract class BaseFragment extends Fragment {

    private LoadingPaper loadingPaper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (loadingPaper == null) {
            loadingPaper = new LoadingPaper(getContext()) {
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

        return loadingPaper;
    }

    public void show() {
        if (loadingPaper != null) {
            loadingPaper.show();
        }
    }

    public void setState(LoadingPaper.LoadResult result) {
        if (loadingPaper != null) {
            loadingPaper.setState(result);
        }
    }

    protected abstract View createSuccessView();

    /**
     * 请求网络修改状态
     */
    protected abstract void load();
}
