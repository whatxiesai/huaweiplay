package com.example.administrator.huawei.mvp.interactor;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.api.CategoryApi;
import com.example.administrator.huawei.api.IGetDataDelegate;
import com.example.administrator.huawei.bean.CategoryBean;
import com.example.administrator.huawei.util.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryFragmentInteractor {

    private IGetDataDelegate<CategoryBean> mDelegate ;

    @Inject
    public CategoryFragmentInteractor(){

    }

    public void loadCategoryData(BaseActivity activity, IGetDataDelegate<CategoryBean> delegate){
        this.mDelegate = delegate ;

        CategoryApi categoryApi = new CategoryApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategoryBean>() {

        @Override
        public void onNext(CategoryBean categoryBean) {
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParseUtils.parseCategoryBean(string);
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
