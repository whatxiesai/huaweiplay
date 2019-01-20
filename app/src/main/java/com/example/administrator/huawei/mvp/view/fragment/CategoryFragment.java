package com.example.administrator.huawei.mvp.view.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.selection.CategorySection;
import com.example.administrator.huawei.adapter.top.CategoryTopWrapper;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.bean.CategoryBean;
import com.example.administrator.huawei.mvp.presenter.impl.CategoryFragmentPresenterImpl;
import com.example.administrator.huawei.view.widget.ViewUpSearch;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.CategoryFragmentView;
import com.example.administrator.huawei.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends BaseMvpFragment<CategoryFragmentPresenterImpl> implements CategoryFragmentView {

    private static final String TAG = "CategoryFragment";

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.search)
    ViewUpSearch search;

    private boolean isExpand;

    @Inject
    public CategoryFragmentPresenterImpl mCategoryFragmentPresenter;

    private CategoryBean mCategoryBean;

    @Override
    protected void load() {
        // 网络请求操作
        mCategoryFragmentPresenter.getCategoryData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_category);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new CategorySection(getContext(), mCategoryBean.getTitle(),
                mCategoryBean.getCategoryDataBeanList()));
        CategoryTopWrapper categoryTopWrapper = new CategoryTopWrapper(getContext(),adapter);
        categoryTopWrapper.addDataAll(mCategoryBean.getCategoryTopBeanList());
        rv.setAdapter(categoryTopWrapper);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
                Log.d(TAG, "first visiable: " + firstVisibleItemPosition);
                if (firstVisibleItemPosition == 0 && dy > 0 &&  isExpand) {
                   search.updateShow(!isExpand);
                   isExpand = false;
                } else if (firstVisibleItemPosition == 0 && dy<0 && !isExpand) {
                    // dy<0，下拉（需要搜索框展开）
                    search.updateShow(!isExpand);
                    isExpand = true;
                }
            }
        });
        return view;
    }

    @Override
    protected CategoryFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mCategoryFragmentPresenter;
    }

    @Override
    public void onCategoryDataSuccess(CategoryBean categoryBean) {
        this.mCategoryBean = categoryBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onCategoryDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }
}
