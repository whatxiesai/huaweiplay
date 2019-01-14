package com.example.administrator.huawei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.bean.AppBean;
import com.example.administrator.huawei.bean.RecommendBean;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ItemViewDelegate;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean> {

    private Context mContext;

    public RecommendAdapter(Context context, List<RecommendBean.RecommendAppBean> datas) {
        super(context, datas);
        this.mContext = context;
        addItemViewDelegate(new AppDelegate());
        addItemViewDelegate(new AdDelegate());
    }

    /**
     *
     */
    public class AppDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setText(R.id.tv_item_title, recommendAppBean.getTitle());
            RecyclerView rv = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
             rv.setLayoutManager(linearLayoutManager);
             AppItemAdapter adapter = new AppItemAdapter(mContext);
             adapter.addDataAll(recommendAppBean.getAppList());
             rv.setAdapter(adapter);
        }
    }


    public class AdDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setImageUrl(R.id.iv_ad1, recommendAppBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2, recommendAppBean.getIconList().get(1));
        }
    }

    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon,appBean.getIcon());
            holder.setText(R.id.tv_app_name,appBean.getName()) ;
            holder.setText(R.id.tv_app_size,appBean.getSizeDesc());
        }
    }
}
