package com.example.administrator.huawei.mvp.view.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.view.LoadingPaper;

public class MyFragment extends BaseFragment {
    @Override
    protected void load() {
        // 网络请求操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                setState(LoadingPaper.LoadResult.empty);
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
