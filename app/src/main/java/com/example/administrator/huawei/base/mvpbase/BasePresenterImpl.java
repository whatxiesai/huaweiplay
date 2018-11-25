package com.example.administrator.huawei.base.mvpbase;


public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected  T mPresenterView;

    @Override
    public void attachView(T baseView) {
        mPresenterView = baseView;
    }

    @Override
    public void detachView() {
        mPresenterView = null;
    }
}
