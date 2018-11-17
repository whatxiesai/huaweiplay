package com.example.administrator.huawei.base;


import android.os.Handler;

public class StoreApplication extends App {
    private static int mMainThreadId;
    private static Handler mHandler;

    public StoreApplication() {
    }

    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }


    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getHandler() {
        return mHandler;
    }
}
