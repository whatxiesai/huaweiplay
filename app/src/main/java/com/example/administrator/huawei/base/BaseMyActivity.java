package com.example.administrator.huawei.base;

import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.di.component.ActivityComponent;
import com.example.administrator.huawei.di.component.DaggerActivityComponent;
import com.example.administrator.huawei.di.module.ActivityModule;

public abstract class BaseMyActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    // 通过dagger2注入的是presenter
    protected ActivityComponent activityComponent;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    public void initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .applicationComponent(((StoreApplication)getApplication()).getApplicationComponent())
                .build();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 完成注入并返回注入的present对象
     * @return
     */
    protected abstract T initInjector();
}
