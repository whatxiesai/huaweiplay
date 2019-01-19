package com.example.administrator.huawei.mvp.view.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.huawei.BaseFragment;
import com.example.administrator.huawei.R;
import com.example.administrator.huawei.adapter.MySubAdapter;
import com.example.administrator.huawei.bean.MyGvBean;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.LoadingPager;
import com.example.administrator.huawei.view.widget.MyEnterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends BaseFragment {

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid ;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout ;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout ;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout ;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer ;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout ;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout ;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout ;

    private List<MyGvBean> gvBeanList = new ArrayList<>() ;
    private String[] titles = {"奖品","礼包",""};

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_my);
        ButterKnife.bind(this,view);

        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize),R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift),R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments),R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message),R.drawable.icon_market_message));

        gv_title_grid.setNumColumns(gvBeanList.size());
        gv_title_grid.setAdapter(new MySubAdapter(getContext(), gvBeanList));
        book_game_layout.setTitle(UIUtils.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtils.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));


        return view;

    }
}
