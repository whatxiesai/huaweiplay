package com.example.administrator.huawei.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.huawei.di.module.ActivityModule;
import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerActivity;
import com.example.administrator.huawei.view.HomeActivity;

import dagger.Component;

@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicaitionContext();

    Activity getActivity();

    void inject(HomeActivity homeActivity);
}
