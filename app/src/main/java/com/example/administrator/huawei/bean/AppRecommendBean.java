package com.example.administrator.huawei.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendBean {

    private List<com.example.administrator.huawei.bean.AppBean> popularAppBeanList ;
    private List<com.example.administrator.huawei.bean.AppBean> tasteAppBeanList ;
    private List<com.example.administrator.huawei.bean.AppBean> hotAppBeanList ;

    public AppRecommendBean(List<com.example.administrator.huawei.bean.AppBean> popularAppBeanList, List<com.example.administrator.huawei.bean.AppBean> tasteAppBeanList, List<com.example.administrator.huawei.bean.AppBean> hotAppBeanList) {
        this.popularAppBeanList = popularAppBeanList;
        this.tasteAppBeanList = tasteAppBeanList;
        this.hotAppBeanList = hotAppBeanList;
    }

    public List<com.example.administrator.huawei.bean.AppBean> getPopularAppBeanList() {
        return popularAppBeanList;
    }

    public List<com.example.administrator.huawei.bean.AppBean> getTasteAppBeanList() {
        return tasteAppBeanList;
    }

    public List<AppBean> getHotAppBeanList() {
        return hotAppBeanList;
    }
}
