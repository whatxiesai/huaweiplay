package com.example.administrator.huawei;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity {

    private static final String TAG = "BaseActivity";
    private ViewGroup title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 初始化布局
        initLayout();
        ButterKnife.bind(this);
        setStatus();
        // 初始化view
        initView();
    }

    // setContentView(R.layout.xxx)
    protected abstract void initLayout();

    // 初始化view
    protected abstract void initView();

    private void setStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置导航栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            title_bar = findViewById(R.id.bar_layout);
            if (title_bar != null) {
                title_bar.setBackgroundResource(R.color.black_alpha_5);
                final int statusBarHeight = getStatusBarHeight();
                title_bar.post(new Runnable() {
                    @Override
                    public void run() {
                        int height = title_bar.getHeight();
                        ViewGroup.LayoutParams layoutParams = title_bar.getLayoutParams();
                        layoutParams.height = statusBarHeight + height;
                        title_bar.setLayoutParams(layoutParams);
                    }
                });
            }
        }
    }

    protected int getStatusBarHeight() {
        try {
            // 通过反射获取到类
            Class<?> tClass = Class.forName("com.android.internal.R$dimen");
            // 创建对象
            Object o = tClass.newInstance();
            // 拿属性
            Field status_bar_height = tClass.getField("status_bar_height");
            // 获取属性的值
            Object o1 = status_bar_height.get(o);
            int height = Integer.parseInt(o1.toString());
            return getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
