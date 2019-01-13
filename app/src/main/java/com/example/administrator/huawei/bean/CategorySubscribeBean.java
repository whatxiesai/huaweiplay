package com.example.administrator.huawei.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribeBean {
    List<com.example.administrator.huawei.bean.AppBean> appBeanList ;

    public CategorySubscribeBean(List<com.example.administrator.huawei.bean.AppBean> appBeanList) {
        this.appBeanList = appBeanList;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }
}
