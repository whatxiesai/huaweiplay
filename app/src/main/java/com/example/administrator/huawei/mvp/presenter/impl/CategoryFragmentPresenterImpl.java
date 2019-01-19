package com.example.administrator.huawei.mvp.presenter.impl;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.base.mvpbase.BasePresenterImpl;
import com.example.administrator.huawei.bean.CategoryBean;
import com.example.administrator.huawei.mvp.interactor.CategoryFragmentInteractor;
import com.example.administrator.huawei.mvp.presenter.CategoryFragmentPresenter;
import com.example.administrator.huawei.view.CategoryFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryFragmentPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresenter {

    @Inject
    CategoryFragmentInteractor categoryFragmentInteractor ;

    @Inject
    public CategoryFragmentPresenterImpl(){

    }

    @Override
    public void getCategoryData(BaseActivity activity) {
        categoryFragmentInteractor.loadCategoryData(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccess(categoryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryDataError(errmsg);
            }
        });
    }
}
