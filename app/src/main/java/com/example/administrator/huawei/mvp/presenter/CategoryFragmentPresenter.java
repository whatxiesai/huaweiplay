package com.example.administrator.huawei.mvp.presenter;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.base.mvpbase.BasePresenter;
import com.example.administrator.huawei.view.CategoryFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryFragmentPresenter extends BasePresenter<CategoryFragmentView> {
    void getCategoryData(BaseActivity activity);
}
