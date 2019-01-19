package com.example.administrator.huawei.view;

import com.example.administrator.huawei.base.mvpbase.BaseView;
import com.example.administrator.huawei.bean.CategoryBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryFragmentView extends BaseView {
    void onCategoryDataSuccess(CategoryBean categoryBean);
    void onCategoryDataError(String msg) ;
}
