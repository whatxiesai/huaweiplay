package com.example.administrator.huawei.api;

public interface IGetDataDelegate<T> {
    void getDataSuccess(T t);
    void getDataError(String msg);
}
