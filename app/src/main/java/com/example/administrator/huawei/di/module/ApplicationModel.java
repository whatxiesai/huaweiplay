package com.example.administrator.huawei.di.module;

import android.content.Context;

import com.example.administrator.huawei.base.StoreApplication;
import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModel {

    private StoreApplication storeApplication;

    public ApplicationModel(StoreApplication application) {
        this.storeApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideContext() {
        return storeApplication.getApplicationContext();
    }
}
