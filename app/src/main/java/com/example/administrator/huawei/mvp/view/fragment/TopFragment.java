package com.example.administrator.huawei.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.selection.TopContactsSection;
import com.example.administrator.huawei.adapter.top.TopTopWrapper;
import com.example.administrator.huawei.base.BaseMvpFragment;
import com.example.administrator.huawei.bean.AppBean;
import com.example.administrator.huawei.bean.TopBean;
import com.example.administrator.huawei.mvp.presenter.impl.TopPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.TopFragmentView;
import com.example.administrator.huawei.view.widget.ViewUpSearch;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFragment extends BaseMvpFragment<TopPresenterImpl> implements TopFragmentView {

    private static final String TAG = "TopFragment";

    private TopBean topBean;

    @BindView(R.id.rv)
    RecyclerView rv ;
    @BindView(R.id.search)
    ViewUpSearch search ;

    private boolean isExpand;

    @Inject
    public TopPresenterImpl topPresenter;

    @Override
    protected void load() {
        // 网络请求操作
     topPresenter.getTopData(mActivity);
    }

    @Override
    protected View createSuccessView() {
       View view = UIUtils.inflate(R.layout.fragment_top);
        ButterKnife.bind(this, view);

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());
        Map<String, List<AppBean>> appBeanMap = topBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();
        for(String name : strings){
            List<AppBean> appBeanList = appBeanMap.get(name);
            TopContactsSection section = new TopContactsSection(getContext(),name,appBeanList);

            sectionAdapter.addSection(section);
        }
        TopTopWrapper topTopWrapper = new TopTopWrapper(getContext(),sectionAdapter);
        topTopWrapper.addDataAll(topBean.getTopTopBeanList());

        rv.setAdapter(topTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int firstVisiblePosition = ((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();
                if(firstVisiblePosition == 0 && isExpand && dy > 0){
                    search.updateShow(!isExpand);
                    isExpand = false ;
                }else if(firstVisiblePosition == 0 && !isExpand && dy < 0){
                    search.updateShow(!isExpand);
                    isExpand = true ;
                }
            }
        });
        return view;
    }

    @Override
    protected TopPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return topPresenter;
    }

    @Override
    public void onTopDataSuccess(TopBean topBean) {
        this.topBean = topBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onTopDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }
}
