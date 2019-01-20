package com.example.administrator.huawei.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.di.component.ActivityComponent;
import com.example.administrator.huawei.di.component.DaggerActivityComponent;
import com.example.administrator.huawei.di.module.ActivityModule;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent mActivityComponent ;
    protected T mPresenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector() ;
        mPresenter.attachView(this);
        initData();
    }

    private void initActivityComponent(){
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((StoreApplication)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 初始化数据
     */
    protected void initData(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract T  initInjector();
}
