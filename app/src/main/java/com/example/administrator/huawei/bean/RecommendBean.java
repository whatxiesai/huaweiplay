package com.example.administrator.huawei.bean;

import java.util.List;

public class RecommendBean {
    private List<String> bannerList;
    private List<RecommendBean.RecommendAppBean> recommendAppBeanList;

    public RecommendBean(List<String> bannerList, List<RecommendBean.RecommendAppBean> recommendAppBeanList) {
        this.bannerList = bannerList;
        this.recommendAppBeanList = recommendAppBeanList;
    }

    public List<String> getBannerList() {
        return this.bannerList;
    }

    public List<RecommendBean.RecommendAppBean> getRecommendAppBeanList() {
        return this.recommendAppBeanList;
    }

    public static class RecommendAppBean {
        private String title;
        private List<String> iconList;
        private List<AppBean> appList;
        private int type = 0;

        public RecommendAppBean(String title, List<String> iconList, List<AppBean> appList, int type) {
            this.title = title;
            this.iconList = iconList;
            this.appList = appList;
            this.type = type;
        }

        public String getTitle() {
            return this.title;
        }

        public List<String> getIconList() {
            return this.iconList;
        }

        public List<AppBean> getAppList() {
            return this.appList;
        }

        public int getType() {
            return this.type;
        }
    }
}
