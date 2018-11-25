package com.example.administrator.huawei.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.huawei.di.module.FragmentModuel;
import com.example.administrator.huawei.di.scope.ContextLife;
import com.example.administrator.huawei.di.scope.PerFragment;
import com.example.administrator.huawei.mvp.view.fragment.CategoryFragment;
import com.example.administrator.huawei.mvp.view.fragment.RecommendFragment;
import com.example.administrator.huawei.mvp.view.fragment.TopFragment;

import dagger.Component;

@PerFragment
@Component(modules = FragmentModuel.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(RecommendFragment recommendFragment);
    void inject(CategoryFragment categoryFragment);
    void inject(TopFragment topFragment);
}
