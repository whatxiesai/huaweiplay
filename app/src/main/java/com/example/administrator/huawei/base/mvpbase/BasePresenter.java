package com.example.administrator.huawei.base.mvpbase;

public interface BasePresenter<T extends  BaseView>  {
    void attachView(T baseView);
    void detachView();
}
