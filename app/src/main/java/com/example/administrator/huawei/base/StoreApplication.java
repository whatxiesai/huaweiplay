package com.example.administrator.huawei.base;


import android.os.Handler;

import com.example.administrator.huawei.BuildConfig;
import com.example.administrator.huawei.di.component.ApplicationComponent;
import com.example.administrator.huawei.di.component.DaggerActivityComponent;
import com.example.administrator.huawei.di.component.DaggerApplicationComponent;
import com.example.administrator.huawei.di.module.ApplicationModel;
import com.zhxu.library.RxRetrofitApp;
import com.zhxu.recyclerview.App;

public class StoreApplication extends App {
    private static int mMainThreadId;
    private static Handler mHandler;
    private ApplicationComponent applicationComponent;

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModel(new ApplicationModel(this)).build();
    }

    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mHandler=new Handler();
        initApplicationComponent();
        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getHandler() {
        return mHandler;
    }
}
