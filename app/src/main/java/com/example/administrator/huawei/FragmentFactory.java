package com.example.administrator.huawei;

import com.example.administrator.huawei.mvp.view.fragment.AppManagerFragment;
import com.example.administrator.huawei.mvp.view.fragment.CategoryFragment;
import com.example.administrator.huawei.mvp.view.fragment.MyFragment;
import com.example.administrator.huawei.mvp.view.fragment.RecommendFragment;
import com.example.administrator.huawei.mvp.view.fragment.TopFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
    public static final int TAB_RECOMMEND = 0;
    public static final int TAB_CATEGORY = 1;
    public static final int TAB_TOP = 2;
    public static final int TAB_APPMANAGER = 3;
    public static final int TAB_MY = 4;
    private static Map<Integer, BaseFragment> mFragments = new HashMap();

    public FragmentFactory() {
    }

    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = (BaseFragment) mFragments.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new RecommendFragment();
                    break;
                case 1:
                    fragment = new CategoryFragment();
                    break;
                case 2:
                    fragment = new TopFragment();
                    break;
                case 3:
                    fragment = new AppManagerFragment();
                    break;
                case 4:
                    fragment = new MyFragment();
            }

            mFragments.put(index, fragment);
        }

        mFragments.put(index, fragment);
        return(BaseFragment)fragment;
    }
}
