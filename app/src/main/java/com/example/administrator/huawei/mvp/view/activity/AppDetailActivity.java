package com.example.administrator.huawei.mvp.view.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.huawei.R;
import com.example.administrator.huawei.base.BaseMvpActivity;
import com.example.administrator.huawei.bean.AppDetailBean;
import com.example.administrator.huawei.mvp.presenter.impl.AppDetailPresenterImpl;
import com.example.administrator.huawei.mvp.view.view.AppDetailActivityView;
import com.example.administrator.huawei.util.UIUtils;
import com.example.administrator.huawei.view.widget.DetailShareButton;
import com.example.administrator.huawei.view.widget.DownloadProgressButton;
import com.example.administrator.huawei.view.widget.SubTabNavigator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AppDetailActivity extends BaseMvpActivity<AppDetailPresenterImpl> implements
        AppDetailActivityView {

    private static final String TAG = "AppDetailActivity";

    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.iv_search)
    ImageView iv_search ;

    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView detail_app_icon ;
    @BindView(R.id.detail_head_app_name_textview)
    TextView detail_app_name ;
    @BindView(R.id.detail_head_download_count_textview)
    TextView detail_app_download_count ;
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar detail_app_stars ;
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout detail_head_label_layout ;
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout detail_head_label_icon_layout ;
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView detail_head_lable_folding ;
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout detail_head_safe_icon_container ;
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout detail_head_safe_icon_layout ;
    @BindView(R.id.subTab)
    SubTabNavigator subTabNavigator ;
    @BindView(R.id.vp)
    ViewPager mViewPager ;
    @BindView(R.id.appdetail_head)
    LinearLayout appdetail_head ;
    @BindView(R.id.detail_download_button)
    DownloadProgressButton detail_download_button ;
    @BindView(R.id.detail_download_share_button)
    DetailShareButton detail_download_share_button ;
    @BindView(R.id.detail_download_comment_button_linearlayout)
    LinearLayout detail_download_comment_button_linearlayout ;

    private AppDetailBean appDetailBean ;

    private boolean expand = false ;

    @Inject
    public AppDetailPresenterImpl appDetailPresenter;
    private String packageName;

    @Override
    protected AppDetailPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return appDetailPresenter;
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_app_detail2);
    }

    @Override
    protected void initData() {
        packageName = getIntent().getStringExtra("packageName");
        appDetailPresenter.getAppDetaiData(this, packageName);
    }

    @Override
    protected void initView() {
        setStatus();
        iv_search.setVisibility(View.VISIBLE);
        title_text.setText(getResources().getString(R.string.title_activity_app_detail));
    }

    @Override
    public void onAppDetailDataSuccess(AppDetailBean appDetailBean) {
        Log.d(TAG, "onAppDetailDataSuccess: " + appDetailBean.getName());
        this.appDetailBean = appDetailBean;
        setDetailHead();
    }

    private void setDetailHead() {
        Glide.with(UIUtils.getContext()).load(appDetailBean.getIcoUrl()).into(detail_app_icon) ;
        detail_app_name.setText(appDetailBean.getName());
        detail_app_download_count.setText(appDetailBean.getIntro());
        detail_app_stars.setRating(Float.parseFloat(appDetailBean.getStars())) ;

        setLable();
        setSafeLable();
    }

    private void setSafeLable() {
        for(AppDetailBean.SafeLabel safeLabelsBean : appDetailBean.getSafeLabelList()){
            View safeLabelView = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
            TextView safe_checker_label = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
            ImageView detail_head_app_icon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);
            TextView detail_safe_desc_textview = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);

            safe_checker.setText(safeLabelsBean.getDetectorName());
            safe_checker_label.setText(safeLabelsBean.getDetectorDesc());
            Glide.with(UIUtils.getContext()).load(safeLabelsBean.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabelsBean.getName());

            detail_head_safe_icon_container.addView(safeLabelView);
        }

        detail_head_label_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expand){
                    expand = false ;
                    detail_head_safe_icon_layout.setVisibility(View.GONE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_down);
                }else {
                    expand = true ;
                    detail_head_safe_icon_layout.setVisibility(View.VISIBLE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });
    }

    private void setLable() {
        List<AppDetailBean.LabelName> labelNameList = appDetailBean.getLabelNameList();
        for (AppDetailBean.LabelName labelNamesBean : labelNameList) {
            View labelView = UIUtils.inflate(R.layout.appdetail_item_head_label_item);
            TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelNamesBean.getName());
            if(labelNamesBean.getType() == 1){
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            }
            detail_head_label_icon_layout.addView(labelView);
        }
    }

    @Override
    public void onAppDetailDataError(String msg) {

    }

    @Override
    public void showToast(String msg) {

    }
}
