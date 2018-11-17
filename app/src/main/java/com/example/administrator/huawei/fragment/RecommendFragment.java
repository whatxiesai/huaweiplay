package com.example.administrator.huawei.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.view.LoadingPaper;

public class RecommendFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected void load() {
        // 网络请求操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                setState(LoadingPaper.LoadResult.success);
            }
        }).start();
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("加载成功");
        return tv;
    }
}
