package com.example.administrator.huawei.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity  mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mActivity;
    }
}
