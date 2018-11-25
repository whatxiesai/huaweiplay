package com.example.administrator.huawei.di.component;

import android.content.Context;

import com.example.administrator.huawei.di.module.ApplicationModel;
import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerApp;

import dagger.Component;

@PerApp
@Component(modules = ApplicationModel.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplicationContext();

}
