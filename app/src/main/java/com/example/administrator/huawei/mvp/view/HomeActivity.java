package com.example.administrator.huawei.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.support.design.widget.TabLayout;

import com.example.administrator.huawei.BaseActivity;
import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.FragmentFactory;
import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.FixPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.main_viewpager)
    ViewPager mainViewPager;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;

    private FixPagerAdapter fixAdapter;
    private String[] titles = {"推荐", "分类", "排行", "管理", "我的"};
    private List<Fragment> fragments;

    private void initViewPagerFrament() {
        fixAdapter = new FixPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(FragmentFactory.createFragment(i));
        }

        fixAdapter.setTitles(titles);
        fixAdapter.setFragments(fragments);
        mainViewPager.setAdapter(fixAdapter);

        // ViewPager与Tablayout绑定
        tabLayout.setupWithViewPager(mainViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                    BaseFragment fragment = FragmentFactory.createFragment(position);
                    fragment.show();
            }
        });
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        final  int statusBarHeight = getStatusBarHeight();
        statusBar.post(new Runnable() {
            @Override
            public void run() {
                int tileHeight = statusBar.getHeight();
                android.widget.LinearLayout.LayoutParams params =
                        (android.widget.LinearLayout.LayoutParams) statusBar.getLayoutParams();
                params.height = statusBarHeight + tileHeight;
                statusBar.setLayoutParams(params);

            }
        });
        initViewPagerFrament();
    }
}
