package com.example.administrator.huawei.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppMoreRecommendBean {

    private List<com.example.administrator.huawei.bean.AppBean> moreAppBean ;

    public AppMoreRecommendBean(List<com.example.administrator.huawei.bean.AppBean> moreAppBean) {
        this.moreAppBean = moreAppBean;
    }

    public List<AppBean> getMoreAppBean() {
        return moreAppBean;
    }
}
