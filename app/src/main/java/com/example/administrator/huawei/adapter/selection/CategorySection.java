package com.example.administrator.huawei.adapter.selection;

import android.content.Context;
import android.service.quicksettings.Tile;
import android.view.View;

import com.example.administrator.huawei.R;
import com.example.administrator.huawei.bean.CategoryBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

public class CategorySection extends StatelessSection {

    private List<CategoryBean.CategoryDataBean> dataBeansList;
    private String title;
    private Context context;

    public CategorySection(Context context, String title, List<CategoryBean.CategoryDataBean> dataBeansList) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_subcat);
        this.dataBeansList = dataBeansList;
        this.context = context;
        this.title = title;
    }

    @Override
    public int getContentItemsTotal() {
        return dataBeansList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        CategoryBean.CategoryDataBean categoryDataBean = dataBeansList.get(position);
        holder.setImageUrl(R.id.appicon, categoryDataBean.getIconUrl());
        holder.setText(R.id.ItemTitle, categoryDataBean.getName());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle, title);
    }
}
