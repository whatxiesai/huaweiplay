package com.example.administrator.huawei.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.R;
import com.example.administrator.huawei.util.UIUtils;

public abstract class LoadingPaper extends FrameLayout {

    /**
     * 默认状态
     */
    public final static int STATE_UNKNOWN = 0;

    /**
     * 加载中的状态
     */
    public final static int STATE_LOADING = 1;

    /**
     * 加载失败
     */
    public final static int STATE_ERROR = 2;

    /**
     * 加载成功
     */
    public final static int STATE_SUCCESS = 3;

    /**
     * 加载空状态
     */
    public final static int STATE_EMPTY = 4;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    private int state = STATE_UNKNOWN;

    public LoadingPaper(@NonNull Context context) {
        this(context, (AttributeSet)null);
    }

    public LoadingPaper(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPaper(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.state = 0;
        this.init();
    }

    /**
     * 将布局添加到帧布局
     */
    private void init() {
        if (loadingView == null) {
            loadingView = createLoadingView();
            this.addView(loadingView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }

        if (errorView == null) {
            errorView = createErrorView();
            this.addView(errorView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }

        if (emptyView == null) {
            emptyView = createEmptyView();
            this.addView(emptyView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }

        showPaper();
    }

    /**
     * 请求网络，修改状态
     */
    public void show() {
        if (state == STATE_UNKNOWN || state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
            load();
        }

        showPaper();
    }

    /**
     * 根据不同的状态创建不同的布局
     */
    private void showPaper() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKNOWN || state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }

        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }

        if (state == STATE_SUCCESS && successView == null) {
            successView = createSuccessView();
            this.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }
    }
    /**
     * 设置状态
     *
     * @param loadResult
     */
    public void setState(LoadResult loadResult) {
        state = loadResult.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPaper();
            }
        });
    }

    /**
     * 请求结果枚举
     */
    public enum LoadResult {
        error(STATE_ERROR), success(STATE_SUCCESS), empty(STATE_EMPTY);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    protected abstract View createSuccessView();

    /**
     * 请求网络修改状态
     */
    protected abstract void load();

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    private View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_error_page);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }
}
