package com.example.administrator.huawei.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerActivity;
import com.example.administrator.huawei.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModuel {

    private Fragment mFragment;

    public FragmentModuel(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment providedFragment() {
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity provideFragmentContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFramentConotext() {
        return mFragment.getContext();
    }
}
