package com.example.administrator.huawei.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.di.component.DaggerFragmentComponent;
import com.example.administrator.huawei.di.component.FragmentComponent;
import com.example.administrator.huawei.di.module.FragmentModuel;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentComponent fragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    private void initFragmentComponent() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModuel(new FragmentModuel(this))
                .applicationComponent(((StoreApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
    }

    protected abstract T initInjector();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
